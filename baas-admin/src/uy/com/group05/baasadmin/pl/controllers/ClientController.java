package uy.com.group05.baasadmin.pl.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import uy.com.group05.baasadmin.common.exceptions.ClientRolException;
import uy.com.group05.baasadmin.common.utils.PropertyHandler;
import uy.com.group05.baascore.sl.services.impl.ApplicationServices;
import uy.com.group05.baascore.sl.services.impl.ClientServices;
import uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.ClientNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.RoleDTO;
import uy.com.group05.baascore.sl.services.soap.RolesClientDTO;
import uy.com.group05.baascore.sl.services.soap.UserCantAccessAppException_Exception;

public class ClientController {
	
	private ClientServices service;
	
	public ClientController() {
		PropertyHandler propertyHandler = new PropertyHandler();
		String wsdlHostLocation = propertyHandler.getProperty("wsdlHostLocation");
		URL url = null;
		try {
			url = new URL(wsdlHostLocation + "/ClientServices?wsdl");
		} catch (MalformedURLException e) {}
		
		this.service = new ClientServices(url);
	}
	
	public List<RoleDTO> GetClientRoles(long appId, long userId, long clientId) throws ClientRolException{
		
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
