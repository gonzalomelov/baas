package uy.com.group05.baascore.dal.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import uy.com.group05.baascore.dal.dao.EntityNoSqlDao;

public class MongoDbEntityNoSqlDao implements EntityNoSqlDao {

	private MongoClient mongo;
	private DB mongoDb;
	
	public MongoDbEntityNoSqlDao() {
		try {
			mongo = new MongoClient("localhost",27017);
		}
		catch (Exception e) {
			
		}
	}

	@Override
	public void addEntity(String application, String entity, String jsonEntity) {
		mongoDb = mongo.getDB(application);
		
		DBCollection dbCollection = mongoDb.getCollection(entity);
		//DBObject dbObject = (DBObject) JSON.parse(jsonEntity.toString());
		DBObject dbObject = (DBObject) JSON.parse(jsonEntity);
		dbCollection.insert(dbObject);
	}

	@Override
	public List<String> getEntities(String application, String entity) {
		mongoDb = mongo.getDB(application);
		
		DBCollection dbCollection = mongoDb.getCollection(entity);
		DBCursor dbCursor = dbCollection.find();
		
		List<String> jsonEntities = new ArrayList<String>();
		
		while (dbCursor.hasNext()) {
			DBObject dbObject = dbCursor.next();
			String jsonEntity = dbObject.toString();
			jsonEntities.add(jsonEntity);			
		}
		
		return jsonEntities;
	}

//	@Override
//	public List<ObjectNode> getEntities(String entity, String filter) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateEntity(String entity, ObjectNode jsonEntity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void removeEntity(String entity, ObjectNode jsonEntity) {
//		// TODO Auto-generated method stub
//		
//	}

}
