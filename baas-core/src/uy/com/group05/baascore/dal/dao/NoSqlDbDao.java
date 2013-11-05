package uy.com.group05.baascore.dal.dao;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;

import com.mongodb.DBCursor;
import com.mongodb.util.JSONParseException;

@Local
public interface NoSqlDbDao {
	
	void createNoSqlDb(String dbName)
			throws MongoDBAlreadyExistsException;
	
	void createEntityCollection(String dbName, String entity)
			throws EntityCollectionAlreadyExistsException;
	
	void addEntity(String application, String entity, String jsonEntity)
			throws JSONParseException;
	
	String getEntities(String application, String entity, String query);
	
	String sync(String appName, String entity, String jsonObjs)
			throws AppNotRegisteredException, EntityNotRegisteredException;
	
//	List<ObjectNode> getEntities(String entity);
//	List<ObjectNode> getEntities(String entity, String filter);
	
	void updateEntity(String application, String entity, String jsonEntity);

//	void removeEntity(String entity, ObjectNode jsonEntity);
}
