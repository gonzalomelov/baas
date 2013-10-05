package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.entitiesws.UserRegisterDTO;

@WebService
public interface UserServices {
	
	@WebMethod
	List<UserDTO> getUsers();
	
	@WebMethod
	UserDTO registerUser(
			@WebParam(name = "user") UserRegisterDTO u)
			throws
				EmailAlreadyRegisteredException;
	
	@WebMethod
	boolean isUserLoggedIn(
			@WebParam(name = "email") String email)
			throws
				UserNotRegisteredException;
	
	@WebMethod
	boolean validateUser(
			@WebParam(name = "email") String email,
			@WebParam(name = "password") String password)
			throws
				UserNotRegisteredException;
	
	@WebMethod
	UserDTO loginUser(
			@WebParam(name = "email") String email,
			@WebParam(name = "password") String password)
			throws
				UserNotRegisteredException;
	
	@WebMethod
	boolean logoutUser(
			@WebParam(name = "email") String email)
			throws
				UserNotRegisteredException;
}
