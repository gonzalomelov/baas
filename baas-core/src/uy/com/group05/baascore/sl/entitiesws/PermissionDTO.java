package uy.com.group05.baascore.sl.entitiesws;

public class PermissionDTO {
	private long id;
	
	private ApplicationDTO application;

	private EntityDTO entity;

	private RoleDTO role;

	private OperationDTO operation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ApplicationDTO getApplication() {
		return application;
	}

	public void setApplication(ApplicationDTO application) {
		this.application = application;
	}

	public EntityDTO getEntity() {
		return entity;
	}

	public void setEntity(EntityDTO entity) {
		this.entity = entity;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public OperationDTO getOperation() {
		return operation;
	}

	public void setOperation(OperationDTO operation) {
		this.operation = operation;
	}
	
	
}
