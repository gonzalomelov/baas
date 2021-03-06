package uy.com.group05.baascore.dal.dao;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Permission;

@Local
public interface PermissionDao extends GenericDao<Permission> {
	List<Permission> readAll(long appId);
	List<Permission> readAll(long appId, long roleId);
	List<Permission> readAllFromEntity(long appId, long entityId);
	List<Permission> readAllFromRol(long appId, long rolId);
	public Permission readWithoutId(long appId, long entityId, long roleId, long operId);
}
