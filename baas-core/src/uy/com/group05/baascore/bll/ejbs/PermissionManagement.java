package uy.com.group05.baascore.bll.ejbs;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uy.com.group05.baascore.bll.ejbs.interfaces.IPermissionManagement;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Operation;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.OperationDao;
import uy.com.group05.baascore.dal.dao.PermissionDao;
import uy.com.group05.baascore.dal.dao.RoleDao;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.UserDao;
import uy.com.group05.baascore.sl.entitiesws.PermissionRoleDTO;


public class PermissionManagement implements IPermissionManagement {
	
	@Inject
	private PermissionDao permissionDao;

	@Inject
	private RoleDao roleDao;
	
	@Inject
	private ApplicationDao appDao;
	
	@Inject
	private UserDao userDao;
	
	@Inject
	private EntityDao entityDao;
	
	@Inject
	private OperationDao operDao;
	
	
	
	@Override
	public List<Role> getRoles(long appId) throws AppNotRegisteredException {
		
		//return (List<Role>) _roleDao.readAll();
		Application app = appDao.read(appId);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese nombre");
		return app.getRoles();
		
	}

	//Esta en AppManagement
//	@Override
//	public boolean createRole(long appId, String name) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean assignUserRole(long appId, long clientId, long roleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assignRolePermissionEntity(long appId, long roleId,
			long entityId, long operationId) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean deleteRole(long appId, long roleId) throws NotImplementedException {
//		throw new NotImplementedException();
//	}

	@Override
	public boolean deleteUserRole(long appId, long clientId, long roleId) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public boolean deleteRolePermissionEntity(long appId, long roleId,
			long entityId, long operationId) throws NotImplementedException {

		throw new NotImplementedException();
	}

	public boolean assingPermissionEntity(long idUser, long idApp, long idEntity, List<PermissionRoleDTO> permRoles) 
			throws EntityNotRegisteredException, AppNotRegisteredException, UserCantAccessAppException{
		//+++++++++++++++
		//FALTA CONTROLAR QUE LOS ROLES PERTENEZCAN A LA APP
		//+++++++++++++++
		
		Application app = appDao.readById(idApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese id");
		List<User> users = app.getUsers();
		if (!users.contains(userDao.read(idUser))) {
			throw new UserCantAccessAppException ("El usuario no es administrador de la aplicacion");
		}
		Entity entity = entityDao.read(idEntity);
		if (!app.getEntities().contains(entity)){
			throw new EntityNotRegisteredException ("No existe una entidad con ese id");
		}
		// Si la lista es vacia no hago nada.
		if (permRoles == null){
			return true;
		}
		// Creo los permisos o elimino.
		Iterator<PermissionRoleDTO> iter = permRoles.iterator();
		while (iter.hasNext()) {
			PermissionRoleDTO pr = iter.next();
			if (pr.isHas()){ //creo si no existe
				
				if(!entity.existsPermission(pr.getIdRole(), pr.getIdOperation())){
					Role role = roleDao.read(pr.getIdRole());
					Permission per = new Permission(app, entity, role, operDao.read(pr.getIdOperation()));
					entity.addPermission(per);
					role.addPermission(per);
					permissionDao.create(per);
					//update?
				}
			}
			else{ //elimino si existe
				if(entity.existsPermission(pr.getIdRole(), pr.getIdOperation())){
					//Elimino el permiso, Rol.Permissions, Permission, Entity.Permissions
					Role role = roleDao.read(pr.getIdRole());
					Permission per = permissionDao.readWithoutId(app.getId(), entity.getId(), role.getId(), pr.getIdOperation());
					//Permission per = new Permission(app, entity, role, operDao.read(pr.getIdOperation()));
					entity.removePermission(per);
					role.removePermission(per);
					permissionDao.delete(per.getId());
					// ID de permiso?
					//permissionDao.create(per);
					//update?
				}
			}
			
		}
		
		return true;
		
	}


	
	
	
}
