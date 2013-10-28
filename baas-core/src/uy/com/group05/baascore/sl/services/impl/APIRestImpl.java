package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.sl.services.rest.APIRest;

public class APIRestImpl implements APIRest {
	
	@Inject
	private APIManagementLocal apiManagementLocal;
	
	public String get(String appName, String entity, String query) {

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
}