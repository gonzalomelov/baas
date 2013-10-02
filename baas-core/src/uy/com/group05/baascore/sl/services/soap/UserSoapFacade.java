package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.entitiesws.UserRegisterDTO;
import uy.com.group05.baascore.sl.services.UserServicesFacade;

@WebService
public interface UserSoapFacade extends UserServicesFacade {
	
	@WebMethod
	@Override
	List<UserDTO> getUsers();
	
	@WebMethod
	@Override
	UserDTO registerUser(UserRegisterDTO u)
		throws
			EmailAlreadyRegisteredException;
	
	@WebMethod
	@Override
	boolean isUserLoggedIn(String username)
		throws
			UserNotRegisteredException;
	
	@WebMethod
	@Override
	boolean validateUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	@WebMethod
	@Override
	UserDTO loginUser(String username, String password)
		throws
			UserNotRegisteredException;
	
	@WebMethod
	@Override
	boolean logoutUser(String username)
		throws
			UserNotRegisteredException;
}
