package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;
import java.util.UUID;

import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.ExternalClient;
import uy.com.group05.baascore.dal.dao.ExternalClientDao;

public class JpaExternalClientDao extends JpaGenericDao<ExternalClient> implements ExternalClientDao {

	@Override
	public ExternalClient readByAccessToken(long appId, UUID accessToken) {
		TypedQuery<ExternalClient> query = em.createQuery("SELECT c FROM ExternalClient c WHERE c.application.id = :appId AND c.accessToken = :accessToken", ExternalClient.class);
		query.setParameter("appId", appId);
		query.setParameter("accessToken", accessToken);
		
		List<ExternalClient> externalClients = query.getResultList();
		
		return externalClients.isEmpty() ? null : externalClients.get(0);
	}

}