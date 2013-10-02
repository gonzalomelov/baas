package uy.com.group05.baasadmin.pl.controllers;

import uy.com.group05.baasadmin.common.exceptions.*;
import uy.com.group05.baasadmin.pl.models.UserModel;
import uy.com.group05.baascore.sl.services.impl.UserServices;
import uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.UserRegisterDTO;
import uy.com.group05.baascore.sl.services.soap.UserSoapFacade;

public class UserController {
	
	public boolean registerUser(UserModel userModel)
		throws
			EmailAlreadyRegisteredException,			
			UnhandledRegistrationException {
		
		try {
			
			if(!userModel.getPassword().equals(userModel.getRepeatedPassword()))
				throw new PasswordRepeatedException("Los passwords no coinciden");
			
			UserServices service = new UserServices();
			UserSoapFacade port = service.getUserServicesPort();
			
			UserRegisterDTO u = new UserRegisterDTO();
			u.setEmail(userModel.getEmail());
			u.setLastname(userModel.getLastname());
			u.setName(userModel.getName());
			u.setPassword(userModel.getPassword());
			
			
			port.registerUser(u);
		
			return true;
		}
		catch (EmailAlreadyRegisteredException_Exception e) {
			throw new EmailAlreadyRegisteredException(e.getMessage());
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
