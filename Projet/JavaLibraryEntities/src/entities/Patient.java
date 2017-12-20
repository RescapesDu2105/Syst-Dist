/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    , @NamedQuery(name = "Patient.findByNom", query = "SELECT p FROM Patient p WHERE p.nom = :nom")
    , @NamedQuery(name = "Patient.findByPrenom", query = "SELECT p FROM Patient p WHERE p.prenom = :prenom")
    , @NamedQuery(name = "Patient.findByLogin", query = "SELECT p FROM Patient p WHERE p.login = :login")
})
public class Patient implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPatient")
    private Integer idPatient;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "Prenom")
    private String prenom;
    @Column(name = "Login")
    private String login;
    @OneToMany(mappedBy = "refPatient")
    private List<Demande> demandeList;

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
    public List<Demande> getDemandeList()
    {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList)
    {
        this.demandeList = demandeList;
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
        return "entities.Patient[ idPatient=" + idPatient + " ]";
    }
    
}
