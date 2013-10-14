package uy.com.group05.baascore.sl.services.rest;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;

@Path("/client")
public interface ClientRest {

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ClientRegistrationDTO register(
			@HeaderParam("apiClientId") UUID apiClientId,
			ClientDTO client);

	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ClientAuthenticationDTO authenticate(
			@HeaderParam("apiClientId") UUID apiClientId,
			@FormParam("appName") String appName,
			@FormParam("email") String email,
			@FormParam("password") String password);
}
