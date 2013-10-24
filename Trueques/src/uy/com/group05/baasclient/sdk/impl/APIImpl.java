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
import uy.com.group05.baasclient.sdk.entities.ClientDTO;
import uy.com.group05.baasclient.sdk.entities.ClientRegistrationDTO;
import uy.com.group05.baasclient.sdk.utils.AssetsPropertyReader;
import android.content.Context;
import android.content.SharedPreferences;

public class APIImpl implements APIFacade {

	private Context context;
	
	public APIImpl (Context context) {
		this.context = context;
	}
	
	@Override
	public String get(String entity)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		String accessToken = prefs.getString("accessToken", "Invalid");
		
		httpGet.setHeader("accessToken", accessToken);
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		if (statusCode != HttpStatus.SC_OK) {
			return "";	
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
		
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = br.readLine()) != null) {
		    builder.append(aux);
		}

		String json = builder.toString();
		
		return json;
	}

	@Override
	public boolean post(String entity, Object jsonObj, Type type )
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		String accessToken = prefs.getString("accessToken", "Invalid");
		
		httpPost.setHeader("accessToken", accessToken);
		
		Gson gson = new Gson();
		
		StringEntity strEntity = new StringEntity(gson.toJson(jsonObj, type));
		strEntity.setContentType("application/json");
		
		httpPost.setEntity(strEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		Boolean ret = Boolean.valueOf(false);
		
		if (statusCode != HttpStatus.SC_OK) {
			return ret;
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
		
		ret = gson.fromJson(br, Boolean.class);
		
		return ret;
	}

}
