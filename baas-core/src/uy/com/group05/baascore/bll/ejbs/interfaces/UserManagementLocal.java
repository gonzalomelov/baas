package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.User;

@Local
public interface UserManagementLocal {
	List<User> getUsers();
	User registerUser(User u);
	boolean isUserLoggedIn(String username);
	boolean validateUser(String username, String password);
	User loginUser(String username, String password);
	boolean logoutUser(String username);
}
