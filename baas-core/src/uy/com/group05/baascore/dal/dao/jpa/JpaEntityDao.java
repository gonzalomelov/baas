package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.common.entities.Entity;
import uy.com.group05.baascore.common.entities.Entity_;
import uy.com.group05.baascore.dal.dao.EntityDao;

public class JpaEntityDao extends JpaGenericDao<Entity> implements EntityDao {
	
	public Entity readByName(String name) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Entity> cq = cb.createQuery(this.type);
		Root<Entity> r = cq.from(this.type);
		cq.select(r);
		cq.where(cb.equal(r.get(Entity_.name), name));
		
		TypedQuery<Entity> typedQuery = em.createQuery(cq);
		
		List<Entity> applications = typedQuery.getResultList();
		
		return applications.isEmpty() ? null : applications.get(0);
	}
	
	public Entity readByName(long appId, String name) {
		TypedQuery<Entity> query = em.createQuery("SELECT c FROM ENTITIES c WHERE c.application.id = :appId AND c.name = :name", Entity.class);
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
