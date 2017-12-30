/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecin;

import entities.Medecin;
import entities.Patient;
import interfaces.SessionBeanAnalysesRemote;
import interfaces.SessionBeanPatientRemote;
import java.awt.CardLayout;
import javax.ejb.EJB;

/**
 *
 * @author Philippe
 */
public class MedecinForm extends javax.swing.JFrame
{
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    @EJB
    private static SessionBeanPatientRemote EJBPatients;
    private final Medecin medecin;
    private final Patient patient;
    
    
    /**
     * Creates new form MedecinForm
     * @param EJBAnalyses
     * @param EJBPatients
     * @param medecin
     * @param patient
     */
    public MedecinForm(SessionBeanAnalysesRemote EJBAnalyses, SessionBeanPatientRemote EJBPatients, Medecin medecin, Patient patient)
    {
        MedecinForm.EJBAnalyses = EJBAnalyses;
        MedecinForm.EJBPatients = EJBPatients;  
        this.medecin = medecin;
        this.patient = patient;
        
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

        jButton_Consulter.setText("Consulter des analyses");
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
    }//GEN-LAST:event_jButton_ConsulterActionPerformed

    private void jButton_QuitterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_QuitterActionPerformed
    {//GEN-HEADEREND:event_jButton_QuitterActionPerformed
        jButton_Prescrire.setEnabled(false);
        jButton_Consulter.setEnabled(false);
        jButton_Modifier.setEnabled(false);
        jButton_Quitter.setEnabled(false);
    }//GEN-LAST:event_jButton_QuitterActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_Consulter;
    public javax.swing.JButton jButton_Modifier;
    public javax.swing.JButton jButton_Prescrire;
    public javax.swing.JButton jButton_Quitter;
    public javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables
}
