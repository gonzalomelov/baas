package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.jws.WebMethod;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ApplicationDTO;

@Local
public interface AppManagementLocal {

	public Application createApplication(String nombreApp, User owner)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException;
	
	public Role createRole(String nombreApp, String nombreRole);
	
	public Entity createEntity(String nombreApp, String nombreEntity);
	
	public List<Application> listApplications(long idUser) 
			throws 
				UserNotRegisteredException;
	
	public long createApplication(long idUser, String nombreApp, List<String> rolesStr, List<String> entidadesStr)
			throws
				NombreAppAlreadyRegisteredException,
				UserNotRegisteredException,
				MongoDBAlreadyExistsException;
	
	public boolean existsApplication(String nombre);
}
