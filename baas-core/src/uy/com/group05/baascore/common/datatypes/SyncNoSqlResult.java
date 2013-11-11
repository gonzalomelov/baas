package uy.com.group05.baascore.common.datatypes;

public class SyncNoSqlResult {

	private String json;
	
	private boolean sincronizar;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public boolean isSincronizar() {
		return sincronizar;
	}

	public void setSincronizar(boolean sincronizar) {
		this.sincronizar = sincronizar;
	}
}
