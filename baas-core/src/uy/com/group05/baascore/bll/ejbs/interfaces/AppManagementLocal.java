package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.exceptions.RoleAlreadyRegisteredException;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.InvalidNameException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ChartDto;

@Local
public interface AppManagementLocal {
	
	public List<Application> listApplications(long idUser) 
			throws 
				UserNotRegisteredException;
	
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException, 
				InvalidNameException;
	
	public List<Application> getAllApplications();
	
	public boolean existsApplication(String nombre);
	
	public boolean existsApplication(long idApp);
	
	public boolean existsEntityApplication(long idApp, String nomEntity) throws AppNotRegisteredException;
	
	public boolean existsRoleApplication(long idApp, String nomRole) throws AppNotRegisteredException;
	
	public long editRoleApplication(long idApp, long idUser, String nomRole)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	RoleAlreadyRegisteredException, InvalidNameException;

	public long editEntityApplication(long idApp, long idUser, String nomEntity)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	EntityAlreadyRegisteredException,
			 	EntityCollectionAlreadyExistsException, InvalidNameException;
	
	public long editApplication(String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
			 	AppNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException;
	
	public boolean assignUserToApplication(long idApp, long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException;
	
	public boolean unassignUserFromApplication(long idApp, long idUser)
			throws
			AppNotRegisteredException,
			UserNotRegisteredException;
	
	public boolean existsPushChannelApplication(long idApp, String nombreCanal)
			throws
			AppNotRegisteredException;
	
	public long addPushChannelToApplication(long idApp, String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException;
	
	public long removePushChannelFromApplication(long idApp, long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	public List<Role> getRolesApplication(long idApp) 
			throws AppNotRegisteredException;
	
	public List<Entity> getEntitiesApplication(long idApp) 
			throws AppNotRegisteredException;
	
	public List<Client> getClientsApplication(long idApp) 
			throws AppNotRegisteredException;
	
	public List<PushChannel> getPushChannelsApplication(long idApp) 
			throws AppNotRegisteredException;
	
	public List<Permission> getPermissionsForEntity(long appId, long entityId)
			throws AppNotRegisteredException, EntityCollectionNotRegisteredException;
	
	public Application getApplication(long appId) throws AppNotRegisteredException;
	
	public Application getApplication(String appName) throws AppNotRegisteredException;
	
	public ChartDto getChartValues(long appId) throws AppNotRegisteredException;
	
}
