package uy.com.group05.baasadmin.pl.controllers;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baasadmin.common.exceptions.ApplicationException;
import uy.com.group05.baasadmin.common.exceptions.EntityException;
import uy.com.group05.baasadmin.common.exceptions.RoleException;
import uy.com.group05.baasadmin.pl.models.AppModel;
import uy.com.group05.baasadmin.pl.models.Application;
import uy.com.group05.baasadmin.pl.models.Cliente;
import uy.com.group05.baasadmin.pl.models.Entity;
import uy.com.group05.baasadmin.pl.models.Operacion;
import uy.com.group05.baasadmin.pl.models.Rol;
import uy.com.group05.baascore.sl.services.impl.ApplicationServices;
import uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.ApplicationDTO;
import uy.com.group05.baascore.sl.services.soap.ClientDTO;
import uy.com.group05.baascore.sl.services.soap.EntityAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.EntityCollectionAlreadyExistsException_Exception;
import uy.com.group05.baascore.sl.services.soap.EntityDTO;
import uy.com.group05.baascore.sl.services.soap.MongoDBAlreadyExistsException_Exception;
import uy.com.group05.baascore.sl.services.soap.NombreAppAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.RoleAlreadyRegisteredException_Exception;
import uy.com.group05.baascore.sl.services.soap.RoleDTO;
import uy.com.group05.baascore.sl.services.soap.UserCantAccessAppException_Exception;
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

	public Application GetAplication(long id) throws ApplicationException{
		
		ApplicationServices service = new ApplicationServices();
		
		uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
		
		try {
			ApplicationDTO app = port.getApplication(id);
			
			Application response = new Application();
			
			response.setId(id);
			response.setName(app.getName());
			response.setToken(app.getApiClientId());
			
			List<Rol> roles = new ArrayList<Rol>();
			for (RoleDTO rol : app.getRoles()) {
				roles.add(new Rol(rol.getName(), rol.getId()));
			}
			
			response.setRoles(roles);
			
			List<Entity> entidades = new ArrayList<Entity>();
			for (EntityDTO entity : app.getEntities()) {
				Entity e = new Entity();
				e.setId(entity.getId());
				e.setName(entity.getName());
				entidades.add(e);
			}
			
			response.setEntidades(entidades);
			
			List<Cliente> clientes = new ArrayList<Cliente>();
			for (ClientDTO cliente : app.getClients()) {
				Cliente c = new Cliente();
				c.setId(cliente.getId());
				c.setName(cliente.getEmail());
				clientes.add(c);
			}		
			
			response.setClientes(clientes);
			
			return response;
			
			
			
			
		} catch (AppNotRegisteredException_Exception e1) {
			throw new ApplicationException(e1.getMessage());
		}
		
		
	}
	
	
	public List<Rol> getRoles(long appId){
		
		ApplicationServices service = new ApplicationServices();
		
		uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
		
		try {
			List<RoleDTO> rolesDto = port.getRolesApplication(appId);
			List<Rol> roles = new ArrayList<Rol>();
			
			for (RoleDTO roleDto : rolesDto) {
				Rol r = new Rol(roleDto.getName(), roleDto.getId());
				roles.add(r);
			}
			
			return roles;
			
		} catch (AppNotRegisteredException_Exception e) {
			return null;
		}
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
		e.setName("Edit");
		
		//e.setName(nombre);
		return e;
	}
	
	public Cliente getCliente(long clientId){
		
		Cliente e = new Cliente();
		e.setId(clientId);
//		String nombre = "trabajo";
//		if(entityId == 1){
//			nombre = "casas"; 
//		}
//		else if( entityId == 2){
//			nombre = "autos";
//		}
		e.setName("Diego Forlan");
		
		//e.setName(nombre);
		return e;
	}
	
	
	public long addEntity(long userId, long appId, String entityName) throws EntityException{
		ApplicationServices service = new ApplicationServices();
		
		uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
		
		try {
			return port.editEntityApplication(appId, userId, entityName);
		} catch (UserCantAccessAppException_Exception e) {
			throw new EntityException(e.getMessage());
		} catch (AppNotRegisteredException_Exception e) {
			throw new EntityException(e.getMessage());
		} catch (EntityCollectionAlreadyExistsException_Exception e) {
			throw new EntityException(e.getMessage());
		} catch (EntityAlreadyRegisteredException_Exception e) {
			throw new EntityException(e.getMessage());
		}
	}
	
	public long addRole(long userId, long appId, String roleName) throws RoleException{
		
		ApplicationServices service = new ApplicationServices();
		uy.com.group05.baascore.sl.services.soap.ApplicationServices port = service.getApplicationServicesPort();
		
		try {
			return port.editRoleApplication(appId, userId, roleName);
		} catch (RoleAlreadyRegisteredException_Exception e) {
			throw new RoleException(e.getMessage());
		} catch (UserCantAccessAppException_Exception e) {
			throw new RoleException(e.getMessage());
		} catch (AppNotRegisteredException_Exception e) {
			throw new RoleException(e.getMessage());
		}
		
		
		
	}
}
