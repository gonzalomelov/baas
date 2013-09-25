package uy.com.group05.baascore.sl.services;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.sl.services.rest.UserRestFacade;
import uy.com.group05.baascore.sl.services.soap.UserSoapFacade;

public class UserServices implements UserRestFacade, UserSoapFacade {

	private UserManagementLocal userManagementLocal = lookupUserManagementLocalBean();

	private UserManagementLocal lookupUserManagementLocalBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UserManagementLocal) c.lookup("java:global/baas-core-ear/baas-core/UserManagement!uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
    }

	@Override
	public List<User> getUsers() {
		return userManagementLocal.getUsers();
	};
	
	@Override
	public User registerUser(User user) {
		return userManagementLocal.registerUser(user);
	}

	@Override
	public boolean isUserLoggedIn(String username) {
		return userManagementLocal.isUserLoggedIn(username);
	}
	
	@Override
	public boolean validateUser(String username, String password) {
		return userManagementLocal.validateUser(username, password);
	}

	@Override
	public User loginUser(String username, String password) {
		return userManagementLocal.loginUser(username, password);
	}

	@Override
	public boolean logoutUser(String username) {
		return userManagementLocal.logoutUser(username);
	}

}
