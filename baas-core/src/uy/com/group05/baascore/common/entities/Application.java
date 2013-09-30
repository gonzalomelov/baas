package uy.com.group05.baascore.common.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

@javax.persistence.Entity
@Table(name = "APPLICATIONS")
@XmlRootElement
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	
	private UUID token;
	
	@JoinTable(name = "USERS_APPLICATIONS")
	@ManyToMany
	private List<User> users;
	
	@OneToMany(mappedBy = "application")
	private List<Client> clients;
	
	@OneToMany(mappedBy = "application")
	private List<Role> roles;
	
	@OneToMany(mappedBy = "application")
	private List<uy.com.group05.baascore.common.entities.Entity> entities;

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
