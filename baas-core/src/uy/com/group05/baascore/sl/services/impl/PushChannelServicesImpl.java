package uy.com.group05.baascore.sl.services.impl;

import javax.inject.Inject;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.sl.services.soap.PushChannelServices;

@WebService(
	endpointInterface="uy.com.group05.baascore.sl.services.soap.PushChannelServices",
	portName="PushServicesPort",
	serviceName="PushServices"
)
public class PushChannelServicesImpl implements PushChannelServices{
	
	@Inject
	PushChannelManagementLocal pushChannelManagementLocal;

	@Override
	public long createPushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException,
			PushChanAlreadyRegisteredException {
		
		return pushChannelManagementLocal.createPushChannel(nombreApp, nombreCanal);		
	}


	@Override
	public boolean existsPushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException {
		
		return pushChannelManagementLocal.existsPushChannel(nombreApp, nombreCanal);		
	}


	@Override
	public boolean assignClientToPushChannel(String nombreApp,
			String nombreCanal, String mailCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		return pushChannelManagementLocal.assignClientToPushChannel(nombreApp, nombreCanal, mailCliente);
	}


	@Override
	public boolean unassignClientFromPushChannel(String nombreApp,
			String nombreCanal, String mailCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		return pushChannelManagementLocal.unassignClientFromPushChannel(nombreApp, nombreCanal, mailCliente);
	}


	@Override
	public boolean sendNotificationToPushChannel(String nombreApp,
			String nombreCanal, String mensaje) throws AppNotRegisteredException,
			PushChanNotRegisteredException {
		
		return pushChannelManagementLocal.sendNotificationToPushChannel(nombreApp, nombreCanal, mensaje);
	}
}
