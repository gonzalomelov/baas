package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;

public interface APIManagementLocal {
	String get(String appName, String entity, String query);
	boolean post(String appName, String entity, String jsonObj) throws AppNotRegisteredException, EntityNotRegisteredException;
}
