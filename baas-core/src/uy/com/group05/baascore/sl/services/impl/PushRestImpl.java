package uy.com.group05.baascore.sl.services.impl;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.mapper.Mapper;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelDTO;
import uy.com.group05.baascore.sl.services.rest.PushRest;

public class PushRestImpl implements PushRest {

	@EJB
	private ClientManagementLocal clientManagementLocal;
	
	@EJB
	private PushChannelManagementLocal pushChannelManagementLocal;
	
	@EJB
	private AppManagementLocal appManagementLocal;
	
	@Inject
	Mapper mapper;
	
	@Override
	public boolean updateRegId(UUID accessToken, String appName, String regId) {
		try {
			long appId = appManagementLocal.getApplication(appName).getId();
			clientManagementLocal.updateRegIdOfClient(accessToken, appId, regId);
			return true;
		} catch (ClientNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (AppNotRegisteredException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean sendNotificationToPushChannel(UUID accessToken, String appName, String pushChanName, String msgKey, String msgValue) {
		try {
			return pushChannelManagementLocal.sendNotificationToPushChannel(appName, pushChanName, msgKey, msgValue);
		} catch (AppNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (PushChanNotRegisteredException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean subscribeToPushChannel(UUID accessToken, String appName, String pushChanName) {
		try {
			long appId = appManagementLocal.getApplication(appName).getId();
			long clientId = clientManagementLocal.getClientWithAccessToken(accessToken, appId).getId();
			long pushChanId = pushChannelManagementLocal.getPushChannel(appId, pushChanName).getId();
			return pushChannelManagementLocal.assignClientToPushChannel(pushChanId, clientId);
		} catch (AppNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (PushChanNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (ClientNotRegisteredException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean unsubscribeFromPushChannel(UUID accessToken, String appName, String pushChanName) {
		try {
			long appId = appManagementLocal.getApplication(appName).getId();
			long clientId = clientManagementLocal.getClientWithAccessToken(accessToken, appId).getId();
			long pushChanId = pushChannelManagementLocal.getPushChannel(appId, pushChanName).getId();
			return pushChannelManagementLocal.unassignClientFromPushChannel(appId, pushChanId, clientId);
		} catch (AppNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (PushChanNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (ClientNotRegisteredException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<SimplePushChannelDTO> getPushChannelsOfApplication(String appName) {
		try {
			long appId = appManagementLocal.getApplication(appName).getId();
			List<PushChannel> pushChannels = appManagementLocal.getPushChannelsApplication(appId);			
			List<SimplePushChannelDTO> pushChannelsDTO = mapper.getMapper().mapAsList(pushChannels, SimplePushChannelDTO.class);
			return pushChannelsDTO;
		} catch (AppNotRegisteredException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean sendNotificationToClient(UUID accessToken, String appName,
			String mailReceiver, String msgKey, String msgValue, String difKey, String difValue) {
		try {
			long appId = appManagementLocal.getApplication(appName).getId();
			if (clientManagementLocal.existsClient(accessToken, appId))
				return pushChannelManagementLocal.sendNotificationToClient(mailReceiver, msgKey, msgValue, difKey, difValue);
			else
				return false;
		} catch (ClientNotRegisteredException e) {
			e.printStackTrace();
			return false;
		} catch (AppNotRegisteredException e) {
			e.printStackTrace();
			return false;
		}
	}
}
