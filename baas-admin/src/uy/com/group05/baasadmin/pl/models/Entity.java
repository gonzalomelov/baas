package uy.com.group05.baasadmin.pl.models;

public class Entity {
	
	private String name;
	
	private long id;
	
	private boolean sync;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isSync() {
		return sync;
	}
	public void setSync(boolean sync) {
		this.sync = sync;
	}
	

	
}
