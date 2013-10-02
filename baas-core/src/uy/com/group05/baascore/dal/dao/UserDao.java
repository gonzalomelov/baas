package uy.com.group05.baascore.dal.dao;

import uy.com.group05.baascore.common.entities.User;

public interface UserDao extends GenericDao<User> {
	User readByEmail(String email);
}
