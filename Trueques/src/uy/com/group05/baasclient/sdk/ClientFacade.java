package uy.com.group05.baasclient.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import uy.com.group05.baasclient.sdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baasclient.sdk.entities.ClientRegistrationDTO;

public interface ClientFacade {
	ClientRegistrationDTO register(String email, String password,
			String name, String lastname)
				throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	ClientAuthenticationDTO authenticate(String email, String password)
		throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	boolean updateRegIdOfClient(Context context, String regId)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
	


	
	
	
	
	
	
	
	
	
	
	boolean sendNotificationToPushChannel(Context context, String nombreCanal, String msgKey, String msgValue)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;











}
