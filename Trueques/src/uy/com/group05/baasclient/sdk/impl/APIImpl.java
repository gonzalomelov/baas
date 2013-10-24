package uy.com.group05.baasclient.sdk.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import uy.com.group05.baasclient.sdk.APIFacade;
import uy.com.group05.baasclient.sdk.utils.AssetsPropertyReader;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class APIImpl implements APIFacade {

	private Context context;
	
	public APIImpl (Context context) {
		this.context = context;
	}
	
	@Override
	public String get(String entity)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    
	    String json;
	    
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	//Llamo al servicio
	    	APIRestClient restClient = new APIRestClient(context);
	    	json = restClient.get(entity);
	    } else {
	    	//Llamo a la base local
	    	json = "";
	    }
		
		return json;
	}

	@Override
	public boolean post(String entity, Object jsonObj, Type type )
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		ConnectivityManager connMgr = (ConnectivityManager) 
    	        context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    
	    boolean result;
	    
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	//Llamo al servicio
	    	APIRestClient restClient = new APIRestClient(context);
	    	result = restClient.post(entity, jsonObj, type);
	    } else {
	    	//Llamo a la base local
	    	result = false;
	    }
		
		return result;
	}

}
