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
@DeclareRoles({"Medecins", "Laborantins"})
public class SessionBeanAnalyses implements SessionBeanAnalysesRemote
{
    @Resource SessionContext sessionContext; 
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Analyses-ejbPU");
    private final EntityManager em = emf.createEntityManager();

    @Override
    @RolesAllowed({"Medecins", "Laborantins"})
    public Principal Authentification()
    {
        //log("Connexion de : " + sessionContext.getCallerPrincipal().getName());
        return sessionContext.getCallerPrincipal();
    }

    @Override
    public Medecin getMedecinByLogin(String login)
    {
        em.getTransaction().begin();
        Medecin m = null;
        try
        {
            m = (Medecin) em.createNamedQuery("Medecin.findByLogin").setParameter("login", login).getSingleResult();
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
