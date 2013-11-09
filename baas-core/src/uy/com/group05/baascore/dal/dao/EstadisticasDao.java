package uy.com.group05.baascore.dal.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import uy.com.group05.baascore.common.entities.Estadisticas;

@Local
public interface EstadisticasDao extends GenericDao<Estadisticas> {
	
	List<Estadisticas> readAll(long appId);
	
	int readByType(long appId, int type, Date minDate, Date maxDate);
	
}
