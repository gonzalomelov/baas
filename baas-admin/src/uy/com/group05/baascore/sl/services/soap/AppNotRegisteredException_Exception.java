
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-28T06:16:18.156-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "AppNotRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class AppNotRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException appNotRegisteredException;

    public AppNotRegisteredException_Exception() {
        super();
    }
    
    public AppNotRegisteredException_Exception(String message) {
        super(message);
    }
    
    public AppNotRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public AppNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException appNotRegisteredException) {
        super(message);
        this.appNotRegisteredException = appNotRegisteredException;
    }

    public AppNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException appNotRegisteredException, Throwable cause) {
        super(message, cause);
        this.appNotRegisteredException = appNotRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.AppNotRegisteredException getFaultInfo() {
        return this.appNotRegisteredException;
    }
}
