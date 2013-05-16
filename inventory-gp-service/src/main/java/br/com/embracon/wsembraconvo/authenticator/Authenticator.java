
package br.com.embracon.wsembraconvo.authenticator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import br.com.embracon.wsembraconvo.Login;
import br.com.embracon.wsteamposition.ws.WsAuthenticator;


/**
 * <p>Java class for authenticator complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="authenticator">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.wsteamposition.embracon.com.br/}wsAuthenticator">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authenticator")
@XmlSeeAlso({
    Login.class
})
public class Authenticator
    extends WsAuthenticator
{


}
