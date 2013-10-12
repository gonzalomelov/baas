package uy.com.group05.baasadmin.pl.models;

import java.util.List;

public class Application {
	
	private long Id;
	
	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Entity> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Entity> entidades) {
		this.entidades = entidades;
	}

	private String Name;
	
	private List<Rol> roles;
	
	private List<Entity> entidades;
	
	private List<Cliente> clientes;
	
	public Application() {
		
	};
	
	public Application(String name, long id){
		this.Name = name;
		this.Id = id;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
