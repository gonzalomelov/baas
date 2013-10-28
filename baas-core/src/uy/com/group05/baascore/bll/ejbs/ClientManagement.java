
package uy.com.group05.baascore.bll.ejbs;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.ExternalApplication;
import uy.com.group05.baascore.common.entities.ExternalClient;
import uy.com.group05.baascore.common.entities.Operation;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.RoleNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.UserCantAccessAppException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.ExternalApplicationDao;
import uy.com.group05.baascore.dal.dao.ExternalClientDao;
import uy.com.group05.baascore.dal.dao.OperationDao;
import uy.com.group05.baascore.dal.dao.PermissionDao;
import uy.com.group05.baascore.dal.dao.RoleDao;
import uy.com.group05.baascore.dal.dao.UserDao;
import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;
import uy.com.group05.baascore.sl.entitiesws.PermissionEntityDTO;
import uy.com.group05.baascore.sl.entitiesws.RolesClientDTO;

@Stateless
public class ClientManagement implements ClientManagementLocal {
	
	@Inject
	private ApplicationDao appDao;
	
	@Inject
	private ClientDao clientDao;
	
	@Inject 
	private UserDao userDao;
	
	@Inject
	private EntityDao entityDao;
	
	@Inject
	private RoleDao roleDao;
	
	@Inject
	private OperationDao operationDao;
	
	@Inject
	private PermissionDao permissionDao;

	@Inject
	private ExternalApplicationDao externalAppDao;
	
	@Inject
	private ExternalClientDao externalClientDao;
	
	@Override
	public Client getClient(long appId, long clientId) {
		//App validation
		Application app = appDao.read(appId);
		
		if (app == null) {
			return null;
		}
		
		return clientDao.read(clientId);
	}
	
	@Override
	public ClientRegistrationDTO register(UUID apiClientId, ClientDTO client) {
		
		ClientRegistrationDTO registration = new ClientRegistrationDTO();
		registration.setOk(false);
		
		//App validation
		Application app = appDao.readByName(client.getAppName());
		
		if (app == null) {
			return registration;
		}
		
		if (!app.getApiClientId().equals(apiClientId)) {
			return registration;
		}
		
		//Client
		if (clientDao.readByEmail(client.getEmail()) != null) {
			//throw new EmailAlreadyRegisteredException("Email already registered");
		}
		
		//TODO Mapper en el SL
		Client c = new Client();
		
		c.setApplication(app);
		c.setEmail(client.getEmail());
		c.setLastname(client.getLastname());
		c.setName(client.getName());
		c.setPassword(client.getPassword());
		
		clientDao.create(c);
		
		registration.setOk(true);
		
		return registration;
	}

	@Override
	public ClientAuthenticationDTO authenticate(String appName, UUID apiClientId,
			String email, String password) {

		ClientAuthenticationDTO auten = new ClientAuthenticationDTO();
		auten.setAccessToken(null);
		auten.setOk(false);
		auten.setRefreshToken(null);
		
		//App validation
		Application app = appDao.readByName(appName);
		
		if (app == null) {
			return auten;
		}
		
		if (!app.getApiClientId().equals(apiClientId)) {
			return auten;
		}
		
		//Client validation
		Client client = clientDao.readByEmail(email);
		
		if (client == null) {
			return auten;	
		}
		
		if (!client.getPassword().equals(password)) {
			return auten;
		}
		
		//Ok
		UUID accessToken = UUID.randomUUID();
		UUID refreshToken = UUID.randomUUID();
		
		client.setAccessToken(accessToken);
		client.setRefreshToken(refreshToken);
		
		client = clientDao.update(client);
		
		auten.setAccessToken(accessToken);
		auten.setOk(true);
		auten.setRefreshToken(refreshToken);
		
		return auten;
	}

