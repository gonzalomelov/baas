package uy.com.group05.baascore.bll.ejbs.interfaces;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.NombreAppAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;

@Local
public interface AppManagementLocal {

	public Application createApplication(String nombreApp, User owner) throws NombreAppAlreadyRegisteredException, UserNotRegisteredException;
	
	public Role createRole(String nombreApp, String nombreRole);
	
	public Entity createEntity(String nombreApp, String nombreEntity);
}
