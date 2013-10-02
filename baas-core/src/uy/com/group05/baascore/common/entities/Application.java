package uy.com.group05.baascore.common.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "APPLICATIONS")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	
	@Column(unique = true)
	private String url;
	
	private UUID token;
	
	@JoinTable(name = "USERS_APPLICATIONS")
	@ManyToMany
	private List<User> users = new ArrayList<User>();
	
	@OneToMany(mappedBy = "application")
	private List<Client> clients = new ArrayList<Client>();
	
	@OneToMany(mappedBy = "application")
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToMany(mappedBy = "application")
	private List<uy.com.group05.baascore.common.entities.Entity> entities
		= new ArrayList<uy.com.group05.baascore.common.entities.Entity>();

	public Application() {}
	
	public Application(String nombreApp, User owner) {
		this.name = nombreApp;
		users.add(owner);
	}
	
	public Application(String name, User owner, List<Role> roles, List<Entity> entidades) {
		this.name = name;
		//this.users = new ArrayList<User>();
		users.add(owner);
		this.roles = roles;
		this.entities = entidades;
		//this.clients = new ArrayList<Client>();
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

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<uy.com.group05.baascore.common.entities.Entity> getEntities() {
		return entities;
	}

	public void setEntities(
			List<uy.com.group05.baascore.common.entities.Entity> entities) {
		this.entities = entities;
	}
	
	
}
