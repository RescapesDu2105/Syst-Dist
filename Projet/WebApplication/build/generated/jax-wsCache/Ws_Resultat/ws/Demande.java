
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour demande complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="demande">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateHeureDemande" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idDemande" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="refMedecin" type="{http://ws/}medecin" minOccurs="0"/>
 *         &lt;element name="refPatient" type="{http://ws/}patient" minOccurs="0"/>
 *         &lt;element name="resultatsDisponibles" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="urgent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "demande", propOrder = {
    "dateHeureDemande",
    "idDemande",
    "refMedecin",
    "refPatient",
    "resultatsDisponibles",
    "urgent"
})
public class Demande {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateHeureDemande;
    protected Integer idDemande;
    protected Medecin refMedecin;
    protected Patient refPatient;
    protected Boolean resultatsDisponibles;
    protected Boolean urgent;

    /**
     * Obtient la valeur de la propriété dateHeureDemande.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateHeureDemande() {
        return dateHeureDemande;
    }

    /**
     * Définit la valeur de la propriété dateHeureDemande.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateHeureDemande(XMLGregorianCalendar value) {
        this.dateHeureDemande = value;
    }

    /**
     * Obtient la valeur de la propriété idDemande.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdDemande() {
        return idDemande;
    }

    /**
     * Définit la valeur de la propriété idDemande.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdDemande(Integer value) {
        this.idDemande = value;
    }

    /**
     * Obtient la valeur de la propriété refMedecin.
     * 
     * @return
     *     possible object is
     *     {@link Medecin }
     *     
     */
    public Medecin getRefMedecin() {
        return refMedecin;
    }

    /**
     * Définit la valeur de la propriété refMedecin.
     * 
     * @param value
     *     allowed object is
     *     {@link Medecin }
     *     
     */
    public void setRefMedecin(Medecin value) {
        this.refMedecin = value;
    }

    /**
     * Obtient la valeur de la propriété refPatient.
     * 
     * @return
     *     possible object is
     *     {@link Patient }
     *     
     */
    public Patient getRefPatient() {
        return refPatient;
    }

    /**
     * Définit la valeur de la propriété refPatient.
     * 
     * @param value
     *     allowed object is
     *     {@link Patient }
     *     
     */
    public void setRefPatient(Patient value) {
        this.refPatient = value;
    }

    /**
     * Obtient la valeur de la propriété resultatsDisponibles.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResultatsDisponibles() {
        return resultatsDisponibles;
    }

    /**
     * Définit la valeur de la propriété resultatsDisponibles.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResultatsDisponibles(Boolean value) {
        this.resultatsDisponibles = value;
    }

    /**
     * Obtient la valeur de la propriété urgent.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUrgent() {
        return urgent;
    }

    /**
     * Définit la valeur de la propriété urgent.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUrgent(Boolean value) {
        this.urgent = value;
    }

}
