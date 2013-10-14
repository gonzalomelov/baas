package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;

@Local
public interface UserManagementLocal {
	List<User> getUsers();
	
	User registerUser(User u)
		throws
			EmailAlreadyRegisteredException;
	
	boolean isUserLoggedIn(String email)
		throws
			UserNotRegisteredException;
	
	boolean validateUser(String email, String password)
		throws
			UserNotRegisteredException;
	
	User loginUser(String email, String password)
		throws
			UserNotRegisteredException;
	
	User loginUserFacebook(User user);
	
	boolean logoutUser(String email)
		throws
			UserNotRegisteredException;
}
