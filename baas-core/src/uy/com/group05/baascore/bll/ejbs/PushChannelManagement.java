package uy.com.group05.baascore.bll.ejbs;

import java.io.IOException;import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.android.gcm.server.*;

import uy.com.group05.baascore.bll.ejbs.interfaces.AppManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.PushChannelDao;
import uy.com.group05.baascore.sl.entitiesws.SimpleEntityDTO;
import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelEntityDTO;

@Stateless
public class PushChannelManagement implements PushChannelManagementLocal{

	@Inject
	PushChannelDao pushDao;
	@Inject
	ApplicationDao appDao;
	@Inject
	ClientDao clientDao;
	@Inject
	EntityDao entityDao;
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
	public boolean assignClientToPushChannel(long idCanal, long idCliente)
			throws	PushChanNotRegisteredException,
					ClientNotRegisteredException {
		
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
	public boolean assignEntityToPushChannel (long idApp, long idCanal, long idEntity)
			throws AppNotRegisteredException, PushChanNotRegisteredException, EntityNotRegisteredException {
		
		Application app = appDao.readById(idApp);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		}
			
		if (!this.existsPushChannel(idCanal)) {
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		}
		
		PushChannel pushChannel = pushDao.readById(idCanal);
		
		Entity entity = entityDao.readById(idEntity);
		if (entity==null || !app.getEntities().contains(entity)){
			throw new EntityNotRegisteredException ("No existe una entidad con id: " + idEntity);
		}
		
		if (pushChannel.getEntities().contains(entity)) {
			return false;
		}
		
		pushChannel.getEntities().add(entity);
		pushDao.update(pushChannel);
		
		return true;
	}
	
