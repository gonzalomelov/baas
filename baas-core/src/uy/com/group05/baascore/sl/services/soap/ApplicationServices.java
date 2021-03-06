package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;








import javax.management.relation.RoleNotFoundException;

import uy.com.group05.baascore.common.exceptions.InvalidNameException;
import uy.com.group05.baascore.common.exceptions.RoleAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;
import uy.com.group05.baascore.sl.entitiesws.ChartDto;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.EntityDTO;
import uy.com.group05.baascore.sl.entitiesws.PermissionDTO;
import uy.com.group05.baascore.sl.entitiesws.PushChannelDTO;
import uy.com.group05.baascore.sl.entitiesws.RoleDTO;
import uy.com.group05.baascore.sl.entitiesws.SimpleApplicationDTO;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelDTO;
import uy.com.group05.baascore.sl.entitiesws.TipoChart;

@WebService
public interface ApplicationServices {
	
	@WebMethod
	public List<SimpleApplicationDTO> listApplications( 
			@WebParam(name = "idUser") long idUser)
			throws 
				UserNotRegisteredException;
	
	@WebMethod
	public long createApplication(
			@WebParam(name = "idUser") long idUser,
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "rolStr") List<String> rolesStr,
			@WebParam(name = "entidadStr") List<String> entidadesStr,
			@WebParam(name = "entidadSync") List<Boolean> entidadesSync)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException, 
				InvalidNameException;
	
	@WebMethod
	public boolean existsApplication(
			@WebParam(name = "nombreApp") String nombre);
	
	@WebMethod
	public boolean existsEntityApplication(
			@WebParam(name ="idApp") long idApp,
			@WebParam(name ="nomEntity") String nomEntity) 
			throws AppNotRegisteredException;
	
	@WebMethod
	public boolean existsRoleApplication(
			@WebParam(name ="idApp") long idApp,
			@WebParam(name ="nomRole") String nomRole) 
			throws AppNotRegisteredException;
	
	@WebMethod
	public long editRoleApplication(
			@WebParam(name ="idApp")long idApp, 
			@WebParam(name ="idUser")long idUser, 
			@WebParam(name ="nomRole") String nomRole)
			throws
			 	AppNotRegisteredException,
			 	RoleAlreadyRegisteredException,
			 	UserCantAccessAppException, InvalidNameException;
	
	@WebMethod
	public long editEntityApplication(
			@WebParam(name ="idApp") long idApp, 
			@WebParam(name ="idUser") long idUser, 
			@WebParam(name ="nomEntity") String nomEntity,
			@WebParam(name ="sync") boolean sync)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	EntityAlreadyRegisteredException,
			 	EntityCollectionAlreadyExistsException, InvalidNameException;
	
	@WebMethod
	public long editApplication(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "rolStr") List<String> rolesStr,
			@WebParam(name = "entidadStr") List<String> entidadesStr)
			throws
			 	AppNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException;
	
	@WebMethod
	public boolean assignUserToApplication(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idUser") long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException;
	
	@WebMethod
	public boolean unassignUserFromApplication(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idUser") long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException;
	
	@WebMethod
	public boolean existsPushChannelApplication(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException;
	
	@WebMethod
	public long addPushChannelToApplication(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException;
	
	@WebMethod
	public long removePushChannelFromApplication(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	@WebMethod
	public List<RoleDTO> getRolesApplication(
			@WebParam(name = "idApp") long idApp) 
			throws AppNotRegisteredException;
	
	@WebMethod
	public List<PermissionDTO> getPermissionsForEntity(
			@WebParam(name = "appId") long appId,
			@WebParam(name = "entityId") long entityId)
			throws AppNotRegisteredException, EntityCollectionNotRegisteredException;
	
	@WebMethod
	public List<PermissionDTO> getPermissionsForRol(
			@WebParam(name = "appId") long appId,
			@WebParam(name = "rolId") long rolId)
			throws AppNotRegisteredException, RoleNotFoundException;

	@WebMethod
	public List<EntityDTO> getEntitiesApplication(
			@WebParam(name = "idApp") long idApp) 
			throws AppNotRegisteredException;
	
	@WebMethod
	public List<ClientDTO> getClientsApplication(
			@WebParam(name = "idApp") long idApp) 
			throws AppNotRegisteredException;
	
	@WebMethod
	public List<SimplePushChannelDTO> getPushChannelsApplication(
			@WebParam(name = "idApp") long idApp) 
			throws AppNotRegisteredException;
	
	@WebMethod
	public ApplicationDTO getApplication(
			@WebParam(name = "idApp") long idApp)
			throws AppNotRegisteredException;
	
	@WebMethod
	public ChartDto getChartsValues(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "tipoChart") TipoChart tipoChart)
					throws AppNotRegisteredException;
	
	@WebMethod
	public String GetEntityName(long appId, long entityId)
			throws AppNotRegisteredException, EntityCollectionNotRegisteredException;
	
	@WebMethod
	public String GetRoleName(long appId, long rolId) throws AppNotRegisteredException, RoleNotFoundException;
	
//	@WebMethod
//	public 
}
