package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Client_;
import uy.com.group05.baascore.dal.dao.ClientDao;

public class JpaClientDao extends JpaGenericDao<Client> implements ClientDao {
	
	public Client readByUsername(String username) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(this.type);
		Root<Client> r = cq.from(this.type);
		cq.select(r);
		
		cq.where(cb.equal(r.get(Client_.name), username));
		
		TypedQuery<Client> typedQuery = em.createQuery(cq);
		
		List<Client> users = typedQuery.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	public Client readByEmail(String email) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(this.type);
		Root<Client> r = cq.from(this.type);
		cq.select(r);
		cq.where(cb.equal(r.get(Client_.email), email));
		
		TypedQuery<Client> typedQuery = em.createQuery(cq);
		
		List<Client> users = typedQuery.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	public Client readByUsername(long appId, String username) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM CLIENTS c WHERE c.application.id = :appId AND c.username = :username", Client.class);
		query.setParameter("appId", appId);
		query.setParameter("username", username);
		
		List<Client> users = query.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	public Client readByEmail(long appId, String email) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM CLIENTS c WHERE c.application.id = :appId AND c.email = :email", Client.class);
		query.setParameter("appId", appId);
		query.setParameter("email", email);
		
		List<Client> users = query.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	public List<Client> readAll(long appId) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM CLIENTS c WHERE c.application.id = :appId", Client.class);
		return query.getResultList();
	}
}
