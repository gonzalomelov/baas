package uy.com.group05.baascore.dal.dao;

import java.util.Collection;

public interface GenericDao<T> {
	public T create(T t);
	public T read(Object id);
	public Collection<T> readAll();
	public T update(T t);
	public void delete(Object id);
}
