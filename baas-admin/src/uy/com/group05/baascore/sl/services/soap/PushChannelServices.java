package uy.com.group05.baascore.sl.services.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-09T00:10:06.517-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebService(targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", name = "PushChannelServices")
@XmlSeeAlso({ObjectFactory.class})
public interface PushChannelServices {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getPushChannelsOfApplication", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetPushChannelsOfApplication")
    @WebMethod
    @ResponseWrapper(localName = "getPushChannelsOfApplicationResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetPushChannelsOfApplicationResponse")
    public java.util.List<uy.com.group05.baascore.sl.services.soap.SimplePushChannelDTO> getPushChannelsOfApplication(
        @WebParam(name = "arg0", targetNamespace = "")
        long arg0
    ) throws AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "assignEntityToPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignEntityToPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "assignEntityToPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignEntityToPushChannelResponse")
    public boolean assignEntityToPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal,
        @WebParam(name = "idEntity", targetNamespace = "")
        long idEntity
    ) throws EntityNotRegisteredException_Exception, PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sendNotificationToPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.SendNotificationToPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "sendNotificationToPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.SendNotificationToPushChannelResponse")
    public boolean sendNotificationToPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal,
        @WebParam(name = "msgKey", targetNamespace = "")
        java.lang.String msgKey,
        @WebParam(name = "msgValue", targetNamespace = "")
        java.lang.String msgValue
    ) throws PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "assignClientToPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignClientToPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "assignClientToPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.AssignClientToPushChannelResponse")
    public boolean assignClientToPushChannel(
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal,
        @WebParam(name = "idCliente", targetNamespace = "")
        long idCliente
    ) throws ClientNotRegisteredException_Exception, PushChanNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "unassignEntityToPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.UnassignEntityToPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "unassignEntityToPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.UnassignEntityToPushChannelResponse")
    public boolean unassignEntityToPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal,
        @WebParam(name = "idEntity", targetNamespace = "")
        long idEntity
    ) throws EntityNotRegisteredException_Exception, PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "savePushChannelEntities", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.SavePushChannelEntities")
    @WebMethod
    @ResponseWrapper(localName = "savePushChannelEntitiesResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.SavePushChannelEntitiesResponse")
    public boolean savePushChannelEntities(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal,
        @WebParam(name = "entity", targetNamespace = "")
        java.util.List<uy.com.group05.baascore.sl.services.soap.SimplePushChannelEntityDTO> entity
    ) throws EntityNotRegisteredException_Exception, PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "createPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.CreatePushChannel")
    @WebMethod
    @ResponseWrapper(localName = "createPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.CreatePushChannelResponse")
    public long createPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "nombreCanal", targetNamespace = "")
        java.lang.String nombreCanal
    ) throws PushChanAlreadyRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "unassignClientFromPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.UnassignClientFromPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "unassignClientFromPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.UnassignClientFromPushChannelResponse")
    public boolean unassignClientFromPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal,
        @WebParam(name = "idCliente", targetNamespace = "")
        long idCliente
    ) throws ClientNotRegisteredException_Exception, PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getClientsFromPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetClientsFromPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "getClientsFromPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetClientsFromPushChannelResponse")
    public java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> getClientsFromPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal
    ) throws PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "existsPushChannelApplication", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.ExistsPushChannelApplication")
    @WebMethod
    @ResponseWrapper(localName = "existsPushChannelApplicationResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.ExistsPushChannelApplicationResponse")
    public boolean existsPushChannelApplication(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "nombreCanal", targetNamespace = "")
        java.lang.String nombreCanal
    ) throws AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getEntitiesAssociatedWithPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetEntitiesAssociatedWithPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "getEntitiesAssociatedWithPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.GetEntitiesAssociatedWithPushChannelResponse")
    public java.util.List<uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO> getEntitiesAssociatedWithPushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal
    ) throws PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "deletePushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.DeletePushChannel")
    @WebMethod
    @ResponseWrapper(localName = "deletePushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.DeletePushChannelResponse")
    public long deletePushChannel(
        @WebParam(name = "idApp", targetNamespace = "")
        long idApp,
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal
    ) throws PushChanNotRegisteredException_Exception, AppNotRegisteredException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "existsPushChannel", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.ExistsPushChannel")
    @WebMethod
    @ResponseWrapper(localName = "existsPushChannelResponse", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/", className = "uy.com.group05.baascore.sl.services.soap.ExistsPushChannelResponse")
    public boolean existsPushChannel(
        @WebParam(name = "idCanal", targetNamespace = "")
        long idCanal
    );
}
