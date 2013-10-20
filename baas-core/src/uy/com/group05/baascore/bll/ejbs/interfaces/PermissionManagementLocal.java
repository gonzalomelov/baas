package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.RoleNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.sl.entitiesws.PermissionEntityDTO;
import uy.com.group05.baascore.sl.entitiesws.PermissionRoleDTO;

@Local
public interface PermissionManagementLocal {
	
	List<Role> getRoles(long appId) throws AppNotRegisteredException;
	
	//boolean createRole(long appId, String name);
	
	boolean assignUserRole (long appId, long clientId, long roleId);
	
	boolean assignRolePermissionEntity(long appId, long roleId, long entityId, long operationId);
	
	//boolean deleteRole(long appId, long roleId);
	
	boolean deleteUserRole (long appId, long clientId, long roleId);
	
	boolean deleteRolePermissionEntity (long appId, long roleId, long entityId, long operationId) 
			throws NotImplementedException;
	
	public boolean assingPermissionEntity(long idUser, long idApp, long idEntity, List<PermissionRoleDTO> permRoles) 
			throws EntityNotRegisteredException, AppNotRegisteredException, UserCantAccessAppException;
	
	public boolean assingPermissionRole(long idUser, long idApp, long idRole, List<PermissionEntityDTO> permEntities) 
			throws RoleNotRegisteredException, AppNotRegisteredException, UserCantAccessAppException;

}
