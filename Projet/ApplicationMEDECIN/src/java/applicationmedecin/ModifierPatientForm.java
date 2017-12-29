/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecin;

import entities.Patient;
import interfaces.SessionBeanPatientRemote;
import javax.ejb.EJB;
import javax.swing.JPanel;

/**
 *
 * @author Philippe
 */
public class ModifierPatientForm extends javax.swing.JFrame
{
    @EJB
    private static SessionBeanPatientRemote EJBPatients;
    private final JPanel jPanel;
    private final Patient patient;
    
    /**
     * Creates new form AjouterPatientForm
     * @param EJBPatients
     * @param jPanel
     * @param patient
     */
    public ModifierPatientForm(SessionBeanPatientRemote EJBPatients, JPanel jPanel, Patient patient)
    {
        ModifierPatientForm.EJBPatients = EJBPatients;
        this.patient = patient;
        this.jPanel = jPanel;
        
        initComponents();
        
        jTF_Nom.setText(patient.getNom());
        jTF_Prenom.setText(patient.getPrenom());
        jTF_Login.setText(patient.getLogin());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTF_Nom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTF_Prenom = new javax.swing.JTextField();
        jTF_Login = new javax.swing.JTextField();
        jButton_Modifier = new javax.swing.JButton();
        jButton_Annuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nom :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Modifier le patient");

        jLabel3.setText("Pr�nom :");

        jLabel4.setText("Login :");

        jButton_Modifier.setText("Modifier");
        jButton_Modifier.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ModifierActionPerformed(evt);
            }
        });

        jButton_Annuler.setText("Annuler");
        jButton_Annuler.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_Annuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Modifier)
                        .addGap(66, 66, 66))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTF_Nom, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(jTF_Login)
                            .addComponent(jTF_Prenom))))
                .addGap(80, 80, 80))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTF_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTF_Prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTF_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Modifier)
                    .addComponent(jButton_Annuler))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AnnulerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AnnulerActionPerformed
    {//GEN-HEADEREND:event_jButton_AnnulerActionPerformed
        jPanel.removeAll();
        jPanel.repaint();
    }//GEN-LAST:event_jButton_AnnulerActionPerformed

    private void jButton_ModifierActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ModifierActionPerformed
    {//GEN-HEADEREND:event_jButton_ModifierActionPerformed
        Patient updatedPatient = new Patient();
        updatedPatient.setIdPatient(patient.getIdPatient());
        updatedPatient.setNom(jTF_Nom.getText());
        updatedPatient.setPrenom(jTF_Prenom.getText());
        updatedPatient.setLogin(jTF_Login.getText());        
        
        EJBPatients.ModifierPatient(updatedPatient);
        
        jPanel.removeAll();
        jPanel.repaint();
    }//GEN-LAST:event_jButton_ModifierActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Annuler;
    private javax.swing.JButton jButton_Modifier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTF_Login;
    private javax.swing.JTextField jTF_Nom;
    private javax.swing.JTextField jTF_Prenom;
    // End of variables declaration//GEN-END:variables
}
