package uy.com.group05.baassdk.sync;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

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
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity + "/" + URLEncoder.encode(query, "ISO-8859-1");
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		//String accessToken = prefs.getString("accessToken", "Invalid");
		String accessToken = "851f7514-cb0f-483a-b523-324708a53364";
		
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
		
		String accessToken = "851f7514-cb0f-483a-b523-324708a53364";
		
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
	
	public boolean put(String entity, String json, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity + "/" + URLEncoder.encode(query, "ISO-8859-1");
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		//String accessToken = prefs.getString("accessToken", "Invalid");
		
		String accessToken = "851f7514-cb0f-483a-b523-324708a53364";
		
		httpPut.setHeader("accessToken", accessToken);
		
		StringEntity strEntity = new StringEntity(json);
		strEntity.setContentType("application/json");
		
		httpPut.setEntity(strEntity);
		
		HttpResponse httpResponse = httpClient.execute(httpPut);
		
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
	
	public boolean delete(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/entities" + "/" + appName + "/" + entity + "/" + URLEncoder.encode(query, "ISO-8859-1");
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpDelete httpDelete = new HttpDelete(url);
		
		SharedPreferences prefs =
				context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		//String accessToken = prefs.getString("accessToken", "Invalid");
		
		String accessToken = "851f7514-cb0f-483a-b523-324708a53364";
		
		httpDelete.setHeader("accessToken", accessToken);
		
		HttpResponse httpResponse = httpClient.execute(httpDelete);
		
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
		String accessToken = "851f7514-cb0f-483a-b523-324708a53364";
		
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
	
	public List<String> getEntities()
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		List<String> entitiesNames = new ArrayList<String>();
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		String url = serviceUrl + "/api/listEntities" + "/" + appName;
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		if (statusCode != HttpStatus.SC_OK) {
			return entitiesNames;	
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
		
		StringBuilder builder = new StringBuilder();
		String aux = "";

		while ((aux = br.readLine()) != null) {
		    builder.append(aux);
		}

		String entitiesNamesJson = builder.toString();
		
		JsonParser jsonParser = new JsonParser();
		JsonArray entitiesNamesJsonArray = (JsonArray) jsonParser.parse(entitiesNamesJson); 
		
		for (int i = 0; i < entitiesNamesJsonArray.size(); i++) {
			JsonPrimitive entityName = (JsonPrimitive) entitiesNamesJsonArray.get(i);
			entitiesNames.add(entityName.toString());
		}
		
		return entitiesNames;
	}
}
