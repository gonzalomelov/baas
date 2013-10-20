package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;

public class PermissionEntityDTO implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 7446551578846666530L;

	private long idEntity;
	private long idOperation;
	private boolean has;
	
	
	public long getIdEntity() {
		return idEntity;
	}
	public void setIdEntity(long idEntity) {
		this.idEntity = idEntity;
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
