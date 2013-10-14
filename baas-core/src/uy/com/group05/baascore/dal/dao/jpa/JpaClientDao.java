package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;
import java.util.UUID;

import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.dal.dao.ClientDao;

public class JpaClientDao extends JpaGenericDao<Client> implements ClientDao {
	
	public Client readByEmail(String email) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.email = :email", Client.class);
		query.setParameter("email", email);
		
		List<Client> users = query.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	public Client readByEmail(long appId, String email) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.application.id = :appId AND c.email = :email", Client.class);
		query.setParameter("appId", appId);
		query.setParameter("email", email);
		
		List<Client> users = query.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
	
	public List<Client> readAll(long appId) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.application.id = :appId", Client.class);
		query.setParameter("appId", appId);
		return query.getResultList();
	}
	
	public Client readByAccessToken(long appId, UUID accessToken) {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.application.id = :appId AND c.accessToken = :accessToken", Client.class);
		//TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.application.id = :appId", Client.class);
		query.setParameter("appId", appId);
		query.setParameter("accessToken", accessToken);
		
		List<Client> users = query.getResultList();
		
		return users.isEmpty() ? null : users.get(0);
	}
}
