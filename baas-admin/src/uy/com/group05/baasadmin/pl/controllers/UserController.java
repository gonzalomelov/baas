package uy.com.group05.baasadmin.pl.controllers;

import uy.com.group05.baasadmin.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baasadmin.common.exceptions.UnhandledRegistrationException;
import uy.com.group05.baasadmin.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baasadmin.pl.models.UserModel;
import uy.com.group05.baascore.sl.services.impl.UserServices;
import uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.User;
import uy.com.group05.baascore.sl.services.soap.UserSoapFacade;
import uy.com.group05.baascore.sl.services.soap.UsernameAlreadyRegisteredException_Exception;

public class UserController {
	
	public boolean registerUser(UserModel userModel)
		throws
			EmailAlreadyRegisteredException,
			UsernameAlreadyRegisteredException,
			UnhandledRegistrationException {
		
		try {
			
			UserServices service = new UserServices();
			UserSoapFacade port = service.getUserServicesPort();
			
			User u = new User();
			u.setEmail(userModel.getEmail());
			u.setLastname(userModel.getLastname());
			u.setName(userModel.getName());
			u.setPassword(userModel.getPassword());
			u.setUsername(userModel.getUsername());
			
			port.registerUser(u);
		
			return true;
		}
		catch (EmailAlreadyRegisteredException_Exception e) {
			throw new EmailAlreadyRegisteredException(e.getMessage());
		}
		catch (UsernameAlreadyRegisteredException_Exception e) {
			throw new UsernameAlreadyRegisteredException(e.getMessage());
		}
		catch (Exception e) {
			throw new UnhandledRegistrationException("Registro no exitoso");
		}
	}
	
	public UserModel loginUser(String username, String password) {
		return null;
	}
	
	public boolean logoutUser(String username) {
		return false;
	}
	
	public boolean validateUser(String username, String password) {
		return false;
	}
	
	public boolean isUserLoggedIn(String username) {
		return false;
	}
}
