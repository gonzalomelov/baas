
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
 * 2013-10-06T16:24:20.496-02:00
 * Generated source version: 2.4.6
 * 
 */

@javax.jws.WebService(
                      serviceName = "ApplicationServices",
                      portName = "ApplicationServicesPort",
                      targetNamespace = "http://impl.services.sl.baascore.group05.com.uy/",
                      wsdlLocation = "http://localhost:8080/baas-core/ApplicationServices?wsdl",
                      endpointInterface = "uy.com.group05.baascore.sl.services.soap.ApplicationServices")
                      
public class ApplicationServicesImpl implements ApplicationServices {

    private static final Logger LOG = Logger.getLogger(ApplicationServicesImpl.class.getName());

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.ApplicationServices#createApplication(long  idUser ,)java.lang.String  nombreApp ,)java.util.List<java.lang.String>  rolStr ,)java.util.List<java.lang.String>  entidadStr )*
     */
    public long createApplication(long idUser,java.lang.String nombreApp,java.util.List<java.lang.String> rolStr,java.util.List<java.lang.String> entidadStr) throws NombreAppAlreadyRegisteredException_Exception , MongoDBAlreadyExistsException_Exception , EntityCollectionAlreadyExistsException_Exception , UserNotRegisteredException_Exception    { 
        LOG.info("Executing operation createApplication");
        System.out.println(idUser);
        System.out.println(nombreApp);
        System.out.println(rolStr);
        System.out.println(entidadStr);
        try {
            long _return = 5193000206338984944l;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new NombreAppAlreadyRegisteredException_Exception("NombreAppAlreadyRegisteredException...");
        //throw new MongoDBAlreadyExistsException_Exception("MongoDBAlreadyExistsException...");
        //throw new EntityCollectionAlreadyExistsException_Exception("EntityCollectionAlreadyExistsException...");
        //throw new UserNotRegisteredException_Exception("UserNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.ApplicationServices#listApplications(long  idUser )*
     */
    public java.util.List<uy.com.group05.baascore.sl.services.soap.ApplicationDTO> listApplications(long idUser) throws UserNotRegisteredException_Exception    { 
        LOG.info("Executing operation listApplications");
        System.out.println(idUser);
        try {
            java.util.List<uy.com.group05.baascore.sl.services.soap.ApplicationDTO> _return = new java.util.ArrayList<uy.com.group05.baascore.sl.services.soap.ApplicationDTO>();
            uy.com.group05.baascore.sl.services.soap.ApplicationDTO _returnVal1 = new uy.com.group05.baascore.sl.services.soap.ApplicationDTO();
            _returnVal1.setId(5670193744875119284l);
            _returnVal1.setName("Name-1966129379");
            _returnVal1.setToken("Token-425543061");
            _returnVal1.setUrl("Url2097894622");
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new UserNotRegisteredException_Exception("UserNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.ApplicationServices#existsApplication(java.lang.String  nombreApp )*
     */
    public boolean existsApplication(java.lang.String nombreApp) { 
        LOG.info("Executing operation existsApplication");
        System.out.println(nombreApp);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.ApplicationServices#existsEntityApplication(java.lang.String  nomApp ,)java.lang.String  nomEntity )*
     */
    public boolean existsEntityApplication(java.lang.String nomApp,java.lang.String nomEntity) throws AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation existsEntityApplication");
        System.out.println(nomApp);
        System.out.println(nomEntity);
        try {
            boolean _return = true;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

    /* (non-Javadoc)
     * @see uy.com.group05.baascore.sl.services.soap.ApplicationServices#existsRoleApplication(java.lang.String  nomApp ,)java.lang.String  nomRole )*
     */
    public boolean existsRoleApplication(java.lang.String nomApp,java.lang.String nomRole) throws AppNotRegisteredException_Exception    { 
        LOG.info("Executing operation existsRoleApplication");
        System.out.println(nomApp);
        System.out.println(nomRole);
        try {
            boolean _return = false;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new AppNotRegisteredException_Exception("AppNotRegisteredException...");
    }

}