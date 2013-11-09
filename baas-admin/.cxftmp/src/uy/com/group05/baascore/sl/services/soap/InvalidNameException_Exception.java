
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-09T15:50:34.675-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "InvalidNameException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class InvalidNameException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.InvalidNameException invalidNameException;

    public InvalidNameException_Exception() {
        super();
    }
    
    public InvalidNameException_Exception(String message) {
        super(message);
    }
    
    public InvalidNameException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameException_Exception(String message, uy.com.group05.baascore.sl.services.soap.InvalidNameException invalidNameException) {
        super(message);
        this.invalidNameException = invalidNameException;
    }

    public InvalidNameException_Exception(String message, uy.com.group05.baascore.sl.services.soap.InvalidNameException invalidNameException, Throwable cause) {
        super(message, cause);
        this.invalidNameException = invalidNameException;
    }

    public uy.com.group05.baascore.sl.services.soap.InvalidNameException getFaultInfo() {
        return this.invalidNameException;
    }
}
