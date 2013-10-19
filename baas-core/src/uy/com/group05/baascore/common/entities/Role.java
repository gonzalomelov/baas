package uy.com.group05.baascore.common.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@javax.persistence.Entity
@Table(name = "ROLES")
@XmlRootElement
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	@ManyToOne
	private Application application;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
	@JsonIgnore
	private List<Permission> permissions = new ArrayList<Permission>();

	public Role() {}
	
	public Role(String name, Application app){
		this.name = name;
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

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	//+++++++++++++++++++++++++++++++++++//
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Role)) return false;
	    Role role = (Role)o;
	    if (this.name.equals(role.getName()) && this.application.equals(role.getApplication()))
	    	return true;
	    else
	    	return false;
	}
	
	public boolean existsPermission(long idEntity, long idOperation){
		if (this.permissions.isEmpty())
			return false;
		Iterator<Permission> iter = this.permissions.iterator();
		boolean encontre = false;
		while (iter.hasNext() && !encontre){
			Permission per = iter.next();
			encontre = (per.getEntity().getId() == idEntity) && (per.getOperation().getId() == idOperation);
		}
		return encontre;
	}
	
	public void addPermission(Permission per) {
		this.permissions.add(per);
	}
	
	public void removePermission(Permission per){
		this.permissions.remove(per);
	}
}
