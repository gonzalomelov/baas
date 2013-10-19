package uy.com.group05.baascore.dal.dao;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Role;

@Local
public interface RoleDao extends GenericDao<Role> {
	Role readByName(String name);
	Role readByName(long appId, String name);
	List<Role> readAll(long appId);
	List<Role> readAll(long appId, long clientId);
}
