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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Philippe
 */
@Entity
@Table(name = "medecin")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m")
    , @NamedQuery(name = "Medecin.findByIdMedecin", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin")
    , @NamedQuery(name = "Medecin.findByNom", query = "SELECT m FROM Medecin m WHERE m.nom = :nom")
    , @NamedQuery(name = "Medecin.findByPrenom", query = "SELECT m FROM Medecin m WHERE m.prenom = :prenom")
    , @NamedQuery(name = "Medecin.findByLogin", query = "SELECT m FROM Medecin m WHERE m.login = :login")
})
public class Medecin implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedecin")
    private Integer idMedecin;
    @Size(max = 45)
    @Column(name = "Nom")
    private String nom;
    @Size(max = 45)
    @Column(name = "Prenom")
    private String prenom;
    @Size(max = 45)
    @Column(name = "Login")
    private String login;
    @OneToMany(mappedBy = "refMedecin")
    private List<Demande> demandeList;

    public Medecin()
    {
    }

    public Medecin(Integer idMedecin)
    {
        this.idMedecin = idMedecin;
    }

    public Integer getIdMedecin()
    {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin)
    {
        this.idMedecin = idMedecin;
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
        hash += (idMedecin != null ? idMedecin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecin))
        {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.idMedecin == null && other.idMedecin != null) || (this.idMedecin != null && !this.idMedecin.equals(other.idMedecin)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return this.getNom() + " " + this.getPrenom();
    }
    
}
