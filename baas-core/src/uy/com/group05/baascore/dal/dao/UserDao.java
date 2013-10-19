package uy.com.group05.baascore.dal.dao;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.User;

@Local
public interface UserDao extends GenericDao<User> {
	User readByEmail(String email);
	User readByFbId(String fbId);
}
