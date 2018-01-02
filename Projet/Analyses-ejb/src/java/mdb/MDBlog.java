/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import entities.Logs;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Philippe
 */
@MessageDriven(activationConfig =
{
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/myTopic")
    ,   @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myTopic")
    ,   @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,   @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/myTopic")
    ,   @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    ,   @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "MDBlog = true")
})
public class MDBlog implements MessageListener
{
    
    public MDBlog()
    {
    }
    
    @Override
    public void onMessage(Message message)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaLibraryAppPU");
        EntityManager em = emf.createEntityManager();        
        
        em.getTransaction().begin();        
        Logs logs = new Logs();
        try
        {
            TextMessage msg = (TextMessage) message;
            logs.setInfos(msg.getText());
            em.persist(logs);            
            em.getTransaction().commit();
        }
        catch(JMSException e)
        {
            e.printStackTrace();
            //em.getTransaction().rollback();
        }
        finally
        {
            em.close();
            emf.close();
        }        
    }
    
}
