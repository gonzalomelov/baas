package uy.com.group05.baascore.bll.ejbs.interfaces;

import javax.ejb.Local;

import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ClientAuthenticationDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.ClientRegistrationDTO;

@Local
public interface ClientManagementLocal {
	public ClientRegistrationDTO register(
			String apiClientId,
			String apiClientSecret,
			ClientDTO client);

	public ClientAuthenticationDTO authenticate(
			String apiClientId,
			String apiClientSecret,
			String email,
			String password,
			String appName);
}
