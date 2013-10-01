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
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;
import uy.com.group05.baascore.dal.dao.RoleDao;
import uy.com.group05.baascore.dal.dao.UserDao;
import uy.com.group05.baascore.sl.services.soap.ApplicationServices;

@Stateless
public class AppManagement implements AppManagementLocal, ApplicationServices{

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
		if (userDao.readByUsername(owner.getUsername()) == null){ //Existe el usuario que la crea
			throw new UserNotRegisteredException("No existe un usuario con ese nommbre");
		}
		
		Application app = new Application(nombreApp, owner);
		
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
	
}
