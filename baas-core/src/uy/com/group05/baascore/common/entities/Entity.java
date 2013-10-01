package uy.com.group05.baascore.common.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@javax.persistence.Entity
@Table(name = "ENTITIES")
public class Entity implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -9116100722219292553L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@ManyToOne
	private Application application;
	
	@OneToMany(mappedBy = "entity")
	@JsonIgnore
	private List<Permission> permission = new ArrayList<Permission>();

	public Entity() {}
	
	public Entity(String nombre, Application app){
		this.name = nombre;
		this.application = app;
		this.permission = new ArrayList<Permission>();
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
	
	
}
