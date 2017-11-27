/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Doublon
 */
public class JPA
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try
        {
            Personnes c = new Personnes();
            c.setNom("Tusset");
            c.setPrenom("Quentin");
            
            em.persist(c);
            
            Personnes P2 = em.find(Personnes.class,1);
            System.out.println(P2.getNom());
            
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
    
}
