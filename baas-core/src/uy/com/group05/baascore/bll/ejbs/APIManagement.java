package uy.com.group05.baascore.bll.ejbs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.datatypes.SyncNoSqlResult;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Estadisticas;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.EstadisticasDao;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.jetty.util.ajax.JSON;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Stateless
public class APIManagement implements APIManagementLocal {
	@Inject
	private NoSqlDbDao noSqlDbDao;
	
	@Inject
	private EntityDao entityDao;
	
	@Inject
	private ApplicationDao appDao;
	
	@Inject
	private EstadisticasDao estadisticasDao;
	
	@Inject
	private PushChannelManagementLocal pushChannelManagementLocal;
	
	@Override
	public String get(String appName, String entity, String query) {
	    if(appName == null){
	    	return null;
	    }
 
		if(entity == null){		
			return null;			
		}
		
		Application app = appDao.readByName(appName);
		
		Estadisticas est = new Estadisticas();
		est.setAppId(app.getId());
		est.setTipoEstadisticas(1);
		est.setTiempo(new Date());
		
		estadisticasDao.create(est);
		
		return noSqlDbDao.getEntities(appName, entity, query); 
 
	}
	
	@Override
	public boolean post(String appName, String entity, String jsonObj) throws AppNotRegisteredException, EntityNotRegisteredException {
		if(entity == null){		
			return false;			
		}
		
		Application app = appDao.readByName(appName);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + appName);
		}
		
		long appId = app.getId();
		
		Entity ent = entityDao.readByName(appId, entity);
		if (ent == null) {
			throw new EntityNotRegisteredException("No existe la entidad con nombre " + entity);
		}
		
		long entityId = ent.getId();

		noSqlDbDao.addEntity(appName, entity, jsonObj);
		
		Estadisticas est = new Estadisticas();
		est.setAppId(appId);
		est.setTipoEstadisticas(1);
		est.setTiempo(new Date());
		
		estadisticasDao.create(est);
		
		pushChannelManagementLocal.sendNotificationsOnEntityPostPutDelete(appId, entityId);

		//######### Llamo a GCM para que todos los clientes actualizen
		try {
			startDataSynchronization(entity);
		}
		catch (Exception e) {
		
		}
		
		return true;
	}
	
	@Override
	public boolean delete(String appName, String entity, String query)
			throws AppNotRegisteredException, EntityNotRegisteredException {
	
		Application app = appDao.readByName(appName);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + appName);
		}
		
		long appId = app.getId();
		
		Entity ent = entityDao.readByName(appId, entity);
		if (ent == null) {
			throw new EntityNotRegisteredException("No existe la entidad con nombre " + entity);
		}
	
		noSqlDbDao.removeEntity(appName, entity, query);
		
		try {
			startDataSynchronization(entity);
		}
		catch (Exception e) {
		
		}
		
		return true;
	}

	@Override
	public boolean put(String appName, String entity, String query, String jsonObj)
			throws AppNotRegisteredException, EntityNotRegisteredException {

		Application app = appDao.readByName(appName);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + appName);
		}
		
		long appId = app.getId();
		
		Entity ent = entityDao.readByName(appId, entity);
		if (ent == null) {
			throw new EntityNotRegisteredException("No existe la entidad con nombre " + entity);
		}
		
		noSqlDbDao.updateEntity(appName, entity, query, jsonObj);
		
		try {
			startDataSynchronization(entity);
		}
		catch (Exception e) {
		
		}
		
		return true;
	}

	@Override
	public String sync(String appName, String entity, String jsonObjs) throws AppNotRegisteredException, EntityNotRegisteredException {
		Application app = appDao.readByName(appName);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + appName);
		}
		
		long appId = app.getId();
		
		Entity ent = entityDao.readByName(appId, entity);
		if (ent == null) {
			throw new EntityNotRegisteredException("No existe la entidad con nombre " + entity);
		}

		SyncNoSqlResult result = noSqlDbDao.sync(appName, entity, jsonObjs);
		
		if (result.isSincronizar()) {
			try {
				startDataSynchronization(entity);
			}
			catch (Exception e) {
			
			}
		}
		
		return result.getJson();
	}
	
	public List<String> getEntitiesNames(String appName) throws AppNotRegisteredException {
		Application app = appDao.readByName(appName);
		if (app == null) {
			throw new AppNotRegisteredException("No existe la aplicación con nombre " + appName);
		}
		
		List<Entity> entities = entityDao.readAll(app.getId());
		List<String> entitiesNames = new ArrayList<String>();
		
		for (Entity entity : entities) {
			entitiesNames.add(entity.getName());
		}
		
		return entitiesNames;
	}
	
	private void startDataSynchronization(String entity) throws Exception {
		String url = "https://android.googleapis.com/gcm/send";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Authorization", "key=AIzaSyBvmUSmsiLpQkkISV7NrH6AgHHwr5c5v6A");
		httpPost.setHeader("Content-Type", "application/json");
		
		String registrationIds =
				"{"
				+ "\"registration_ids\":[\"APA91bEmVgFtIagMeGLXiehdXTXRDIfbX4vCpULU5A0US9cnnWEFn1tM2xY1vpHi8eW9CVfU6OhZtL86f8EhSU7LcWJZgNRYd6XXhSKrl7pa2yzVUQXtssvGC5IlMtkQtSbd8hVeTVu4RMyOsxlLNVlCsHfKs16GPVnuWu-8NEhknuHGednc2Ms\"], "
				+ "\"data\":{ \"entity\":\"" + entity + "\"}"
				+ "}";
		
		StringEntity strEntity = new StringEntity(registrationIds);
		httpPost.setEntity(strEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		if (statusCode != HttpStatus.SC_OK) {
			throw new Exception("Cannot send messages");
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
		
		
		return ;
	}
}
