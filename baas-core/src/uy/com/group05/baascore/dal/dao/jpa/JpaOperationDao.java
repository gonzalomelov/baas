package uy.com.group05.baascore.dal.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.common.entities.Operation;
import uy.com.group05.baascore.common.entities.Operation_;
import uy.com.group05.baascore.dal.dao.OperationDao;

public class JpaOperationDao extends JpaGenericDao<Operation> implements OperationDao {
	
	public Operation readByName(String name) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Operation> cq = cb.createQuery(this.type);
		Root<Operation> r = cq.from(this.type);
		cq.select(r);
		cq.where(cb.equal(r.get(Operation_.name), name));
		
		TypedQuery<Operation> typedQuery = em.createQuery(cq);
		
		List<Operation> applications = typedQuery.getResultList();
		
		return applications.isEmpty() ? null : applications.get(0);
	}
}
