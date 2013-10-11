
package uy.com.group05.baascore.bll.ejbs;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;

@Stateless
public class ClientManagement implements ClientManagementLocal {
	
	@Inject
	private ApplicationDao appDao;
	
	@Inject
	private ClientDao clientDao;

	@Override
	public ClientRegistrationDTO register(String apiClientId,
			String apiClientSecret, ClientDTO client) {
		
		Application app = appDao.readByName(client.getAppName());
		if (app == null) {
			//throw new NombreAppAlreadyRegisteredException("Ya existe una aplicacion con ese nombre");
		}
			
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
		
		ClientRegistrationDTO registrationDTO = new ClientRegistrationDTO();
		registrationDTO.setOk(true);
		
		return registrationDTO;
	}

	@Override
	public ClientAuthenticationDTO authenticate(String apiClientId,
			String apiClientSecret, String email, String password,
			String appName) {

		ClientAuthenticationDTO auten = new ClientAuthenticationDTO();
		auten.setAccessToken("accessToken");
		auten.setOk(false);
		auten.setRefreshToken("refreshToken");
		
		Client client = clientDao.readByEmail(email);
		
		if (client != null) {
			auten.setOk(client.getPassword().equals(password));	
		}
		
		return auten;
	} 
	
	
}

