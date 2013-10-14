package uy.com.group05.baascore.dal.dao;

import java.util.List;
import java.util.UUID;

import uy.com.group05.baascore.common.entities.Client;

public interface ClientDao extends GenericDao<Client> {
	Client readByEmail(String email);
	Client readByEmail(long appId, String email);
	List<Client> readAll(long appId);
	Client readByAccessToken(long appId, UUID accessToken);
}

