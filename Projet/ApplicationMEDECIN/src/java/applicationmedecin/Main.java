/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecin;

import entities.Medecin;
import interfaces.SessionBeanAnalysesRemote;
import interfaces.SessionBeanPatientRemote;
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
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Philippe
 */
public class Main
{

    @Resource(mappedName = "jms/myTopic")
    private static Topic myTopic;
    @Resource(mappedName = "jms/myTopicFactory")
    private static ConnectionFactory myTopicFactory;    
    
    private static Connection connection = null;
    private static Session session = null;
    
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    @EJB
    private static SessionBeanPatientRemote EJBPatients;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {           
            Principal principal = EJBAnalyses.AuthentificationMedecin();
            Medecin medecin = EJBAnalyses.getMedecinByLogin(principal.getName());
            

            if(medecin == null)
            {
                //Erreur
                //JOptionPane.showMessageDialog(this, "Le nom d'utilisateur et/ou le mot de passe est incorrect !", "Erreur", JOptionPane.ERROR_MESSAGE);
                System.err.println("Erreur de login 1");
                System.exit(1);
            }
            else
            {        
                connection = myTopicFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                connection.start();
            
                new EntrerPatientForm(EJBAnalyses, EJBPatients, medecin, myTopic, connection, session).setVisible(true);
            }
        }
        catch (Exception ex) 
        {
            System.err.println("Erreur de login 2");
            System.exit(1);
        }
    }    
}
