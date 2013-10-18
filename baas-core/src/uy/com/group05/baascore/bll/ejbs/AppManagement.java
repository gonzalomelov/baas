package uy.com.group05.baascore.bll.ejbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.InvalidNameException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.RoleAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;
import uy.com.group05.baascore.dal.dao.PermissionDao;
import uy.com.group05.baascore.dal.dao.RoleDao;
import uy.com.group05.baascore.dal.dao.UserDao;

@Stateless
public class AppManagement implements AppManagementLocal{

	@Inject
	ApplicationDao appDao;
	@Inject
	UserDao userDao;
	@Inject
	RoleDao roleDao;
	@Inject
	EntityDao entityDao;
	@Inject
	NoSqlDbDao noSqlDbDao;
	@Inject
	PermissionDao permissionDao;
	@Inject
	ClientDao clientDao;
	
	
	public List<Application> listApplications(long idUser) throws UserNotRegisteredException{
		User user = userDao.read(idUser);
		if(user == null)//no existe usuario
			throw new UserNotRegisteredException("No existe el usuario con id:"+idUser);
		//Obtenego la lista de apps del usuario
		
		List<Application> apps = appDao.readFromUser(idUser);
		
		return apps;
	}
	
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException, InvalidNameException {
		//Obtengo el usuario
		User user = userDao.read(idUser);
		//Hago los controles
		if(user == null) //no existe usuario
			throw new UserNotRegisteredException("No existe el usuario con id:"+idUser);
		if (nombreApp.equals(""))//No existe la app
			throw new InvalidNameException("Debe ingresar un nombre para la aplicacion");
		if (appDao.readByName(nombreApp) != null)//No existe la app
			throw new NombreAppAlreadyRegisteredException("Ya existe una aplicacion con ese nombre");

		//Creo la App
		Application app = new Application(nombreApp, user);
		
		//Elimino duplicados de roles y entidades
		/*Set<String> rolesSet= new LinkedHashSet<String>();
		rolesSet.addAll(rolesStr);
		rolesStr.clear();
		rolesStr.addAll(rolesSet);
		Set<String> entidadesSet= new LinkedHashSet<String>();
		entidadesSet.addAll(entidadesStr);
		entidadesStr.clear();
		entidadesStr.addAll(entidadesSet);*/
		
		List<Role> roles = new ArrayList<Role>();
		List<Entity> entidades = new ArrayList<Entity>();
		//Creo los roles (si hay)
		if (rolesStr != null){
			Iterator<String> iter = rolesStr.iterator();
			while (iter.hasNext()){
					Role r = new Role(iter.next(), app);
					if (!roles.contains(r) && !r.getName().equals("")){
						roles.add(r);
						roleDao.create(r);
					}
			}
		}
		//Creo las entidades (si hay)
		if(entidadesStr != null){
			Iterator<String> iter = entidadesStr.iterator();
			while (iter.hasNext()){
				Entity e = new Entity(iter.next(), app);
				if(!entidades.contains(e) && !e.getName().equals("")){
					entidades.add(e);
					entityDao.create(e);
				}
			}
		}
		
		//Genero appId
		app.setApiClientId(UUID.randomUUID());
		
		//Seteo Roles y Entidades a App
		app.setRoles(roles);
		app.setEntities(entidades);
		
		appDao.create(app);
		
		// Creo base MongoDB para la nueva APP
		noSqlDbDao.createNoSqlDb(nombreApp);
		if(!app.getEntities().isEmpty())
			// Creo las coleciones dentro de la base MongoDB para cada entidad
			for(Entity e : app.getEntities()) {
				noSqlDbDao.createEntityCollection(nombreApp, e.getName());
			}
		
		return app.getId();
	}

	public boolean existsApplication(String nombre){
		return (appDao.readByName(nombre) != null);
	}
	
