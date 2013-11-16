package uy.com.group05.baascore.dal.dao.mongodb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.bson.types.ObjectId;

import uy.com.group05.baascore.common.datatypes.SyncNoSqlResult;
import uy.com.group05.baascore.common.exceptions.AppNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.EntityCollectionAlreadyExistsException;
import uy.com.group05.baascore.common.exceptions.EntityNotRegisteredException;
import uy.com.group05.baascore.common.exceptions.MongoDBAlreadyExistsException;
import uy.com.group05.baascore.common.utils.PropertyHandler;
import uy.com.group05.baascore.dal.dao.NoSqlDbDao;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;

@Stateless
public class MongoDbEntityNoSqlDao implements NoSqlDbDao {

	private MongoClient mongo;
	
	public MongoDbEntityNoSqlDao() {
		PropertyHandler propertyHandler = new PropertyHandler();
		
		String mongoHost = propertyHandler.getProperty("mongoHost");
		int mongoPort = Integer.parseInt(propertyHandler.getProperty("mongoPort"));
		
		String mongoUser = propertyHandler.getProperty("mongoUser");
		String mongoPassword = propertyHandler.getProperty("mongoPassword");
		
		try {
			System.out.println("before MongoClient");
			mongo = new MongoClient(mongoHost, mongoPort);
			
			boolean auth = mongo.getDB("admin").authenticate(mongoUser, mongoPassword.toCharArray());
			
			System.out.println(auth);
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
			throws EntityCollectionAlreadyExistsException, MongoException {
		
		System.out.println("mongo.getDB(dbName)");
		DB mongoDb = mongo.getDB(dbName);
		
		if (mongoDb.collectionExists(entity)) {
			throw new EntityCollectionAlreadyExistsException("Ya existe la colleción para la base especificada");
		}
		
		BasicDBObject options = new BasicDBObject();
		options.put("capped", false);
		
		System.out.println("mongoDb.createCollection");
		mongoDb.createCollection(entity, options);
		System.out.println("Success");
	}
	
	@Override
	public void addEntity(String application, String entity, String jsonEntity)
			throws JSONParseException {
		
		DB mongoDb = mongo.getDB(application);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		
		DBObject dbObject = (DBObject) JSON.parse(jsonEntity);
		
		dbObject.put("updatedat", (new Timestamp(System.currentTimeMillis())).toString());
		
		dbCollection.insert(dbObject);	
	}

	@Override
	public String getEntities(String application, String entity, String query ) {
		DB mongoDb = mongo.getDB(application);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		String jsonResult;
		
		Object object = JSON.parse(query);
		DBObject dbObject = (DBObject) object;
	
		jsonResult = com.mongodb.util.JSON.serialize(dbCollection.find(dbObject));
		
		return jsonResult;
	}
	
	@Override
	public void updateEntity(String application, String entity, String query, String jsonEntity)
			throws JSONParseException {
		
		DB mongoDb = mongo.getDB(application);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		
		DBObject dbQuery = (DBObject) JSON.parse(query);
		DBObject dbObject = (DBObject) JSON.parse(jsonEntity);
		
		dbObject.put("updatedat", (new Timestamp(System.currentTimeMillis())).toString());
		
		dbCollection.update(dbQuery, dbObject);
	}

	@Override
	public void removeEntity(String application, String entity, String query) {
		DB mongoDb = mongo.getDB(application);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		
		DBObject dbObject = (DBObject) JSON.parse(query);
		
		dbCollection.remove(dbObject);
	}
	
	@Override
	public SyncNoSqlResult sync(String appName, String entity, String jsonObjs)
			throws AppNotRegisteredException, EntityNotRegisteredException {
		
		boolean sincronizar = false;
		
		DB mongoDb = mongo.getDB(appName);
		DBCollection dbCollection = mongoDb.getCollection(entity);
		
		DBObject remoteObjects = (DBObject) JSON.parse(jsonObjs);
		BasicDBList remoteList = (BasicDBList) remoteObjects;
		
		for (int i = 0; i<remoteList.size(); i++) {
			String remoteJson = (String) remoteList.get(i);
			BasicDBObject remoteObj = (BasicDBObject) JSON.parse(remoteJson);
			
			if (!remoteObj.containsField("syncid")) {
				//Si no se encuentra el elemento por id, lo agrego seteandole el updatedat
				remoteObj.put("updatedat", (new Timestamp(System.currentTimeMillis())).toString());
				dbCollection.insert(remoteObj);
				
				sincronizar = true;
				
			} else {
				//Si el elemento ya se encuentra comparo por fecha de modificación
				ObjectId remoteObjId = new ObjectId(remoteObj.getString("syncid"));
				
				BasicDBObject queryObject = new BasicDBObject();
				queryObject.put("_id", remoteObjId);
			    
				DBObject queryDbObject = (DBObject) queryObject;
				
				BasicDBObject localObj = (BasicDBObject) dbCollection.findOne(queryDbObject);
				
				boolean delete = remoteObj.getBoolean("delete");
				
				if (delete) {
					dbCollection.remove(queryDbObject);
					
					sincronizar = true;
					
				} else {
					Timestamp localObjUpdatedAt =  Timestamp.valueOf(localObj.getString("updatedat"));
					Timestamp remoteObjUpdatedAt =  Timestamp.valueOf(remoteObj.getString("updatedat"));
					
					//Actualizo si el objeto remoto es mas nuevo que el local
					//if (remoteObjUpdatedAt.after(localObjUpdatedAt)) {
					if (true) {
						
						//Valor de modificacion para syncronización
						remoteObj.put("updatedat", new Timestamp(System.currentTimeMillis()).toString());
						
						dbCollection.update(queryDbObject, remoteObj);
						
						sincronizar = true;
					}	
				}
				
			}	
		}
		
		SyncNoSqlResult result = new SyncNoSqlResult();
		result.setJson(JSON.serialize(dbCollection.find()));
		result.setSincronizar(sincronizar);
		
		return result;
	}
	
}
