/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Patient;
import interfaces.SessionBeanPatientRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Philippe
 */
@Stateless
public class SessionBeanPatient implements SessionBeanPatientRemote
{   
    @Override
    public List getPatients()
    {        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        //ArrayList<Patient> lp = null;
        List lp = null;
        em.getTransaction().begin();        
        try
        {
            lp = em.createNamedQuery("Patient.findAll").getResultList();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            em.close();
            emf.close();
        }          
        
        return lp;
    }
    
    @Override
    public ArrayList<Patient> ChercherPatient(String nom, String prenom)
    {        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        ArrayList<Patient> lp = null;
        em.getTransaction().begin();        
        try
        {
            Query query = em.createNamedQuery("Patient.findByNomPrenom");
            query.setParameter("nom", nom);
            query.setParameter("prenom", prenom);
            
            lp = new ArrayList<>(query.getResultList());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            em.close();
            emf.close();
        }          
        
        return lp;
    }

    @Override
    public int AjouterPatient(String nom, String prenom, String login)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();        
        int error = 0;
        
        em.getTransaction().begin();
        
        try
        {
            Patient p = new Patient();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setLogin(login);
            
            em.persist(p);
            
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
            error = 1;
        }
        finally
        {
            em.close();
            emf.close();
        }
        
        return error;
    }
    @Override
    public int AjouterPatient(Patient p)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();        
        int error = 0;
        
        em.getTransaction().begin();
        
        try
        {
            if(em.createNamedQuery("Patient.findByLogin").setParameter("login", p.getLogin()).getResultList().size() > 0)
            {
                System.out.println("count " + em.createNamedQuery("Patient.findByLogin").setParameter("login", p.getLogin()).getMaxResults());
                error = 2;
            }
            else
            {
                em.persist(p);            
                em.getTransaction().commit();
                if(!em.contains(p))
                    error = 3;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //em.getTransaction().rollback();
            error = 1;
        }
        finally
        {
            em.close();
            emf.close();
        }
        
        return error;
    }

    @Override
    public void ModifierPatient(Patient p)
    {        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        try
        {         
            Query query = em.createQuery("UPDATE Patient SET nom = :nom, prenom = :prenom, login = :login WHERE idPatient = :idPatient");
            query.setParameter("nom", p.getNom());
            query.setParameter("prenom", p.getPrenom());
            query.setParameter("login", p.getLogin());
            query.setParameter("idPatient", p.getIdPatient());
            query.executeUpdate();            
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                em.getTransaction().rollback();
            }
            catch(Exception ex) {}
        }
        finally
        {
            em.close();
            emf.close();
        }        
    }
    
    @Override
    public Patient getPatient(int idPatient)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Patient p = null;
        try
        {
            p = (Patient) em.createNamedQuery("Patient.findByIdPatient").setParameter("idPatient", idPatient).getSingleResult();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            em.close();
            emf.close();
        }
        
        return p;
    }
}
