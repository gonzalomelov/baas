package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Role;
import uy.com.group05.baascore.common.entities.Role_;
import uy.com.group05.baascore.dal.dao.RoleDao;

public class JpaRoleDao extends JpaGenericDao<Role> implements RoleDao {
	
	public Role readByName(String name) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(this.type);
		Root<Role> r = cq.from(this.type);
		cq.select(r);
		cq.where(cb.equal(r.get(Role_.name), name));
		
		TypedQuery<Role> typedQuery = em.createQuery(cq);
		
		List<Role> applications = typedQuery.getResultList();
		
		return applications.isEmpty() ? null : applications.get(0);
	}
	
	public Role readByName(long appId, String name) {
		TypedQuery<Role> query = em.createQuery("SELECT c FROM ROLES c WHERE c.application.id = :appId AND c.name = :name", Role.class);
		query.setParameter("appId", appId);
		query.setParameter("name", name);
		
		List<Role> roles = query.getResultList();
		
		return roles.isEmpty() ? null : roles.get(0);
	}
	
	public List<Role> readAll(long appId) {
		TypedQuery<Role> query = em.createQuery("SELECT c FROM ROLES c WHERE c.application.id = :appId", Role.class);
		return query.getResultList();
	}
	
	public List<Role> readAll(long appId, long clientId) {
		TypedQuery<Role> query =
				em.createQuery("select distinct r "
						+ "from Client c inner join c.roles r "
						+ "where c.id = :clientId and"
						+ "c.application.id = :appId", Role.class);
		
		query.setParameter("appId", appId);
		query.setParameter("clientId", clientId);
		
		return query.getResultList();
	}
}
