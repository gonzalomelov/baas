package uy.com.group05.baascore.sl.services;

import java.util.List;

import uy.com.group05.baascore.common.entities.User;

public interface UserServicesFacade {
	List<User> getUsers();
	
	User registerUser(User u);
	
	boolean isUserLoggedIn(String username);
	
	boolean validateUser(String username, String password);
	
	User loginUser(String username, String password);
	
	boolean logoutUser(String username);
	
}
