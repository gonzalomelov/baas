package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;

@WebService
public interface ApplicationServices {
	
	/*@WebMethod
	public ApplicationDTO createApplication(String nombreApp, User owner)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException;
	
	@WebMethod
	public Role createRole(String nombreApp, String nombreRole);
	@WebMethod
	public Entity createEntity(String nombreApp, String nombreEntity);
	*/
	@WebMethod
	public List<ApplicationDTO> listApplications(long idUser) 
			throws 
				UserNotRegisteredException;
	
	@WebMethod
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException;
	@WebMethod
	public boolean existsApplication(String nombre);
}
