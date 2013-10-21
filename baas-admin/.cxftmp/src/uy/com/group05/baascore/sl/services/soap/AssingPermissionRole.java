
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assingPermissionRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assingPermissionRole">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idRole" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="permEntities" type="{http://soap.services.sl.baascore.group05.com.uy/}permissionEntityDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assingPermissionRole", propOrder = {
    "idUser",
    "idApp",
    "idRole",
    "permEntities"
})
public class AssingPermissionRole {

    protected long idUser;
    protected long idApp;
    protected long idRole;
    protected List<PermissionEntityDTO> permEntities;

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
     * Gets the value of the permEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PermissionEntityDTO }
     * 
     * 
     */
    public List<PermissionEntityDTO> getPermEntities() {
        if (permEntities == null) {
            permEntities = new ArrayList<PermissionEntityDTO>();
        }
        return this.permEntities;
    }

}
