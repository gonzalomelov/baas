package uy.com.group05.baascore.bll.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
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
	AppManagementLocal appMgmt;
	@Inject
	ClientManagementLocal clientMgmt;
	
	@Override	
	public long createPushChannel(long idApp, String nombreCanal)
			throws AppNotRegisteredException,
			PushChanAlreadyRegisteredException {
		
		return appMgmt.addPushChannelToApplication(idApp, nombreCanal);
	}

	@Override
	public long deletePushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		return appMgmt.removePushChannelFromApplication(idApp, idCanal);
	}

	@Override
	public boolean existsPushChannelApplication(long idApp, String nombreCanal)
			throws AppNotRegisteredException {
		
		return appMgmt.existsPushChannelApplication(idApp, nombreCanal);
	}
	
	@Override
	public boolean existsPushChannel(long idCanal) {
		return (pushDao.read(idCanal) != null);
	}

	@Override
	public boolean assignClientToPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		if (!appMgmt.existsApplication(idApp))
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		
		if (!this.existsPushChannel(idCanal))
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		
		PushChannel pushChannel = pushDao.read(idCanal);
		Client client = clientMgmt.getClient(idCliente);
		
		// Si el cliente ya está suscripto, no hago nada
		if (pushChannel.hasClient(client))
			return false;
		
		// Si no está suscripto, lo agrego
		pushChannel.addClient(client);
		pushDao.update(pushChannel);
		return true;
	}

	@Override
	public boolean unassignClientFromPushChannel(long idApp,
			long idCanal, long idCliente)
			throws AppNotRegisteredException, PushChanNotRegisteredException,
			ClientNotRegisteredException {
		
		if (!appMgmt.existsApplication(idApp))
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		
		if (!this.existsPushChannel(idCanal))
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		
		PushChannel pushChannel = pushDao.read(idCanal);
		Client client = clientMgmt.getClient(idCliente);
		
		// Si el cliente no está suscripto, no hago nada
		if (!pushChannel.hasClient(client))
			return false;
		
		// Si está suscripto, lo elimino
		pushChannel.removeClient(client);
		pushDao.update(pushChannel);
		return true;
	}
	
	@Override
	public List<PushChannel> getPushChannelsOfApplication(long idApp)
			throws AppNotRegisteredException {
		
		if (!appMgmt.existsApplication(idApp))
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		
		return appMgmt.getPushChannelsApplication(idApp);
	}

	@Override
	public List<Client> getClientsFromPushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		if (!appMgmt.existsApplication(idApp))
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		
		if (!this.existsPushChannel(idCanal))
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		
		return pushDao.readAllFromCanal(idApp, idCanal);
	}

	@Override
	public boolean sendNotificationToPushChannel(long idApp,
			long idCanal, String mensaje)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		// TODO Auto-generated method stub
		
		return false;
	}
	
}
