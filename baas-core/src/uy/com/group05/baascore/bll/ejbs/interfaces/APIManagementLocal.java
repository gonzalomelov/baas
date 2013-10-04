package uy.com.group05.baascore.bll.ejbs.interfaces;

import java.util.List;

public interface APIManagementLocal {
	List<String> get(String appName, String entity, String query);
	boolean post(String appName, String entity, String jsonObj);
}
