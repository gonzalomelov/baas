package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;

public class PermissionRoleDTO implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7446551578846666530L;

	private long idRole;
	private long idOperation;
	private boolean has;
	
	
	public long getIdRole() {
		return idRole;
	}
	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
	public long getIdOperation() {
		return idOperation;
	}
	public void setIdOperation(long idOperation) {
		this.idOperation = idOperation;
	}
	public boolean isHas() {
		return has;
	}
	public void setHas(boolean has) {
		this.has = has;
	}
	
	
	
	
	
}
