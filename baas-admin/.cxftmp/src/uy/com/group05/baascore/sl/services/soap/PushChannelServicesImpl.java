
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package uy.com.group05.baascore.sl.services.soap;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-10-27T16:08:11.505-02:00
 * Generated source version: 2.4.6
 * 
 */

@javax.jws.WebService(
                      serviceName = "PushServices",
                      portName = "PushServicesPort",
                      targetNamespace = "http://impl.services.sl.baascore.group05.com.uy/",
                      wsdlLocation = "http://localhost:8080/baas-core/PushServices?wsdl",
                      endpointInterface = "uy.com.group05.baascore.sl.services.soap.PushChannelServices")
                      
public class PushChannelServicesImpl implements PushChannelServices {

    private static final Logger LOG = Logger.getLogger(PushChannelServicesImpl.class.getName());

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#getPushChannelsOfApplication(long  arg0 )*
     */
    public java.util.List<uy.com.group05.baascore.sl.services.soap.SimplePushChannelDTO> getPushChannelsOfApplication(long arg0) throws AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation getPushChannelsOfApplication");
        System.out.println(arg0);
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.SimplePushChannelDTO> _return = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.SimplePushChannelDTO>();
            uy.com.group05.baascore.sl.services.soap.SimplePushChannelDTO _returnVal1 = new uy.com.group05.baascore.sl.services.soap.SimplePushChannelDTO();
            uy.com.group05.baascore.sl.services.soap.ApplicationDTO _returnVal1Application = new uy.com.group05.baascore.sl.services.soap.ApplicationDTO();
            _returnVal1Application.setApiClientId("ApiClientId-656773591");
            java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> _returnVal1ApplicationClients = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.ClientDTO>();
            _returnVal1Application.getClients().addAll(_returnVal1ApplicationClients);
            java.util.List<uy.com.group05.baascore.sl.services.soap.EntityDTO> _returnVal1ApplicationEntities = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.EntityDTO>();
            _returnVal1Application.getEntities().addAll(_returnVal1ApplicationEntities);
            _returnVal1Application.setId(6696636747886041449l);
            _returnVal1Application.setName("Name1915137577");
            java.util.List<uy.com.group05.baascore.sl.services.soap.RoleDTO> _returnVal1ApplicationRoles = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.RoleDTO>();
            _returnVal1Application.getRoles().addAll(_returnVal1ApplicationRoles);
            java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> _returnVal1ApplicationUsers = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.UserDTO>();
            _returnVal1Application.getUsers().addAll(_returnVal1ApplicationUsers);
            _returnVal1.setApplication(_returnVal1Application);
            java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> _returnVal1Clients = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.ClientDTO>();
            uy.com.group05.baascore.sl.services.soap.ClientDTO _returnVal1ClientsVal1 = new uy.com.group05.baascore.sl.services.soap.ClientDTO();
            _returnVal1ClientsVal1.setAppName("AppName374182043");
            uy.com.group05.baascore.sl.services.soap.ApplicationDTO _returnVal1ClientsVal1Application = new uy.com.group05.baascore.sl.services.soap.ApplicationDTO();
            _returnVal1ClientsVal1Application.setApiClientId("ApiClientId560256805");
            java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> _returnVal1ClientsVal1ApplicationClients = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.ClientDTO>();
            _returnVal1ClientsVal1Application.getClients().addAll(_returnVal1ClientsVal1ApplicationClients);
            java.util.List<uy.com.group05.baascore.sl.services.soap.EntityDTO> _returnVal1ClientsVal1ApplicationEntities = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.EntityDTO>();
            _returnVal1ClientsVal1Application.getEntities().addAll(_returnVal1ClientsVal1ApplicationEntities);
            _returnVal1ClientsVal1Application.setId(9208593603499363112l);
            _returnVal1ClientsVal1Application.setName("Name-1105732708");
            java.util.List<uy.com.group05.baascore.sl.services.soap.RoleDTO> _returnVal1ClientsVal1ApplicationRoles = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.RoleDTO>();
            _returnVal1ClientsVal1Application.getRoles().addAll(_returnVal1ClientsVal1ApplicationRoles);
            java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> _returnVal1ClientsVal1ApplicationUsers = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.UserDTO>();
            _returnVal1ClientsVal1Application.getUsers().addAll(_returnVal1ClientsVal1ApplicationUsers);
            _returnVal1ClientsVal1.setApplication(_returnVal1ClientsVal1Application);
            _returnVal1ClientsVal1.setEmail("Email-234947621");
            _returnVal1ClientsVal1.setId(-125304992188065166l);
            _returnVal1ClientsVal1.setLastname("Lastname1142200569");
            _returnVal1ClientsVal1.setName("Name110840138");
            _returnVal1ClientsVal1.setPassword("Password246409998");
            java.util.List<uy.com.group05.baascore.sl.services.soap.SimpleRoleDTO> _returnVal1ClientsVal1Roles = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.SimpleRoleDTO>();
            _returnVal1ClientsVal1.getRoles().addAll(_returnVal1ClientsVal1Roles);
            _returnVal1Clients.add(_returnVal1ClientsVal1);
            _returnVal1.getClients().addAll(_returnVal1Clients);
            _returnVal1.setId(-4701913445844114255l);
            _returnVal1.setName("Name915771541");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#assignEntityToPushChannel(long  idApp ,)long  idCanal ,)long  idEntity )*
     */
    public boolean assignEntityToPushChannel(long idApp,long idCanal,long idEntity) throws EntityNotRegisteredException_Exception , PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation assignEntityToPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        System.out.println(idEntity);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new EntityNotRegisteredException_Exception("EntityNotRegisteredException...");
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#sendNotificationToPushChannel(long  idApp ,)long  idCanal ,)java.lang.String  mensaje )*
     */
    public boolean sendNotificationToPushChannel(long idApp,long idCanal,java.lang.String mensaje) throws PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation sendNotificationToPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        System.out.println(mensaje);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#assignClientToPushChannel(long  idApp ,)long  idCanal ,)long  idCliente )*
     */
    public boolean assignClientToPushChannel(long idApp,long idCanal,long idCliente) throws ClientNotRegisteredException_Exception , PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation assignClientToPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        System.out.println(idCliente);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ClientNotRegisteredException_Exception("ClientNotRegisteredException...");
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#unassignEntityToPushChannel(long  idApp ,)long  idCanal ,)long  idEntity )*
     */
    public boolean unassignEntityToPushChannel(long idApp,long idCanal,long idEntity) throws EntityNotRegisteredException_Exception , PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation unassignEntityToPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        System.out.println(idEntity);
        try {
            boolean _return = true;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new EntityNotRegisteredException_Exception("EntityNotRegisteredException...");
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#createPushChannel(long  idApp ,)java.lang.String  nombreCanal )*
     */
    public long createPushChannel(long idApp,java.lang.String nombreCanal) throws PushChanAlreadyRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation createPushChannel");
        System.out.println(idApp);
        System.out.println(nombreCanal);
        try {
            long _return = -6970259318930556819l;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PushChanAlreadyRegisteredException_Exception("PushChanAlreadyRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#unassignClientFromPushChannel(long  idApp ,)long  idCanal ,)long  idCliente )*
     */
    public boolean unassignClientFromPushChannel(long idApp,long idCanal,long idCliente) throws ClientNotRegisteredException_Exception , PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation unassignClientFromPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        System.out.println(idCliente);
        try {
            boolean _return = true;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new ClientNotRegisteredException_Exception("ClientNotRegisteredException...");
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#getClientsFromPushChannel(long  idApp ,)long  idCanal )*
     */
    public java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> getClientsFromPushChannel(long idApp,long idCanal) throws PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation getClientsFromPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> _return = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.ClientDTO>();
            uy.com.group05.baascore.sl.services.soap.ClientDTO _returnVal1 = new uy.com.group05.baascore.sl.services.soap.ClientDTO();
            _returnVal1.setAppName("AppName-1228909193");
            uy.com.group05.baascore.sl.services.soap.ApplicationDTO _returnVal1Application = new uy.com.group05.baascore.sl.services.soap.ApplicationDTO();
            _returnVal1Application.setApiClientId("ApiClientId-1001305345");
            java.util.List<uy.com.group05.baascore.sl.services.soap.ClientDTO> _returnVal1ApplicationClients = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.ClientDTO>();
            _returnVal1Application.getClients().addAll(_returnVal1ApplicationClients);
            java.util.List<uy.com.group05.baascore.sl.services.soap.EntityDTO> _returnVal1ApplicationEntities = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.EntityDTO>();
            _returnVal1Application.getEntities().addAll(_returnVal1ApplicationEntities);
            _returnVal1Application.setId(5769945195031773992l);
            _returnVal1Application.setName("Name-1901825682");
            java.util.List<uy.com.group05.baascore.sl.services.soap.RoleDTO> _returnVal1ApplicationRoles = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.RoleDTO>();
            _returnVal1Application.getRoles().addAll(_returnVal1ApplicationRoles);
            java.util.List<uy.com.group05.baascore.sl.services.soap.UserDTO> _returnVal1ApplicationUsers = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.UserDTO>();
            _returnVal1Application.getUsers().addAll(_returnVal1ApplicationUsers);
            _returnVal1.setApplication(_returnVal1Application);
            _returnVal1.setEmail("Email1201064517");
            _returnVal1.setId(-3020314415362455106l);
            _returnVal1.setLastname("Lastname-88364408");
            _returnVal1.setName("Name1677504946");
            _returnVal1.setPassword("Password-1151395177");
            java.util.List<uy.com.group05.baascore.sl.services.soap.SimpleRoleDTO> _returnVal1Roles = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.SimpleRoleDTO>();
            uy.com.group05.baascore.sl.services.soap.SimpleRoleDTO _returnVal1RolesVal1 = new uy.com.group05.baascore.sl.services.soap.SimpleRoleDTO();
            _returnVal1RolesVal1.setId(1197665863916620188l);
            _returnVal1RolesVal1.setName("Name762733970");
            _returnVal1Roles.add(_returnVal1RolesVal1);
            _returnVal1.getRoles().addAll(_returnVal1Roles);
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#existsPushChannelApplication(long  idApp ,)java.lang.String  nombreCanal )*
     */
    public boolean existsPushChannelApplication(long idApp,java.lang.String nombreCanal) throws AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation existsPushChannelApplication");
        System.out.println(idApp);
        System.out.println(nombreCanal);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#getEntitiesAssociatedWithPushChannel(long  idApp ,)long  idCanal )*
     */
    public java.util.List<uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO> getEntitiesAssociatedWithPushChannel(long idApp,long idCanal) throws PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation getEntitiesAssociatedWithPushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO> _return = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO>();
            uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO _returnVal1 = new uy.com.group05.baascore.sl.services.soap.SimpleEntityDTO();
            _returnVal1.setId(2420124950707936437l);
            _returnVal1.setName("Name-947961905");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#deletePushChannel(long  idApp ,)long  idCanal )*
     */
    public long deletePushChannel(long idApp,long idCanal) throws PushChanNotRegisteredException_Exception , AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation deletePushChannel");
        System.out.println(idApp);
        System.out.println(idCanal);
        try {
            long _return = -8995899751556981019l;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new PushChanNotRegisteredException_Exception("PushChanNotRegisteredException...");
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.PushChannelServices#existsPushChannel(long  idCanal )*
     */
    public boolean existsPushChannel(long idCanal) { 
        LOG.info("Executing operation existsPushChannel");
        System.out.println(idCanal);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
