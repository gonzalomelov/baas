package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.sl.entitiesws.PermissionRoleDTO;

@WebService
public interface PermissionServices {

	@WebMethod
	public boolean assingPermissionEntity(
			@WebParam(name = "idUser") long idUser, 
			@WebParam(name = "idApp") long idApp, 
			@WebParam(name = "idEntity") long idEntity, 
			@WebParam(name = "permRoles") List<PermissionRoleDTO> permRoles) 
			throws EntityNotRegisteredException, AppNotRegisteredException, UserCantAccessAppException;
}
