package uy.com.group05.baascore.sl.services.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.PushChannelDTO;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelDTO;

@WebService
public interface PushChannelServices {	
	@WebMethod
	public List<SimplePushChannelDTO> getPushChannelsOfApplication(long idApp)
			throws AppNotRegisteredException;
	
	@WebMethod
	public long createPushChannel(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException;
	
	@WebMethod
	public long deletePushChannel(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	@WebMethod
	public boolean existsPushChannelApplication(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "nombreCanal") String nombre)
			throws
			AppNotRegisteredException;
	
	@WebMethod
	public boolean existsPushChannel(
			@WebParam(name = "idCanal") long idCanal);	
	
	@WebMethod
	public boolean assignClientToPushChannel(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal,
			@WebParam(name = "idCliente") long idCliente)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				ClientNotRegisteredException;
	
	@WebMethod
	public boolean unassignClientFromPushChannel(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal,
			@WebParam(name = "idCliente") long idCliente)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				ClientNotRegisteredException;
	
	@WebMethod
	public boolean assignEntityToPushChannel (
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal,
			@WebParam(name = "idEntity") long idEntity)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				EntityNotRegisteredException;
	
	@WebMethod
	public List<ClientDTO> getClientsFromPushChannel(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	@WebMethod
	public boolean sendNotificationToPushChannel(
			@WebParam(name = "idApp") long idApp,
			@WebParam(name = "idCanal") long idCanal,
			@WebParam(name = "mensaje") String mensaje)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
}
