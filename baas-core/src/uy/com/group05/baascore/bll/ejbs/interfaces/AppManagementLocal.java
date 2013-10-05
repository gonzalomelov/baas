package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;

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
				MongoDBAlreadyExistsException,
				EntityCollectionAlreadyExistsException;
	
	public boolean existsApplication(String nombre);
	
	public boolean existsEntityApplication(String nomApp, String nomEntity) throws AppNotRegisteredException;
	
	public boolean existsRoleApplication(String nomApp, String nomRole) throws AppNotRegisteredException;
}
