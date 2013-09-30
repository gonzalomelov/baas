package uy.com.group05.baascore.dal.dao;

import uy.com.group05.baascore.common.entities.Operation;

public interface OperationDao extends GenericDao<Operation> {
	Operation readByName(String name);
}
