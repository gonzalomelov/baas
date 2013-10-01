package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;

@WebService
public interface ApplicationServices {
	
	@WebMethod
	public Application createApplication(String nombreApp, User owner)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException;
	
	@WebMethod
	public Role createRole(String nombreApp, String nombreRole);
	@WebMethod
	public Entity createEntity(String nombreApp, String nombreEntity);
}
