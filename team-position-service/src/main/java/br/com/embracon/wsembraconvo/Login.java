
package br.com.embracon.wsembraconvo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import br.com.embracon.wsembraconvo.authenticator.Authenticator;
import br.com.embracon.wsembraconvo.teamposition.Branch;
import br.com.embracon.wsembraconvo.teamposition.Collaborator;
import br.com.embracon.wsembraconvo.teamposition.Regional;


/**
 * <p>Java class for login complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="login">
 *   &lt;complexContent>
 *     &lt;extension base="{http://authenticator.wsembraconvo.embracon.com.br/}authenticator">
 *       &lt;sequence>
 *         &lt;element name="userLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "login", propOrder = {
    "userLogin"
})
@XmlSeeAlso({
    Collaborator.class,
    Regional.class,
    Branch.class
})
public class Login
    extends Authenticator
{

    protected String userLogin;

    /**
     * Gets the value of the userLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Sets the value of the userLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserLogin(String value) {
        this.userLogin = value;
    }

}
