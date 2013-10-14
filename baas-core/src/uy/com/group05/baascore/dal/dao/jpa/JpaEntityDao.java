package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.dal.dao.EntityDao;

public class JpaEntityDao extends JpaGenericDao<Entity> implements EntityDao {
	
	public Entity readByName(String name) {
		TypedQuery<Entity> query = em.createQuery("SELECT c FROM Entity c WHERE c.name = :name", Entity.class);
		query.setParameter("name", name);
		
		List<Entity> entities = query.getResultList();
		
		return entities.isEmpty() ? null : entities.get(0);
	}
	
	public Entity readByName(long appId, String name) {
		TypedQuery<Entity> query = em.createQuery("SELECT c FROM Entity c WHERE c.application.id = :appId AND c.name = :name", Entity.class);
		query.setParameter("appId", appId);
		query.setParameter("name", name);
		
		List<Entity> entities = query.getResultList();
		
		return entities.isEmpty() ? null : entities.get(0);
	}
	
	public List<Entity> readAll(long appId) {
		TypedQuery<Entity> query = em.createQuery("SELECT c FROM ENTITIES c WHERE c.application.id = :appId", Entity.class);
		return query.getResultList();
	}
}
