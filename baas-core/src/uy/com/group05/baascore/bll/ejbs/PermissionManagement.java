package uy.com.group05.baascore.bll.ejbs;

import java.util.List;

import javax.inject.Inject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uy.com.group05.baascore.bll.ejbs.interfaces.IPermissionManagement;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.dal.dao.PermissionDao;
import uy.com.group05.baascore.dal.dao.RoleDao;


public class PermissionManagement implements IPermissionManagement {

	
	@Inject
	private PermissionDao _permissionDao;

	@Inject
	private RoleDao _roleDao;
	
	
	
	@Override
	public List<Role> getRoles(long appId) {
		
		return (List<Role>) _roleDao.readAll();
		
	}

	@Override
	public boolean createRole(long appId, String name) {
		// TODO Auto-generated method stub
		return false;
	}

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

	@Override
	public boolean deleteRole(long appId, long roleId) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public boolean deleteUserRole(long appId, long clientId, long roleId) throws NotImplementedException {
		throw new NotImplementedException();
	}

	@Override
	public boolean deleteRolePermissionEntity(long appId, long roleId,
			long entityId, long operationId) throws NotImplementedException {

		throw new NotImplementedException();
	}

	
	
	
}
