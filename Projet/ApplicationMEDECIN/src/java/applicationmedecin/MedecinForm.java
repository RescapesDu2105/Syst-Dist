/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecin;

import entities.Analyses;
import entities.Demande;
import entities.Medecin;
import entities.Patient;
import interfaces.SessionBeanAnalysesRemote;
import interfaces.SessionBeanPatientRemote;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Philippe
 */
public class MedecinForm extends javax.swing.JFrame implements MessageListener
{
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    @EJB
    private static SessionBeanPatientRemote EJBPatients;
    private final Medecin medecin;
    private final Patient patient;
           
    private final HashMap<Demande, ArrayList<Analyses>> ResultatsUrgents;
    
    private final Topic topic;
    private final Connection connection;
    private final Session session;
    
    private MessageProducer producer = null;
    private MessageConsumer consumer = null;
    
    /**
     * Creates new form MedecinForm
     * @param EJBAnalyses
     * @param EJBPatients
     * @param medecin
     * @param patient
     * @param topic
     * @param connection
     * @param session
     */
    public MedecinForm(SessionBeanAnalysesRemote EJBAnalyses, SessionBeanPatientRemote EJBPatients, Medecin medecin, Patient patient, Topic topic, Connection connection, Session session)
    {
        MedecinForm.EJBAnalyses = EJBAnalyses;
        MedecinForm.EJBPatients = EJBPatients;  
        this.medecin = medecin;
        this.patient = patient;     
        this.ResultatsUrgents = EJBAnalyses.getAllResultatsAnalysesByPatient(this.patient, true);
        
        this.topic = topic;
        this.connection = connection;
        this.session = session;
        
        try
        {           
            consumer = session.createConsumer(this.topic);
            consumer.setMessageListener((MessageListener) this);

            producer = session.createProducer(this.topic);
        }
        catch (JMSException ex)
        {
            Logger.getLogger(MedecinForm.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        
        initComponents();
        jPanel.setLayout(new CardLayout());
        setLocationRelativeTo(null);
        
        setTitle("Medecin : " + medecin.toString() + " | Patient : " + patient.toString());
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

        jButton_Modifier = new javax.swing.JButton();
        jButton_Prescrire = new javax.swing.JButton();
        jButton_Consulter = new javax.swing.JButton();
        jButton_Quitter = new javax.swing.JButton();
        jPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButton_Modifier.setText("Modifier le patient");
        jButton_Modifier.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ModifierActionPerformed(evt);
            }
        });

        jButton_Prescrire.setText("Prescrire des analyses");
        jButton_Prescrire.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_PrescrireActionPerformed(evt);
            }
        });

        jButton_Consulter.setText("Consulter des résultats");
        jButton_Consulter.setToolTipText("");
        jButton_Consulter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ConsulterActionPerformed(evt);
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

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton_Modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Prescrire, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Consulter, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Quitter, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Prescrire, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Consulter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Quitter, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ModifierActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModifierActionPerformed
    {//GEN-HEADEREND:event_jButton_ModifierActionPerformed
        jButton_Modifier.setEnabled(false);
        jButton_Prescrire.setEnabled(false);
        jButton_Consulter.setEnabled(false);
        jButton_Quitter.setEnabled(false);
        
        jPanel.removeAll();
        jPanel.add(new ModifierPatientPanel(EJBPatients, this, jPanel, patient)); 
        this.revalidate();
    }//GEN-LAST:event_jButton_ModifierActionPerformed

    private void jButton_PrescrireActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_PrescrireActionPerformed
    {//GEN-HEADEREND:event_jButton_PrescrireActionPerformed
        jButton_Modifier.setEnabled(false);
        jButton_Prescrire.setEnabled(false);
        jButton_Consulter.setEnabled(false);
        jButton_Quitter.setEnabled(false);
        
        jPanel.removeAll();
        jPanel.add(new PrescriptionPanel(EJBAnalyses, EJBPatients, medecin, patient, this)); 
        this.revalidate();
    }//GEN-LAST:event_jButton_PrescrireActionPerformed

    private void jButton_ConsulterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ConsulterActionPerformed
    {//GEN-HEADEREND:event_jButton_ConsulterActionPerformed
        jButton_Prescrire.setEnabled(false);
        jButton_Modifier.setEnabled(false);
        jButton_Consulter.setEnabled(false);
        jButton_Quitter.setEnabled(false);
        
        String[] options = new String[] {"Consulter les résultats urgents (" + ResultatsUrgents.size() + ")", "Consulter tous les résultats", "Annuler"};
        int Choix = JOptionPane.showOptionDialog(null, "Consulter quels résultats ?", "Consulter", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        
        switch (Choix)
        {
            case 0:
                jPanel.removeAll();
                jPanel.add(new ResultatsPanel(this, ResultatsUrgents)); 
                this.revalidate();
                break;
            case 1:
                jPanel.removeAll();
                jPanel.add(new ResultatsPanel(this, EJBAnalyses.getAllResultatsAnalysesByPatient(patient, false))); 
                this.revalidate();
                break;
            case 2:
                jButton_Prescrire.setEnabled(true);
                jButton_Modifier.setEnabled(true);
                jButton_Consulter.setEnabled(true);
                jButton_Quitter.setEnabled(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton_ConsulterActionPerformed

    private void jButton_QuitterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_QuitterActionPerformed
    {//GEN-HEADEREND:event_jButton_QuitterActionPerformed
        String[] options = new String[] {"Annuler", "Changer de patient", "Quitter"};
        int Choix = JOptionPane.showOptionDialog(null, "Que souhaitez-vous faire ?", "Que faire ?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        
        if(Choix == 1)
        {
            this.dispose();            
            new EntrerPatientForm(EJBAnalyses, EJBPatients, medecin, topic, connection, session).setVisible(true);
        }
        else if(Choix == 2)
        {
            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_jButton_QuitterActionPerformed

    @Override
    public void onMessage(Message message)
    {
        System.out.println("====== Réception d'une notification de myTopic =====");
        if(jPanel != null)
        {
            try 
            {
                if(!message.getBooleanProperty("MDBlog"))
                {
                    ObjectMessage om = (ObjectMessage) message;

                    Demande d = (Demande) om.getObject();
                    ResultatsUrgents.put(d, EJBAnalyses.getAnalysesByDemande(d));
                }
            } 
            catch (JMSException ex) 
            {
                ex.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_Consulter;
    public javax.swing.JButton jButton_Modifier;
    public javax.swing.JButton jButton_Prescrire;
    public javax.swing.JButton jButton_Quitter;
    public javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables

}
