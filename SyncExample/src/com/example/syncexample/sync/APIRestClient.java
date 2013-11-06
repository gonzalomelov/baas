package com.example.syncexample.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;

public class APIRestClient {
	
	private Context context;
	
	public APIRestClient(Context context) {
		this.context = context;
	}
	
	public String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity + "/" + query;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		//String accessToken = prefs.getString("accessToken", "Invalid");
		String accessToken = "40250661-0cc4-4a2d-9bee-1b338ec72c3e";
		
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
	
	public boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		//String accessToken = prefs.getString("accessToken", "Invalid");
		
		String accessToken = "40250661-0cc4-4a2d-9bee-1b338ec72c3e";
		
		httpPost.setHeader("accessToken", accessToken);
		
		StringEntity strEntity = new StringEntity(json);
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
		
		Gson gson = new Gson();
		ret = gson.fromJson(br, Boolean.class);
		
		return ret;
	}
	
	public String sync(String entity, String jsonEntities)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/sync" + "/" + appName + "/" + entity;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk", Context.MODE_PRIVATE);
		
		//String accessToken = prefs.getString("accessToken", "Invalid");
		String accessToken = "40250661-0cc4-4a2d-9bee-1b338ec72c3e";
		
		httpPost.setHeader("accessToken", accessToken);
		
		StringEntity strEntity = new StringEntity(jsonEntities);
		strEntity.setContentType("application/json");
		
		httpPost.setEntity(strEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
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
}
