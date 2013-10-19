package uy.com.group05.baascore.dal.dao;

import java.util.List;

import uy.com.group05.baascore.common.entities.Entity;

public interface EntityDao extends GenericDao<Entity> {
	Entity readByName(String name);
	Entity readByName(long appId, String name);
	List<Entity> readAll(long appId);
	//Entity readById(long entityId);
}
