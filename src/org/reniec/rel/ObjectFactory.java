
package org.reniec.rel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.reniec.rel package. 
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

    private final static QName _GetRegIdentAproximacion_QNAME = new QName("http://rel.reniec.org/", "getRegIdentAproximacion");
    private final static QName _GetRegIdentConsolidada2_QNAME = new QName("http://rel.reniec.org/", "getRegIdentConsolidada2");
    private final static QName _GetRegIdentConsolidada2Response_QNAME = new QName("http://rel.reniec.org/", "getRegIdentConsolidada2Response");
    private final static QName _GetSession_QNAME = new QName("http://rel.reniec.org/", "getSession");
    private final static QName _GetSessionResponse_QNAME = new QName("http://rel.reniec.org/", "getSessionResponse");
    private final static QName _GetRegIdentAproximacionResponse_QNAME = new QName("http://rel.reniec.org/", "getRegIdentAproximacionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.reniec.rel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRegIdentAproximacionResponse }
     * 
     */
    public GetRegIdentAproximacionResponse createGetRegIdentAproximacionResponse() {
        return new GetRegIdentAproximacionResponse();
    }

    /**
     * Create an instance of {@link GetSessionResponse }
     * 
     */
    public GetSessionResponse createGetSessionResponse() {
        return new GetSessionResponse();
    }

    /**
     * Create an instance of {@link GetRegIdentConsolidada2 }
     * 
     */
    public GetRegIdentConsolidada2 createGetRegIdentConsolidada2() {
        return new GetRegIdentConsolidada2();
    }

    /**
     * Create an instance of {@link GetRegIdentAproximacion }
     * 
     */
    public GetRegIdentAproximacion createGetRegIdentAproximacion() {
        return new GetRegIdentAproximacion();
    }

    /**
     * Create an instance of {@link GetSession }
     * 
     */
    public GetSession createGetSession() {
        return new GetSession();
    }

    /**
     * Create an instance of {@link GetRegIdentConsolidada2Response }
     * 
     */
    public GetRegIdentConsolidada2Response createGetRegIdentConsolidada2Response() {
        return new GetRegIdentConsolidada2Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRegIdentAproximacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rel.reniec.org/", name = "getRegIdentAproximacion")
    public JAXBElement<GetRegIdentAproximacion> createGetRegIdentAproximacion(GetRegIdentAproximacion value) {
        return new JAXBElement<GetRegIdentAproximacion>(_GetRegIdentAproximacion_QNAME, GetRegIdentAproximacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRegIdentConsolidada2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rel.reniec.org/", name = "getRegIdentConsolidada2")
    public JAXBElement<GetRegIdentConsolidada2> createGetRegIdentConsolidada2(GetRegIdentConsolidada2 value) {
        return new JAXBElement<GetRegIdentConsolidada2>(_GetRegIdentConsolidada2_QNAME, GetRegIdentConsolidada2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRegIdentConsolidada2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rel.reniec.org/", name = "getRegIdentConsolidada2Response")
    public JAXBElement<GetRegIdentConsolidada2Response> createGetRegIdentConsolidada2Response(GetRegIdentConsolidada2Response value) {
        return new JAXBElement<GetRegIdentConsolidada2Response>(_GetRegIdentConsolidada2Response_QNAME, GetRegIdentConsolidada2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rel.reniec.org/", name = "getSession")
    public JAXBElement<GetSession> createGetSession(GetSession value) {
        return new JAXBElement<GetSession>(_GetSession_QNAME, GetSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rel.reniec.org/", name = "getSessionResponse")
    public JAXBElement<GetSessionResponse> createGetSessionResponse(GetSessionResponse value) {
        return new JAXBElement<GetSessionResponse>(_GetSessionResponse_QNAME, GetSessionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRegIdentAproximacionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rel.reniec.org/", name = "getRegIdentAproximacionResponse")
    public JAXBElement<GetRegIdentAproximacionResponse> createGetRegIdentAproximacionResponse(GetRegIdentAproximacionResponse value) {
        return new JAXBElement<GetRegIdentAproximacionResponse>(_GetRegIdentAproximacionResponse_QNAME, GetRegIdentAproximacionResponse.class, null, value);
    }

}
