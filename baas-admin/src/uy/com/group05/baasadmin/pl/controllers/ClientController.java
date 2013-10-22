package uy.com.group05.baasadmin.pl.controllers;

import java.util.List;

import uy.com.group05.baasadmin.common.exceptions.ClientRolException;
import uy.com.group05.baascore.sl.services.impl.ClientServices;
import uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.ClientNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.RoleDTO;
import uy.com.group05.baascore.sl.services.soap.RolesClientDTO;
import uy.com.group05.baascore.sl.services.soap.UserCantAccessAppException_Exception;

public class ClientController {
	
	public List<RoleDTO> GetClientRoles(long appId, long userId, long clientId) throws ClientRolException{
		
		ClientServices service = new ClientServices();
		
		uy.com.group05.baascore.sl.services.soap.ClientServices port = service.getClientServicesPort();
		
		try {
			return port.getRolesFromClient(appId, userId, clientId);
		} catch (ClientNotRegisteredException_Exception e) {
			throw new ClientRolException(e.getMessage());
		} catch (UserCantAccessAppException_Exception e) {
			throw new ClientRolException(e.getMessage());
		} catch (AppNotRegisteredException_Exception e) {
			throw new ClientRolException(e.getMessage());
		}
		
		
	}
	
	public void saveClientRoles(long appId, long userId, long clientId, List<RolesClientDTO> rolesClient ) throws ClientRolException{
		
		ClientServices service = new ClientServices();
		
		uy.com.group05.baascore.sl.services.soap.ClientServices port = service.getClientServicesPort();
		
		
		try {
			port.assignRoleToClients(appId, userId, clientId, rolesClient);
		} catch (ClientNotRegisteredException_Exception e) {
			throw new ClientRolException(e.getMessage());
		} catch (UserCantAccessAppException_Exception e) {
			throw new ClientRolException(e.getMessage());
		} catch (AppNotRegisteredException_Exception e) {
			throw new ClientRolException(e.getMessage());
		}
		
	}

}
