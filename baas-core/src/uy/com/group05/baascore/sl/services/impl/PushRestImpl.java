package uy.com.group05.baascore.sl.services.impl;

import java.util.UUID;

import javax.ejb.EJB;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.sl.services.rest.PushRest;

public class PushRestImpl implements PushRest {

	@EJB
	private ClientManagementLocal clientManagementLocal;
	
	@EJB
	private PushChannelManagementLocal pushChannelManagementLocal;
	
	@EJB
	private AppManagementLocal appManagementLocal;
	
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
	public boolean sendNotification(UUID accessToken, String appName, String pushChanName, String msgKey, String msgValue) {
		try {
			return pushChannelManagementLocal.sendNotificationToPushChannel(appName, pushChanName, msgKey, msgValue);
		} catch (AppNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (PushChanNotRegisteredException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (PushChanNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClientNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
