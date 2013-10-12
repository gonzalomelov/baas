package uy.com.group05.baasadmin.pl.models;

public class Rol {

	private String roleName;
	private long id;
	
	public Rol(String name, long id){
		this.roleName = name;
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
