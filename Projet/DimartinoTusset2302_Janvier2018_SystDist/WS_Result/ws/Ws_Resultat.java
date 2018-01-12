/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entities.Analyses;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Philippe
 */
@WebService(serviceName = "Ws_Resultat")
public class Ws_Resultat
{
    @WebMethod(operationName = "getResultats")
    public ArrayList<Analyses> getResultats(int reference)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        ArrayList<Analyses> Analyses = null;
        try
        {
            Analyses = new ArrayList<>(em.createNamedQuery("Analyses.findByDemandeId").setParameter("demandeId", reference).getResultList());
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
        
        return Analyses; 
    }
}
