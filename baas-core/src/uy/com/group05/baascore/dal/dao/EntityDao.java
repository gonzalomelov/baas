package uy.com.group05.baascore.dal.dao;

import uy.com.group05.baascore.common.entities.Entity;

public interface EntityDao extends GenericDao<Entity> {
	Entity readByName(String name);
}
