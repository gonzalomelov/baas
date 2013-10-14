package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Operation;
import uy.com.group05.baascore.dal.dao.OperationDao;

public class JpaOperationDao extends JpaGenericDao<Operation> implements OperationDao {
	
	public Operation readByName(String name) {
		TypedQuery<Operation> typedQuery = em.createQuery("select o from Operation o.name = :name", Operation.class);
		typedQuery.setParameter("name", name);
		
		
		List<Operation> operations = typedQuery.getResultList();
		
		return operations.isEmpty() ? null : operations.get(0);
	}
}
