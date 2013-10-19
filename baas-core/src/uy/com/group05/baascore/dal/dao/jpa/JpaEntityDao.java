package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.dal.dao.EntityDao;

public class JpaEntityDao extends JpaGenericDao<Entity> implements EntityDao {
	
	static final Logger logger = Logger.getLogger(JpaEntityDao.class);
	
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
		TypedQuery<Entity> query = em.createQuery("SELECT c FROM Entity c WHERE c.application.id = :appId", Entity.class);
		query.setParameter("appId", appId);
		return query.getResultList();
	}
	
//	public Entity readById(long entityId) {
//		Entity e = em.find(Entity.class, entityId);
//		/*TypedQuery<Entity> query = em.createQuery("SELECT c FROM Entity c WHERE c.id = :id", Entity.class);
//		query.setParameter("id", entityId);
//		
//		Entity e = query.getSingleResult();
//		*/
//		if(e!=null){
//			//e.getApplication();
//			e.getPermission();
//			logger.info("e!=null ..." + e.getPermission());
//		}
//		return e;
//	}
}
