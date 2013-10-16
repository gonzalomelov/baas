package uy.com.group05.baascore.sl.services.impl;

import javax.inject.Inject;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.services.soap.ClientServices;

@WebService(
	endpointInterface="uy.com.group05.baascore.sl.services.soap.ClientServices",
	portName="ClientServicesPort",
	serviceName="ClientServices"
)
public class ClientServicesImpl implements ClientServices {
	@Inject
	Mapper mapper;
	
	@Inject
	ClientManagementLocal clientManagementLocal;
	
	public ClientDTO getClient(long appId, long clientId) {
		
		Client client = clientManagementLocal.getClient(appId, clientId); 
		
		ClientDTO clientDto = mapper.getMapper().map(client, ClientDTO.class);
		
		return clientDto;
	}
}
