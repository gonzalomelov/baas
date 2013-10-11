package uy.com.group05.baasclient.sdk;

public interface APIFacade {
	String get(String appName, String entity);
	boolean post(String appName, String entity, String jsonObj);
}
