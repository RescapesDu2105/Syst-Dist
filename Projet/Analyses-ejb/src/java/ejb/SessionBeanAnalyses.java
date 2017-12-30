/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Analyses;
import entities.Demande;
import entities.Medecin;
import entities.Patient;
import interfaces.SessionBeanAnalysesRemote;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
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
@DeclareRoles("Medecins")//, "Laborantins", "asadmin"})
public class SessionBeanAnalyses implements SessionBeanAnalysesRemote
{
    @Resource SessionContext sessionContext; 
    @Override
    @RolesAllowed("Medecins")//, "Laborantins", "asadmin"})
    public Principal Authentification()
    {
        try
        {
            System.out.println("Authentification");
            System.out.println("ctx = " + sessionContext);
            System.out.println("caller = " + sessionContext.getCallerPrincipal());
            
            if(sessionContext.isCallerInRole("Medecins"))
            {
                Principal callerPrincipal = sessionContext.getCallerPrincipal();
                if(callerPrincipal.getName().equals("philippedimartino"))
                {
                    return sessionContext.getCallerPrincipal();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Medecin getMedecinByLogin(String login)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Medecin m = null;
        try
        {
            //System.out.println("Salut");
            m = em.createNamedQuery("Medecin.findByLogin", Medecin.class).setParameter("login", login).getSingleResult();
            //System.out.println("Medecin = " + m);
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
        
        return m;
    }
        
    @Override
    public ArrayList<Analyses> getAnalyses()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        ArrayList<Analyses> analyses = null;
        try
        {
            analyses = new ArrayList<>(em.createNamedQuery("Analyses.findAll").getResultList());
            System.out.println("analyses = " + analyses.size());
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
        
        return analyses;
    }

    @Override
    public int PrescrireDemande(Medecin medecin, Patient patient, boolean urgent, ArrayList<String> analyses)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        int DemandeId = -1;
        
        em.getTransaction().begin();
        try
        {
            Analyses a = new Analyses();
            
            
            Demande d = new Demande();
            d.setRefMedecin(medecin);
            d.setRefPatient(patient);
            d.setDateHeureDemande(new Timestamp(System.currentTimeMillis()));
            d.setUrgent(urgent == true ? 1 : 0);
            em.persist(d);             
            DemandeId = (int) em.createQuery("SELECT LAST_INSERT_ID() FROM bd_systdist.demande").getSingleResult();
            em.getTransaction().commit();
            //Envoi sur la QUEUE
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
            em.getTransaction().rollback(); 
        }
        finally
        {
            em.close();
            emf.close();
        }
        
        return DemandeId;
    }
}
