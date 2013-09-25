package uy.com.group05.baasadmin.pl.controllers;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import uy.com.group05.baasadmin.pl.models.UserModel;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.IsUserLoggedIn;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.IsUserLoggedInResponse;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.LoginUser;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.LoginUserResponse;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.LogoutUser;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.LogoutUserResponse;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.RegisterUser;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.RegisterUserResponse;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.User;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.ValidateUser;
import uy.com.group05.baasadmin.pl.services.UserSoapFacadeStub.ValidateUserResponse;

public class UserController {
	
	public boolean registerUser(UserModel userModel) {
		
		UserSoapFacadeStub stub;
		
		try {
			stub = new UserSoapFacadeStub();
			
			RegisterUser request = new RegisterUser();
			
			User param = new User();
			param.setEmail(userModel.getEmail());
			param.setLastname(userModel.getLastname());
			param.setName(userModel.getName());
			param.setPassword(userModel.getPassword());
			param.setUsername(userModel.getUsername());
			
			request.setUser(param);
			
			RegisterUserResponse response =  stub.registerUser(request);
			
			User user = response.get_return();
					
			return user != null;
			
		}
		catch (AxisFault e) {
			return false;
		}
		catch (RemoteException e) {
			return false;
		}
		
	}
	
	public UserModel loginUser(String username, String password) {
		UserSoapFacadeStub stub;
		
		try {
			stub = new UserSoapFacadeStub();
			
			LoginUser request = new LoginUser();
			
			request.setUsername(username);
			request.setPassword(password);
			
			LoginUserResponse response =  stub.loginUser(request);
			
			User user = response.get_return();
			
			if (user == null) {
				return null;
			}
			
			UserModel ret = new UserModel();
			ret.setEmail(user.getEmail());
			ret.setLastname(user.getLastname());
			ret.setName(user.getName());
			ret.setUsername(user.getUsername());
			
			return ret;
			
		}
		catch (AxisFault e) {
			return null;
		}
		catch (RemoteException e) {
			return null;
		}
	}
	
	public boolean logoutUser(String username) {
		UserSoapFacadeStub stub;
		
		try {
			stub = new UserSoapFacadeStub();
			
			LogoutUser request = new LogoutUser();
			
			request.setUsername(username);
			
			LogoutUserResponse response =  stub.logoutUser(request);
			
			boolean logoutOk = response.get_return();
					
			return logoutOk;
			
		}
		catch (AxisFault e) {
			return false;
		}
		catch (RemoteException e) {
			return false;
		}
	}
	
	public boolean validateUser(String username, String password) {
		UserSoapFacadeStub stub;
		
		try {
			stub = new UserSoapFacadeStub();
			
			ValidateUser request = new ValidateUser();
			
			request.setUsername(username);
			request.setPassword(password);
			
			ValidateUserResponse response =  stub.validateUser(request);
			
			boolean validationOk = response.get_return();
					
			return validationOk;
			
		}
		catch (AxisFault e) {
			return false;
		}
		catch (RemoteException e) {
			return false;
		}
	}
	
	public boolean isUserLoggedIn(String username) {
		UserSoapFacadeStub stub;
		
		try {
			stub = new UserSoapFacadeStub();
			
			IsUserLoggedIn request = new IsUserLoggedIn();
			
			request.setUsername(username);
			
			IsUserLoggedInResponse response =  stub.isUserLoggedIn(request);
			
			boolean logoutOk = response.get_return();
					
			return logoutOk;
			
		}
		catch (AxisFault e) {
			return false;
		}
		catch (RemoteException e) {
			return false;
		}
	}
}