	@Override
	public boolean validate(String appName, String operation, String entityName, UUID accessToken) {
		
		//App validation
		Application app = appDao.readByName(appName);
		
		if (app == null) {
			return false;
		}
		
		//Client validation
		Client client = clientDao.readByAccessToken(app.getId(), accessToken);
		
		if (client == null) {
			return false;	
		}
		
		Entity entity = entityDao.readByName(app.getId(), entityName); 
		
		if (entity == null) {
			return false;
		}
		
		List<Role> clientRoles = roleDao.readAll(app.getId(), client.getId());
		
		for (Role role : clientRoles) {
			List<Permission> rolePermissions = permissionDao.readAll(app.getId(), role.getId());
			
			for (Permission permission : rolePermissions) {
				if (permission.getApplication().getId() == app.getId() &&
					permission.getEntity().getId() == entity.getId() &&
					permission.getOperation().getName().equals(operation) &&
					permission.getRole().getId() == role.getId()) {
					
					return true;
				}
			}
		}
		
		return false;
	} 

	@Override
	public ClientAuthenticationDTO authenticateExternal(long appId, long externalAppId, UUID apiClientId,
			String email, String password) {
		
		ClientAuthenticationDTO auten = new ClientAuthenticationDTO();
		auten.setAccessToken(null);
		auten.setOk(false);
		auten.setRefreshToken(null);
		
		//App validation
		Application app = appDao.read(appId);
		
		if (app == null) {
			return auten;
		}
		
		if (app.getApiClientId() != apiClientId) {
			return auten;
		}
		
		//External App validation
		ExternalApplication externalApp = externalAppDao.read(externalAppId);
		
		if (externalApp == null) {
			return auten;
		}
		
		//Association between App and External App
		ExternalApplication associatedExternalApp = appDao.readAssociatedExternalApplication(app.getId(), externalAppId);
		
		if (associatedExternalApp == null) {
			return auten;
		}
		
		//Client validation
		
		//########################################################################
		//TODO Llamar al servicio validarUsuarioAplicacionExterna(appId, externalAppId, apiClientId, email, password)
		boolean externalUserValid = true;
		//########################################################################
		
		if (!externalUserValid) {
			return auten;
		}
		
		//Ok
		UUID accessToken = UUID.randomUUID();
		UUID refreshToken = UUID.randomUUID();
		
		ExternalClient externalClient = new ExternalClient();
		externalClient.setAccessToken(accessToken);
		externalClient.setApplication(app);
		externalClient.setRefreshToken(refreshToken);
		
		app.getExternalClients().add(externalClient);
		
		externalClientDao.create(externalClient);
		
		appDao.update(app);
		
		auten.setAccessToken(accessToken);
		auten.setOk(true);
		auten.setRefreshToken(refreshToken);
		
		return auten;
	}

	
	public boolean validateExternal(String appName, String operation, String entityName, UUID accessToken) {
		
		//App validation
		Application app = appDao.readByName(appName);
		
		if (app == null) {
			return false;
		}
		
		//Client validation
		ExternalClient externalClient = externalClientDao.readByAccessToken(app.getId(), accessToken);
		
		if (externalClient == null) {
			return false;	
		}
		
		Entity entity = entityDao.readByName(app.getId(), entityName); 
		
		if (entity == null) {
			return false;
		}
		
		List<Role> externalClientsAppRoles = appDao.readExternalClientsAppRole(app.getId());

		for (Role role : externalClientsAppRoles) {
			List<Permission> rolePermissions = permissionDao.readAll(app.getId(), role.getId());
			
			for (Permission permission : rolePermissions) {
				if (permission.getApplication().getId() == app.getId() &&
					permission.getEntity().getId() == entity.getId() &&
					permission.getOperation().getName() == operation &&
					permission.getRole().getId() == role.getId()) {
					
					return true;
				}
			}	
		}
		
		
		return false;
	}
	
	public Client getClient(long idClient) {
		return clientDao.read(idClient);
	}
	
