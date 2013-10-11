package uy.com.group05.baasclient.sdk.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import org.apache.http.message.BufferedHeader;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;

import uy.com.group05.baasclient.sdk.ClientFacade;
import uy.com.group05.baasclient.sdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baasclient.sdk.entities.ClientDTO;
import uy.com.group05.baasclient.sdk.entities.ClientRegistrationDTO;
import uy.com.group05.baasclient.sdk.utils.AssetsPropertyReader;

public class ClientImpl implements ClientFacade {

	@Override
	public ClientRegistrationDTO register(Context context, String email, String password,
			String name, String lastname)
				throws UnsupportedEncodingException, ClientProtocolException, IOException {
				
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/client/authenticate";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		String apiClientId = AssetsPropertyReader.getProperties(context, "apiClientId");
		String apiClientSecret = AssetsPropertyReader.getProperties(context, "apiClientSecret");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("apiClientId", apiClientId);
		httpPost.setHeader("apiClientSecret", apiClientSecret);
		
		ClientDTO client = new ClientDTO();
		
		client.setAppName(appName);
		client.setEmail(email);
		client.setLastname(lastname);
		client.setName(name);
		client.setPassword(password);
		
		Gson gson = new Gson();
		
		StringEntity entity = new StringEntity(gson.toJson(client));
		
		httpPost.setEntity(entity);
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		ClientRegistrationDTO registrationDTO = new ClientRegistrationDTO();
		registrationDTO.setOk(false);
		
		if (statusCode == HttpStatus.SC_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpResponse.getEntity().getContent()));
			
			registrationDTO = gson.fromJson(br, ClientRegistrationDTO.class);
		}
		
		return registrationDTO;
	}

	@Override
	public ClientAuthenticationDTO authenticate(Context context, String email, String password)
		throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		String serviceUrl = AssetsPropertyReader.getProperties(context, "baasUrl");
		
		String url = serviceUrl + "/client/authenticate";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		
		String apiClientId = AssetsPropertyReader.getProperties(context, "apiClientId");
		String apiClientSecret = AssetsPropertyReader.getProperties(context, "apiClientSecret");
		String appName = AssetsPropertyReader.getProperties(context, "appName");
		
		httpPost.setHeader("apiClientId", apiClientId);
		httpPost.setHeader("apiClientSecret", apiClientSecret);
		
		List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
		formParameters.add(new BasicNameValuePair("email", email));
		formParameters.add(new BasicNameValuePair("password", password));
		formParameters.add(new BasicNameValuePair("appName", appName));
		
		httpPost.setEntity(new UrlEncodedFormEntity(formParameters));
		
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		ClientAuthenticationDTO authenticationDTO = new ClientAuthenticationDTO();
		authenticationDTO.setOk(false);
		
		if (statusCode == HttpStatus.SC_OK) {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpResponse.getEntity().getContent()));
				
			Gson gson = new Gson(); 
			
			authenticationDTO = gson.fromJson(br, ClientAuthenticationDTO.class);
		}
		
		return authenticationDTO;
	}

}
