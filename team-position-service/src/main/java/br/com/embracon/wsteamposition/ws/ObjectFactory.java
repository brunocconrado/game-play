
package br.com.embracon.wsteamposition.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.embracon.wsteamposition.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindBranch_QNAME = new QName("http://ws.wsteamposition.embracon.com.br/", "findBranch");
    private final static QName _FindRegionalResponse_QNAME = new QName("http://ws.wsteamposition.embracon.com.br/", "findRegionalResponse");
    private final static QName _FindRegional_QNAME = new QName("http://ws.wsteamposition.embracon.com.br/", "findRegional");
    private final static QName _FindBranchResponse_QNAME = new QName("http://ws.wsteamposition.embracon.com.br/", "findBranchResponse");
    private final static QName _FindCollaborator_QNAME = new QName("http://ws.wsteamposition.embracon.com.br/", "findCollaborator");
    private final static QName _FindCollaboratorResponse_QNAME = new QName("http://ws.wsteamposition.embracon.com.br/", "findCollaboratorResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.embracon.wsteamposition.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindCollaboratorResponse }
     * 
     */
    public FindCollaboratorResponse createFindCollaboratorResponse() {
        return new FindCollaboratorResponse();
    }

    /**
     * Create an instance of {@link FindCollaborator }
     * 
     */
    public FindCollaborator createFindCollaborator() {
        return new FindCollaborator();
    }

    /**
     * Create an instance of {@link FindBranchResponse }
     * 
     */
    public FindBranchResponse createFindBranchResponse() {
        return new FindBranchResponse();
    }

    /**
     * Create an instance of {@link FindRegional }
     * 
     */
    public FindRegional createFindRegional() {
        return new FindRegional();
    }

    /**
     * Create an instance of {@link FindRegionalResponse }
     * 
     */
    public FindRegionalResponse createFindRegionalResponse() {
        return new FindRegionalResponse();
    }

    /**
     * Create an instance of {@link FindBranch }
     * 
     */
    public FindBranch createFindBranch() {
        return new FindBranch();
    }

    /**
     * Create an instance of {@link WsAuthenticator }
     * 
     */
    public WsAuthenticator createWsAuthenticator() {
        return new WsAuthenticator();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBranch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsteamposition.embracon.com.br/", name = "findBranch")
    public JAXBElement<FindBranch> createFindBranch(FindBranch value) {
        return new JAXBElement<FindBranch>(_FindBranch_QNAME, FindBranch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRegionalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsteamposition.embracon.com.br/", name = "findRegionalResponse")
    public JAXBElement<FindRegionalResponse> createFindRegionalResponse(FindRegionalResponse value) {
        return new JAXBElement<FindRegionalResponse>(_FindRegionalResponse_QNAME, FindRegionalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRegional }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsteamposition.embracon.com.br/", name = "findRegional")
    public JAXBElement<FindRegional> createFindRegional(FindRegional value) {
        return new JAXBElement<FindRegional>(_FindRegional_QNAME, FindRegional.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsteamposition.embracon.com.br/", name = "findBranchResponse")
    public JAXBElement<FindBranchResponse> createFindBranchResponse(FindBranchResponse value) {
        return new JAXBElement<FindBranchResponse>(_FindBranchResponse_QNAME, FindBranchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCollaborator }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsteamposition.embracon.com.br/", name = "findCollaborator")
    public JAXBElement<FindCollaborator> createFindCollaborator(FindCollaborator value) {
        return new JAXBElement<FindCollaborator>(_FindCollaborator_QNAME, FindCollaborator.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCollaboratorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsteamposition.embracon.com.br/", name = "findCollaboratorResponse")
    public JAXBElement<FindCollaboratorResponse> createFindCollaboratorResponse(FindCollaboratorResponse value) {
        return new JAXBElement<FindCollaboratorResponse>(_FindCollaboratorResponse_QNAME, FindCollaboratorResponse.class, null, value);
    }

}
