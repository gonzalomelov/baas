package uy.com.group05.baascore.dal.dao;

import java.util.List;

import uy.com.group05.baascore.common.entities.Role;

public interface RoleDao extends GenericDao<Role> {
	Role readByName(String name);
	Role readByName(long appId, String name);
	List<Role> readAll(long appId);
}
