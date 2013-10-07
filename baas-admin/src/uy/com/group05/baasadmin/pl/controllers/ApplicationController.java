package uy.com.group05.baasadmin.pl.controllers;

import java.util.List;

import uy.com.group05.baasadmin.common.exceptions.ApplicationException;
import uy.com.group05.baasadmin.pl.models.AppModel;
import uy.com.group05.baasadmin.pl.models.Application;
import uy.com.group05.baascore.sl.services.impl.ApplicationServices;
import uy.com.group05.baascore.sl.services.soap.ApplicationDTO;
import uy.com.group05.baascore.sl.services.soap.EntityCollectionAlreadyExistsException_Exception;
import uy.com.group05.baascore.sl.services.soap.MongoDBAlreadyExistsException_Exception;
import uy.com.group05.baascore.sl.services.soap.NombreAppAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.UserNotRegisteredException_Exception;

public class ApplicationController {

	public AppModel GetAplicaciones(long UserId) throws ApplicationException {

		
				
		try {
			
			
			
			AppModel model = new AppModel();
			
			ApplicationServices service = new ApplicationServices();
			
			uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
			
			List<ApplicationDTO> lista = port.listApplications(UserId);
			
			
			
			for (ApplicationDTO app : lista) {
				Application a = new Application(app.getName(), app.getId());
				model.getAplicaciones().add(a);				
				
			}
			
			return model;
			
		} catch (UserNotRegisteredException_Exception e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e.getMessage());
		}
		
		

	}

	public void CreateApplication(long UserId, String appName, List<String> roles, List<String> entities) throws ApplicationException{
		
		ApplicationServices service = new ApplicationServices();
		
		uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
		
		try {
			port.createApplication(UserId, appName, roles, entities);
		} catch (NombreAppAlreadyRegisteredException_Exception e) {
			throw new ApplicationException(e.getMessage());
		} catch (MongoDBAlreadyExistsException_Exception e) {
			throw new ApplicationException(e.getMessage());
		} catch (EntityCollectionAlreadyExistsException_Exception e) {
			throw new ApplicationException(e.getMessage());
		} catch (UserNotRegisteredException_Exception e) {
			throw new ApplicationException(e.getMessage());
		}
		
		
		
	}

}
