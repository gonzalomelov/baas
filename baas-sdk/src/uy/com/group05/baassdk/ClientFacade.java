package uy.com.group05.baassdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baassdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baassdk.entities.ClientRegistrationDTO;

import android.content.Context;

public interface ClientFacade {
	ClientRegistrationDTO register(String email, String password,
			String name, String lastname)
				throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	ClientAuthenticationDTO authenticate(String email, String password)
		throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	boolean updateRegIdOfClient(Context context, String regId)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
}
