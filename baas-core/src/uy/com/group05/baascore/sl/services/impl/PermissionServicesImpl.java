package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.PermissionManagementLocal;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.RoleNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.PermissionEntityDTO;
import uy.com.group05.baascore.sl.entitiesws.PermissionRoleDTO;
import uy.com.group05.baascore.sl.services.soap.PermissionServices;

@WebService(
		endpointInterface="uy.com.group05.baascore.sl.services.soap.PermissionServices",
		portName="PermissionServicesPort",
		serviceName="PermissionServices"
	)

public class PermissionServicesImpl implements PermissionServices{

	@Inject
	Mapper mapper;
	
	@Inject
	PermissionManagementLocal permissionManagement;
	
	public boolean assingPermissionEntity(long idUser, long idApp, long idEntity, List<PermissionRoleDTO> permRoles) 
			throws EntityNotRegisteredException, AppNotRegisteredException, UserCantAccessAppException{
		return permissionManagement.assingPermissionEntity(idUser, idApp, idEntity, permRoles);
	}
	
	public boolean assingPermissionRole(long idUser, long idApp, long idRole, List<PermissionEntityDTO> permEntities) 
			throws RoleNotRegisteredException, AppNotRegisteredException, UserCantAccessAppException{
		return permissionManagement.assingPermissionRole(idUser, idApp, idRole, permEntities);
	}
}
