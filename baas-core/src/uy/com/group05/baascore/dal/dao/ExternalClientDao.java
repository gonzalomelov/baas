package uy.com.group05.baascore.dal.dao;

import java.util.UUID;

import uy.com.group05.baascore.common.entities.ExternalClient;

public interface ExternalClientDao extends GenericDao<ExternalClient> {
	ExternalClient readByAccessToken(long appId, UUID accessToken);
}
