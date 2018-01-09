/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationclienttestremote;

import businessTestRemote.SessionBeanTestRemoteRemote;
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
 * @author Doublon
 */
public class Main
{

    @Resource(mappedName = "jms/myQueue")
    private static Topic myQueue;

    @Resource(mappedName = "jms/myTestTopicFactory")
    private static ConnectionFactory myTestTopicFactory;

    @EJB
    private static SessionBeanTestRemoteRemote sessionBeanTestRemote;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //System.out.println(sessionBeanTestRemote.SayHello("Tusset"));
        
    }

    private Message createJMSMessageForjmsMyQueue(Session session, Object messageData) throws JMSException
    {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToMyQueue(Object messageData) throws JMSException
    {
        Connection connection = null;
        Session session = null;
        try
        {
            connection = myTestTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(myQueue);
            messageProducer.send(createJMSMessageForjmsMyQueue(session, messageData));
        } finally
        {
            if (session != null)
            {
                try
                {
                    session.close();
                } catch (JMSException e)
                {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null)
            {
                connection.close();
            }
        }
    }
    
}
