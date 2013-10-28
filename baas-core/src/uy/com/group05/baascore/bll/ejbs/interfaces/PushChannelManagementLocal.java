package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.sl.entitiesws.SimpleEntityDTO;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelEntityDTO;

@Local
public interface PushChannelManagementLocal {
	
	public long createPushChannel(long idApp, String nombreCanal)
			throws AppNotRegisteredException,
			PushChanAlreadyRegisteredException;
	
	public long deletePushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException;


	public boolean existsPushChannelApplication(long idApp, String nombreCanal)
			throws AppNotRegisteredException;
	
	public boolean existsPushChannel(long idCanal);

	public boolean assignClientToPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException;


	public boolean unassignClientFromPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException;

	public boolean assignEntityToPushChannel (long idApp, long idCanal, long idEntity)
			throws AppNotRegisteredException, PushChanNotRegisteredException, EntityNotRegisteredException;
	
	public void sendNotificationsOnEntityPostPutDelete(long appId, long entityId)
			throws AppNotRegisteredException, EntityNotRegisteredException;
	
	public boolean unassignEntityToPushChannel (long idApp, long idCanal, long idEntity)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				EntityNotRegisteredException; 
	
	public boolean savePushChannelEntities(long appId, long pushChannelId, List<SimplePushChannelEntityDTO> entities)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				EntityNotRegisteredException;
	
	public List<Entity> getEntitiesAssociatedWithPushChannel(long idApp, long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException;
	
	public List<PushChannel> getPushChannelsAssociatedWithEntity(long idApp, long idEntity)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException;
	
	public List<PushChannel> getPushChannelsOfApplication(long idApp)
			throws AppNotRegisteredException;
	
	public List<Client> getClientsFromPushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException;


	public boolean sendNotificationToPushChannel(long idApp,
			long idCanal, String msgKey, String msgValue) throws AppNotRegisteredException,
			PushChanNotRegisteredException;
	
	public boolean sendNotificationToPushChannel(String appName,
			String pushChanName, String msgKey, String msgValue) throws AppNotRegisteredException,
			PushChanNotRegisteredException;
}
