package uy.com.group05.baascore.common.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@javax.persistence.Entity
@Table(name = "ENTITIES")
public class Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@ManyToOne
	private Application application;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "entity" )
	@JsonIgnore
	private List<Permission> permission = new ArrayList<Permission>();

	@ManyToMany(mappedBy = "entities")
	private List<PushChannel> pushChannels = new ArrayList<PushChannel>();
	
	public Entity() {}
	
	public Entity(String nombre, Application app){
		this.name = nombre;
		this.application = app;
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
	
	public List<PushChannel> getPushChannels() {
		return pushChannels;
	}

	public void setPushChannels(List<PushChannel> pushChannels) {
		this.pushChannels = pushChannels;
	}

	//++++++++++++++++++++++++++++//
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Entity)) return false;
	    Entity entity = (Entity)o;
	    if (this.name.equals(entity.getName()) && this.application.equals(entity.getApplication()))
	    	return true;
	    else
	    	return false;
	}
	
	public boolean existsPermission(long idRole, long idOperation){
		if (this.permission.isEmpty())
			return false;
		Iterator<Permission> iter = this.permission.iterator();
		boolean encontre = false;
		while (iter.hasNext() && !encontre){
			Permission per = iter.next();
			encontre = (per.getRole().getId() == idRole) && (per.getOperation().getId() == idOperation);
		}
		return encontre;
	}
	
	public void addPermission(Permission per) {
		this.permission.add(per);
	}
	
	public void removePermission(Permission per){
		this.permission.remove(per);
	}
}
