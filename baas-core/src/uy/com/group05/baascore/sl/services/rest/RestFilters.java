//package uy.com.group05.baascore.sl.services.rest;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//
//import org.apache.cxf.jaxrs.ext.RequestHandler;
//import org.apache.cxf.jaxrs.ext.ResponseHandler;
//import org.apache.cxf.jaxrs.model.ClassResourceInfo;
//import org.apache.cxf.jaxrs.model.OperationResourceInfo;
//import org.apache.cxf.message.Message;
//
//public class RestFilters implements RequestHandler, ResponseHandler {
//
//	@Context
//	private HttpServletRequest httpRequest;
//	
//	@Context
//	private UriInfo uriInfo;
//	
//	@Override
//	public Response handleRequest(
//			Message message,
//			ClassResourceInfo resourceInfo) {
//		
//		System.out.println("request");
//		
//		return null; 
//	}
//	
//	@Override
//	public Response handleResponse(
//			Message message,
//			OperationResourceInfo resourceInfo,
//			Response response) {
//		
//		System.out.println("response");
//		
//		return response;
//	}
//
//	
//
//}
