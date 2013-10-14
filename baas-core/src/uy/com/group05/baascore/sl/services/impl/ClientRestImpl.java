package uy.com.group05.baascore.sl.services.impl;

import java.util.UUID;

import javax.ejb.EJB;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.services.rest.ClientRest;
import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;

public class ClientRestImpl implements ClientRest {
	
	@EJB
	Mapper mapper;
	
	@EJB
	private ClientManagementLocal clientManagementLocal;
	
	@Override
	public ClientRegistrationDTO register(UUID apiClientId, ClientDTO client) {
		return clientManagementLocal.register(apiClientId, client);
	}

	@Override
	public ClientAuthenticationDTO authenticate(UUID apiClientId, String appName, String email, String password) {
		return clientManagementLocal.authenticate(appName, apiClientId, email, password);
	}
}
