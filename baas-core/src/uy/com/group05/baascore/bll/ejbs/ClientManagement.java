
package uy.com.group05.baascore.bll.ejbs;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.OperationDao;
import uy.com.group05.baascore.dal.dao.PermissionDao;
import uy.com.group05.baascore.dal.dao.RoleDao;
import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;

@Stateless
public class ClientManagement implements ClientManagementLocal {
	
	@Inject
	private ApplicationDao appDao;
	
	@Inject
	private ClientDao clientDao;
	
	@Inject
	private EntityDao entityDao;
	
	@Inject
	private RoleDao roleDao;
	
	@Inject
	private OperationDao operationDao;
	
	@Inject
	private PermissionDao permissionDao;

	@Override
	public ClientRegistrationDTO register(String apiClientId,
			String apiClientSecret, ClientDTO client) {
		
		ClientRegistrationDTO registration = new ClientRegistrationDTO();
		registration.setOk(false);
		
		//App validation
		Application app = appDao.readByName(client.getAppName());
		
		if (app == null) {
			return registration;
		}
		
		if (!app.getApiClientId().equals(apiClientId) ||
			!app.getApiClientSecret().equals(apiClientSecret)) {
			
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
	public ClientAuthenticationDTO authenticate(String appName, String apiClientId,
			String apiClientSecret, String email, String password) {

		ClientAuthenticationDTO auten = new ClientAuthenticationDTO();
		auten.setAccessToken("");
		auten.setOk(false);
		auten.setRefreshToken("");
		
		//App validation
		Application app = appDao.readByName(appName);
		
		if (app == null) {
			return auten;
		}
		
		if (!app.getApiClientId().equals(apiClientId) ||
			!app.getApiClientSecret().equals(apiClientSecret)) {
			
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
		String accessToken = UUID.randomUUID().toString();
		String refreshToken = UUID.randomUUID().toString();
		
		client.setAccessToken(accessToken);
		client.setRefreshToken(refreshToken);
		
		client = clientDao.update(client);
		
		auten.setAccessToken(accessToken);
		auten.setOk(true);
		auten.setRefreshToken(refreshToken);
		
		return auten;
	}

	@Override
	public boolean validate(String appName, String operation, String entityName, String accessToken) {
		
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
					permission.getOperation().getName() == operation &&
					permission.getRole().getId() == role.getId()) {
					
					return true;
				}
			}
		}
		
		return false;
	} 
}

