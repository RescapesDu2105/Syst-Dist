
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
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

    private final static QName _Analyses_QNAME = new QName("http://ws/", "analyses");
    private final static QName _GetResultats_QNAME = new QName("http://ws/", "getResultats");
    private final static QName _Patient_QNAME = new QName("http://ws/", "patient");
    private final static QName _GetResultatsResponse_QNAME = new QName("http://ws/", "getResultatsResponse");
    private final static QName _Demande_QNAME = new QName("http://ws/", "demande");
    private final static QName _Medecin_QNAME = new QName("http://ws/", "medecin");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Analyses }
     * 
     */
    public Analyses createAnalyses() {
        return new Analyses();
    }

    /**
     * Create an instance of {@link GetResultats }
     * 
     */
    public GetResultats createGetResultats() {
        return new GetResultats();
    }

    /**
     * Create an instance of {@link Patient }
     * 
     */
    public Patient createPatient() {
        return new Patient();
    }

    /**
     * Create an instance of {@link GetResultatsResponse }
     * 
     */
    public GetResultatsResponse createGetResultatsResponse() {
        return new GetResultatsResponse();
    }

    /**
     * Create an instance of {@link Demande }
     * 
     */
    public Demande createDemande() {
        return new Demande();
    }

    /**
     * Create an instance of {@link Medecin }
     * 
     */
    public Medecin createMedecin() {
        return new Medecin();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Analyses }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "analyses")
    public JAXBElement<Analyses> createAnalyses(Analyses value) {
        return new JAXBElement<Analyses>(_Analyses_QNAME, Analyses.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResultats }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getResultats")
    public JAXBElement<GetResultats> createGetResultats(GetResultats value) {
        return new JAXBElement<GetResultats>(_GetResultats_QNAME, GetResultats.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Patient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "patient")
    public JAXBElement<Patient> createPatient(Patient value) {
        return new JAXBElement<Patient>(_Patient_QNAME, Patient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResultatsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getResultatsResponse")
    public JAXBElement<GetResultatsResponse> createGetResultatsResponse(GetResultatsResponse value) {
        return new JAXBElement<GetResultatsResponse>(_GetResultatsResponse_QNAME, GetResultatsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Demande }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "demande")
    public JAXBElement<Demande> createDemande(Demande value) {
        return new JAXBElement<Demande>(_Demande_QNAME, Demande.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Medecin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "medecin")
    public JAXBElement<Medecin> createMedecin(Medecin value) {
        return new JAXBElement<Medecin>(_Medecin_QNAME, Medecin.class, null, value);
    }

}
