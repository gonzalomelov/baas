package uy.com.group05.baascore.dal.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uy.com.group05.baascore.dal.dao.GenericDao;

public abstract class JpaGenericDao<T> implements GenericDao<T> {
	@PersistenceContext(unitName ="primary")
	protected EntityManager em;
	
	protected Class<T> type;
	
	public JpaGenericDao() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public T create(T t) {
		em.persist(t);
		return t;
	}
	
	@Override
	public T read(Object id) {
		return em.find(type, id);
	}
	
	@Override
	public Collection<T> readAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(type);
		Root<T> r = cq.from(type);
		cq.select(r);
		return em.createQuery(cq).getResultList();
	}
	
	@Override
	public T update(T t) {
		return em.merge(t);
	}
	
	@Override
	public void delete(Object id) {
		em.remove(em.find(type, id));
	}
}
