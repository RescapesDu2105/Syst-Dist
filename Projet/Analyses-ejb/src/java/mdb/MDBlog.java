/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

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
        
    }
    
}
