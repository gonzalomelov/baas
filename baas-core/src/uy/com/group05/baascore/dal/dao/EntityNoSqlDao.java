package uy.com.group05.baascore.dal.dao;

import java.util.List;

public interface EntityNoSqlDao {
	void addEntity(String application, String entity, String jsonEntity);
	List<String> getEntities(String application, String entity);
//	List<ObjectNode> getEntities(String entity);
//	List<ObjectNode> getEntities(String entity, String filter);
//	void updateEntity(String entity, ObjectNode jsonEntity);
//	void removeEntity(String entity, ObjectNode jsonEntity);
}
