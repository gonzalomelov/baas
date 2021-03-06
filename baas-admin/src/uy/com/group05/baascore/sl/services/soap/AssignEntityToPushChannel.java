
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for assignEntityToPushChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assignEntityToPushChannel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCanal" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idEntity" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assignEntityToPushChannel", propOrder = {
    "idApp",
    "idCanal",
    "idEntity"
})
public class AssignEntityToPushChannel {

    protected long idApp;
    protected long idCanal;
    protected long idEntity;

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
     * Gets the value of the idCanal property.
     * 
     */
    public long getIdCanal() {
        return idCanal;
    }

    /**
     * Sets the value of the idCanal property.
     * 
     */
    public void setIdCanal(long value) {
        this.idCanal = value;
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

}
