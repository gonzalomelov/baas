package uy.com.group05.baasadmin.pl.controllers;

import java.net.MalformedURLException;
import java.net.URL;

import uy.com.group05.baasadmin.common.exceptions.*;
import uy.com.group05.baasadmin.common.utils.PropertyHandler;
import uy.com.group05.baasadmin.pl.models.UserModel;
import uy.com.group05.baascore.sl.services.impl.ApplicationServices;
import uy.com.group05.baascore.sl.services.impl.UserServices;
import uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.UserDTO;
import uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.UserRegisterDTO;
import uy.com.group05.baascore.sl.services.impl.UserServices;

public class UserController {
	
	private UserServices service;
	
	public UserController() {
		PropertyHandler propertyHandler = new PropertyHandler();
		String wsdlHostLocation = propertyHandler.getProperty("wsdlHostLocation");
		URL url = null;
		try {
			url = new URL(wsdlHostLocation + "/UserServices?wsdl");
		} catch (MalformedURLException e) {}
		
		this.service = new UserServices(url);
	}
	
	public long registerUser(UserModel userModel)
		throws
			EmailAlreadyRegisteredException,			
			UnhandledRegistrationException, PasswordRepeatedException {
		
		try {
			
			if(!userModel.getPassword().equals(userModel.getRepeatedPassword()))
				throw new PasswordRepeatedException("Los passwords no coinciden");
			
			if(userModel.getPassword() == null)
				throw new PasswordRepeatedException("El password no puede ser vacio.");
			
			
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
	
	public UserModel loginFacebook(String email, String name, String lastname, String fbID){
		
		uy.com.group05.baascore.sl.services.soap.UserServices port = service.getUserServicesPort();
		
		UserDTO userResponse = port.loginUserFacebook(email, name, lastname, fbID);
		
		
		UserModel retorno = new UserModel();
		
		retorno.setEmail(userResponse.getEmail());
		retorno.setLastname(userResponse.getLastname());
		retorno.setName(userResponse.getName());
		retorno.setUserId(userResponse.getId());
		
		return retorno;
		
	}
}
