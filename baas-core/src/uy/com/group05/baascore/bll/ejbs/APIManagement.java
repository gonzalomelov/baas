package uy.com.group05.baascore.bll.ejbs;

import java.util.List;

import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;

public class APIManagement implements APIManagementLocal {
	@Inject
	private NoSqlDbDao noSqlDbDao;
	
	@Override
	public List<String> get(String appName, String entity, String query) {
	    if(appName == null){
	    	return null;
	    }
 
		if(entity == null){		
			return null;			
		}
		
		return noSqlDbDao.getEntities(appName, entity); 
 
	}
	
	@Override
	public boolean post(String appName, String entity, String jsonObj) {
		if(entity == null){		
			return false;			
		}

		noSqlDbDao.addEntity(appName, entity, jsonObj);

		return true;
	}
}
