package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.group05.baascore.sl.entitiesws.ClientDTO;

@WebService
public interface ClientServices {
	@WebMethod
	public ClientDTO getClient(long appId, long clientId);
}
