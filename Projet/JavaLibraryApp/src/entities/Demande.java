package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Philippe
 */
@Entity
@Table(name = "demande")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findByIdDemande", query = "SELECT d FROM Demande d WHERE d.idDemande = :idDemande")
    , @NamedQuery(name = "Demande.findByDateHeureDemande", query = "SELECT d FROM Demande d WHERE d.dateHeureDemande = :dateHeureDemande")
    , @NamedQuery(name = "Demande.findByUrgent", query = "SELECT d FROM Demande d WHERE d.urgent = :urgent")
    , @NamedQuery(name = "Demande.findByResultatsDisponibles", query = "SELECT d FROM Demande d WHERE d.resultatsDisponibles = true")
})
public class Demande implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDemande")
    private Integer idDemande;
    @Column(name = "DateHeureDemande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureDemande;
    @Column(name = "Urgent")
    private Boolean urgent;
    @Column(name = "ResultatsDisponibles")
    private Boolean resultatsDisponibles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demande")
    private List<Analyses> analysesList;
    @JoinColumn(name = "RefMedecin", referencedColumnName = "idMedecin")
    @ManyToOne
    private Medecin refMedecin;
    @JoinColumn(name = "RefPatient", referencedColumnName = "idPatient")
    @ManyToOne
    private Patient refPatient;

    public Demande()
    {
    }

    public Demande(Integer idDemande)
    {
        this.idDemande = idDemande;
    }

    public Integer getIdDemande()
    {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande)
    {
        this.idDemande = idDemande;
    }

    public Date getDateHeureDemande()
    {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(Date dateHeureDemande)
    {
        this.dateHeureDemande = dateHeureDemande;
    }

    public Boolean getUrgent()
    {
        return urgent;
    }

    public void setUrgent(Boolean urgent)
    {
        this.urgent = urgent;
    }

    @XmlTransient
    public List<Analyses> getAnalysesList()
    {
        return analysesList;
    }

    public void setAnalysesList(List<Analyses> analysesList)
    {
        this.analysesList = analysesList;
    }

    public Medecin getRefMedecin()
    {
        return refMedecin;
    }

    public void setRefMedecin(Medecin refMedecin)
    {
        this.refMedecin = refMedecin;
    }

    public Patient getRefPatient()
    {
        return refPatient;
    }

    public void setRefPatient(Patient refPatient)
    {
        this.refPatient = refPatient;
    }

    public Boolean getResultatsDisponibles()
    {
        return resultatsDisponibles;
    }

    public void setResultatsDisponibles(Boolean resultatsDisponibles)
    {
        this.resultatsDisponibles = resultatsDisponibles;
    }
    
   
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idDemande != null ? idDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande))
        {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.idDemande == null && other.idDemande != null) || (this.idDemande != null && !this.idDemande.equals(other.idDemande)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Demande[ idDemande=" + idDemande + " ]";
    }
    
}
