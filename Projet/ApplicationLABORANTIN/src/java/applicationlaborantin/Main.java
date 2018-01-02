/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantin;

import interfaces.SessionBeanAnalysesRemote;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Doublon
 */
public class Main
{

    @Resource(mappedName = "jms/myQueue")
    private static Queue myQueue;
    @Resource(mappedName = "jms/myQueueFactory")
    private static ConnectionFactory myQueueFactory; 
    private static Connection connectionQueue = null;
    private static Session sessionQueue = null;
    
    @Resource(mappedName = "jms/myTopic")
    private static Topic myTopic;
    @Resource(mappedName = "jms/myTopicFactory")
    private static ConnectionFactory myTopicFactory;
    private static Connection connectionTopic = null;
    private static Session sessionTopic = null;
        
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            Principal principal = EJBAnalyses.AuthentificationLaborantin();

            if(principal == null)
            {
                //JOptionPane.showMessageDialog(this, "Le nom d'utilisateur et/ou le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
                System.err.println("Erreur de login");
                System.exit(1);
            }
            else
            {
                connectionQueue = myQueueFactory.createConnection();
                sessionQueue = connectionQueue.createSession(false, Session.AUTO_ACKNOWLEDGE);
                connectionQueue.start();
                
                
                connectionTopic = myTopicFactory.createConnection();
                sessionTopic = connectionTopic.createSession(false, Session.AUTO_ACKNOWLEDGE);
                connectionTopic.start();
                new LaborantinFrame(EJBAnalyses, myQueue, connectionQueue, sessionQueue, myTopic, connectionTopic, sessionTopic).setVisible(true);
            }
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }    
}
