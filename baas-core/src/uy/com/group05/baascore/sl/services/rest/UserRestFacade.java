package uy.com.group05.baascore.sl.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.EmailAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UsernameAlreadyRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.UserDTO;
import uy.com.group05.baascore.sl.services.UserServicesFacade;

@Path("/users")
public interface UserRestFacade extends UserServicesFacade {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<UserDTO> getUsers(); 
	
	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public User registerUser(User user)
		throws
			UsernameAlreadyRegisteredException,
			EmailAlreadyRegisteredException;
	
	@Path("/userLoggedIn")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Override
	public boolean isUserLoggedIn(
			@FormParam("username") String username)
				throws
					UserNotRegisteredException;
	
	@Path("/validate")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Override
	public boolean validateUser(
			@FormParam("username") String username,
			@FormParam("password") String password)
				throws
					UserNotRegisteredException;
	
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Override
	public User loginUser(
			@FormParam("username") String username,
			@FormParam("password") String password)
				throws
					UserNotRegisteredException;
	
	@Path("/logout")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Override
	public boolean logoutUser(
			@FormParam("username") String username)
				throws
					UserNotRegisteredException;
	
}
