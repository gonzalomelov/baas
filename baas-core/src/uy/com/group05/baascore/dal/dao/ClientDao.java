package uy.com.group05.baascore.dal.dao;

import uy.com.group05.baascore.common.entities.Client;

public interface ClientDao extends GenericDao<Client> {
	Client readByUsername(String username);
	Client readByEmail(String email);
}

