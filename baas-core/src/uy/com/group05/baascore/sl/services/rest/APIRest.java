package uy.com.group05.baascore.sl.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
public interface APIRest {
	
	@GET	
	@Path("/entities/{appName}/{entity}")
	@Produces(MediaType.APPLICATION_JSON)	
	public String get(
			//@HeaderParam("accessToken") UUID accessToken,
			@PathParam("appName") String appName,
			@PathParam("entity") String entity);
	
	@GET	
	@Path("/entities/{appName}/{entity}/{query}")
	@Produces(MediaType.APPLICATION_JSON)	
	public String getQuery(
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
	
	@DELETE
	@Path("/entities/{appName}/{entity}/{query}")
	public boolean delete(
			//@HeaderParam("accessToken") UUID accessToken,
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			@PathParam("query") String query);
	
	@PUT
	@Path("/entities/{appName}/{entity}/{query}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public boolean put(
			//@HeaderParam("accessToken") UUID accessToken,
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			@PathParam("query") String query,
			String jsonObj);
	
	@POST
	@Path("/sync/{appName}/{entity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public String sync(
			//@HeaderParam("accessToken") UUID accessToken,
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			String jsonObjs);
	
	@GET
	@Path("/listEntities/{appName}")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<String> getEntities(
			@PathParam("appName") String appName);
	
}