
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-09T00:10:06.481-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "ClientNotRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class ClientNotRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.ClientNotRegisteredException clientNotRegisteredException;

    public ClientNotRegisteredException_Exception() {
        super();
    }
    
    public ClientNotRegisteredException_Exception(String message) {
        super(message);
    }
    
    public ClientNotRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.ClientNotRegisteredException clientNotRegisteredException) {
        super(message);
        this.clientNotRegisteredException = clientNotRegisteredException;
    }

    public ClientNotRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.ClientNotRegisteredException clientNotRegisteredException, Throwable cause) {
        super(message, cause);
        this.clientNotRegisteredException = clientNotRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.ClientNotRegisteredException getFaultInfo() {
        return this.clientNotRegisteredException;
    }
}
