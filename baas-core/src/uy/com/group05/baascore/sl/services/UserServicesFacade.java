package uy.com.group05.baascore.sl.services;

import java.util.List;

import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.entitiesws.UserRegisterDTO;

public interface UserServicesFacade {
	List<UserDTO> getUsers();
	
	UserDTO registerUser(UserRegisterDTO u)
		throws
			EmailAlreadyRegisteredException;
	
	boolean isUserLoggedIn(String username)
		throws
			UserNotRegisteredException;
	
	boolean validateUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	UserDTO loginUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	boolean logoutUser(String username)
		throws
			UserNotRegisteredException;
	
}
