package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.common.entities.User;
import uy.com.group05.baascore.common.entities.User_;
import uy.com.group05.baascore.dal.dao.GenericDao;

public class JpaUserDao extends JpaGenericDao<User> implements GenericDao<User> {
	
	public User readByUsername(String username) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(this.type);
		Root<User> r = cq.from(this.type);
		cq.select(r);
		cq.where(cb.equal(r.get(User_.username), username));
		
		TypedQuery<User> typedQuery = em.createQuery(cq);
		
		List<User> users = typedQuery.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
}
