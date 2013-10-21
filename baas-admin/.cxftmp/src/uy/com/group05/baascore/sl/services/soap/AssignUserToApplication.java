
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignUserToApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignUserToApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idUser" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignUserToApplication", propOrder = {
    "idApp",
    "idUser"
})
public class AssignUserToApplication {

    protected long idApp;
    protected long idUser;

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

}
