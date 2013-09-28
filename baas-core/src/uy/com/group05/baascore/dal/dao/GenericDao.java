package uy.com.group05.baascore.dal.dao;

import java.util.Collection;

public interface GenericDao<T> {
	public T create(T t);// throws Exception;
	public T read(Object id);// throws Exception;
	public Collection<T> readAll();// throws Exception;
	public T update(T t);// throws Exception;
	public void delete(Object id);// throws Exception;
}
