package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.PushChannelDTO;
import uy.com.group05.baascore.sl.services.soap.PushChannelServices;

@WebService(
	endpointInterface="uy.com.group05.baascore.sl.services.soap.PushChannelServices",
	portName="PushServicesPort",
	serviceName="PushServices"
)
public class PushChannelServicesImpl implements PushChannelServices{
	
	@Inject
	Mapper mapper;
	
	@Inject
	PushChannelManagementLocal pushChannelManagementLocal;

	@Override
	public long createPushChannel(long idApp, String nombreCanal)
			throws AppNotRegisteredException,
			PushChanAlreadyRegisteredException {
		
		return pushChannelManagementLocal.createPushChannel(idApp, nombreCanal);		
	}
	
	@Override
	public long deletePushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException,
			PushChanNotRegisteredException {
		
		return pushChannelManagementLocal.deletePushChannel(idApp, idCanal);		
	}

	@Override
	public boolean existsPushChannelApplication(long idApp, String nombreCanal)
			throws AppNotRegisteredException {
		
		return pushChannelManagementLocal.existsPushChannelApplication(idApp, nombreCanal);		
	}
	
	@Override
	public boolean existsPushChannel(long idCanal) {
		
		return pushChannelManagementLocal.existsPushChannel(idCanal);		
	}


	@Override
	public boolean assignClientToPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		return pushChannelManagementLocal.assignClientToPushChannel(idApp, idCanal, idCliente);
	}


	@Override
	public boolean unassignClientFromPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		return pushChannelManagementLocal.unassignClientFromPushChannel(idApp, idCanal, idCliente);
	}

	@Override
	public List<PushChannelDTO> getPushChannelsOfApplication(long idApp)
			throws AppNotRegisteredException {
		
		return mapper.getMapper().mapAsList(pushChannelManagementLocal.getPushChannelsOfApplication(idApp), PushChannelDTO.class);
	}

	@Override
	public List<ClientDTO> getClientsFromPushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		return mapper.getMapper().mapAsList(pushChannelManagementLocal.getClientsFromPushChannel(idApp, idCanal), ClientDTO.class);
	}
	
	@Override
	public boolean sendNotificationToPushChannel(long idApp,
			long idCanal, String mensaje) throws AppNotRegisteredException,
			PushChanNotRegisteredException {
		
		return pushChannelManagementLocal.sendNotificationToPushChannel(idApp, idCanal, mensaje);
	}
}
