/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantin;

import entities.Analyses;
import entities.Demande;
import interfaces.SessionBeanAnalysesRemote;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author Philippe
 */
public class LaborantinFrame extends javax.swing.JFrame implements MessageListener
{
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    
    private final Queue queue;
    private final Connection connectionQ;
    private final Session sessionQ;    

    private MessageProducer producerQ = null;
    private MessageConsumer consumerQ = null;  
    
    private TraiterFrame traiterFrame = null;
    
    /**
     * Creates new form LaborantinFrame
     * @param EJBAnalyses
     * @param queue
     * @param connectionQ
     * @param sessionQ
     */
    public LaborantinFrame(SessionBeanAnalysesRemote EJBAnalyses, Queue queue, Connection connectionQ, Session sessionQ)
    {        
        LaborantinFrame.EJBAnalyses = EJBAnalyses;
        this.queue = queue;
        this.connectionQ = connectionQ;
        this.sessionQ = sessionQ;
        
        try
        {
            consumerQ = sessionQ.createConsumer(queue);
            consumerQ.setMessageListener((MessageListener) this);
            
            producerQ = sessionQ.createProducer(queue);
        }
        catch(JMSException ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
        
        initComponents();
        setLocationRelativeTo(null);
        
        if(traiterFrame != null)
            jButton_Traiter.setEnabled(true);
    }
    
    @Override
    public void onMessage(Message message)
    {
        System.out.println("====== R�ception d'une notification de myQueue =====");
        try 
        {
            ObjectMessage om = (ObjectMessage) message;

            if(jButton_Traiter != null)
                jButton_Traiter.setEnabled(true);

            Demande d = (Demande) om.getObject();
            ArrayList<Analyses> analyses = EJBAnalyses.getAnalysesByDemande(d);
            traiterFrame = new TraiterFrame(EJBAnalyses, d, analyses);       
        } 
        catch (JMSException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton_Traiter = new javax.swing.JButton();
        jButton_Quitter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        jButton_Traiter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Traiter.setText("Traiter la demande");
        jButton_Traiter.setEnabled(false);
        jButton_Traiter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_TraiterActionPerformed(evt);
            }
        });

        jButton_Quitter.setText("Quitter");
        jButton_Quitter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_QuitterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton_Traiter, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jButton_Quitter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton_Traiter, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Quitter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_TraiterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_TraiterActionPerformed
    {//GEN-HEADEREND:event_jButton_TraiterActionPerformed
        jButton_Traiter.setEnabled(false);
        traiterFrame.setVisible(true);
        
        /*try
        {
            sessionQ.commit();
        }
        catch (JMSException ex)
        {
            Logger.getLogger(LaborantinFrame.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton_TraiterActionPerformed

    private void jButton_QuitterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_QuitterActionPerformed
    {//GEN-HEADEREND:event_jButton_QuitterActionPerformed
        try
        {
            connectionQ.close();
        }
        catch (JMSException ex)
        {
            Logger.getLogger(LaborantinFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton_QuitterActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        try
        {
            connectionQ.close();
        }
        catch (JMSException ex)
        {
            Logger.getLogger(LaborantinFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Quitter;
    private javax.swing.JButton jButton_Traiter;
    // End of variables declaration//GEN-END:variables
}