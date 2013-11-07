package uy.com.group05.baascore.sl.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.group05.baascore.sl.entitiesws.ApplicationEntity;
import uy.com.group05.baascore.sl.entitiesws.ClientEntity;




@Path("/integracion")
public interface IntegracionRest {
	
	@GET	
	@Path("/aplicaciones")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<ApplicationEntity> getAplicaciones();
	
	@GET	
	@Path("/authenticate/{idApp}/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)	
	public ClientEntity loguearUsuarioFinalApp(	
			@PathParam("idApp") long idApp,
			@PathParam("email") String email,
			@PathParam("password") String password
			);

}


