package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.RoleDTO;
import uy.com.group05.baascore.sl.entitiesws.RolesClientDTO;
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
	
	public boolean assignRoleToClient(long idApp, long idUser, long idRole, long idClient) 
			throws ClientNotRegisteredException, EntityNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException{
		
		return clientManagementLocal.assignRoleToClient(idApp, idUser, idRole, idClient);
	}
	
	public List<RoleDTO> getRolesFromClient(long idApp, long idUser, long idClient) 
			throws ClientNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException{
		
		List<Role> roles = clientManagementLocal.getRolesFromClient(idApp, idUser, idClient);
		List<RoleDTO> rolesDTO = mapper.getMapper().mapAsList(roles, RoleDTO.class); 
		
		return rolesDTO;
	}
	
	public boolean assignRoleToClients(long idApp, long idUser, long idClient, List<RolesClientDTO> rolesClient) 
			throws AppNotRegisteredException, UserCantAccessAppException, ClientNotRegisteredException{
		return clientManagementLocal.assignRoleToClients(idApp, idUser, idClient, rolesClient);
	}
}
