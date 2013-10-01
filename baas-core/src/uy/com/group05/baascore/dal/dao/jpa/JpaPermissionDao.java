package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.dal.dao.PermissionDao;

public class JpaPermissionDao extends JpaGenericDao<Permission> implements PermissionDao {
	public List<Permission> readAll(long appId) {
		TypedQuery<Permission> query = em.createQuery("SELECT c FROM PERMISSIONS c WHERE c.application.id = :appId", Permission.class);
		return query.getResultList();
	}
}
