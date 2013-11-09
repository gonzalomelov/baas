
package uy.com.group05.baascore.sl.services.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for chartDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="chartDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dispRegistrados" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mensajesPushEnviados" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pedidosHttp" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chartDto", propOrder = {
    "dispRegistrados",
    "mensajesPushEnviados",
    "pedidosHttp"
})
public class ChartDto {

    @XmlElement(nillable = true)
    protected List<Integer> dispRegistrados;
    @XmlElement(nillable = true)
    protected List<Integer> mensajesPushEnviados;
    @XmlElement(nillable = true)
    protected List<Integer> pedidosHttp;

    /**
     * Gets the value of the dispRegistrados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dispRegistrados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDispRegistrados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getDispRegistrados() {
        if (dispRegistrados == null) {
            dispRegistrados = new ArrayList<Integer>();
        }
        return this.dispRegistrados;
    }

    /**
     * Gets the value of the mensajesPushEnviados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajesPushEnviados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajesPushEnviados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getMensajesPushEnviados() {
        if (mensajesPushEnviados == null) {
            mensajesPushEnviados = new ArrayList<Integer>();
        }
        return this.mensajesPushEnviados;
    }

    /**
     * Gets the value of the pedidosHttp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pedidosHttp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPedidosHttp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getPedidosHttp() {
        if (pedidosHttp == null) {
            pedidosHttp = new ArrayList<Integer>();
        }
        return this.pedidosHttp;
    }

}
