package uy.com.group05.baascore.sl.services;

import java.util.List;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;

public interface UserServicesFacade {
	List<User> getUsers();
	
	User registerUser(User u)
		throws
			UsernameAlreadyRegisteredException,
			EmailAlreadyRegisteredException;
	
	boolean isUserLoggedIn(String username);
	
	boolean validateUser(String username, String password);
	
	User loginUser(String username, String password);
	
	boolean logoutUser(String username);
	
}
