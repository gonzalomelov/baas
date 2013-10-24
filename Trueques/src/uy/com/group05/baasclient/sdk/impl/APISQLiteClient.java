package uy.com.group05.baasclient.sdk.impl;

import java.lang.reflect.Type;

import android.content.Context;

public class APISQLiteClient {
	
	private Context context;
	
	public APISQLiteClient(Context context) {
		this.context = context;
	}
	
	public String get(String entity) {
		return "";
	}
	
	public boolean post(String entity, Object jsonObj, Type type) {
		return true;
	}
}
