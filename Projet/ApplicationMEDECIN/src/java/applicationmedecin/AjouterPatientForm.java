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
import javax.ejb.EJB;
import javax.swing.JOptionPane;

/**
 *
 * @author Philippe
 */
public class AjouterPatientForm extends javax.swing.JFrame
{
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    @EJB
    private static SessionBeanPatientRemote EJBPatients;
    private final Medecin medecin;
    private final EntrerPatientForm frame;
    /**
     * Creates new form AjouterPatientForm
     * @param EJBAnalyses
     * @param EJBPatients
     * @param frame
     * @param medecin
     */
    public AjouterPatientForm(SessionBeanAnalysesRemote EJBAnalyses, SessionBeanPatientRemote EJBPatients, Medecin medecin, EntrerPatientForm frame)
    {        
        AjouterPatientForm.EJBAnalyses = EJBAnalyses;
        AjouterPatientForm.EJBPatients = EJBPatients;  
        this.medecin = medecin;
        this.frame = frame;
        
        initComponents();
        setLocationRelativeTo(this);
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
        jButton_Ajouter = new javax.swing.JButton();
        jButton_Annuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Nom :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Ajouter un patient");

        jTF_Nom.setText("Barsics");

        jLabel3.setText("Pr�nom :");

        jLabel4.setText("Login :");

        jTF_Prenom.setText("Joseph");

        jTF_Login.setText("babajojo");

        jButton_Ajouter.setText("Ajouter");
        jButton_Ajouter.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_AjouterActionPerformed(evt);
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
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton_Annuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Ajouter))
                    .addComponent(jTF_Nom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jTF_Login, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTF_Prenom, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addContainerGap(51, Short.MAX_VALUE))
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
                    .addComponent(jButton_Ajouter)
                    .addComponent(jButton_Annuler))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AnnulerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AnnulerActionPerformed
    {//GEN-HEADEREND:event_jButton_AnnulerActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_AnnulerActionPerformed

    private void jButton_AjouterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_AjouterActionPerformed
    {//GEN-HEADEREND:event_jButton_AjouterActionPerformed
        if(jTF_Nom.getText().isEmpty() || jTF_Prenom.getText().isEmpty() || jTF_Login.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Des champs n'ont pas �t� compl�t�s !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            frame.dispose();
            this.dispose();
            
            Patient p = new Patient();
            p.setNom(jTF_Nom.getText());
            p.setPrenom(jTF_Prenom.getText());
            p.setLogin(jTF_Login.getText());
            
            int error = EJBPatients.AjouterPatient(p);
            switch (error)
            {
                case 1:
                    JOptionPane.showMessageDialog(this, "Une erreur interne s'est produite !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    break;
                case 2:                    
                    JOptionPane.showMessageDialog(this, "Le login est d�j� utilis� !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    break;
                case 3:                     
                    JOptionPane.showMessageDialog(this, "Le patient n'a pas bien �t� ins�r� !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                    break;
                default:                    
                    new MedecinForm(EJBAnalyses, EJBPatients, medecin, p).setVisible(true);
                    break;
            }
        }
    }//GEN-LAST:event_jButton_AjouterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Ajouter;
    private javax.swing.JButton jButton_Annuler;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTF_Login;
    private javax.swing.JTextField jTF_Nom;
    private javax.swing.JTextField jTF_Prenom;
    // End of variables declaration//GEN-END:variables
}
