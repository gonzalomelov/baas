
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignRoleToClient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignRoleToClient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idRole" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idClient" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignRoleToClient", propOrder = {
    "idApp",
    "idUser",
    "idRole",
    "idClient"
})
public class AssignRoleToClient {

    protected long idApp;
    protected long idUser;
    protected long idRole;
    protected long idClient;

    /**
     * Gets the value of the idApp property.
     * 
     */
    public long getIdApp() {
        return idApp;
    }

    /**
     * Sets the value of the idApp property.
     * 
     */
    public void setIdApp(long value) {
        this.idApp = value;
    }

    /**
     * Gets the value of the idUser property.
     * 
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * Sets the value of the idUser property.
     * 
     */
    public void setIdUser(long value) {
        this.idUser = value;
    }

    /**
     * Gets the value of the idRole property.
     * 
     */
    public long getIdRole() {
        return idRole;
    }

    /**
     * Sets the value of the idRole property.
     * 
     */
    public void setIdRole(long value) {
        this.idRole = value;
    }

    /**
     * Gets the value of the idClient property.
     * 
     */
    public long getIdClient() {
        return idClient;
    }

    /**
     * Sets the value of the idClient property.
     * 
     */
    public void setIdClient(long value) {
        this.idClient = value;
    }

}
