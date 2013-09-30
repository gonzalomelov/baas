package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Application_;
import uy.com.group05.baascore.dal.dao.ApplicationDao;

public class JpaApplicationDao extends JpaGenericDao<Application> implements ApplicationDao {
	
	public Application readByName(String name) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Application> cq = cb.createQuery(this.type);
		Root<Application> r = cq.from(this.type);
		cq.select(r);
		cq.where(cb.equal(r.get(Application_.name), name));
		
		TypedQuery<Application> typedQuery = em.createQuery(cq);
		
		List<Application> applications = typedQuery.getResultList();
		
		return applications.isEmpty() ? null : applications.get(0);
	}
}