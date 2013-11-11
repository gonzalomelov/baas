package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

import javax.ws.rs.PathParam;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;

public interface APIManagementLocal {
	String get(String appName, String entity, String query);
	boolean post(String appName, String entity, String jsonObj) throws AppNotRegisteredException, EntityNotRegisteredException;
	boolean delete(String appName, String entity, String query) throws AppNotRegisteredException, EntityNotRegisteredException;
	boolean put(String appName, String entity, String query, String jsonObj) throws AppNotRegisteredException, EntityNotRegisteredException;
	String sync(String appName, String entity, String jsonObjs) throws AppNotRegisteredException, EntityNotRegisteredException;
	List<String> getEntitiesNames(String appName) throws AppNotRegisteredException;
}
