package uy.com.group05.baascore.bll.ejbs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import uy.com.group05.baascore.common.entities.Estadisticas;
import uy.com.group05.baascore.common.entities.PushChannel;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.ClientNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanAlreadyRegisteredException;
import uy.com.group05.baascore.common.exceptions.PushChanNotRegisteredException;
import uy.com.group05.baascore.common.utils.PropertyHandler;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.ClientDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.EstadisticasDao;
import uy.com.group05.baascore.dal.dao.PushChannelDao;
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
	
	@Inject
	private EstadisticasDao estadisticasDao;
	
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
		
		
		
		Estadisticas est = new Estadisticas();
		est.setAppId(pushChannel.getApplication().getId());
		est.setTipoEstadisticas(3);
		est.setTiempo(new Date());
		
		estadisticasDao.create(est);
		
		// Si el cliente ya est� suscripto, no hago nada
		if (pushChannel.hasClient(client))
			return true;
		
		// Si no est� suscripto, lo agrego
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
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
		
		if (!this.existsPushChannel(idCanal))
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		
		PushChannel pushChannel = pushDao.read(idCanal);
		Client client = clientMgmt.getClient(idCliente);
		
		// Si el cliente no est� suscripto, no hago nada
		if (!pushChannel.hasClient(client))
			return false;
		
		// Si est� suscripto, lo elimino
		pushChannel.removeClient(client);
		pushDao.update(pushChannel);
		return true;
	}
	
	@Override
	public boolean assignEntityToPushChannel (long idApp, long idCanal, long idEntity)
			throws AppNotRegisteredException, PushChanNotRegisteredException, EntityNotRegisteredException {
		
		Application app = appDao.readById(idApp);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
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
	
	@Override	
	public void sendNotificationsOnEntityPost(long appId, long entityId)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException {
		
		System.out.println("Se va a mandar una notificacion push porque se cre� una entidad");
		sendNotificationsOnEntityPostPutDeleteSync(appId, entityId, "post");
	}
	
	@Override	
	public void sendNotificationsOnEntityPut(long appId, long entityId)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException {
		
		System.out.println("Se va a mandar una notificacion push porque se actualiz� una entidad");
		sendNotificationsOnEntityPostPutDeleteSync(appId, entityId, "put");
	}
	
	@Override	
	public void sendNotificationsOnEntityDelete(long appId, long entityId)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException {
		
		System.out.println("Se va a mandar una notificacion push porque se elimin� una entidad");
		sendNotificationsOnEntityPostPutDeleteSync(appId, entityId, "delete");
	}
	
	@Override	
	public void sendNotificationsOnEntitySync(long appId, long entityId)
			throws
				AppNotRegisteredException,
				EntityNotRegisteredException {
		
		System.out.println("Se va a mandar una notificacion push porque se sincroniz� una entidad");
		sendNotificationsOnEntityPostPutDeleteSync(appId, entityId, "sync");
	}
	
	private void sendNotificationsOnEntityPostPutDeleteSync(long appId, long entityId, String accion)
			throws
			AppNotRegisteredException,
			EntityNotRegisteredException {
		Application app = appDao.readById(appId);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
		}
		
		Entity entity = entityDao.readById(entityId);
		if (entity == null) {
			throw new EntityNotRegisteredException("No existe la entidad con id " + entityId);
		}
		
		Estadisticas est = new Estadisticas();
		est.setAppId(appId);
		est.setTipoEstadisticas(2);
		est.setTiempo(new Date());
		
		estadisticasDao.create(est);
		
		List<PushChannel> canales = getPushChannelsAssociatedWithEntity(appId, entityId);
		if (canales.size() == 0)
			System.out.println("No hay canales asociados a la entidad " + entity.getName());
		
		for (PushChannel canal : canales) {
			try {
				boolean ok = sendNotificationToPushChannel(appId, canal.getId(), accion, entity.getName());
				if (ok)
					System.out.println("Se mand� la notificacion push al canal " + canal.getName() + " por la entidad " + entity.getName());
				else
					System.out.println("No se mand� la notificacion push al canal " + canal.getName() + " por la entidad " + entity.getName());
			} catch (PushChanNotRegisteredException e) {
				// No deber�a pasar
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
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
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
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + appId);
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
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
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
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
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
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
		
		return appMgmt.getPushChannelsApplication(idApp);
	}

	@Override
	public List<Client> getClientsFromPushChannel(long idApp, long idCanal)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		if (!appMgmt.existsApplication(idApp))
			throw new AppNotRegisteredException("No existe la aplicaci�n con id " + idApp);
		
		if (!this.existsPushChannel(idCanal))
			throw new PushChanNotRegisteredException("No existe el canal push con id " + idCanal);
		
		return pushDao.readAllFromCanal(idApp, idCanal);
	}

	@Override
	public boolean sendNotificationToPushChannel(long idApp,
			long idCanal, String accion, String nomEntidad)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		List<Client> clientes = getClientsFromPushChannel(idApp, idCanal);
		if (clientes.isEmpty())
			System.out.println("------> No hay clientes asociados al canal push.");
		
		PropertyHandler propertyHandler = new PropertyHandler();
		String gcmApiKey = propertyHandler.getProperty("gcmApiKey");
		
		for (Client c : clientes) {
			String regId = c.getGcm_regId();
			
			Sender sender = new Sender(gcmApiKey);
			Message message = new Message.Builder().timeToLive(600).addData("accion", accion).addData("entidad", nomEntidad).addData("type", "notification").build();
			System.out.println("Se va a mandar una notificaci�n a " + c.getEmail());
			
			try {
				if (regId != null) {
					Result result = sender.send(message, regId, 5);
					System.out.println("Result del m�todo send de GCM: " + result.getErrorCodeName());
					if (result.getMessageId() != null) {
						 String canonicalRegId = result.getCanonicalRegistrationId();
						 if (canonicalRegId != null) {
						   // same device has more than one registration ID: update database
							 System.out.println("CAMBI� EL REGID... ACTUALIZAR EN LA BASE EL REGID DEL CLIENTE!!!!");
							 return false;
						 }
					} else {
						String error = result.getErrorCodeName();
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							// application has been removed from device - unregister database
							System.out.println("NO EXISTE M�S EL REGID... SE DESINSTAL� LA APLICACI�N.... BORRAR DE LA BASE EL REGID DEL CLIENTE!!!!");
							return false;
						}
					}	
				}
				else {
					System.out.println("Advertencia: se pretendi� mandar una notificaci�n a " + c.getEmail() + " pero no tiene regId asociado!");
				}
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	@Override
	public boolean sendNotificationToPushChannel(String appName,
			String pushChanName, String accion, String entidad)
			throws AppNotRegisteredException, PushChanNotRegisteredException {
		
		long appId = appMgmt.getApplication(appName).getId();
		long pushChanId = getPushChannel(appId, pushChanName).getId();
		
		return sendNotificationToPushChannel(appId, pushChanId, accion, entidad);
		
	}
	
	@Override
	public boolean sendNotificationToClient(String mailReceiver, String msgKey, String msgValue, String difKey, String difValue)
			throws ClientNotRegisteredException {
		
		Client cliDest = clientMgmt.getClientFromEmail(mailReceiver);
		
		String regId = cliDest.getGcm_regId();
		//String regId = "APA91bHDcO84iVqd0AXPlU1QptCM0ioLh9MKexkrfEIpz8khLS584yeXjYSb_RD_ggEEH0b008BZyQmkIu9XWzSnfCgl4hleH1yQ8N1mjbq25xyzemXiMFWDoOF-sWgb0GK1NFDfqWnzCjsomW5t-KTbucKfuh5iItKsA2gzdsQuLVtbesHu5cE";
		
		if (regId == null || regId.isEmpty()) {
			System.out.println("Se intent� mandar una notificaci�n a " + mailReceiver + " pero no tiene regId asociado.");
			return false;
		}
		
		PropertyHandler propertyHandler = new PropertyHandler();
		String gcmApiKey = propertyHandler.getProperty("gcmApiKey");
		
		Sender sender = new Sender(gcmApiKey);
		Message message;
		if (difKey == null) {
			message = new Message.Builder().timeToLive(600).addData(msgKey, msgValue).addData("type", "notification").build();
			System.out.println("Mando notificaci�n sin difKey.");
		}
		else {
			message = new Message.Builder().timeToLive(600).addData(msgKey, msgValue).addData(difKey, difValue).addData("type", "notification").build();
			System.out.println("Mando notificaci�n con difKey: " + difKey + " y difValue: " + difValue);
		}
		try {
			Result result = sender.send(message, regId, 5);
			if (result.getMessageId() != null) {
				 String canonicalRegId = result.getCanonicalRegistrationId();
				 if (canonicalRegId != null) {
				   // same device has more than one registration ID: update database
					 System.out.println("CAMBI� EL REGID... ACTUALIZAR EN LA BASE EL REGID DEL CLIENTE!!!!");
					 return false;
				 }
			} else {
				String error = result.getErrorCodeName();
				if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
					// application has been removed from device - unregister database
					System.out.println("NO EXISTE M�S EL REGID... SE DESINSTAL� LA APLICACI�N.... BORRAR DE LA BASE EL REGID DEL CLIENTE!!!!");
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public PushChannel getPushChannel(long appId, String pushChanName)
		throws PushChanNotRegisteredException {
		
		PushChannel pc = pushDao.readByName(appId, pushChanName);
		if (pc == null) {
			throw new PushChanNotRegisteredException("No existe el canal push con nombre " + pushChanName + " para la aplicaci�n con id " + appId);
		}
		
		return pc;
	}
	
}
