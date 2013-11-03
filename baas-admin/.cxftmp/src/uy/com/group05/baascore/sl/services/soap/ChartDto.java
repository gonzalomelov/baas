
package uy.com.group05.baascore.sl.services.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="dispRegistrados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mensajesPushEnviados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pedidosHttp" type="{http://www.w3.org/2001/XMLSchema}int"/>
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

    protected int dispRegistrados;
    protected int mensajesPushEnviados;
    protected int pedidosHttp;

    /**
     * Gets the value of the dispRegistrados property.
     * 
     */
    public int getDispRegistrados() {
        return dispRegistrados;
    }

    /**
     * Sets the value of the dispRegistrados property.
     * 
     */
    public void setDispRegistrados(int value) {
        this.dispRegistrados = value;
    }

    /**
     * Gets the value of the mensajesPushEnviados property.
     * 
     */
    public int getMensajesPushEnviados() {
        return mensajesPushEnviados;
    }

    /**
     * Sets the value of the mensajesPushEnviados property.
     * 
     */
    public void setMensajesPushEnviados(int value) {
        this.mensajesPushEnviados = value;
    }

    /**
     * Gets the value of the pedidosHttp property.
     * 
     */
    public int getPedidosHttp() {
        return pedidosHttp;
    }

    /**
     * Sets the value of the pedidosHttp property.
     * 
     */
    public void setPedidosHttp(int value) {
        this.pedidosHttp = value;
    }

}
