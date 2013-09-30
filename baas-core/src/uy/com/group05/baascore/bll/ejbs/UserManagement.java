package uy.com.group05.baascore.bll.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baascore.dal.dao.UserDao;

@Stateless
public class UserManagement implements UserManagementLocal {
	
	@Inject
	private UserDao userDao;
	
	@Override
	public List<User> getUsers() {
		return (List<User>) userDao.readAll();
	}
	
	@Override
	public User registerUser(User user)
		throws
			UsernameAlreadyRegisteredException,
			EmailAlreadyRegisteredException {
		
		if (userDao.readByUsername(user.getUsername()) != null) {
			throw new UsernameAlreadyRegisteredException("Username already registered");
		}
		
		if (userDao.readByEmail(user.getEmail()) != null) {
			throw new EmailAlreadyRegisteredException("Email already registered");
		}
		
		return userDao.create(user);
	}
	
	@Override
	public boolean isUserLoggedIn(String username)
		throws
			UserNotRegisteredException {
		
		User user = userDao.readByUsername(username);
		
		if (user == null) {
			throw new UserNotRegisteredException("Usuario no registrado");
		}
		
		return user.isLoggedIn();
	}
	
	@Override
	public boolean validateUser(String username, String password)
		throws
			UserNotRegisteredException {
		
		User user = userDao.readByUsername(username);
		
		if (user == null) {
			throw new UserNotRegisteredException("Usuario no registrado");
		}
		
		return user.getPassword().equals(password);
	}
	
	@Override
	public User loginUser(String username, String password)
		throws
			UserNotRegisteredException {
		
		User user = userDao.readByUsername(username);
		
		if (user == null) {
			throw new UserNotRegisteredException("Usuario no registrado");
		}
		
		if (!user.getPassword().equals(password) || user.isLoggedIn()) {
			return null;
		}
		
		user.setLoggedIn(true);
		userDao.update(user);
		
		return user;
	}
	
	@Override
	public boolean logoutUser(String username)
		throws
			UserNotRegisteredException {
		
		User user = userDao.readByUsername(username);
		
		if (user == null) {
			throw new UserNotRegisteredException("Usuario no registrado");
		}
		
		if (!user.isLoggedIn()) {
			return false;
		}
		
		user.setLoggedIn(false);
		userDao.update(user);
		
		return true;
	}
}
