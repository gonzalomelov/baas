package uy.com.group05.baascore.dal.dao;

import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Application;
import uy.com.group05.baascore.common.entities.ExternalApplication;

@Local
public interface ExternalApplicationDao extends GenericDao<ExternalApplication> {
	
}
