package uy.com.group05.baasclient.sdk;

import android.content.Context;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import uy.com.group05.baasclient.sdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baasclient.sdk.entities.ClientRegistrationDTO;

public interface ClientFacade {
	ClientRegistrationDTO register(Context context, String email, String password,
			String name, String lastname)
				throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	ClientAuthenticationDTO authenticate(Context context, String email, String password)
		throws UnsupportedEncodingException, ClientProtocolException, IOException;
}
