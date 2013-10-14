package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.entitiesws.UserRegisterDTO;
import uy.com.group05.baascore.sl.services.soap.UserServices;

@WebService(
	endpointInterface="uy.com.group05.baascore.sl.services.soap.UserServices",
	portName="UserServicesPort",
	serviceName="UserServices"
)
public class UserServicesImpl implements UserServices {

	@EJB
	Mapper mapper;
	
	@EJB
	UserManagementLocal userManagementLocal;

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userManagementLocal.getUsers();

		List<UserDTO> result = mapper.getMapper().mapAsList(users, UserDTO.class);
		
		return result;
	};
	
	@Override
	public UserDTO registerUser(UserRegisterDTO userRegisterDTO)
		throws
			EmailAlreadyRegisteredException {
		
		User user = mapper.getMapper().map(userRegisterDTO, User.class);
		
		user = userManagementLocal.registerUser(user);
		
		UserDTO userDTO = mapper.getMapper().map(user, UserDTO.class);
		
		return userDTO; 
	}

	public boolean isUserLoggedIn(String email)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.isUserLoggedIn(email);
	}
	
	@Override
	public boolean validateUser(String email, String password)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.validateUser(email, password);
	}

	@Override
	public UserDTO loginUser(String email, String password)
		throws
			UserNotRegisteredException {
		
		User user = userManagementLocal.loginUser(email, password);
		
		UserDTO userDTO = mapper.getMapper().map(user, UserDTO.class);
		
		return userDTO;
	}

	@Override
	public UserDTO loginUserFacebook(String email, String name, String lastname, String fbId) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setLastname(lastname);
		user.setFbId(fbId);
		
		user = userManagementLocal.loginUserFacebook(user);
		
		UserDTO userDTO = mapper.getMapper().map(user, UserDTO.class);
		
		return userDTO;
	}
	
	@Override
	public boolean logoutUser(String email)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.logoutUser(email);
	}

}
