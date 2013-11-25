package uy.com.group05.baasadmin.pl.models;

public class EntitySync {
	public String name;
	public boolean sync;
	
	public EntitySync(String name, boolean sync) {
		this.name = name;
		this.sync = sync;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSync() {
		return sync;
	}
	public void setSync(boolean sync) {
		this.sync = sync;
	}
	
	
}
