package uy.com.group05.baascore.bll.ejbs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;
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
	
	public Application createApplication(String nombreApp, User owner)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException {
		
		if (appDao.readByName(nombreApp) != null){ //No existe la app
			throw new NombreAppAlreadyRegisteredException("Ya existe una aplicacion con ese nombre");
		}
		
		Application app = new Application(nombreApp, owner, new ArrayList<Role>(), new ArrayList<Entity>());
		
		noSqlDbDao.createNoSqlDb(nombreApp);
		
		return appDao.create(app);
	}
	
	public Role createRole(String nombreApp, String nombreRole) { //Segun DS retorna role, capaz mejor retornar app
		Application app= appDao.readByName(nombreApp);
		if (app == null){ //Existe la app
			//throw new No existe la aplicacion
		}
		Iterator<Role> iter = app.getRoles().iterator();
		boolean existe = false;
		while (iter.hasNext() && !existe){
			existe = iter.next().getName().equals(nombreRole);
		}
		if (existe){ //Existe el usuario que la crea
			//throw new Ya existe un rol con el mismo nombre
		}
		
		List<Role> roles = app.getRoles();
		Role role = new Role(nombreRole, app);
		roles.add(role);
		app.setRoles(roles);
		
		return roleDao.create(role);
	}
	
	public Entity createEntity(String nombreApp, String nombreEntity) { //Segun DS retorna role, capaz mejor retornar app
		Application app= appDao.readByName(nombreApp);
		if (app == null){ //Existe la app
			//throw new No existe la aplicacion
		}
		Iterator<Entity> iter = app.getEntities().iterator();
		boolean existe = false;
		while (iter.hasNext() && !existe){
			existe = iter.next().getName().equals(nombreEntity);
		}
		if (existe){ //Existe el usuario que la crea
			//throw new Ya existe un rol con el mismo nombre
		}
		
		List<Entity> entities = app.getEntities();
		Entity entity = new Entity(nombreEntity, app);
		entities.add(entity);
		app.setEntities(entities);
		
		return entityDao.create(entity);
	}
	
	public List<Application> listApplications(long idUser) throws UserNotRegisteredException{
		User user = userDao.read(idUser);
		if(user == null)//no existe usuario
			throw new UserNotRegisteredException("No existe el usuario con id:"+idUser);
		
		//Obtenego la lista de apps del usuario
		return appDao.readFromUser(idUser); 
	}
	
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException{
		//Obtengo el usuario
		User user = userDao.read(idUser);
		//Hago los controles
		if(user == null) //no existe usuario
			throw new UserNotRegisteredException("No existe el usuario con id:"+idUser);
		if (appDao.readByName(nombreApp) != null)//No existe la app
			throw new NombreAppAlreadyRegisteredException("Ya existe una aplicacion con ese nombre");

		//Creo la App
		Application app = new Application(nombreApp, user);
		
		List<Role> roles = new ArrayList<Role>();
		List<Entity> entidades = new ArrayList<Entity>();
		//Creo los roles
		Iterator<String> iter = rolesStr.iterator();
		while (iter.hasNext()){
			Role r = new Role(iter.next(), app);
			roles.add(r);
		}
		iter = entidadesStr.iterator();
		while (iter.hasNext()){
			Entity e = new Entity(iter.next(), app);
			entidades.add(e);
		}
		//Seteo Roles y Entidades a App
		app.setRoles(roles);
		app.setEntities(entidades);
		appDao.create(app);
		noSqlDbDao.createNoSqlDb(nombreApp);
		return app.getId();
	}

	public boolean existsApplication(String nombre){
		return (appDao.readByName(nombre) != null);
	}
}
