package uy.com.group05.baascore.sl.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.ClientDTO;
import uy.com.group05.baascore.sl.entitiesws.PushChannelDTO;
import uy.com.group05.baascore.sl.entitiesws.SimpleEntityDTO;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelDTO;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelEntityDTO;
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
	public boolean assignClientToPushChannel(long idCanal, long idCliente)
			throws	PushChanNotRegisteredException,
					ClientNotRegisteredException {
		
		return pushChannelManagementLocal.assignClientToPushChannel(idCanal, idCliente);
	}

	@Override
	public boolean unassignClientFromPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		return pushChannelManagementLocal.unassignClientFromPushChannel(idApp, idCanal, idCliente);
	}

	@Override
	public boolean assignEntityToPushChannel (long idApp, long idCanal, long idEntity)
		throws AppNotRegisteredException, PushChanNotRegisteredException, EntityNotRegisteredException {
		
		return pushChannelManagementLocal.assignEntityToPushChannel(idApp, idCanal, idEntity);
		
	}

	@Override
	public boolean unassignEntityToPushChannel (long idApp, long idCanal, long idEntity)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				EntityNotRegisteredException {
		
		return pushChannelManagementLocal.unassignEntityToPushChannel(idApp, idCanal, idEntity);
	}
	
	@Override
	public boolean savePushChannelEntities(long appId, long pushChannelId, List<SimplePushChannelEntityDTO> entities)
		throws
			AppNotRegisteredException,
			PushChanNotRegisteredException,
			EntityNotRegisteredException {
		
		return pushChannelManagementLocal.savePushChannelEntities(appId, pushChannelId, entities);
	}
	
	@Override
	public List<SimpleEntityDTO> getEntitiesAssociatedWithPushChannel(long idApp, long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException {
		
		List<Entity> entities = pushChannelManagementLocal.getEntitiesAssociatedWithPushChannel(idApp, idCanal);
		
		List<SimpleEntityDTO> entitiesDto = mapper.getMapper().mapAsList(entities, SimpleEntityDTO.class); 
		
		return entitiesDto;
	}
	
	@Override
	public List<SimplePushChannelDTO> getPushChannelsOfApplication(long idApp)
			throws AppNotRegisteredException {
		
		List<PushChannel> pushChannels = pushChannelManagementLocal.getPushChannelsOfApplication(idApp);
		
		List<SimplePushChannelDTO> pushChannelsDto = mapper.getMapper().mapAsList(pushChannels, SimplePushChannelDTO.class); 
		
		return pushChannelsDto;
	}

	@Override
	public List<ClientDTO> getClientsFromPushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		return mapper.getMapper().mapAsList(pushChannelManagementLocal.getClientsFromPushChannel(idApp, idCanal), ClientDTO.class);
	}
	
	@Override
	public boolean sendNotificationToPushChannel(long idApp,
			long idCanal, String msgKey, String msgValue) throws AppNotRegisteredException,
			PushChanNotRegisteredException {
		
		return pushChannelManagementLocal.sendNotificationToPushChannel(idApp, idCanal, msgKey, msgValue);
	}
}
