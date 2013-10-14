package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.exceptions.RoleAlreadyRegisteredException;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Permission;
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
				EntityCollectionAlreadyExistsException;
	
	public boolean existsApplication(String nombre);
	
	public boolean existsEntityApplication(long idApp, String nomEntity) throws AppNotRegisteredException;
	
	public boolean existsRoleApplication(long idApp, String nomRole) throws AppNotRegisteredException;
	
	public long editRoleApplication(long idApp, long idUser, String nomRole)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	RoleAlreadyRegisteredException;

	public long editEntityApplication(long idApp, long idUser, String nomEntity)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	EntityAlreadyRegisteredException,
			 	EntityCollectionAlreadyExistsException;
	
	public long editApplication(String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
			 	AppNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException;
	
	public boolean assignUserToApplication(String nombreApp, long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException;
	
	public boolean unassignUserFromApplication(String nombreApp, long idUser)
			throws
			AppNotRegisteredException,
			UserNotRegisteredException;
	
	public boolean existsPushChannelApplication(String nombreApp, String nombreCanal)
			throws
			AppNotRegisteredException;
	
	public long addPushChannelToApplication(String nombreApp, String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException;
	
	public long removePushChannelFromApplication(String nombreApp, String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	public List<Permission> getPermissionsForEntity(long appId, long entityId)
			throws AppNotRegisteredException, EntityCollectionNotRegisteredException;
	
}
