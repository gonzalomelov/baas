package uy.com.group05.baascore.dal.dao;

import uy.com.group05.baascore.common.entities.Role;

public interface RoleDao extends GenericDao<Role> {
	Role readByName(String name);
}
