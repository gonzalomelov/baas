package uy.com.group05.baascore.dal.dao;

import java.util.List;
import java.util.UUID;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.ExternalApplication;
import uy.com.group05.baascore.common.entities.Role;

public interface ApplicationDao extends GenericDao<Application> {
	Application readByName(String name);
	List<Application> readFromUser(long userId);
	Application readFromApiClientId(UUID apiClientId);
	ExternalApplication readAssociatedExternalApplication(long appId, long externalAppId);
	List<Role> readExternalClientsAppRole(long appId);
}
