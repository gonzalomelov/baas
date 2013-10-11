package uy.com.group05.baascore.sl.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public interface APIRest {
	
	@GET	
	@Path("/entities/{appName}/{entity}")
	@Produces(MediaType.APPLICATION_JSON)	
	public String get(
			@PathParam("appName") String appName,
			@PathParam("entity") String entity);
	
	@POST
	@Path("/entities/{appName}/{entity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public boolean post(
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			String jsonObj);
	
}