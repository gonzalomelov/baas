package uy.com.group05.baascore.dal.dao;

import java.util.List;

import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;

import com.mongodb.util.JSONParseException;

public interface NoSqlDbDao {
	
	void createNoSqlDb(String dbName)
			throws MongoDBAlreadyExistsException;
	
	void createEntityCollection(String dbName, String entity)
			throws EntityCollectionAlreadyExistsException;
	
	void addEntity(String application, String entity, String jsonEntity)
			throws JSONParseException;
	
	String getEntities(String application, String entity);
	
//	List<ObjectNode> getEntities(String entity);
//	List<ObjectNode> getEntities(String entity, String filter);
//	void updateEntity(String entity, ObjectNode jsonEntity);
//	void removeEntity(String entity, ObjectNode jsonEntity);
}
