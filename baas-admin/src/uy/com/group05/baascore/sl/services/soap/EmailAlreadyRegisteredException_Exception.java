
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-09T15:50:24.684-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "EmailAlreadyRegisteredException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class EmailAlreadyRegisteredException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException emailAlreadyRegisteredException;

    public EmailAlreadyRegisteredException_Exception() {
        super();
    }
    
    public EmailAlreadyRegisteredException_Exception(String message) {
        super(message);
    }
    
    public EmailAlreadyRegisteredException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException emailAlreadyRegisteredException) {
        super(message);
        this.emailAlreadyRegisteredException = emailAlreadyRegisteredException;
    }

    public EmailAlreadyRegisteredException_Exception(String message, uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException emailAlreadyRegisteredException, Throwable cause) {
        super(message, cause);
        this.emailAlreadyRegisteredException = emailAlreadyRegisteredException;
    }

    public uy.com.group05.baascore.sl.services.soap.EmailAlreadyRegisteredException getFaultInfo() {
        return this.emailAlreadyRegisteredException;
    }
}
