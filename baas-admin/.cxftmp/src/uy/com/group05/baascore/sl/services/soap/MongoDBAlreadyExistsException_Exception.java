
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.4.6
 * 2013-11-08T22:54:30.593-02:00
 * Generated source version: 2.4.6
 */

@WebFault(name = "MongoDBAlreadyExistsException", targetNamespace = "http://soap.services.sl.baascore.group05.com.uy/")
public class MongoDBAlreadyExistsException_Exception extends Exception {
    
    private uy.com.group05.baascore.sl.services.soap.MongoDBAlreadyExistsException mongoDBAlreadyExistsException;

    public MongoDBAlreadyExistsException_Exception() {
        super();
    }
    
    public MongoDBAlreadyExistsException_Exception(String message) {
        super(message);
    }
    
    public MongoDBAlreadyExistsException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public MongoDBAlreadyExistsException_Exception(String message, uy.com.group05.baascore.sl.services.soap.MongoDBAlreadyExistsException mongoDBAlreadyExistsException) {
        super(message);
        this.mongoDBAlreadyExistsException = mongoDBAlreadyExistsException;
    }

    public MongoDBAlreadyExistsException_Exception(String message, uy.com.group05.baascore.sl.services.soap.MongoDBAlreadyExistsException mongoDBAlreadyExistsException, Throwable cause) {
        super(message, cause);
        this.mongoDBAlreadyExistsException = mongoDBAlreadyExistsException;
    }

    public uy.com.group05.baascore.sl.services.soap.MongoDBAlreadyExistsException getFaultInfo() {
        return this.mongoDBAlreadyExistsException;
    }
}