	public boolean existsEntityApplication(long idApp, String nomEntity) throws AppNotRegisteredException{
		Application app = appDao.read(idApp);
		if ( app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		return app.getEntities().contains(new Entity(nomEntity, app));
	}
	
	public boolean existsRoleApplication(long idApp, String nomRole) throws AppNotRegisteredException{
		Application app = appDao.read(idApp);
		if ( app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		return app.getRoles().contains(new Role(nomRole, app));
	}
	
	public long editRoleApplication(long idApp, long idUser, String nomRole)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	RoleAlreadyRegisteredException, InvalidNameException{
		
		Application app = appDao.read(idApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		if (!app.getUsers().contains(userDao.read(idUser))) {
			throw new UserCantAccessAppException ("El usuario no es administrador de la aplicacion");
		}
		//Compruebo
		//Obtengo Roles existentes
		List<Role> roles = app.getRoles();
		Role r = new Role(nomRole, app);
		if (roles.contains(r))
			throw new RoleAlreadyRegisteredException("Ya existe un rol con ese nombre");
		if (nomRole.equals(""))//No existe la app
			throw new InvalidNameException("Debe ingresar un nombre para el Rol");
		
		roles.add(r);
		Role retorno = roleDao.create(r);

		//Seteo nuevos roles
		app.setRoles(roles);
		
		return retorno.getId();
	}
	
	public long editEntityApplication(long idApp, long idUser, String nomEntity)
			throws
			 	AppNotRegisteredException,
			 	EntityAlreadyRegisteredException,
			 	UserCantAccessAppException, 
			 	EntityCollectionAlreadyExistsException, InvalidNameException {
		
		Application app = appDao.read(idApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		if (!app.getUsers().contains(userDao.read(idUser))) {
			throw new UserCantAccessAppException ("El usuario no es administrador de la aplicacion");
		}
		if (nomEntity.equals(""))//No existe la app
			throw new InvalidNameException("Debe ingresar un nombre para la entidad");
		//Compruebo
		//Obtengo Entidades existentes
		List<Entity> entities = app.getEntities();
		Entity e = new Entity(nomEntity, app);
		
		if (entities.contains(e)){
			throw new EntityAlreadyRegisteredException("Ya existe una entidad con ese nombre");
		}
		//Creo la coleccion para cada entidad dentro de la base MongoDB de la APP
		noSqlDbDao.createEntityCollection(app.getName(), e.getName()); //Primero porque si falla, no se va a crear la entidad.
		entities.add(e);
		Entity retorno = entityDao.create(e);
	
		//Seteo nuevos entidades
		app.setEntities(entities);
		
		return retorno.getId();
	}
	
	public long editApplication(String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
			 	AppNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException {
	
		Application app = appDao.readByName(nombreApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");

		//Obtengo Roles y Entidades existentes
		List<Role> roles = app.getRoles();
		List<Entity> entidades = app.getEntities();
		//Creo los roles
		Iterator<String> iter = rolesStr.iterator();
		while (iter.hasNext()){
				Role r = new Role(iter.next(), app);
				if (!app.getRoles().contains(r)){
					roles.add(r);
					roleDao.create(r);
				}
		}
		iter = entidadesStr.iterator();
		while (iter.hasNext()){
			Entity e = new Entity(iter.next(), app);
			if (!app.getEntities().contains(e)){
				//Creo la coleccion para cada entidad dentro de la base MongoDB de la APP
				noSqlDbDao.createEntityCollection(nombreApp, e.getName()); //Primero porque si falla, no se va a crear la entidad.
				entidades.add(e);
				entityDao.create(e);
			}
		}
		
		//Seteo Roles y Entidades a App
		app.setRoles(roles);
		app.setEntities(entidades);
		
		return app.getId();
	}
	
	public boolean assignUserToApplication(String nombreApp, long idUser) throws AppNotRegisteredException, UserNotRegisteredException {
		Application app = appDao.readByName(nombreApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		
		User user = userDao.read(idUser);
		if (user == null)
			throw new UserNotRegisteredException("No existe el usuario con id:" + idUser);
		
		// Si el usuario no tenía asignada la app, se la asigno
		// Si ya tenía asignada la app, devuelvo false
		List<Application> userApps = user.getApplications();
		if (!userApps.contains(app)) {
			// Agrego la app a la lista de apps del usuario
			userApps.add(app);
			user.setApplications(userApps);
			userDao.update(user);
			
			// Agrego el usuario a la lista de usuarios de la app
			List<User> appUsers = app.getUsers();
			appUsers.add(user);
			app.setUsers(appUsers);
			appDao.update(app);
		}
		else
			return false;
		
		return true;
	}

	public boolean unassignUserFromApplication(String nombreApp, long idUser)
			throws AppNotRegisteredException, UserNotRegisteredException {
		
		Application app = appDao.readByName(nombreApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		
		User user = userDao.read(idUser);
		if (user == null)
			throw new UserNotRegisteredException("No existe el usuario con id:" + idUser);
		
		// Si el usuario ya tenía asignada la app, se la desasigno
		// Si no tenía asignada la app, devuelvo false
		List<Application> userApps = user.getApplications();
		if (userApps.contains(app)) {
			// Elimino la app e la lista de apps del usuario
			userApps.remove(app);
			user.setApplications(userApps);
			userDao.update(user);
			
			// Elimino el usuario de la lista de usuarios de la app
			List<User> appUsers = app.getUsers();
			appUsers.remove(user);
			app.setUsers(appUsers);
			appDao.update(app);
		}
		else
			return false;
		
		return true;
	}

	@Override
	public boolean existsPushChannelApplication(String nombreApp,
			String nombreCanal) throws AppNotRegisteredException {
		
		Application app = appDao.readByName(nombreApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación de nombre " + nombreApp);
		
		return (app.getPushChannel(nombreCanal) != null);
	}

	@Override
	public long addPushChannelToApplication(String nombreApp,
			String nombreCanal)
					throws
						AppNotRegisteredException,
						PushChanAlreadyRegisteredException {
		
		Application app = appDao.readByName(nombreApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación de nombre " + nombreApp);
		
		if (app.getPushChannel(nombreCanal) == null) {
			PushChannel pc = new PushChannel(nombreCanal,app);
			app.addPushChannel(pc);
			appDao.update(app);
			return pc.getId();
		}
		else
			throw new PushChanAlreadyRegisteredException("Ya existe el canal push con nombre " + nombreCanal + " para la aplicación de nombre " + nombreApp);
		
	}

	@Override
	public long removePushChannelFromApplication(String nombreApp,
			String nombreCanal) throws AppNotRegisteredException,
			PushChanNotRegisteredException {
		
		Application app = appDao.readByName(nombreApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación de nombre " + nombreApp);
		
		if (app.getPushChannel(nombreCanal) != null) {
			PushChannel pc = app.getPushChannel(nombreCanal);
			app.removePushChannel(pc);
			appDao.update(app);
			return pc.getId();
		}
		else
			throw new PushChanNotRegisteredException("No existe el canal push con nombre " + nombreCanal + " para la aplicación de nombre " + nombreApp);
	}
	
	public List<Role> getRolesApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.read(idApp);
		
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación con id= "+ idApp);
		
		return roleDao.readAll(idApp);
		
	}
	
	public List<Entity> getEntitiesApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación con id= "+ idApp);
		
		return app.getEntities();
		
	}
	
	public List<Client> getClientsApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación con id= "+ idApp);
		
		return app.getClients();
		
	}
	
	public List<PushChannel> getPushChannelsApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación con id= "+ idApp);
		
		return app.getPushChannels();
		
	}
	
	@Override
	public List<Permission> getPermissionsForEntity(long appId, long entityId)
			throws AppNotRegisteredException, EntityCollectionNotRegisteredException {
		
		Application app = appDao.read(appId);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación con id " + appId);
		
		Entity entity = entityDao.read(entityId);
		
		if (entity == null) {
			throw new EntityCollectionNotRegisteredException("No existe la entidad");
		}
		
		return permissionDao.readAllFromEntity(appId, entityId);
	}
	
	public Application getApplication(long appId) throws AppNotRegisteredException {
		Application app = appDao.readById(appId);
		
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicación con id " + appId);
		
		return app;
	}
}
