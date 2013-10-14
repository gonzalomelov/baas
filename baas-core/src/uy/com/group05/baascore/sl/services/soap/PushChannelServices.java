package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;

@WebService
public interface PushChannelServices {	
	@WebMethod
	public long createPushChannel(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal)
			throws
				AppNotRegisteredException,
				PushChanAlreadyRegisteredException;
	
	@WebMethod
	public boolean existsPushChannel(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombre)
			throws
			AppNotRegisteredException;
	
	@WebMethod
	public boolean assignClientToPushChannel(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal,
			@WebParam(name = "idCliente") String mailCliente)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				ClientNotRegisteredException;
	
	@WebMethod
	public boolean unassignClientFromPushChannel(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal,
			@WebParam(name = "idCliente") String mailCliente)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				ClientNotRegisteredException;
	
	@WebMethod
	public boolean sendNotificationToPushChannel(
			@WebParam(name = "nombreApp") String nombreApp,
			@WebParam(name = "nombreCanal") String nombreCanal,
			@WebParam(name = "mensaje") String mensaje)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
}
