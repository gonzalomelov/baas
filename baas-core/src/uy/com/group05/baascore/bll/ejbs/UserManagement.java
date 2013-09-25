package uy.com.group05.baascore.bll.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.dal.dao.jpa.JpaUserDao;

@Stateless
public class UserManagement implements UserManagementLocal {
	
	@Inject
	private JpaUserDao userDao;
	
	@Override
	public List<User> getUsers() {
		return (List<User>) userDao.readAll();
	}
	
	@Override
	public User registerUser(User user) {
		return userDao.create(user);
	}
	
	@Override
	public boolean isUserLoggedIn(String username) {
		User user = userDao.readByUsername(username);
		return user != null && user.isLoggedIn();
	}
	
	@Override
	public boolean validateUser(String username, String password) {
		User user = userDao.readByUsername(username);
		return user != null && user.getPassword().equals(password);
	}
	
	@Override
	public User loginUser(String username, String password) {
		User user = userDao.readByUsername(username);
		
		if (user == null || !user.getPassword().equals(password) || user.isLoggedIn()) {
			return null;
		}
		
		user.setLoggedIn(true);
		userDao.update(user);
		
		return user;
	}
	
	@Override
	public boolean logoutUser(String username) {
		User user = userDao.readByUsername(username);
		
		if (user == null || !user.isLoggedIn()) {
			return false;
		}
		
		user.setLoggedIn(false);
		userDao.update(user);
		
		return true;
	}
}
