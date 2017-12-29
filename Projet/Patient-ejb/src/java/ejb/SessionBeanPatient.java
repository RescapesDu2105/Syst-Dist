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
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Philippe
 */
@Stateless
public class SessionBeanPatient implements SessionBeanPatientRemote
{   
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
    private final EntityManager em = emf.createEntityManager();
        
    @Override
    public List getPatients()
    {        
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
        }          
        
        return lp;
    }
    
    @Override
    public ArrayList<Patient> ChercherPatient(String nom, String prenom)
    {        
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
        }          
        
        return lp;
    }

    @Override
    public boolean AjouterPatient(String nom, String prenom, String login)
    {
        boolean error = false;
        
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
            error = true;
        }
        finally
        {
            em.close();
        }
        
        return error;
    }
    @Override
    public boolean AjouterPatient(Patient p)
    {
        boolean error = false;
        
        em.getTransaction().begin();
        
        try
        {
            if(em.contains(p))
            {
                System.out.println("Contient");
            }
            else
            {
                em.persist(p);            
                em.getTransaction().commit();
                error = !em.contains(p);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            //em.getTransaction().rollback();
            error = true;
        }
        finally
        {
            em.close();
        }
        
        return error;
    }

    @Override
    public void ModifierPatient(int idPatient)
    {
        Patient p = getPatient(idPatient);
        
        // Modifier mais comment ?
        
    }
    
    @Override
    public Patient getPatient(int idPatient)
    {
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
        }
        
        return p;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
