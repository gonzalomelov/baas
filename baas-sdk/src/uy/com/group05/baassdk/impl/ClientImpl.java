package uy.com.group05.baassdk.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import uy.com.group05.baassdk.ClientFacade;
import uy.com.group05.baassdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baassdk.entities.ClientDTO;
import uy.com.group05.baassdk.entities.ClientRegistrationDTO;
import uy.com.group05.baassdk.sync.AssetsPropertyReader;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class ClientImpl implements ClientFacade {

	private Context context;
	
	public ClientImpl (Context context) {
		this.context = context;
	}
	
	@Override
	public ClientRegistrationDTO register(String email, String password, String name, String lastname)
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
				
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/client/register";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		String apiClientId = AssetsPropertyReader.getProperties(context, "apiClientId");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("apiClientId", apiClientId);
		
		ClientDTO client = new ClientDTO();
		
		client.setAppName(appName);
		client.setEmail(email);
		client.setLastname(lastname);
		client.setName(name);
		client.setPassword(password);
		
		Gson gson = new Gson();
		
		StringEntity entity = new StringEntity(gson.toJson(client));
		entity.setContentType("application/json");
		
		httpPost.setEntity(entity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		ClientRegistrationDTO registrationDTO = new ClientRegistrationDTO();
		registrationDTO.setOk(false);
		
		if (statusCode != HttpStatus.SC_OK) {
			return registrationDTO;
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
		
		registrationDTO = gson.fromJson(br, ClientRegistrationDTO.class);
		
		return registrationDTO;
	}

	@Override
	public ClientAuthenticationDTO authenticate(String email, String password)
		throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/client/authenticate";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		String apiClientId = AssetsPropertyReader.getProperties(context, "apiClientId");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("apiClientId", apiClientId);
		
		List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
		formParameters.add(new BasicNameValuePair("email", email));
		formParameters.add(new BasicNameValuePair("password", password));
		formParameters.add(new BasicNameValuePair("appName", appName));
		
		httpPost.setEntity(new UrlEncodedFormEntity(formParameters));
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		ClientAuthenticationDTO authenticationDTO = new ClientAuthenticationDTO();
		authenticationDTO.setOk(false);
		
		if (statusCode != HttpStatus.SC_OK) {
			return authenticationDTO;	
		}
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
			
		Gson gson = new Gson(); 
		
		authenticationDTO = gson.fromJson(br, ClientAuthenticationDTO.class);
		
		SharedPreferences prefs =
			     context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		prefs.edit().putString("accessToken", authenticationDTO.getAccessToken()).commit();
		prefs.edit().putString("refreshToken", authenticationDTO.getRefreshToken()).commit();
		
		if (authenticationDTO==null)
			Log.i("TAG", "authenticationDTO==NULL");
		else
			Log.i("TAG", authenticationDTO.getAccessToken());
		
		return authenticationDTO;
	}

	@Override
	public boolean updateRegIdOfClient(Context context, String regId)
			throws UnsupportedEncodingException, ClientProtocolException,
			IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/push/updateRegId";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		SharedPreferences prefs =
			     context.getSharedPreferences("uy.com.group05.baasclient.sdk",Context.MODE_PRIVATE);
		
		String accessToken = prefs.getString("accessToken", "");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("accessToken", accessToken);
		
		List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
		formParameters.add(new BasicNameValuePair("appName", appName));
		formParameters.add(new BasicNameValuePair("regId", regId));
		
		httpPost.setEntity(new UrlEncodedFormEntity(formParameters));
		
		android.util.Log.i("GCM SDK", "accessToken: " + accessToken);
		android.util.Log.i("GCM SDK", "appName: " + appName);
		android.util.Log.i("GCM SDK", "regId: " + regId);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		android.util.Log.i("GCM SDK", "statusCode: " + statusCode);
		
		if (statusCode == HttpStatus.SC_OK) {
			return true;
		}
		else {
			return false;
		}
	}
}
