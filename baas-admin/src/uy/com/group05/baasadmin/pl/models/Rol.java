package uy.com.group05.baasadmin.pl.models;

public class Rol {

	private String roleName;
	private int id;
	
	public Rol(String name, int id){
		this.roleName = name;
		this.id = id;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
