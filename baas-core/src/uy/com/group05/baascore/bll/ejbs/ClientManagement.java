package uy.com.group05.baascore.bll.ejbs;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ClientDao;

@Stateless
public class ClientManagement implements ClientManagementLocal {
	
	@Inject
	private ClientDao clientDao; 
	
	@Override
	public boolean validateClientCredentials(String email, String password)
		throws ClientNotRegisteredException
	{
		Client client = clientDao.readByEmail(email);
		
		if (client == null) {
			throw new ClientNotRegisteredException("No existe el cliente registrado");
		}
		
		return client.getPassword().equals(password);
	}
}
