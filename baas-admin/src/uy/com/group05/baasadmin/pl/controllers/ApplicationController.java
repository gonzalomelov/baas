package uy.com.group05.baasadmin.pl.controllers;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baasadmin.common.exceptions.ApplicationException;
import uy.com.group05.baasadmin.pl.models.AppModel;
import uy.com.group05.baasadmin.pl.models.Application;
import uy.com.group05.baasadmin.pl.models.Cliente;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.Operacion;
import uy.com.group05.baasadmin.pl.models.Rol;
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

	public Application GetAplication(long id){
		
//		ApplicationServices service = new ApplicationServices();
//		
//		uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
//		
	//	port.
		
		Application response = new Application();
		response.setId(id);
		response.setName("gallito");
		
		List<Rol> roles = new ArrayList<Rol>();
		roles.add(new Rol("admin", 1));
		roles.add(new Rol("guest", 2));
		
		response.setRoles(roles);
		
		List<Entity> entidades = new ArrayList<Entity>();
		Entity e = new Entity();
		e.setId(1);
		e.setName("casas");
		entidades.add(e);
		
		e = new Entity();
		e.setId(2);
		e.setName("autos");
		entidades.add(e);
		
		e = new Entity();
		e.setId(3);
		e.setName("trabajo");
		entidades.add(e);
		
		response.setEntidades(entidades);
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Cliente c = new Cliente();
		c.setId(1);
		c.setName("Diego@Forlan.com");
		clientes.add(c);
		
		c = new Cliente();
		c.setId(2);
		c.setName("Luis@Suarez.com");
		clientes.add(c);
		
		c = new Cliente();
		c.setId(3);
		c.setName("Edinson@Cavani.com");
		clientes.add(c);
		
		
		response.setClientes(clientes);
		
		return response;
	}
	
	
	public List<Rol> getRoles(long appId){
		
		//Application response = new Application();
		
		List<Rol> roles = new ArrayList<Rol>();
		roles.add(new Rol("admin", 1));
		roles.add(new Rol("guest", 2));
		roles.add(new Rol("super", 3));
		//response.setRoles(roles);
		
		return roles;
	}
	
	public List<Operacion> getOperaciones(long appId){
		
		List<Operacion> opreaciones = new ArrayList<Operacion>();
		opreaciones.add(new Operacion(1,"DELETE"));
		opreaciones.add(new Operacion(2,"GET"));
		opreaciones.add(new Operacion(3,"POST"));
		opreaciones.add(new Operacion(4,"PUT"));
		
		return opreaciones;
		
	}
	
	public Entity getEntity(long entityId){
		
		Entity e = new Entity();
		e.setId(entityId);
		String nombre = "trabajo";
		if(entityId == 1){
			nombre = "casas"; 
		}
		else if( entityId == 2){
			nombre = "autos";
		}
		e.setName("casas");
		
		e.setName(nombre);
		return e;
	}
}
