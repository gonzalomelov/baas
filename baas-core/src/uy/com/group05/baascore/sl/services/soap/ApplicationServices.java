package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
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
}
