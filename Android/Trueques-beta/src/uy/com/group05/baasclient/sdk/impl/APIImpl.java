package uy.com.group05.baasclient.sdk.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baasclient.sdk.APIFacade;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class APIImpl implements APIFacade {

	private Context context;
	
	public APIImpl (Context context) {
		this.context = context;
	}
	
	@Override
	public String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    
	    String json="";
	    
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	//Llamo al servicio
	    	APIRestClient restClient = new APIRestClient(context);
	    	json = restClient.get(entity, query);
	    } else {
	    	//Llamo a la base local
//	    	APISQLiteClient sqliteClient = new APISQLiteClient(context);
//	    	json = sqliteClient.get(entity);
	    }
		
		return json;
	}

	@Override
	public boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    
	    boolean result=false;
	    
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	//Llamo al servicio
	    	APIRestClient restClient = new APIRestClient(context);
	    	result = restClient.post(entity, json);
	    } else {
	    	//Llamo a la base local
//	    	APISQLiteClient sqliteClient = new APISQLiteClient(context);
//	    	result = sqliteClient.post(entity, json);
	    }
		
		return result;
	}

}
