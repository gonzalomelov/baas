package uy.com.group05.baascore.dal.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;

public class MongoDbEntityNoSqlDao implements NoSqlDbDao {

	private MongoClient mongo;
	
	public MongoDbEntityNoSqlDao() {
		try {
			mongo = new MongoClient("localhost",27017);
		}
		catch (Exception e) {
			
		}
	}

	@Override
	public void createNoSqlDb(String dbName)
			throws MongoDBAlreadyExistsException {
		
		List<String> databaseNames = mongo.getDatabaseNames();
		
		if (databaseNames.contains(dbName)) {
			throw new MongoDBAlreadyExistsException("Ya existe una base MongoDB con dicho nombre");
		}
			
		mongo.getDB(dbName);
	}
	
	@Override
	public void createEntityCollection(String dbName, String entity)
			throws EntityCollectionAlreadyExistsException {
		
		DB mongoDb = mongo.getDB(dbName);
		
		if (mongoDb.collectionExists(entity)) {
			throw new EntityCollectionAlreadyExistsException("Ya existe la colleción para la base especificada");
		}
		
		BasicDBObject options = new BasicDBObject();
		options.put("capped", false);
		
		mongoDb.createCollection(entity, options);
	}
	
	@Override
	public void addEntity(String application, String entity, String jsonEntity)
			throws JSONParseException {
		
		DB mongoDb = mongo.getDB(application);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		
		DBObject dbObject = (DBObject) JSON.parse(jsonEntity);
		dbCollection.insert(dbObject);	
	}

	@Override
	public String getEntities(String application, String entity) {
		DB mongoDb = mongo.getDB(application);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		
		String jsonResult = com.mongodb.util.JSON.serialize(dbCollection.find());
		
		return jsonResult;
	}

}
