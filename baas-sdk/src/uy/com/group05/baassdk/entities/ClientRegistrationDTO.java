package uy.com.group05.baassdk.entities;

import java.io.Serializable;

public class ClientRegistrationDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -2666085771218693572L;
	
	private boolean ok;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
