package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
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
	UserDTO registerUser(UserRegisterDTO u)
		throws
			EmailAlreadyRegisteredException;
	
	@WebMethod
	boolean isUserLoggedIn(String username)
		throws
			UserNotRegisteredException;
	
	@WebMethod
	boolean validateUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	@WebMethod
	UserDTO loginUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	@WebMethod
	boolean logoutUser(String username)
		throws
			UserNotRegisteredException;
}
