package uy.com.group05.baascore.dal.dao;

import java.util.List;

import uy.com.group05.baascore.common.entities.Permission;

public interface PermissionDao extends GenericDao<Permission> {
	List<Permission> readAll(long appId);
}
