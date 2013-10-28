package uy.com.group05.baascore.bll.ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;
import uy.com.group05.baascore.bll.ejbs.interfaces.PushChannelManagementLocal;
import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.dal.dao.ApplicationDao;
import uy.com.group05.baascore.dal.dao.EntityDao;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;

@Stateless
public class APIManagement implements APIManagementLocal {
	@Inject
	private NoSqlDbDao noSqlDbDao;
	
	@Inject
	private EntityDao entityDao;
	
	@Inject
	private ApplicationDao appDao;
	
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
		
		return noSqlDbDao.getEntities(appName, entity); 
 
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
		
		pushChannelManagementLocal.sendNotificationsOnEntityPostPutDelete(appId, entityId);

		return true;
	}
}
