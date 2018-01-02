/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantin;

import entities.Analyses;
import entities.Demande;
import interfaces.SessionBeanAnalysesRemote;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Philippe
 */
public class TraiterFrame extends javax.swing.JFrame
{
    @EJB
    private static SessionBeanAnalysesRemote EJBAnalyses;
    private final Demande demande;
    private final ArrayList<Analyses> analyses;
    private final Date dateDebut;
    /**
     * Creates new form TraiterFrame
     * @param EJBAnalyses
     * @param demande
     * @param analyses
     */
    public TraiterFrame(SessionBeanAnalysesRemote EJBAnalyses, Demande demande, ArrayList<Analyses> analyses)
    {
        TraiterFrame.EJBAnalyses = EJBAnalyses;
        this.demande = demande;
        this.analyses = analyses;
        this.dateDebut = Date.from(Instant.now());
        
        initComponents();
        setLocationRelativeTo(null);
        
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        
        jLabel.setText("R�f�rence de la demande : " + analyses.get(0).getDemande().getIdDemande());
        //System.out.println("analyses = " + analyses.size());
        this.analyses.forEach((a) ->
        {
            Object[] obj = new Object[2];
            obj[0] = a.getItem();
            dtm.addRow(obj);
        });
        
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

        jButton_Confirmer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_Confirmer.setText("Confirmer");
        jButton_Confirmer.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_ConfirmerActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Item", "Valeur"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, true
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
        jScrollPane1.setViewportView(jTable);

        jLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton_Confirmer)
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Confirmer)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ConfirmerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_ConfirmerActionPerformed
    {//GEN-HEADEREND:event_jButton_ConfirmerActionPerformed
        boolean Ok = true;
        for(int i = 0 ; i < jTable.getModel().getRowCount() && Ok ; i++)
        {
            if(jTable.getModel().getValueAt(i, 1) == null)
                Ok = false;
        }
        
        if(Ok)
        {
            for(int i = 0 ; i < jTable.getModel().getRowCount() && Ok ; i++)
            {
                analyses.get(i).setValeur((String) jTable.getModel().getValueAt(i, 1));
            }
            demande.setResultatsDisponibles(true);
            EJBAnalyses.TraiterDemande(demande, analyses, dateDebut, Date.from(Instant.now()));
            this.dispose();
        }
        else
            JOptionPane.showMessageDialog(this, "Toutes les valeurs n'ont pas �t� encod�es !", "Attention", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jButton_ConfirmerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Confirmer;
    private javax.swing.JLabel jLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
