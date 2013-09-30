package uy.com.group05.baascore.sl.services.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.dal.dao.EntityNoSqlDao;
import uy.com.group05.baascore.dal.dao.GenericDao;
import uy.com.group05.baascore.dal.dao.jpa.JpaGenericDao;


@Path("/APIRest")
public class APIRest {
	
	@Inject	
	private EntityNoSqlDao _dalInstance;
	
	@GET	
	@Produces("application/json")	
	@Path("{appName}/{entity}/{query}")
	public List<String> Get(
			@PathParam("appName") String appName,
			@PathParam("entity") String entity,
			@PathParam("query") String query) {

	    if(appName == null){
	    	return null;
	    }
 
		if(entity == null){		
			
			return null;			
		}
		

		return _dalInstance.getEntities(appName, entity); 
 
	}
	
	
	
	@POST
	@Path("{appName}/{entity}")
	@Produces(MediaType.APPLICATION_JSON)	
	  public boolean PostApiRest(
			  @PathParam("appName") String appName,
				@PathParam("entity") String entity,				
				String inputJsonObj			  
			  ) throws Exception {
		
		if(entity == null){		
			
			return false;			
		}
	
		try{
	
			
			
			
			_dalInstance.addEntity(appName, entity, inputJsonObj);
			
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	  }
	
}
