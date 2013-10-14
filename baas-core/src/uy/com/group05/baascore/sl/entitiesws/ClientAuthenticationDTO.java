package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;
import java.util.UUID;

public class ClientAuthenticationDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4239100687374087964L;
	
	private boolean ok;
	private UUID accessToken;
	private UUID refreshToken;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public UUID getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(UUID accessToken) {
		this.accessToken = accessToken;
	}
	public UUID getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(UUID refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
