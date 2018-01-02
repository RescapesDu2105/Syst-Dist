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
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Philippe
 */
@Stateless
@DeclareRoles({"Medecin", "Laborantin"})
public class SessionBeanAnalyses implements SessionBeanAnalysesRemote
{

    @Resource(mappedName = "jms/myTopic")
    private Topic myTopic;

    @Inject
    @JMSConnectionFactory("jms/myTopicFactory")
    private JMSContext contextT;

    @Resource(mappedName = "jms/myQueue")
    private Queue myQueue;

    @Inject
    @JMSConnectionFactory("jms/myQueueFactory")
    private JMSContext contextQ;
    @Resource SessionContext sessionContext; 
    
    @Override
    @RolesAllowed("Medecin")
    public Principal AuthentificationMedecin()
    {
        try
        {
            //System.out.println("Authentification");
            //System.out.println("ctx = " + sessionContext);
            //System.out.println("caller = " + sessionContext.getCallerPrincipal());
            
            if(sessionContext.isCallerInRole("Medecin"))
            {
                return sessionContext.getCallerPrincipal();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    @RolesAllowed("Laborantin")
    public Principal AuthentificationLaborantin()
    {
        try
        {
            //System.out.println("Authentification");
            //System.out.println("ctx = " + sessionContext);
            //System.out.println("caller = " + sessionContext.getCallerPrincipal());
            
            if(sessionContext.isCallerInRole("Laborantin"))
            {
                return sessionContext.getCallerPrincipal();
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
    public ArrayList<Analyses> getAnalysesByDemande(Demande Demande)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        ArrayList<Analyses> analyses = null;
        try
        {
            analyses = new ArrayList<>(em.createNamedQuery("Analyses.findByDemande").setParameter("demande", Demande).getResultList());
            //System.out.println("analyses = " + analyses.size());
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
            Demande d = new Demande();
            d.setRefMedecin(medecin);
            d.setRefPatient(patient);
            d.setDateHeureDemande(new Timestamp(System.currentTimeMillis()));
            d.setUrgent(urgent);
            em.persist(d);             
            em.flush();
            
            //if(patient.getDemandeList() == null)
              //  patient.setDemandeList(new ArrayList<Demande>());
            
            //patient.getDemandeList().add(d);
            //em.persist(patient);
            DemandeId = d.getIdDemande();
            for(String s : analyses)
            {                
                Analyses a = new Analyses();
                a.setDemande(d);
                a.setItem(s);
                em.persist(a);
            }
            em.getTransaction().commit();
            
            ObjectMessage om = contextQ.createObjectMessage();
            om.setObject(d);
            om.setBooleanProperty("Demande", true);
            
            contextQ.createProducer().send(myQueue, om);
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
        
        return DemandeId;
    }

    @Override
    public void TraiterDemande(Demande demande, ArrayList<Analyses> analyses)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try
        {
            for(Analyses a : analyses)
            {
                Query query = em.createQuery("UPDATE Analyses a SET a.valeur = :valeur WHERE a.idAnalyses = :idAnalyses");
                query.setParameter("valeur", a.getValeur());
                query.setParameter("idAnalyses", a.getIdAnalyses()); 
                query.executeUpdate();                
            }
            em.getTransaction().commit();
            
            if(demande.getUrgent() == true)
            {
                ObjectMessage om = contextT.createObjectMessage();
                om.setObject(demande);

                contextT.createProducer().send(myTopic, om);
            }
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
    }
}
