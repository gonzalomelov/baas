package uy.com.group05.baascore.sl.services.impl;

import java.util.List;

import javax.inject.Inject;

import uy.com.group05.baascore.bll.ejbs.interfaces.APIManagementLocal;
import uy.com.group05.baascore.sl.services.rest.APIRest;

public class APIRestImpl implements APIRest {
	
	@Inject
	private APIManagementLocal apiManagementLocal;
	
	public String get(String appName, String entity) {

	    return apiManagementLocal.get(appName, entity, "");
	}
	
	public boolean post(String appName, String entity, String jsonObj) {

		return apiManagementLocal.post(appName, entity, jsonObj);
	}
}