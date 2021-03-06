package uy.com.group05.baascore.common.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "PERMISSIONS")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Application application;
	
	@ManyToOne
	private Entity entity;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private Operation operation;

	public Permission() {
	}
	
	public Permission(Application app, Entity entity, Role role, Operation oper){
		this.application = app;
		this.entity = entity;
		this.role = role;
		this.operation = oper;
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	//++++++++++++++++++++++++++++//
	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Permission)) return false;
	    Permission per = (Permission)o;
	    if (this.application.equals(per.getApplication()) && this.entity.equals(per.getEntity()) 
	    		&& this.role.equals(per.getRole()) && this.operation.equals(per.getOperation()))
	    	return true;
	    else
	    	return false;
	}
	
}
