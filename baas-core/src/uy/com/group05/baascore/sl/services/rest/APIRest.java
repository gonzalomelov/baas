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
	@Path("/entities/{appName}/{entity}/{query}")
	@Produces(MediaType.APPLICATION_JSON)	
	public String get(
			//@HeaderParam("accessToken") UUID accessToken,
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			@PathParam("query") String query);
	
	@POST
	@Path("/entities/{appName}/{entity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public boolean post(
			//@HeaderParam("accessToken") UUID accessToken,
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			String jsonObj);
	
}