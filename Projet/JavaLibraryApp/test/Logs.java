/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Philippe
 */
@Entity
@Table(name = "logs")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l")
    , @NamedQuery(name = "Logs.findByIdLogs", query = "SELECT l FROM Logs l WHERE l.idLogs = :idLogs")
    , @NamedQuery(name = "Logs.findByInfos", query = "SELECT l FROM Logs l WHERE l.infos = :infos")
})
public class Logs implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLogs")
    private Integer idLogs;
    @Size(max = 45)
    @Column(name = "Infos")
    private String infos;

    public Logs()
    {
    }

    public Logs(Integer idLogs)
    {
        this.idLogs = idLogs;
    }

    public Integer getIdLogs()
    {
        return idLogs;
    }

    public void setIdLogs(Integer idLogs)
    {
        this.idLogs = idLogs;
    }

    public String getInfos()
    {
        return infos;
    }

    public void setInfos(String infos)
    {
        this.infos = infos;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idLogs != null ? idLogs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs))
        {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.idLogs == null && other.idLogs != null) || (this.idLogs != null && !this.idLogs.equals(other.idLogs)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "entities.Logs[ idLogs=" + idLogs + " ]";
    }
    
}
