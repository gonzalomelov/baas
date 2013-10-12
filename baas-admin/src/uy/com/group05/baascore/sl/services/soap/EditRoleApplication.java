
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editRoleApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editRoleApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nomRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editRoleApplication", propOrder = {
    "idApp",
    "idUser",
    "nomRole"
})
public class EditRoleApplication {

    protected long idApp;
    protected long idUser;
    protected String nomRole;

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
     * Gets the value of the nomRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomRole() {
        return nomRole;
    }

    /**
     * Sets the value of the nomRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomRole(String value) {
        this.nomRole = value;
    }

}
