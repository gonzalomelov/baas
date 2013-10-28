package uy.com.group05.baascore.sl.services.rest;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/push")
public interface PushRest {
	@POST
	@Path("/updateRegId")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateRegId(
			@HeaderParam("accessToken") UUID accessToken,
			@FormParam("appName") String appName,
			@FormParam("regId") String regId);
	
	@POST
	@Path("/sendNotificationToPushChannel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean sendNotification(
			@HeaderParam("accessToken") UUID accessToken,
			@FormParam("appName") String appName,
			@FormParam("pushChanName") String pushChanName,
			@FormParam("msgKey") String msgKey,
			@FormParam("msgValue") String msgValue);
	
	@POST
	@Path("/subscribe")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean subscribeToPushChannel(
			@HeaderParam("accessToken") UUID accessToken,
			@FormParam("appName") String appName,
			@FormParam("pushChanName") String pushChanName);	
}
