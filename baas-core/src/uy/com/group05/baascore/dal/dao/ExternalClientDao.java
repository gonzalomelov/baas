package uy.com.group05.baascore.dal.dao;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.ExternalClient;

@Local
public interface ExternalClientDao extends GenericDao<ExternalClient> {
	ExternalClient readByAccessToken(long appId, String accessToken);
}
