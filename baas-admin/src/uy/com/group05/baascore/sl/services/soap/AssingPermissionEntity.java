
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assingPermissionEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assingPermissionEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idEntity" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="permRoles" type="{http://soap.services.sl.baascore.group05.com.uy/}permissionRoleDTO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assingPermissionEntity", propOrder = {
    "idUser",
    "idApp",
    "idEntity",
    "permRoles"
})
public class AssingPermissionEntity {

    protected long idUser;
    protected long idApp;
    protected long idEntity;
    protected List<PermissionRoleDTO> permRoles;

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
     * Gets the value of the idEntity property.
     * 
     */
    public long getIdEntity() {
        return idEntity;
    }

    /**
     * Sets the value of the idEntity property.
     * 
     */
    public void setIdEntity(long value) {
        this.idEntity = value;
    }

    /**
     * Gets the value of the permRoles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permRoles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PermissionRoleDTO }
     * 
     * 
     */
    public List<PermissionRoleDTO> getPermRoles() {
        if (permRoles == null) {
            permRoles = new ArrayList<PermissionRoleDTO>();
        }
        return this.permRoles;
    }

}
