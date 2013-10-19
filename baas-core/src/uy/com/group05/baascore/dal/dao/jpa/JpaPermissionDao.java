package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.Permission;
import uy.com.group05.baascore.dal.dao.PermissionDao;

@Stateless
public class JpaPermissionDao extends JpaGenericDao<Permission> implements PermissionDao {
	public List<Permission> readAll(long appId) {
		TypedQuery<Permission> query = em.createQuery("SELECT c FROM Permission c WHERE c.application.id = :appId", Permission.class);
		query.setParameter("appId", appId);
		return query.getResultList();
	}
	
	public List<Permission> readAll(long appId, long roleId) {
		TypedQuery<Permission> query =
				em.createQuery("select distinct p "
						+ "from Permission p "
						+ "where p.role.id = :roleId and "
						+ "p.application.id = :appId", Permission.class);
		
		query.setParameter("appId", appId);
		query.setParameter("roleId", roleId);
		
		return query.getResultList();
	}
	
	public List<Permission> readAllFromEntity(long appId, long entityId) {
		TypedQuery<Permission> query =
				em.createQuery("select distinct p "
						+ "from Permission p "
						+ "where p.entity.id = :entityId and "
						+ "p.application.id = :appId", Permission.class);
		
		query.setParameter("appId", appId);
		query.setParameter("entityId", entityId);
		
		return query.getResultList();
	}
	
	public Permission readWithoutId(long appId, long entityId, long roleId, long operId) {
		TypedQuery<Permission> query =
				em.createQuery("select distinct p "
						+ "from Permission p "
						+ "where p.entity.id = :entityId and "
						+ "p.application.id = :appId and "
						+ "p.role.id = :roleId and "
						+ "p.operation.id = :operId", Permission.class);
		
		query.setParameter("appId", appId);
		query.setParameter("entityId", entityId);
		query.setParameter("roleId", roleId);
		query.setParameter("operId", operId);
		
		return query.getSingleResult();
	}
}
