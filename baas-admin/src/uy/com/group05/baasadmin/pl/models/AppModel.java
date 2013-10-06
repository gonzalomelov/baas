package uy.com.group05.baasadmin.pl.models;

import java.util.ArrayList;
import java.util.List;

public class AppModel {

	private List<Application> aplicaciones;

	public AppModel(){
		aplicaciones = new ArrayList<Application>();
	}
	
	public List<Application> getAplicaciones() {
		return aplicaciones;
	}

	public void setAplicaciones(List<Application> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}
	
	
}
