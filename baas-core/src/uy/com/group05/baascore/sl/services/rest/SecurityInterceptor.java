//package uy.com.group05.baascore.sl.services.rest;
//
//import java.lang.reflect.Method;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.Provider;
//
//import org.jboss.resteasy.annotations.interception.ServerInterceptor;
//import org.jboss.resteasy.core.ResourceMethod;
//import org.jboss.resteasy.core.ServerResponse;
//import org.jboss.resteasy.spi.Failure;
//import org.jboss.resteasy.spi.HttpRequest;
//import org.jboss.resteasy.spi.interception.AcceptedByMethod;
//import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
//
//@Provider
//@ServerInterceptor
//public class SecurityInterceptor implements PreProcessInterceptor, AcceptedByMethod {
//
//	@Override
//	public boolean accept(Class declaring, Method method) {
//		return declaring.isAnnotationPresent(GET.class);
//	}
//	
//	@Override
//	public ServerResponse preProcess(HttpRequest request, ResourceMethod method)
//			throws Failure, WebApplicationException {
//		
//		HttpHeaders httpHeaders = request.getHttpHeaders();
//		
//		MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
//		
//		
//		String authorization = requestHeaders.getFirst("Authorization");
//		
//		return null;
//	}
//
//}