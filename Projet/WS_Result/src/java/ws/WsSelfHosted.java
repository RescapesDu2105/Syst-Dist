/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entities.Patient;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Doublon&Zeydax
 */

@WebService(serviceName = "WSHoster")
public class WsSelfHosted
{
    @WebMethod(operationName = "ConsulterResultats")
    public String ConsulterResultats(@WebParam(name = "Reference") String Reference)
    {
        
        
        return "Hello " + Reference + " !!!!";
    }
    
    @WebMethod(operationName = "AjouterPatient")
    public void AjouterPatient(@WebParam(name = "idPatient") Integer idPatient, @WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "login") String login)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WS_ResultPU");
        EntityManager em = emf.createEntityManager();
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
    
    
}