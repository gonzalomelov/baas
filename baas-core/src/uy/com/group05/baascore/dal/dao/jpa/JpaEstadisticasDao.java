package uy.com.group05.baascore.dal.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import uy.com.group05.baascore.common.entities.Client;
import uy.com.group05.baascore.common.entities.Estadisticas;
import uy.com.group05.baascore.dal.dao.EstadisticasDao;

public class JpaEstadisticasDao extends JpaGenericDao<Estadisticas> implements EstadisticasDao{


	@Override
	public List<Estadisticas> readAll(long appId) {
		TypedQuery<Estadisticas> query = em.createQuery("SELECT c FROM Estadisticas c WHERE c.appId = :appId", Estadisticas.class);
		query.setParameter("appId", appId);
		
		List<Estadisticas> estadisticas = query.getResultList();
		
		return estadisticas;
	}

	@Override
	public int readByType(long appId, int type, Date minDate, Date maxDate) {
		Query query = em.createQuery("Select count(*) FROM Estadisticas c WHERE c.AppId = :appId AND c.TipoEstadisticas = :tipo AND c.Tiempo >= :minDate AND Tiempo <= :maxDate");
		query.setParameter("appId", appId);
		query.setParameter("tipo", type);
		query.setParameter("minDate", minDate);
		query.setParameter("maxDate", maxDate);
		
		int estadisticas = ((Number)query.getSingleResult()).intValue();
		
		return estadisticas;
	}

}
