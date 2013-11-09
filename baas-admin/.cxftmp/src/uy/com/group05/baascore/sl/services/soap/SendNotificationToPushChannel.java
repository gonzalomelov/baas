
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendNotificationToPushChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendNotificationToPushChannel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idApp" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="idCanal" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="msgKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="msgValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendNotificationToPushChannel", propOrder = {
    "idApp",
    "idCanal",
    "msgKey",
    "msgValue"
})
public class SendNotificationToPushChannel {

    protected long idApp;
    protected long idCanal;
    protected String msgKey;
    protected String msgValue;

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
     * Gets the value of the msgKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgKey() {
        return msgKey;
    }

    /**
     * Sets the value of the msgKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgKey(String value) {
        this.msgKey = value;
    }

    /**
     * Gets the value of the msgValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgValue() {
        return msgValue;
    }

    /**
     * Sets the value of the msgValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgValue(String value) {
        this.msgValue = value;
    }

}
