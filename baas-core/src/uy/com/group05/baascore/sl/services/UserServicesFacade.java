package uy.com.group05.baascore.sl.services;

import java.util.List;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;

public interface UserServicesFacade {
	List<User> getUsers();
	
	User registerUser(User u)
		throws
			UsernameAlreadyRegisteredException,
			EmailAlreadyRegisteredException;
	
	boolean isUserLoggedIn(String username)
		throws
			UserNotRegisteredException;
	
	boolean validateUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	User loginUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	boolean logoutUser(String username)
		throws
			UserNotRegisteredException;
	
}
