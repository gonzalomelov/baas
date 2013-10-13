package uy.com.group05.baasadmin.pl.models;

public class RolEntityPermission {
	private long RolId;
	
	private long EntityId;
	
	private long PermissionId;
	
	private boolean permission;

	public long getRolId() {
		return RolId;
	}

	public void setRolId(long rolId) {
		RolId = rolId;
	}

	public long getEntityId() {
		return EntityId;
	}

	public void setEntityId(long entityId) {
		EntityId = entityId;
	}

	public long getPermissionId() {
		return PermissionId;
	}

	public void setPermissionId(long permissionId) {
		PermissionId = permissionId;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}
	
	
}
