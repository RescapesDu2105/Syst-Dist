/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Doublon
 */
@Entity
@Table(name = "personnes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Personnes.findAll", query = "SELECT p FROM Personnes p")
    , @NamedQuery(name = "Personnes.findByIdPersonne", query = "SELECT p FROM Personnes p WHERE p.idPersonne = :idPersonne")
    , @NamedQuery(name = "Personnes.findByNom", query = "SELECT p FROM Personnes p WHERE p.nom = :nom")
    , @NamedQuery(name = "Personnes.findByPrenom", query = "SELECT p FROM Personnes p WHERE p.prenom = :prenom")
})
public class Personnes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "idPersonne")
    private Integer idPersonne;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "Prenom")
    private String prenom;

    public Personnes()
    {
    }

    public Personnes(Integer idPersonne)
    {
        this.idPersonne = idPersonne;
    }

    public Integer getIdPersonne()
    {
        return idPersonne;
    }

    public void setIdPersonne(Integer idPersonne)
    {
        this.idPersonne = idPersonne;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idPersonne != null ? idPersonne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnes))
        {
            return false;
        }
        Personnes other = (Personnes) object;
        if ((this.idPersonne == null && other.idPersonne != null) || (this.idPersonne != null && !this.idPersonne.equals(other.idPersonne)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "jpa.Personnes[ idPersonne=" + idPersonne + " ]";
    }
    
}
