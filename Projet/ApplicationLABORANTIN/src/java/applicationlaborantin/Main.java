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
    private static Connection connectionQueue = null;
    private static Session sessionQueue = null;    
        
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
                new LaborantinFrame(EJBAnalyses, myQueue, connectionQueue, sessionQueue).setVisible(true);
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }    
}
