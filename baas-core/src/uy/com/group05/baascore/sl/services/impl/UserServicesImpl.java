package uy.com.group05.baascore.sl.services.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.services.rest.UserRestFacade;
import uy.com.group05.baascore.sl.services.soap.UserSoapFacade;

@WebService(
	endpointInterface="uy.com.group05.baascore.sl.services.soap.UserSoapFacade",
	portName="UserServicesPort",
	serviceName="UserServices"
)
public class UserServicesImpl implements UserRestFacade, UserSoapFacade {

	private UserManagementLocal userManagementLocal;
	
	@PostConstruct
	public void init() {
		try {
            javax.naming.Context c = new InitialContext();
            userManagementLocal = (UserManagementLocal) c.lookup("java:global/baas-core-ear/baas-core/UserManagement!uy.com.group05.baascore.bll.ejbs.interfaces.UserManagementLocal");
        } catch (NamingException ne) {
            throw new RuntimeException(ne);
        }
	}

	@Override
	public List<UserDTO> getUsers() {
		
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory.classMap(User.class, UserDTO.class)
			//.exclude("password")
			//.exclude("applications")
			.byDefault()
			.register();
		
		List<User> users = userManagementLocal.getUsers();
		
		MapperFacade mapper = mapperFactory.getMapperFacade();
		
		List<UserDTO> result = mapper.map(users, List.class);
		
		System.out.println("fin");
		
		return result;
	};
	
	@Override
	public User registerUser(User user)
		throws
			UsernameAlreadyRegisteredException,
			EmailAlreadyRegisteredException {
		
		
		return userManagementLocal.registerUser(user);
	
	}

	@Override
	public boolean isUserLoggedIn(String username)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.isUserLoggedIn(username);
	}
	
	@Override
	public boolean validateUser(String username, String password)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.validateUser(username, password);
	}

	@Override
	public User loginUser(String username, String password)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.loginUser(username, password);
	}

	@Override
	public boolean logoutUser(String username)
		throws
			UserNotRegisteredException {
		
		return userManagementLocal.logoutUser(username);
	}

}
