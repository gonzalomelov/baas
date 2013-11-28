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
 * 2013-11-28T04:28:19.359-02:00
 * Generated source version: 2.4.6
 * 
 */
@WebServiceClient(name = "ApplicationServices", 
                  wsdlLocation = "http://baascore-group5tsi2.rhcloud.com/baas-core/ApplicationServices?wsdl",
                  targetNamespace = "http://impl.services.sl.baascore.group05.com.uy/") 
public class ApplicationServices extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.services.sl.baascore.group05.com.uy/", "ApplicationServices");
    public final static QName ApplicationServicesPort = new QName("http://impl.services.sl.baascore.group05.com.uy/", "ApplicationServicesPort");
    static {
        URL url = null;
        try {
            url = new URL("http://baascore-group5tsi2.rhcloud.com/baas-core/ApplicationServices?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ApplicationServices.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://baascore-group5tsi2.rhcloud.com/baas-core/ApplicationServices?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ApplicationServices(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ApplicationServices(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ApplicationServices() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ApplicationServices(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ApplicationServices(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public ApplicationServices(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns ApplicationServices
     */
    @WebEndpoint(name = "ApplicationServicesPort")
    public uy.com.group05.baascore.sl.services.soap.ApplicationServices getApplicationServicesPort() {
        return super.getPort(ApplicationServicesPort, uy.com.group05.baascore.sl.services.soap.ApplicationServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ApplicationServices
     */
    @WebEndpoint(name = "ApplicationServicesPort")
    public uy.com.group05.baascore.sl.services.soap.ApplicationServices getApplicationServicesPort(WebServiceFeature... features) {
        return super.getPort(ApplicationServicesPort, uy.com.group05.baascore.sl.services.soap.ApplicationServices.class, features);
    }

}
