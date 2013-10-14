
package uy.com.group05.baascore.sl.services.soap;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import uy.com.group05.baascore.sl.services.impl.ApplicationServices;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-10-14T19:41:40.294-02:00
 * Generated source version: 2.4.6
 * 
 */
public final class ApplicationServices_ApplicationServicesPort_Client {

    private static final QName SERVICE_NAME = new QName("http://impl.services.sl.baascore.group05.com.uy/", "ApplicationServices");

    private ApplicationServices_ApplicationServicesPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ApplicationServices.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ApplicationServices ss = new ApplicationServices(wsdlURL, SERVICE_NAME);
        ApplicationServices port = ss.getApplicationServicesPort();  
        
        {
        System.out.println("Invoking addPushChannelToApplication...");
        java.lang.String _addPushChannelToApplication_nombreApp = "_addPushChannelToApplication_nombreApp-1921871385";
        java.lang.String _addPushChannelToApplication_nombreCanal = "_addPushChannelToApplication_nombreCanal1732741672";
        try {
            long _addPushChannelToApplication__return = port.addPushChannelToApplication(_addPushChannelToApplication_nombreApp, _addPushChannelToApplication_nombreCanal);
            System.out.println("addPushChannelToApplication.result=" + _addPushChannelToApplication__return);

        } catch (PushChanAlreadyRegisteredException_Exception e) { 
            System.out.println("Expected exception: PushChanAlreadyRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking createApplication...");
        long _createApplication_idUser = 5945648282920976748l;
        java.lang.String _createApplication_nombreApp = "_createApplication_nombreApp-1047716144";
        java.util.List<java.lang.String> _createApplication_rolStr = new java.util.ArrayList<java.lang.String>();
        java.lang.String _createApplication_rolStrVal1 = "_createApplication_rolStrVal840668633";
        _createApplication_rolStr.add(_createApplication_rolStrVal1);
        java.util.List<java.lang.String> _createApplication_entidadStr = new java.util.ArrayList<java.lang.String>();
        java.lang.String _createApplication_entidadStrVal1 = "_createApplication_entidadStrVal1562090694";
        _createApplication_entidadStr.add(_createApplication_entidadStrVal1);
        try {
            long _createApplication__return = port.createApplication(_createApplication_idUser, _createApplication_nombreApp, _createApplication_rolStr, _createApplication_entidadStr);
            System.out.println("createApplication.result=" + _createApplication__return);

        } catch (NombreAppAlreadyRegisteredException_Exception e) { 
            System.out.println("Expected exception: NombreAppAlreadyRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (MongoDBAlreadyExistsException_Exception e) { 
            System.out.println("Expected exception: MongoDBAlreadyExistsException has occurred.");
            System.out.println(e.toString());
        } catch (EntityCollectionAlreadyExistsException_Exception e) { 
            System.out.println("Expected exception: EntityCollectionAlreadyExistsException has occurred.");
            System.out.println(e.toString());
        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking editApplication...");
        java.lang.String _editApplication_nombreApp = "_editApplication_nombreApp429891556";
        java.util.List<java.lang.String> _editApplication_rolStr = new java.util.ArrayList<java.lang.String>();
        java.lang.String _editApplication_rolStrVal1 = "_editApplication_rolStrVal-387139698";
        _editApplication_rolStr.add(_editApplication_rolStrVal1);
        java.util.List<java.lang.String> _editApplication_entidadStr = new java.util.ArrayList<java.lang.String>();
        java.lang.String _editApplication_entidadStrVal1 = "_editApplication_entidadStrVal1775592850";
        _editApplication_entidadStr.add(_editApplication_entidadStrVal1);
        try {
            long _editApplication__return = port.editApplication(_editApplication_nombreApp, _editApplication_rolStr, _editApplication_entidadStr);
            System.out.println("editApplication.result=" + _editApplication__return);

        } catch (MongoDBAlreadyExistsException_Exception e) { 
            System.out.println("Expected exception: MongoDBAlreadyExistsException has occurred.");
            System.out.println(e.toString());
        } catch (EntityCollectionAlreadyExistsException_Exception e) { 
            System.out.println("Expected exception: EntityCollectionAlreadyExistsException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking removePushChannelFromApplication...");
        java.lang.String _removePushChannelFromApplication_nombreApp = "_removePushChannelFromApplication_nombreApp-1619430410";
        java.lang.String _removePushChannelFromApplication_nombreCanal = "_removePushChannelFromApplication_nombreCanal895175540";
        try {
            long _removePushChannelFromApplication__return = port.removePushChannelFromApplication(_removePushChannelFromApplication_nombreApp, _removePushChannelFromApplication_nombreCanal);
            System.out.println("removePushChannelFromApplication.result=" + _removePushChannelFromApplication__return);

        } catch (PushChanNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: PushChanNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking listApplications...");
        long _listApplications_idUser = 8607794125156819166l;
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.ApplicationDTO> _listApplications__return = port.listApplications(_listApplications_idUser);
            System.out.println("listApplications.result=" + _listApplications__return);

        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking editRoleApplication...");
        long _editRoleApplication_idApp = -123971100808830008l;
        long _editRoleApplication_idUser = 402895161746322184l;
        java.lang.String _editRoleApplication_nomRole = "_editRoleApplication_nomRole318893745";
        try {
            long _editRoleApplication__return = port.editRoleApplication(_editRoleApplication_idApp, _editRoleApplication_idUser, _editRoleApplication_nomRole);
            System.out.println("editRoleApplication.result=" + _editRoleApplication__return);

        } catch (RoleAlreadyRegisteredException_Exception e) { 
            System.out.println("Expected exception: RoleAlreadyRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (UserCantAccessAppException_Exception e) { 
            System.out.println("Expected exception: UserCantAccessAppException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking assignUserToApplication...");
        java.lang.String _assignUserToApplication_nombreApp = "_assignUserToApplication_nombreApp-929306185";
        long _assignUserToApplication_idUser = -6504620751608193788l;
        try {
            boolean _assignUserToApplication__return = port.assignUserToApplication(_assignUserToApplication_nombreApp, _assignUserToApplication_idUser);
            System.out.println("assignUserToApplication.result=" + _assignUserToApplication__return);

        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsApplication...");
        java.lang.String _existsApplication_nombreApp = "_existsApplication_nombreApp-243049095";
        boolean _existsApplication__return = port.existsApplication(_existsApplication_nombreApp);
        System.out.println("existsApplication.result=" + _existsApplication__return);


        }
        {
        System.out.println("Invoking existsEntityApplication...");
        long _existsEntityApplication_idApp = -4754677751305647596l;
        java.lang.String _existsEntityApplication_nomEntity = "_existsEntityApplication_nomEntity1000751502";
        try {
            boolean _existsEntityApplication__return = port.existsEntityApplication(_existsEntityApplication_idApp, _existsEntityApplication_nomEntity);
            System.out.println("existsEntityApplication.result=" + _existsEntityApplication__return);

        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsRoleApplication...");
        long _existsRoleApplication_idApp = 8390212879464055542l;
        java.lang.String _existsRoleApplication_nomRole = "_existsRoleApplication_nomRole510619356";
        try {
            boolean _existsRoleApplication__return = port.existsRoleApplication(_existsRoleApplication_idApp, _existsRoleApplication_nomRole);
            System.out.println("existsRoleApplication.result=" + _existsRoleApplication__return);

        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking existsPushChannelApplication...");
        java.lang.String _existsPushChannelApplication_nombreApp = "_existsPushChannelApplication_nombreApp-613642349";
        java.lang.String _existsPushChannelApplication_nombreCanal = "_existsPushChannelApplication_nombreCanal1970556628";
        try {
            boolean _existsPushChannelApplication__return = port.existsPushChannelApplication(_existsPushChannelApplication_nombreApp, _existsPushChannelApplication_nombreCanal);
            System.out.println("existsPushChannelApplication.result=" + _existsPushChannelApplication__return);

        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking unassignUserFromApplication...");
        java.lang.String _unassignUserFromApplication_nombreApp = "_unassignUserFromApplication_nombreApp-1236407881";
        long _unassignUserFromApplication_idUser = -4361897608393609520l;
        try {
            boolean _unassignUserFromApplication__return = port.unassignUserFromApplication(_unassignUserFromApplication_nombreApp, _unassignUserFromApplication_idUser);
            System.out.println("unassignUserFromApplication.result=" + _unassignUserFromApplication__return);

        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (UserNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: UserNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking editEntityApplication...");
        long _editEntityApplication_idApp = -741699142429841606l;
        long _editEntityApplication_idUser = 1739858230051345081l;
        java.lang.String _editEntityApplication_nomEntity = "_editEntityApplication_nomEntity1665560203";
        try {
            long _editEntityApplication__return = port.editEntityApplication(_editEntityApplication_idApp, _editEntityApplication_idUser, _editEntityApplication_nomEntity);
            System.out.println("editEntityApplication.result=" + _editEntityApplication__return);

        } catch (EntityAlreadyRegisteredException_Exception e) { 
            System.out.println("Expected exception: EntityAlreadyRegisteredException has occurred.");
            System.out.println(e.toString());
        } catch (EntityCollectionAlreadyExistsException_Exception e) { 
            System.out.println("Expected exception: EntityCollectionAlreadyExistsException has occurred.");
            System.out.println(e.toString());
        } catch (UserCantAccessAppException_Exception e) { 
            System.out.println("Expected exception: UserCantAccessAppException has occurred.");
            System.out.println(e.toString());
        } catch (AppNotRegisteredException_Exception e) { 
            System.out.println("Expected exception: AppNotRegisteredException has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
