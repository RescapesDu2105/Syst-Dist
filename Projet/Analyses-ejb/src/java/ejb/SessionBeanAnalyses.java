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
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
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
    public HashMap<Demande, ArrayList<Analyses>> getAllResultatsAnalysesByPatient(Patient patient, boolean urgent)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        HashMap<Demande, ArrayList<Analyses>> Resultats = new HashMap<>();
        ArrayList<Demande> Demandes = null;
        try
        {
            Demandes = new ArrayList<>(em.createNamedQuery("Demande.findByResultatsDisponibles").setParameter("patient", patient).setParameter("urgent", urgent).getResultList());
            for(Demande d : Demandes)
            {
                Resultats.put(d, getAnalysesByDemande(d));
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
        
        return Resultats;
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
            d.setResultatsDisponibles(false);
            em.persist(d);             
            em.flush();
            
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
    public void TraiterDemande(Demande demande, ArrayList<Analyses> analyses, Date dateDebut, Date dateFin)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try
        {
            Query query = em.createQuery("UPDATE Demande d SET d.resultatsDisponibles = true WHERE d.idDemande = :idDemande");
            query.setParameter("idDemande", demande.getIdDemande());            
            query.executeUpdate();                
            
            for(Analyses a : analyses)
            {
                query = em.createQuery("UPDATE Analyses a SET a.valeur = :valeur WHERE a.idAnalyses = :idAnalyses");
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
            
            log(demande.getIdDemande(), dateDebut, dateFin);
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
    
    private void log(int idDemande, Date dateDebut, Date dateFin) 
    {
        try
        {
            long diff = Math.abs(dateFin.getTime() - dateDebut.getTime());
            long Heures = diff / (60 * 60 * 1000);
            long Minutes = diff % (60 * 60 * 1000);
            
            TextMessage tm = contextT.createTextMessage();
            tm.setText("La durée de traitement de l'analyse " + idDemande + " a été de " + Heures + "h" + Minutes + " min");
            tm.setBooleanProperty("MDBlog", true);
            
            contextT.createProducer().send(myTopic, tm);
        } 
        catch (JMSException ex) 
        {
            ex.printStackTrace();
        }
    }
}
