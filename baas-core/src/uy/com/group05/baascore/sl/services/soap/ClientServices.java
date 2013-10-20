package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;

@WebService
public interface ClientServices {
	@WebMethod
	public ClientDTO getClient(
			@WebParam(name = "appId") long appId, 
			@WebParam(name = "clientId") long clientId);
	
	@WebMethod
	public boolean assignRoleToClient(
			@WebParam(name = "idApp") long idApp, 
			@WebParam(name = "idUser") long idUser, 
			@WebParam(name = "idRole") long idRole, 
			@WebParam(name = "idClient") long idClient) 
			throws ClientNotRegisteredException, EntityNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException;
}
