package uy.com.group05.baascore.dal.dao;

import java.util.List;

import uy.com.group05.baascore.common.entities.Application;

public interface ApplicationDao extends GenericDao<Application> {
	Application readByName(String name);
	List<Application> readFromUser(long userId);
}
