package uy.com.group05.baascore.sl.services.rest;

import javax.xml.bind.DatatypeConverter;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;

@ServerInterceptor
public class SecurityInterceptor implements PreProcessInterceptor {

	@Inject
	private ClientManagementLocal clientManagementLocal;
	
	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method)
			throws Failure, WebApplicationException {
		
		HttpHeaders httpHeaders = request.getHttpHeaders();
		
		MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
		
		String authorization = requestHeaders.getFirst("Authorization");
	
		if (authorization == null || !authorization.substring(0, 5).equals("Basic")) {
			return new ServerResponse("Access denied for these resource", 403, new Headers<Object>());	
		}
		
		String credentialsBase64 = authorization.substring(6);
		
		byte[] decoded = DatatypeConverter.parseBase64Binary(credentialsBase64);
		String credentials = new String(decoded); 
		
		String[] s = credentials.split(":");
		
		String email = s[0];
		String password = s[1];
		
		if (!clientManagementLocal.validateClientCredentials(email, password))
		{
			return new ServerResponse("Access denied for these resource", 403, new Headers<Object>());
		}
		
		return null;
	}

}