	public boolean assignRoleToClient(long idApp, long idUser, long idRole, long idClient) 
			throws ClientNotRegisteredException, EntityNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese id");
		List<User> users = app.getUsers();
		if (!users.contains(userDao.read(idUser))) {
			throw new UserCantAccessAppException ("El usuario no es administrador de la aplicacion");
		}
		Role role = roleDao.read(idRole);
		if (role==null || !app.getRoles().contains(role)){
			throw new EntityNotRegisteredException ("No existe un rol con ese id en la app");
		}
		Client client = clientDao.read(idClient);
		if (client==null || !app.getClients().contains(client)){
			throw new ClientNotRegisteredException ("No existe un client con ese id en la app");
		}
		
		if (!client.existsRole(idRole))
			client.addRole(role);
		return true;
	}
	
	public void updateRegIdOfClient(UUID accessToken, long appId, String regId) throws ClientNotRegisteredException, AppNotRegisteredException {
		Application app = appDao.read(appId);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + appId);
		}
		
		Client c = clientDao.readByAccessToken(appId, accessToken);
		if (c == null)
			throw new ClientNotRegisteredException("No existe el cliente con accessToken " + accessToken);
		
		c.setGcm_regId(regId);
		clientDao.update(c);
	}

	public List<Role> getRolesFromClient(long idApp, long idUser, long idClient) throws ClientNotRegisteredException, UserCantAccessAppException, AppNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese id");
		List<User> users = app.getUsers();
		if (!users.contains(userDao.read(idUser))) {
			throw new UserCantAccessAppException ("El usuario no es administrador de la aplicacion");
		}
		Client client = clientDao.read(idClient);
		if (client==null || !app.getClients().contains(client)){
			throw new ClientNotRegisteredException ("No existe un client con ese id en la app");
		}
		
		List<Role> rs=client.getRoles();
		rs.size();
		return rs;
	}
	
	public boolean assignRoleToClients(long idApp, long idUser, long idClient, List<RolesClientDTO> rolesClient) 
			throws AppNotRegisteredException, UserCantAccessAppException, ClientNotRegisteredException{
		
		Application app = appDao.readById(idApp);
		if (app == null)//No existe la app
			throw new AppNotRegisteredException("No existe una aplicacion con ese id");
		List<User> users = app.getUsers();
		if (!users.contains(userDao.read(idUser))) {
			throw new UserCantAccessAppException ("El usuario no es administrador de la aplicacion");
		}
		Client client = clientDao.read(idClient);
		if (client==null || !app.getClients().contains(client)){
			throw new ClientNotRegisteredException ("No existe un client con ese id en la app");
		}
		// Si la lista es vacia no hago nada.
		if (rolesClient == null){
			return true;
		}
		// Asigno los roles al cliente.
		for (RolesClientDTO rc : rolesClient){
			if (rc.isHas()){ //creo si no existe
				
				if(!client.existsRole(rc.getIdRole())) {
					Role role = roleDao.read(rc.getIdRole());
					if(role!=null && role.getApplication().getId()==idApp){
						client.addRole(role);
					}
				}
			}
			else{ //elimino si existe
				if(client.existsRole(rc.getIdRole())){
					//Elimino el permiso, Rol.Permissions, Permission, Entity.Permissions
					Role role = roleDao.read(rc.getIdRole());
					if(role!=null && role.getApplication().getId()==idApp){
						client.removeRole(role);
					}
				}
			
			}
		}	
		return true;
	}
	
	public Client getClientWithAccessToken(UUID accessToken, long appId)
			throws	ClientNotRegisteredException {
		
		Client c = clientDao.readByAccessToken(appId, accessToken);
		if (c == null) {
			throw new ClientNotRegisteredException("No existe el cliente con access token " + accessToken);
		}
		
		return c;		
	}

	@Override
	public boolean existsClient(long clientId) {
		if (clientDao.read(clientId) != null) {
			return true;
		}
		else {
			return false;
		}
	}
}

