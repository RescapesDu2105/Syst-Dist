/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Medecin;
import interfaces.SessionBeanAnalysesRemote;
import java.security.Principal;
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
        }
        
        return m;
    }
    
}
