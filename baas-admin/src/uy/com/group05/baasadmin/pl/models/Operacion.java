package uy.com.group05.baasadmin.pl.models;

public class Operacion {

	private long id;
	
	private String name;
	
	public Operacion(){}
	
	public Operacion(long oppId, String oppName){
		id = oppId;
		name = oppName;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
