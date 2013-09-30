package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uy.com.group05.baascore.common.entities.Role;

public interface IPermissionManagement {
	
	List<Role> getRoles(long appId);
	
	boolean createRole(long appId, String name);
	
	boolean assignUserRole (long appId, long clientId, long roleId);
	
	boolean assignRolePermissionEntity(long appId, long roleId, long entityId, long operationId);
	
	boolean deleteRole(long appId, long roleId);
	
	boolean deleteUserRole (long appId, long clientId, long roleId);
	
	boolean deleteRolePermissionEntity (long appId, long roleId, long entityId, long operationId) throws NotImplementedException;

}
