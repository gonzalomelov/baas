package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;

public class RolesClientDTO implements Serializable{
	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7446551578846666530L;
	private boolean has;
	private long idRole;
	
	
	public boolean isHas() {
		return has;
	}
	
	public void setHas(boolean has) {
		this.has = has;
	}
	
	public long getIdRole() {
		return idRole;
	}
	
	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
