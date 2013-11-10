package uy.com.group05.baassdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

public interface APIFacade {
	String get(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	boolean post(String entity, String json)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	boolean update(String entity, String query, String values)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
	
	boolean delete(String entity, String query)
			throws UnsupportedEncodingException, ClientProtocolException, IOException;
}
