package uy.com.group05.baasadmin.pl.models;

import java.util.ArrayList;
import java.util.List;

public class AppModel {

	private List<Application> aplicaciones;
	
	private int cantApps;

	public AppModel(){
		aplicaciones = new ArrayList<Application>();
	}
	
	public List<Application> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<Application> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public int getCantApps() {
		return aplicaciones.size();
	}

	public void setCantApps(int cantApps) {
		this.cantApps = cantApps;
	}

	
	
	
}
