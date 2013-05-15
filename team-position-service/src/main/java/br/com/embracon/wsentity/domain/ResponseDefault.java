
package br.com.embracon.wsentity.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import br.com.embracon.wsentity.domain.entity.teamposition.CollaboratorInfo;
import br.com.embracon.wsentity.domain.entity.teamposition.RegionalInfo;


/**
 * <p>Java class for responseDefault complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="responseDefault">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="processMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processStatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statusLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseDefault", propOrder = {
    "processMessage",
    "processStatus",
    "statusLogin"
})
@XmlSeeAlso({
    CollaboratorInfo.class,
    RegionalInfo.class,
    Branch.class
})
public class ResponseDefault {

    protected String processMessage;
    protected Integer processStatus;
    protected String statusLogin;

    /**
     * Gets the value of the processMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessMessage() {
        return processMessage;
    }

    /**
     * Sets the value of the processMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessMessage(String value) {
        this.processMessage = value;
    }

    /**
     * Gets the value of the processStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProcessStatus() {
        return processStatus;
    }

    /**
     * Sets the value of the processStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProcessStatus(Integer value) {
        this.processStatus = value;
    }

    /**
     * Gets the value of the statusLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusLogin() {
        return statusLogin;
    }

    /**
     * Sets the value of the statusLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusLogin(String value) {
        this.statusLogin = value;
    }

}
