package uy.com.group05.baasadmin.pl.controllers;

import uy.com.group05.baasadmin.common.exceptions.*;
import uy.com.group05.baasadmin.pl.models.UserModel;
import uy.com.group05.baascore.sl.services.impl.UserServices;
import uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.UserDTO;
import uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.UserRegisterDTO;
import uy.com.group05.baascore.sl.services.impl.UserServices;

public class UserController {
	
	public long registerUser(UserModel userModel)
		throws
			EmailAlreadyRegisteredException,			
			UnhandledRegistrationException, PasswordRepeatedException {
		
		
		
		try {
			
			if(!userModel.getPassword().equals(userModel.getRepeatedPassword()))
				throw new PasswordRepeatedException("Los passwords no coinciden");
			
			if(userModel.getPassword() == null)
				throw new PasswordRepeatedException("El password no puede ser vacio.");
			
			UserServices service = new UserServices();
			uy.com.group05.baascore.sl.services.soap.UserServices port = service.getUserServicesPort();
			
			UserRegisterDTO u = new UserRegisterDTO();
			u.setEmail(userModel.getEmail());
			u.setLastname(userModel.getLastname());
			u.setName(userModel.getName());
			u.setPassword(userModel.getPassword());
			
			
			UserDTO dto = port.registerUser(u);
			
			
		
			return dto.getId();
		}
		catch (EmailAlreadyRegisteredException_Exception e) {
			throw new EmailAlreadyRegisteredException(e.getMessage());
		}	
		catch (PasswordRepeatedException e) {
			throw new EmailAlreadyRegisteredException(e.getMessage());
		}
		catch (Exception e) {
			throw new UnhandledRegistrationException("Registro no exitoso");
		}
	}
	
	public UserModel loginUser(String username, String password) throws LoginException,UnhandledRegistrationException {

		try {
			
			if(username == null || password == null)
				throw new LoginException("Los datos ingresados no son correctos.");
			
			UserServices service = new UserServices();
			uy.com.group05.baascore.sl.services.soap.UserServices port = service.getUserServicesPort();
			
			UserDTO datosUsuario = port.loginUser(username, password);
			
			UserModel retorno = new UserModel();
			
			retorno.setEmail(datosUsuario.getEmail());
			retorno.setLastname(datosUsuario.getLastname());
			retorno.setName(datosUsuario.getName());
			retorno.setUserId(datosUsuario.getId());
			
			
		
			return retorno;
		}
		catch (UserNotRegisteredException_Exception e) {
			throw new LoginException(e.getMessage());
		}	
		catch (LoginException e) {
			throw new LoginException(e.getMessage());
		}
		catch (Exception e) {
			throw new UnhandledRegistrationException("Login no exitoso");
		}
	}
	
	public boolean logoutUser(String email) {
		UserServices service = new UserServices();
		uy.com.group05.baascore.sl.services.soap.UserServices port = service.getUserServicesPort();
		
		 try {
			return port.logoutUser(email);
		} catch (UserNotRegisteredException_Exception e) {
			return false;
		}
	}
	
	public boolean validateUser(String username, String password) {
		return false;
	}
	
	public boolean isUserLoggedIn(String username) {
		return false;
	}
}
