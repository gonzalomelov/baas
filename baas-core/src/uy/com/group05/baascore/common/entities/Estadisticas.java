package uy.com.group05.baascore.common.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADISTICAS")
public class Estadisticas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int TipoEstadisticas;
	
	private Date Tiempo;
	
	private long AppId;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTipoEstadisticas() {
		return TipoEstadisticas;
	}

	public void setTipoEstadisticas(int tipoEstadisticas) {
		TipoEstadisticas = tipoEstadisticas;
	}

	public Date getTiempo() {
		return Tiempo;
	}

	public void setTiempo(Date tiempo) {
		Tiempo = tiempo;
	}

	public long getAppId() {
		return AppId;
	}

	public void setAppId(long appId) {
		AppId = appId;
	}
}
