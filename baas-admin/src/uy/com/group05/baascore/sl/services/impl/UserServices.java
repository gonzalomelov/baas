package uy.com.group05.baascore.sl.services.impl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-09T15:50:24.738-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "UserServices", 
                  wsdlLocation = "http://baas-gonzalomelov.rhcloud.com/baas-core/UserServices?wsdl",
                  targetNamespace = "http://impl.services.sl.baascore.group05.com.uy/") 
public class UserServices extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.services.sl.baascore.group05.com.uy/", "UserServices");
    public final static QName UserServicesPort = new QName("http://impl.services.sl.baascore.group05.com.uy/", "UserServicesPort");
    static {
        URL url = null;
        try {
            url = new URL("http://baas-gonzalomelov.rhcloud.com/baas-core/UserServices?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserServices.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://baas-gonzalomelov.rhcloud.com/baas-core/UserServices?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserServices(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserServices(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserServices() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public UserServices(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public UserServices(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public UserServices(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns UserServices
     */
    @WebEndpoint(name = "UserServicesPort")
    public uy.com.group05.baascore.sl.services.soap.UserServices getUserServicesPort() {
        return super.getPort(UserServicesPort, uy.com.group05.baascore.sl.services.soap.UserServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserServices
     */
    @WebEndpoint(name = "UserServicesPort")
    public uy.com.group05.baascore.sl.services.soap.UserServices getUserServicesPort(WebServiceFeature... features) {
        return super.getPort(UserServicesPort, uy.com.group05.baascore.sl.services.soap.UserServices.class, features);
    }

}
