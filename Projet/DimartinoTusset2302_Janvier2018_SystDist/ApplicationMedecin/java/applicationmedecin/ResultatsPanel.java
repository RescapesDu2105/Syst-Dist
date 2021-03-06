/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecin;

import entities.Analyses;
import entities.Demande;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Philippe
 */
public class ResultatsPanel extends javax.swing.JPanel
{    
    private final MedecinForm medecinForm;
    private final HashMap<Demande, ArrayList<Analyses>> Resultats;
    private final ArrayList<Demande> Demandes;

    /**
     * Creates new form ResultatsPanel
     * @param medecinForm
     * @param Resultats
     */
    public ResultatsPanel(MedecinForm medecinForm, HashMap<Demande, ArrayList<Analyses>> Resultats)
    {
        this.medecinForm = medecinForm;
        this.Resultats = Resultats;
        initComponents();
        
        DefaultTableModel dlm = (DefaultTableModel) jTable.getModel();
        Object[][] objects = new Object[Resultats.size()][5];
        ArrayList<Demande> Demandes = new ArrayList<>();
        Demandes.addAll(Resultats.keySet());
        this.Demandes = Demandes;
        
        for(int i = 0 ; i < Demandes.size() ; i++)
        {
            Demande d = Demandes.get(i);
            objects[i][0] = d.getIdDemande();
            objects[i][1] = d.getRefPatient();
            objects[i][2] = d.getRefMedecin();
            objects[i][3] = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.FRANCE).format(d.getDateHeureDemande());
            objects[i][4] = d.getUrgent();
            dlm.addRow(objects[i]);
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable.setDefaultRenderer(String.class, centerRenderer);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButton_Fermer = new javax.swing.JButton();

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "R�f�rence", "RefPatient", "RefMedecin", "DateHeureDemande", "Urgent"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jTable.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jButton_Fermer.setText("Fermer");
        jButton_Fermer.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_FermerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jButton_Fermer)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Fermer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableMouseClicked
    {//GEN-HEADEREND:event_jTableMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) 
        {
            int selectedRow = jTable.getSelectedRow();
            Demande selectedDemande = (Demande) Demandes.get(selectedRow);             
            new AfficherAnalysesFrame(selectedDemande.getIdDemande(), Resultats.get(selectedDemande)).setVisible(true);
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jButton_FermerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_FermerActionPerformed
    {//GEN-HEADEREND:event_jButton_FermerActionPerformed
        medecinForm.jPanel.removeAll();
        medecinForm.jPanel.repaint();        
        
        medecinForm.jButton_Prescrire.setEnabled(true);
        medecinForm.jButton_Modifier.setEnabled(true);
        medecinForm.jButton_Consulter.setEnabled(true);
        medecinForm.jButton_Quitter.setEnabled(true);
    }//GEN-LAST:event_jButton_FermerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Fermer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
