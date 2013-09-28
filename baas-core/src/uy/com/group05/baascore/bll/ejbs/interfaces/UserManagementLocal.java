package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;

@Local
public interface UserManagementLocal {
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
