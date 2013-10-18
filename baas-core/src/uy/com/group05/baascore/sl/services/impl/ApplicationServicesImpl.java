package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;










import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.InvalidNameException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.RoleAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.EntityDTO;
import uy.com.group05.baascore.sl.entitiesws.PermissionDTO;
import uy.com.group05.baascore.sl.entitiesws.RoleDTO;
import uy.com.group05.baascore.sl.entitiesws.SimpleApplicationDTO;
import uy.com.group05.baascore.sl.services.soap.ApplicationServices;
import uy.com.group05.baascore.sl.entitiesws.PushChannelDTO;

@WebService(
	endpointInterface="uy.com.group05.baascore.sl.services.soap.ApplicationServices",
	portName="ApplicationServicesPort",
	serviceName="ApplicationServices"
)
public class ApplicationServicesImpl implements ApplicationServices{

	@Inject
	Mapper mapper;
	
	@Inject
	AppManagementLocal appManagementLocal;
	
	public List<SimpleApplicationDTO> listApplications(long idUser) 
			throws 
				UserNotRegisteredException{
		
		List<Application> listApps = appManagementLocal.listApplications(idUser);
		
		List<SimpleApplicationDTO> response = mapper.getMapper().mapAsList(listApps, SimpleApplicationDTO.class);

		return response;
	}


	@Override
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws 
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException, InvalidNameException {
		return appManagementLocal.createApplication(idUser, nombreApp, rolesStr, entidadesStr);
	}
	
	
	public boolean existsApplication(String nombre){
		return appManagementLocal.existsApplication(nombre);
	}
	
	public boolean existsRoleApplication(long idApp, String nomRole) throws AppNotRegisteredException{
		return appManagementLocal.existsRoleApplication(idApp, nomRole);	
	}
	
	public boolean existsEntityApplication(long idApp, String nomEntity) throws AppNotRegisteredException{
		return appManagementLocal.existsEntityApplication(idApp, nomEntity);	
	}

	public long editRoleApplication(long idApp, long idUser, String nomRole)
			throws
			 	AppNotRegisteredException,
			 	RoleAlreadyRegisteredException,
			 	UserCantAccessAppException, InvalidNameException {
		return appManagementLocal.editRoleApplication(idApp, idUser, nomRole);
	}
	
	public long editEntityApplication(long idApp, long idUser, String nomEntity)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	EntityAlreadyRegisteredException,
			 	EntityCollectionAlreadyExistsException, InvalidNameException{
		return appManagementLocal.editEntityApplication(idApp, idUser, nomEntity);
	}
	
	public long editApplication(String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
			 	AppNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException{
		return appManagementLocal.editApplication(nombreApp, rolesStr, entidadesStr);
	}
	
	public boolean assignUserToApplication(String nombreApp, long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException {
		return appManagementLocal.assignUserToApplication(nombreApp, idUser);
	}
	
	public boolean unassignUserFromApplication(String nombreApp, long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException {
		return appManagementLocal.unassignUserFromApplication(nombreApp, idUser);
	}


	@Override
	public boolean existsPushChannelApplication(String nombreApp, String nombreCanal)
			throws
				AppNotRegisteredException {
		return appManagementLocal.existsPushChannelApplication(nombreApp, nombreCanal);
	}


	@Override
	public long addPushChannelToApplication(String nombreApp, String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException {
		return appManagementLocal.addPushChannelToApplication(nombreApp, nombreCanal);
	}


	@Override
	public long removePushChannelFromApplication(String nombreApp, String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException {
		return appManagementLocal.removePushChannelFromApplication(nombreApp, nombreCanal);
	}
	
	public List<PermissionDTO> getPermissionsForEntity(long appId, long entityId)
			throws
				AppNotRegisteredException,
				EntityCollectionNotRegisteredException {
		
		List<Permission> permissions = appManagementLocal.getPermissionsForEntity(appId, entityId);

		List<PermissionDTO> response = mapper.getMapper().mapAsList(permissions, PermissionDTO.class);
		
		return response;
	}
	
	public ApplicationDTO getApplication(long idApp) throws AppNotRegisteredException {
		Application app = appManagementLocal.getApplication(idApp);
		
		ApplicationDTO appDto = mapper.getMapper().map(app, ApplicationDTO.class);
		
		return appDto; 
	}


	@Override
	public List<RoleDTO> getRolesApplication(long idApp)
			throws AppNotRegisteredException {
		List<Role> roles = appManagementLocal.getRolesApplication(idApp);
		
		List<RoleDTO> rolesDTO = mapper.getMapper().mapAsList(roles, RoleDTO.class); 
		
		return rolesDTO;
	}


	@Override
	public List<EntityDTO> getEntitiesApplication(long idApp)
			throws AppNotRegisteredException {
		List<Entity> entities = appManagementLocal.getEntitiesApplication(idApp);
		List<EntityDTO> entitiesDTO = mapper.getMapper().mapAsList(entities, EntityDTO.class); 
		
		return entitiesDTO;
	}


	@Override
	public List<ClientDTO> getClientsApplication(long idApp)
			throws AppNotRegisteredException {
		List<Client> clients = appManagementLocal.getClientsApplication(idApp);
		List<ClientDTO> clientsDTO = mapper.getMapper().mapAsList(clients, ClientDTO.class); 
		
		return clientsDTO;
	}


	@Override
	public List<PushChannelDTO> getPushChannelsApplication(long idApp)
			throws AppNotRegisteredException {
		List<PushChannel> pushChannels = appManagementLocal.getPushChannelsApplication(idApp);
		List<PushChannelDTO> pushChannelsDTO = mapper.getMapper().mapAsList(pushChannels, PushChannelDTO.class); 
		
		return pushChannelsDTO;
	}
}
