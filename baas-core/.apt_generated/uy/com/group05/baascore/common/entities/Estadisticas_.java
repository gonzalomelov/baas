package uy.com.group05.baascore.common.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Estadisticas.class)
public abstract class Estadisticas_ {

	public static volatile SingularAttribute<Estadisticas, Long> id;
	public static volatile SingularAttribute<Estadisticas, Date> Tiempo;
	public static volatile SingularAttribute<Estadisticas, Integer> TipoEstadisticas;
	public static volatile SingularAttribute<Estadisticas, Long> AppId;

}

