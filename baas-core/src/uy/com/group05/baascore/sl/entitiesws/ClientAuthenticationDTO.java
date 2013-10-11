package uy.com.group05.baascore.sl.entitiesws;

import java.io.Serializable;

public class ClientAuthenticationDTO implements Serializable {
	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4239100687374087964L;
	
	private boolean ok;
	private String accessToken;
	private String refreshToken;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
