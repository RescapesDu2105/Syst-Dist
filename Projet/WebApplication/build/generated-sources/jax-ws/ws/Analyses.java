
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour analyses complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="analyses">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="demande" type="{http://ws/}demande" minOccurs="0"/>
 *         &lt;element name="idAnalyses" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="item" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "analyses", propOrder = {
    "demande",
    "idAnalyses",
    "item",
    "valeur"
})
public class Analyses {

    protected Demande demande;
    protected Integer idAnalyses;
    protected String item;
    protected String valeur;

    /**
     * Obtient la valeur de la propriété demande.
     * 
     * @return
     *     possible object is
     *     {@link Demande }
     *     
     */
    public Demande getDemande() {
        return demande;
    }

    /**
     * Définit la valeur de la propriété demande.
     * 
     * @param value
     *     allowed object is
     *     {@link Demande }
     *     
     */
    public void setDemande(Demande value) {
        this.demande = value;
    }

    /**
     * Obtient la valeur de la propriété idAnalyses.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdAnalyses() {
        return idAnalyses;
    }

    /**
     * Définit la valeur de la propriété idAnalyses.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdAnalyses(Integer value) {
        this.idAnalyses = value;
    }

    /**
     * Obtient la valeur de la propriété item.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItem() {
        return item;
    }

    /**
     * Définit la valeur de la propriété item.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItem(String value) {
        this.item = value;
    }

    /**
     * Obtient la valeur de la propriété valeur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValeur() {
        return valeur;
    }

    /**
     * Définit la valeur de la propriété valeur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValeur(String value) {
        this.valeur = value;
    }

}
