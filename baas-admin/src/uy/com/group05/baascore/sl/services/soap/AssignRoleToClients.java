
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignRoleToClients complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignRoleToClients">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idClient" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rolesClient" type="{http://soap.services.sl.baascore.group05.com.uy/}rolesClientDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignRoleToClients", propOrder = {
    "idApp",
    "idUser",
    "idClient",
    "rolesClient"
})
public class AssignRoleToClients {

    protected long idApp;
    protected long idUser;
    protected long idClient;
    protected List<RolesClientDTO> rolesClient;

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

    /**
     * Gets the value of the rolesClient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rolesClient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRolesClient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RolesClientDTO }
     * 
     * 
     */
    public List<RolesClientDTO> getRolesClient() {
        if (rolesClient == null) {
            rolesClient = new ArrayList<RolesClientDTO>();
        }
        return this.rolesClient;
    }

}
