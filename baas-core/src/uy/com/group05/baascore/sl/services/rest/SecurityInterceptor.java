package uy.com.group05.baascore.sl.services.rest;

import java.lang.reflect.Method;
import java.util.UUID;

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
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import uy.com.group05.baascore.bll.ejbs.interfaces.ClientManagementLocal;

@ServerInterceptor
public class SecurityInterceptor implements PreProcessInterceptor, AcceptedByMethod {

	@Inject
	private ClientManagementLocal clientManagementLocal;
	
	@Override
	public ServerResponse preProcess(HttpRequest request, ResourceMethod method)
			throws Failure, WebApplicationException {
		
		HttpHeaders httpHeaders = request.getHttpHeaders();
		
		MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
	
		String url = request.getUri().getPath();
		String[] urlParams = url.split("/");
	
		String methodGroup = urlParams[2];
		
		if (methodGroup.equals("entities")) {
			UUID accessToken;
			
			try {
				accessToken = UUID.fromString(requestHeaders.getFirst("accessToken"));
			}
			catch (IllegalArgumentException e) {
				return new ServerResponse("Access denied for these resource", 403, new Headers<Object>());
			}
			
			String appName = urlParams[3];
			String entity = urlParams[4];
			String query = urlParams.length == 6 ? urlParams[5] : "";
			
			String operation = request.getHttpMethod();
			
			if (!clientManagementLocal.validate(appName, operation, entity, accessToken))
			{
				return new ServerResponse("Access denied for these resource", 403, new Headers<Object>());
			}	
		}
		
		return null;
	}

	@Override
	public boolean accept(Class declaring, Method method) {
		if (declaring == ClientRest.class) {
			return false;
		}
		
		if (declaring == APIRest.class) {
			return true;
		}
		
		if (declaring == PushRest.class) {
			return false;
		}
		
		return true;
	}

}