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
 * 2013-11-09T15:52:37.610-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "PermissionServices", 
                  wsdlLocation = "http://baas-gonzalomelov.rhcloud.com/baas-core/PermissionServices?wsdl",
                  targetNamespace = "http://impl.services.sl.baascore.group05.com.uy/") 
public class PermissionServices extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.services.sl.baascore.group05.com.uy/", "PermissionServices");
    public final static QName PermissionServicesPort = new QName("http://impl.services.sl.baascore.group05.com.uy/", "PermissionServicesPort");
    static {
        URL url = null;
        try {
            url = new URL("http://baas-gonzalomelov.rhcloud.com/baas-core/PermissionServices?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PermissionServices.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://baas-gonzalomelov.rhcloud.com/baas-core/PermissionServices?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PermissionServices(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PermissionServices(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PermissionServices() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PermissionServices(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PermissionServices(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PermissionServices(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns PermissionServices
     */
    @WebEndpoint(name = "PermissionServicesPort")
    public uy.com.group05.baascore.sl.services.soap.PermissionServices getPermissionServicesPort() {
        return super.getPort(PermissionServicesPort, uy.com.group05.baascore.sl.services.soap.PermissionServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PermissionServices
     */
    @WebEndpoint(name = "PermissionServicesPort")
    public uy.com.group05.baascore.sl.services.soap.PermissionServices getPermissionServicesPort(WebServiceFeature... features) {
        return super.getPort(PermissionServicesPort, uy.com.group05.baascore.sl.services.soap.PermissionServices.class, features);
    }

}
