package uy.com.group05.baascore.sl.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.group05.baascore.sl.entitiesws.SimplePushChannelDTO;

@Path("/push")
public interface PushRest {
	@POST
	@Path("/updateRegId")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateRegId(
			@HeaderParam("accessToken") String accessToken,
			@FormParam("appName") String appName,
			@FormParam("regId") String regId);
	
	@POST
	@Path("/sendNotificationToPushChannel")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean sendNotificationToPushChannel(
			@HeaderParam("accessToken") String accessToken,
			@FormParam("appName") String appName,
			@FormParam("pushChanName") String pushChanName,
			@FormParam("msgKey") String msgKey,
			@FormParam("msgValue") String msgValue);
	
	@POST
	@Path("/sendNotificationToClient")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean sendNotificationToClient(
			@HeaderParam("accessToken") String accessToken,
			@FormParam("appName") String appName,
			@FormParam("mailReceiver") String mailReceiver,
			@FormParam("msgKey") String msgKey,
			@FormParam("msgValue") String msgValue,
			@FormParam("difKey") String difKey,
			@FormParam("difValue") String difValue);
	
	@POST
	@Path("/subscribe")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean subscribeToPushChannel(
			@HeaderParam("accessToken") String accessToken,
			@FormParam("appName") String appName,
			@FormParam("pushChanName") String pushChanName);
	
	@POST
	@Path("/unsubscribe")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean unsubscribeFromPushChannel(
			@HeaderParam("accessToken") String accessToken,
			@FormParam("appName") String appName,
			@FormParam("pushChanName") String pushChanName);
	
	@GET
	@Path("/getPushChannels")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SimplePushChannelDTO> getPushChannelsOfApplication(
			@HeaderParam("appName") String appName);
}
