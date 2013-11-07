package uy.com.group05.baasclient.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;

public interface APIFacade {
	String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
}