	public void sendNotificationsOnEntityPostPutDelete(long appId, long entityId)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException {
		
		Application app = appDao.readById(appId);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + appId);
		}
		
		Entity entity = entityDao.readById(entityId);
		if (entity == null) {
			throw new EntityNotRegisteredException("No existe la entidad con id " + entityId);
		}
		
		List<PushChannel> canales = getPushChannelsAssociatedWithEntity(appId, entityId);
		
		for (PushChannel canal : canales) {
			try {
				sendNotificationToPushChannel(appId, canal.getId(), "post", entity.getName());
			} catch (PushChanNotRegisteredException e) {
				// No debería pasar
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean unassignEntityToPushChannel(long idApp, long idCanal, long idEntity)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				EntityNotRegisteredException {

		Application app = appDao.readById(idApp);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		}
			
		if (!this.existsPushChannel(idCanal)) {
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		}
		
		PushChannel pushChannel = pushDao.readById(idCanal);
		
		Entity entity = entityDao.readById(idEntity);
		if (entity==null || !app.getEntities().contains(entity)){
			throw new EntityNotRegisteredException ("No existe una entidad con id: " + idEntity);
		}
		
		if (!pushChannel.getEntities().contains(entity)) {
			return false;
		}
		
		pushChannel.getEntities().remove(entity);
		pushDao.update(pushChannel);
		
		return true;
	} 
	
	@Override
	public boolean savePushChannelEntities(long appId, long pushChannelId, List<SimplePushChannelEntityDTO> entities)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException,
				EntityNotRegisteredException {
		
		Application app = appDao.readById(appId);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + appId);
		}
			
		if (!this.existsPushChannel(pushChannelId)) {
			throw new PushChanNotRegisteredException("No existe el canal push con id " + pushChannelId);
		}
		
		PushChannel pushChannel = pushDao.readById(pushChannelId);
		
		List<Entity> oldPushChannelEntities = pushChannel.getEntities();
		List<Entity> newPushChannelEntities = new ArrayList<Entity>();
		
		for (SimplePushChannelEntityDTO pushChannelEntity : entities) {
			Entity entity = null;
			for (Entity e : oldPushChannelEntities) {
				if (e.getId() == pushChannelEntity.getId()) {
					entity = e;
					break;
				}
			}
			
			if (entity == null) {
				if (pushChannelEntity.isAssociated()) {
					entity = new Entity();
					entity.setId(pushChannelEntity.getId());
					newPushChannelEntities.add(entity);
				}	
			} else {
				if (pushChannelEntity.isAssociated()) {
					entity = new Entity();
					entity.setId(pushChannelEntity.getId());
					newPushChannelEntities.add(entity);
				}
			}
		}
		
		pushChannel.setEntities(newPushChannelEntities);
		pushDao.update(pushChannel);
		
		return true;
	}
	
	@Override
	public List<Entity> getEntitiesAssociatedWithPushChannel(long idApp, long idCanal)
			throws
				AppNotRegisteredException,
				PushChanNotRegisteredException {
		
		Application app = appDao.readById(idApp);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		}
			
		if (!this.existsPushChannel(idCanal)) {
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		}
		
		PushChannel pushChannel = pushDao.readById(idCanal);
		
		return pushChannel.getEntities();
	}
	
	@Override
	public List<PushChannel> getPushChannelsAssociatedWithEntity(long idApp, long idEntity)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException {
		
		Application app = appDao.readById(idApp);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con id " + idApp);
		}
			
		Entity entity = entityDao.read(idEntity);
		if (entity == null) {
			throw new EntityNotRegisteredException("No existe la entidad con id " + idEntity);
		}
		
		return entity.getPushChannels();
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
			long idCanal, String msgKey, String msgValue)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		// TODO Auto-generated method stub
		
		List<Client> clientes = getClientsFromPushChannel(idApp, idCanal);
		if (clientes.isEmpty())
			System.out.println("------> No hay clientes asociados al canal push.");
		
		for (Client c : clientes) {
			String regId = c.getGcm_regId();
			//String regId = "APA91bHDcO84iVqd0AXPlU1QptCM0ioLh9MKexkrfEIpz8khLS584yeXjYSb_RD_ggEEH0b008BZyQmkIu9XWzSnfCgl4hleH1yQ8N1mjbq25xyzemXiMFWDoOF-sWgb0GK1NFDfqWnzCjsomW5t-KTbucKfuh5iItKsA2gzdsQuLVtbesHu5cE";
			
			Sender sender = new Sender("AIzaSyByhp5CPEb74Vt034btzqy2iLkRaDQUTuM"); // API KEY, PARAMETRIZAR
			Message message = new Message.Builder().timeToLive(600).addData(msgKey, msgValue).build();
			
			try {
				Result result = sender.send(message, regId, 5);
				if (result.getMessageId() != null) {
					 String canonicalRegId = result.getCanonicalRegistrationId();
					 if (canonicalRegId != null) {
					   // same device has more than one registration ID: update database
						 System.out.println("CAMBIÓ EL REGID... ACTUALIZAR EN LA BASE EL REGID DEL CLIENTE!!!!");
						 return false;
					 }
				} else {
					String error = result.getErrorCodeName();
					if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
						// application has been removed from device - unregister database
						System.out.println("NO EXISTE MÁS EL REGID... SE DESINSTALÓ LA APLICACIÓN.... BORRAR DE LA BASE EL REGID DEL CLIENTE!!!!");
						return false;
					}
				}
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean sendNotificationToPushChannel(String appName,
			String pushChanName, String msgKey, String msgValue)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		long appId = appMgmt.getApplication(appName).getId();
		long pushChanId = getPushChannel(appId, pushChanName).getId();
		
		return sendNotificationToPushChannel(appId, pushChanId, msgKey, msgValue);
		
	}
	
	@Override
	public PushChannel getPushChannel(long appId, String pushChanName)
		throws PushChanNotRegisteredException {
		
		PushChannel pc = pushDao.readByName(appId, pushChanName);
		if (pc == null) {
			throw new PushChanNotRegisteredException("No existe el canal push con nombre " + pushChanName + " para la aplicación con id " + appId);
		}
		
		return pc;
	}
	
}
