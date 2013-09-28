package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baascore.sl.services.UserServicesFacade;

@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface UserSoapFacade extends UserServicesFacade {
	
	@WebMethod
	@Override
	List<User> getUsers();
	
	@WebMethod
	@Override
	User registerUser(User u)
		throws
			UsernameAlreadyRegisteredException,
			EmailAlreadyRegisteredException;
	
	@WebMethod
	@Override
	boolean isUserLoggedIn(String username);
	
	@WebMethod
	@Override
	boolean validateUser(String username, String password);
	
	@WebMethod
	@Override
	User loginUser(String username, String password);
	
	@WebMethod
	@Override
	boolean logoutUser(String username);
}
