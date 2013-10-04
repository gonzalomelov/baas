package uy.com.group05.baascore.sl.services.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;

@Path("/api")
public class APIRest {
	@Inject
	private APIManagementLocal apiManagementLocal;

	@GET
	public String helloWorld(){
		System.out.println("Hello");
		return "Hello World";
	}
	
	@GET	
	@Path("{appName}/{entity}/{query}")
	@Produces(MediaType.APPLICATION_JSON)	
	public List<String> get(
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			@PathParam("query") String query) {

	    return apiManagementLocal.get(appName, entity, query);
	}
	
	@POST
	@Path("{appName}/{entity}")
	@Produces(MediaType.APPLICATION_JSON)	
	public boolean post(
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,				
			String jsonObj) {

		return apiManagementLocal.post(appName, entity, jsonObj);
	}
}