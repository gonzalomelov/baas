package uy.com.group05.baascore.bll.ejbs.interfaces;

import javax.ejb.Local;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;

@Local
public interface PushChannelManagementLocal {
	
	public long createPushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException,
			PushChanAlreadyRegisteredException;
	
	public long deletePushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException;


	public boolean existsPushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException;


	public boolean assignClientToPushChannel(String nombreApp,
			String nombreCanal, String mailCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException;


	public boolean unassignClientFromPushChannel(String nombreApp,
			String nombreCanal, String mailCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException;


	public boolean sendNotificationToPushChannel(String nombreApp,
			String nombreCanal, String mensaje) throws AppNotRegisteredException,
			PushChanNotRegisteredException;
}
