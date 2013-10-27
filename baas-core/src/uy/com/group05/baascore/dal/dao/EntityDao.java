package uy.com.group05.baascore.dal.dao;

import java.util.List;

import javax.ejb.Local;
import uy.com.group05.baascore.common.entities.Entity;

@Local
public interface EntityDao extends GenericDao<Entity> {
	Entity readByName(String name);
	Entity readByName(long appId, String name);
	List<Entity> readAll(long appId);
	Entity readById(long entityId);
}
