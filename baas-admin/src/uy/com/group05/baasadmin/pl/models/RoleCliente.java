package uy.com.group05.baasadmin.pl.models;

public class RoleCliente {

	private long clientId;
	
	private long roleId;
	
	private boolean rol;

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public boolean isRol() {
		return rol;
	}

	public void setRol(boolean rol) {
		this.rol = rol;
	}
}
