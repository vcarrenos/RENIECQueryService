
package org.reniec.rel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRegIdentConsolidada2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRegIdentConsolidada2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sesion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTxEmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dniUserEmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRegIdentConsolidada2", propOrder = {
    "sesion",
    "usuario",
    "codTxEmp",
    "dniUserEmp",
    "dni"
})
public class GetRegIdentConsolidada2 {

    protected String sesion;
    protected String usuario;
    protected String codTxEmp;
    protected String dniUserEmp;
    protected String dni;

    /**
     * Gets the value of the sesion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSesion() {
        return sesion;
    }

    /**
     * Sets the value of the sesion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSesion(String value) {
        this.sesion = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the codTxEmp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodTxEmp() {
        return codTxEmp;
    }

    /**
     * Sets the value of the codTxEmp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodTxEmp(String value) {
        this.codTxEmp = value;
    }

    /**
     * Gets the value of the dniUserEmp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDniUserEmp() {
        return dniUserEmp;
    }

    /**
     * Sets the value of the dniUserEmp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDniUserEmp(String value) {
        this.dniUserEmp = value;
    }

    /**
     * Gets the value of the dni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the value of the dni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDni(String value) {
        this.dni = value;
    }

}
