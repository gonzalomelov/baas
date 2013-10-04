package uy.com.group05.baascore.sl.services.impl;

import javax.ejb.EJB;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.services.rest.ClientRest;

public class ClientServicesImpl implements ClientRest {
	
	@EJB
	Mapper mapper;
	
	@EJB
	private ClientManagementLocal clientManagementLocal;
	
	public boolean validateClientCredentials(String email, String password) {
		
		return clientManagementLocal.validateClientCredentials(email, password);
	}
}
