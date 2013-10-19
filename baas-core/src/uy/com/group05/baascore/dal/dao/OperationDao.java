package uy.com.group05.baascore.dal.dao;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Operation;

@Local
public interface OperationDao extends GenericDao<Operation> {
	Operation readByName(String name);
}
