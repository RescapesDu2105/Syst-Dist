/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.Demande;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Philippe
 */
@Entity
@Table(name = "patient")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Patient.findAll", query = "SELECT p FROM Patient p")
    , @NamedQuery(name = "Patient.findByIdPatient", query = "SELECT p FROM Patient p WHERE p.idPatient = :idPatient")
    , @NamedQuery(name = "Patient.findByLogin", query = "SELECT p FROM Patient p WHERE p.login = :login")
    , @NamedQuery(name = "Patient.findByNomPrenom", query = "SELECT p FROM Patient p WHERE UPPER(p.nom) = UPPER(:nom) AND UPPER(p.prenom) = UPPER(:prenom)")    
    , @NamedQuery(name = "Patient.update", query = "UPDATE Patient SET nom = :nom, prenom = :prenom, login = :login WHERE idPatient = :idPatient")
})
public class Patient implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPatient")
    private Integer idPatient;
    @Size(max = 45)
    @Column(name = "Nom")
    private String nom;
    @Size(max = 45)
    @Column(name = "Prenom")
    private String prenom;
    @Size(max = 45)
    @Column(name = "Login")
    private String login;
    @OneToMany(mappedBy = "refPatient")
    private Collection<Demande> demandeCollection;

    public Patient()
    {
    }

    public Patient(Integer idPatient)
    {
        this.idPatient = idPatient;
    }

    public Integer getIdPatient()
    {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient)
    {
        this.idPatient = idPatient;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    @XmlTransient
    public Collection<Demande> getDemandeCollection()
    {
        return demandeCollection;
    }

    public void setDemandeCollection(Collection<Demande> demandeCollection)
    {
        this.demandeCollection = demandeCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPatient != null ? idPatient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient))
        {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.idPatient == null && other.idPatient != null) || (this.idPatient != null && !this.idPatient.equals(other.idPatient)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return this.nom + " " + this.prenom;
    }
    
}
