package uy.com.group05.baascore.bll.ejbs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.relation.RoleNotFoundException;

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
import uy.com.group05.baascore.dal.dao.EstadisticasDao;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;
import uy.com.group05.baascore.dal.dao.PermissionDao;
import uy.com.group05.baascore.dal.dao.PushChannelDao;
import uy.com.group05.baascore.dal.dao.RoleDao;
import uy.com.group05.baascore.dal.dao.UserDao;
import uy.com.group05.baascore.sl.entitiesws.ChartDto;
import uy.com.group05.baascore.sl.entitiesws.TipoChart;

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
	@Inject
	PushChannelDao pushChannelDao;
	@Inject
	EstadisticasDao estadisticasDao;
	
	public List<Application> listApplications(long idUser) throws UserNotRegisteredException{
		User user = userDao.read(idUser);
		if(user == null)//no existe usuario
			throw new UserNotRegisteredException("No existe el usuario con id:"+idUser);
		//Obtenego la lista de apps del usuario
		
		List<Application> apps = appDao.readFromUser(idUser);
		
		return apps;
	}
	
	public List<Application> getAllApplications(){
		
		
		return (List<Application>) appDao.readAll();
		
	}
	
	public boolean validarNombre(String nom){
		Pattern pat = Pattern.compile("[a-zA-Z0-9_]{3,30}");
	     Matcher mat = pat.matcher(nom);
	     if (mat.matches()) {
	         return true;//System.out.println("SI");
	     } else {
	         return false;//System.out.println("NO");
	     }
	}
	
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr, List<Boolean> entidadesSync)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException, InvalidNameException {
		//Validar nombre
		if(!validarNombre(nombreApp)){
			throw new InvalidNameException("El nombre debe tener entre 5 y 30 caracteres alfanumericos.");
		}
		//Obtengo el usuario
		User user = userDao.read(idUser);
		//Hago los controles
		if(user == null) //no existe usuario
			throw new UserNotRegisteredException("No existe el usuario con id:"+idUser);
		if (nombreApp.equals(""))//No existe la app
			throw new InvalidNameException("Debe ingresar un nombre para la aplicacion");
		if (appDao.readByName(nombreApp) != null || noSqlDbDao.existNoSqlDb(nombreApp))//No existe la app
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
					if (!roles.contains(r) && !r.getName().equals("") && validarNombre(r.getName())){
						roles.add(r);
						roleDao.create(r);
					}
			}
		}
		//Creo las entidades (si hay)
		if(entidadesStr != null){
			Iterator<String> iter = entidadesStr.iterator();
			Iterator<Boolean> iterSync = entidadesSync.iterator();
			while (iter.hasNext()){
				Entity e = new Entity(iter.next(), app, iterSync.next());
				if(!entidades.contains(e) && !e.getName().equals("") && validarNombre(e.getName())){
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
		return app.getEntities().contains(new Entity(nomEntity, app, true));
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
		//Validar nombre
		if(!validarNombre(nomRole)){
			throw new InvalidNameException("El nombre debe tener entre 5 y 30 caracteres alfanumericos.");
		}
		
		roles.add(r);
		Role retorno = roleDao.create(r);

		//Seteo nuevos roles
		app.setRoles(roles);
		
		return retorno.getId();
	}
	
	public long editEntityApplication(long idApp, long idUser, String nomEntity, boolean sync)
			throws
			 	AppNotRegisteredException,
			 	EntityAlreadyRegisteredException,
			 	UserCantAccessAppException, 
			 	EntityCollectionAlreadyExistsException, InvalidNameException {
		
		Application app = appDao.readById(idApp);
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
		Entity e = new Entity(nomEntity, app, sync);
		
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
			Entity e = new Entity(iter.next(), app, true);
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
	
	public boolean assignUserToApplication(long idApp, long idUser) throws AppNotRegisteredException, UserNotRegisteredException {
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		
		User user = userDao.read(idUser);
		if (user == null)
			throw new UserNotRegisteredException("No existe el usuario con id:" + idUser);
		
		// Si el usuario no ten�a asignada la app, se la asigno
		// Si ya ten�a asignada la app, devuelvo false
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

	public boolean unassignUserFromApplication(long idApp, long idUser)
			throws AppNotRegisteredException, UserNotRegisteredException {
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		
		User user = userDao.read(idUser);
		if (user == null)
			throw new UserNotRegisteredException("No existe el usuario con id:" + idUser);
		
		// Si el usuario ya ten�a asignada la app, se la desasigno
		// Si no ten�a asignada la app, devuelvo false
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
	public boolean existsPushChannelApplication(long idApp,
			String nombreCanal) throws AppNotRegisteredException {
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
		
		return (app.getPushChannel(nombreCanal) != null);
	}

	@Override
	public long addPushChannelToApplication(long idApp,
			String nombreCanal)
					throws
						AppNotRegisteredException,
						PushChanAlreadyRegisteredException {
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
		
		if (app.getPushChannel(nombreCanal) == null) {
			PushChannel pc = new PushChannel(nombreCanal,app);
			//app.addPushChannel(pc);
			pc.setApplication(app);
			
			//appDao.update(app);
			
			pushChannelDao.create(pc);
			
			
			return pc.getId();
		}
		else
			throw new PushChanAlreadyRegisteredException("Ya existe el canal push con nombre " + nombreCanal + " para la aplicaci�n con id " + idApp);
		
	}

	@Override
	public long removePushChannelFromApplication(long idApp,
			long idCanal) throws AppNotRegisteredException,
			PushChanNotRegisteredException {
		
		Application app = appDao.read(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n de nombre " + idApp);
		
		
		for (PushChannel pc : app.getPushChannels()) {
			if (pc.getId() == idCanal) {
				app.removePushChannel(pc);
				appDao.update(app);
				return pc.getId();
			}
		}
		
		throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal + " para la aplicaci�n con id " + idApp);
	}
	
	public List<Role> getRolesApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id= "+ idApp);
		
		return app.getRoles();//roleDao.readAll(idApp);
		
	}
	
	public List<Entity> getEntitiesApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id= "+ idApp);
		
		return app.getEntities();
		
	}
	
	public List<Client> getClientsApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id= "+ idApp);
		
		return app.getClients();
		
	}
	
	public List<PushChannel> getPushChannelsApplication(long idApp) throws AppNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id= "+ idApp);
		
		List<PushChannel> pushChannels = app.getPushChannels(); 
		
		return pushChannels;
		
	}
	
	@Override
	public List<Permission> getPermissionsForEntity(long appId, long entityId)
			throws AppNotRegisteredException, EntityCollectionNotRegisteredException {
		
		Application app = appDao.read(appId);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
		
		Entity entity = entityDao.read(entityId);
		
		if (entity == null) {
			throw new EntityCollectionNotRegisteredException("No existe la entidad");
		}
		
		return permissionDao.readAllFromEntity(appId, entityId);
	}
	
	@Override
	public List<Permission> getPermissionsForRol(long appId, long rolId)
			throws AppNotRegisteredException, RoleNotFoundException {
		
		Application app = appDao.read(appId);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
		
		Entity entity = entityDao.read(rolId);
		
		if (entity == null) {
			throw new RoleNotFoundException("No existe la entidad");
		}
		
		return permissionDao.readAllFromRol(appId, rolId);
	}
	
	public Application getApplication(long appId) throws AppNotRegisteredException {
		Application app = appDao.readById(appId);
		
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
		
		return app;
	}

	@Override
	public boolean existsApplication(long idApp) {
		return (appDao.read(idApp) != null);
	}
	
	public Application getApplication(String appName) throws AppNotRegisteredException {
		Application app = appDao.readByName(appName);
		
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con nombre " + appName);
		
		return app;
	}
	

	@Override
	public ChartDto getChartsValues(long idApp, TipoChart tipoChart)
			throws AppNotRegisteredException {
		
		Application app = appDao.readById(idApp);
		
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
		
		ChartDto respuesta = new ChartDto();
		
		List<Integer> dispRegistrados = new ArrayList<Integer>();
		List<Integer> mensajesPush = new ArrayList<Integer>();
		List<Integer> pedidosHttp = new ArrayList<Integer>();
		
		Date tiempoActual = new Date();
		Date tiempoMinAux;
		Date tiempoMaxAux;
		Calendar cal = Calendar.getInstance();
		switch(tipoChart){
			case Minutos:
				for(int i = 0; i < 10; i++){
					
					
					cal.setTime(tiempoActual);
					cal.add(Calendar.MINUTE, -i - 1);
					tiempoMinAux = cal.getTime();
					
					
					cal.setTime(tiempoActual);
					cal.add(Calendar.MINUTE, -i);
					tiempoMaxAux = cal.getTime();

							

					// pedidos http
					int valor = estadisticasDao.readByType(idApp, 1, tiempoMinAux, tiempoMaxAux);
					pedidosHttp.add(valor);
					
					// pedidos mensajesPush
					valor = estadisticasDao.readByType(idApp, 2, tiempoMinAux, tiempoMaxAux);
					mensajesPush.add(valor);
					
					// pedidos dispRegistrados
					valor = estadisticasDao.readByType(idApp, 3, tiempoMinAux, tiempoMaxAux);
					dispRegistrados.add(valor);
					
				}
				break;
			case Horas:
for(int i = 0; i < 10; i++){
					
					
					cal.setTime(tiempoActual);
					cal.add(Calendar.HOUR, -i - 1);
					tiempoMinAux = cal.getTime();
					
					
					cal.setTime(tiempoActual);
					cal.add(Calendar.HOUR, -i);
					tiempoMaxAux = cal.getTime();

							

					// pedidos http
					int valor = estadisticasDao.readByType(idApp, 1, tiempoMinAux, tiempoMaxAux);
					pedidosHttp.add(valor);
					
					// pedidos mensajesPush
					valor = estadisticasDao.readByType(idApp, 2, tiempoMinAux, tiempoMaxAux);
					mensajesPush.add(valor);
					
					// pedidos dispRegistrados
					valor = estadisticasDao.readByType(idApp, 3, tiempoMinAux, tiempoMaxAux);
					dispRegistrados.add(valor);
					
				}
				break;
			case Dias:
				for(int i = 0; i < 15; i++){
									
									
									cal.setTime(tiempoActual);
									cal.add(Calendar.DATE, -i - 1);
									tiempoMinAux = cal.getTime();
									
									
									cal.setTime(tiempoActual);
									cal.add(Calendar.DATE, -i);
									tiempoMaxAux = cal.getTime();

											

									// pedidos http
									int valor = estadisticasDao.readByType(idApp, 1, tiempoMinAux, tiempoMaxAux);
									pedidosHttp.add(valor);
									
									// pedidos mensajesPush
									valor = estadisticasDao.readByType(idApp, 2, tiempoMinAux, tiempoMaxAux);
									mensajesPush.add(valor);
									
									// pedidos dispRegistrados
									valor = estadisticasDao.readByType(idApp, 3, tiempoMinAux, tiempoMaxAux);
									dispRegistrados.add(valor);
									
								}
								break;
			case Mes:
				for(int i = 0; i < 6; i++){
									
									
									cal.setTime(tiempoActual);
									cal.add(Calendar.MONTH, -i - 1);
									tiempoMinAux = cal.getTime();
									
									
									cal.setTime(tiempoActual);
									cal.add(Calendar.MONTH, -i);
									tiempoMaxAux = cal.getTime();

											

									// pedidos http
									int valor = estadisticasDao.readByType(idApp, 1, tiempoMinAux, tiempoMaxAux);
									pedidosHttp.add(valor);
									
									// pedidos mensajesPush
									valor = estadisticasDao.readByType(idApp, 2, tiempoMinAux, tiempoMaxAux);
									mensajesPush.add(valor);
									
									// pedidos dispRegistrados
									valor = estadisticasDao.readByType(idApp, 3, tiempoMinAux, tiempoMaxAux);
									dispRegistrados.add(valor);
									
								}
								break;
				default: break;
		}		
		
		
		respuesta.setDispRegistrados(dispRegistrados);
		respuesta.setMensajesPushEnviados(mensajesPush);
		respuesta.setPedidosHttp(pedidosHttp);
		
		return respuesta;
	}

	
	public String GetEntityName(long appId, long entityId) throws AppNotRegisteredException, EntityCollectionNotRegisteredException{
		
		Application app = appDao.read(appId);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
		
		Entity entity = entityDao.read(entityId);
		
		if (entity == null) {
			throw new EntityCollectionNotRegisteredException("No existe la entidad");
		}
		
		
		return entity.getName();
		
		
	}

	
	public String GetRolName(long appId, long rolId) throws AppNotRegisteredException,  RoleNotFoundException{
		
		Application app = appDao.read(appId);
		if (app == null)
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
		
		Role rol = roleDao.read(rolId);
		
		if (rol == null) {
			throw new RoleNotFoundException("No existe el rol");
		}
		
		
		return rol.getName();
		
		
	}
	
//	private int generarValoresRandomicos(int min, int max){
//		Random rand = new Random();
//
//        // nextInt is normally exclusive of the top value,
//        // so add 1 to make it inclusive
//        return rand.nextInt((max - min) + 1) + min;
//	}
}
