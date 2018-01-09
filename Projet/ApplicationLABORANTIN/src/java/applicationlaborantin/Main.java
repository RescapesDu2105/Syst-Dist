/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantin;

import interfaces.SessionBeanAnalysesRemote;
import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
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
    
    private static Connection connectionQueue;
    private static Session sessionQueue;    
        
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
                System.err.println("Authentification invalide !");
                System.exit(1);
            }
            else        
            {
                connectionQueue = myQueueFactory.createConnection();
                sessionQueue = connectionQueue.createSession(false, Session.AUTO_ACKNOWLEDGE);
                connectionQueue.start();
                
                new LaborantinFrame(EJBAnalyses, myQueue, connectionQueue, sessionQueue).setVisible(true);
            }
        }
        catch (JMSException ex) 
        {        
            ex.printStackTrace();
            System.exit(1);
        }
        catch (Exception ex) 
        {
            System.err.println("Authentification invalide !");
            
            ex.printStackTrace();
            System.exit(1);
        }
    }    
}
