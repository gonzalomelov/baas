package uy.com.group05.baascore.sl.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.sl.services.rest.APIRest;

public class APIRestImpl implements APIRest {
	
	@Inject
	private APIManagementLocal apiManagementLocal;
	
	public String get(String appName, String entity) {

		String query = "";
		
	    return apiManagementLocal.get(appName, entity, query);
	}
	
	public String getQuery(String appName, String entity, String query) {

	    return apiManagementLocal.get(appName, entity, query);
	}
	
	public boolean post(String appName, String entity, String jsonObj) {

		try {
			return apiManagementLocal.post(appName, entity, jsonObj);
		} catch (AppNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (EntityNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String sync(String appName, String entity, String jsonObjs) {

		try {
			return apiManagementLocal.sync(appName, entity, jsonObjs);
		} catch (AppNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (EntityNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public List<String> getEntities(String appName) {

		try {
			return apiManagementLocal.getEntitiesNames(appName);
		} catch (AppNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
}