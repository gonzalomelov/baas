package uy.com.group05.baascore.bll.ejbs;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.dal.dao.PushChannelDao;

@Stateless
public class PushChannelManagement implements PushChannelManagementLocal{

	@Inject
	PushChannelDao pushDao;
	@Inject
	ApplicationDao appDao;
	@Inject
	ClientDao clientDao;
	@Inject
	AppManagement appMgmt;
	
	@Override	
	public long createPushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException,
			PushChanAlreadyRegisteredException {
		
		return appMgmt.addPushChannelToApplication(nombreApp, nombreCanal);
	}

	@Override
	public long deletePushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		return appMgmt.removePushChannelFromApplication(nombreApp, nombreCanal);
	}

	@Override
	public boolean existsPushChannel(String nombreApp, String nombreCanal)
			throws AppNotRegisteredException {
		
		return appMgmt.existsPushChannelApplication(nombreApp, nombreCanal);
	}

	@Override
	public boolean assignClientToPushChannel(String nombreApp,
			String nombreCanal, String mailCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		if (!appMgmt.existsApplication(nombreApp))
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + nombreApp);
		
		if (!this.existsPushChannel(nombreApp, nombreCanal))
			throw new PushChanNotRegisteredException("No existe el canal push de nombre " + nombreCanal);
		
		Application app = appDao.readByName(nombreApp);
		PushChannel pushChannel = pushDao.readByName(app.getId(), nombreCanal);
		Client client = clientDao.readByEmail(app.getId(), mailCliente);
		
		// Si el cliente ya está suscripto, no hago nada
		if (pushChannel.hasClient(client))
			return false;
		
		// Si no está suscripto, lo agrego
		pushChannel.addClient(client);
		pushDao.update(pushChannel);
		return true;
	}

	@Override
	public boolean unassignClientFromPushChannel(String nombreApp,
			String nombreCanal, String mailCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		if (!appMgmt.existsApplication(nombreApp))
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + nombreApp);
		
		if (!this.existsPushChannel(nombreApp, nombreCanal))
			throw new PushChanNotRegisteredException("No existe el canal push de nombre " + nombreCanal);
		
		Application app = appDao.readByName(nombreApp);
		PushChannel pushChannel = pushDao.readByName(app.getId(), nombreCanal);
		Client client = clientDao.readByEmail(app.getId(), mailCliente);
		
		// Si el cliente no está suscripto, no hago nada
		if (!pushChannel.hasClient(client))
			return false;
		
		// Si está suscripto, lo elimino
		pushChannel.removeClient(client);
		pushDao.update(pushChannel);
		return true;
	}

	@Override
	public boolean sendNotificationToPushChannel(String nombreApp,
			String nombreCanal, String mensaje)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		// TODO Auto-generated method stub
		
		return false;
	}
	
}
