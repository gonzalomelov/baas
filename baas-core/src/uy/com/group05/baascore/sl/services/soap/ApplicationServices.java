package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.RoleAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;

@WebService
public interface ApplicationServices {
	
	@WebMethod
	public List<ApplicationDTO> listApplications( 
			@WebParam(name = "idUser") long idUser)
			throws 
				UserNotRegisteredException;
	
	@WebMethod
	public long createApplication(
			@WebParam(name = "idUser") long idUser,
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "rolStr") List<String> rolesStr,
			@WebParam(name = "entidadStr") List<String> entidadesStr)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException;
	
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
			 	UserCantAccessAppException;
	
	@WebMethod
	public long editEntityApplication(
			@WebParam(name ="idApp") long idApp, 
			@WebParam(name ="idUser") long idUser, 
			@WebParam(name ="nomEntity") String nomEntity)
			throws
			 	AppNotRegisteredException,
			 	UserCantAccessAppException,
			 	EntityAlreadyRegisteredException,
			 	EntityCollectionAlreadyExistsException;
	
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
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "idUser") long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException;
	
	@WebMethod
	public boolean unassignUserFromApplication(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "idUser") long idUser)
			throws
				AppNotRegisteredException,
				UserNotRegisteredException;
	
	@WebMethod
	public boolean existsPushChannelApplication(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException;
	
	@WebMethod
	public long addPushChannelToApplication(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException;
	
	@WebMethod
	public long removePushChannelFromApplication(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	
}
