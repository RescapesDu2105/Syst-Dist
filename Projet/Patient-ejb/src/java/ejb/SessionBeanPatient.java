/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Patient;
import interfaces.SessionBeanPatientRemote;
import java.security.Principal;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Philippe
 */
@Stateless
public class SessionBeanPatient implements SessionBeanPatientRemote
{   
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Patient-ejbPU");
    private final EntityManager em = emf.createEntityManager();
        
    @Override
    public ArrayList<Patient> getPatients()
    {        
        ArrayList<Patient> lp = null;
        em.getTransaction().begin();        
        try
        {
            lp = (ArrayList<Patient>) em.createNamedQuery("Patient.findAll").getResultList();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;      
        }
        finally
        {
            em.close();
        }          
        
        return lp;
    }

    @Override
    public void AjouterPatient(int idPatient, String nom, String prenom, String login)
    {
        em.getTransaction().begin();
        
        try
        {
            Patient p = new Patient();
            
            p.setIdPatient(idPatient);
            p.setLogin(login);
            p.setNom(nom);
            p.setPrenom(prenom);
            
            em.persist(p);
            
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        finally
        {
            em.close();
        }
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
