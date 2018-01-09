/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationclienttestremote;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Doublon
 */
public class myTestTopicGUI extends javax.swing.JFrame
{

    @Resource(mappedName = "jms/myQueue")
    private static Topic myQueue;

    @Resource(mappedName = "jms/myTestTopicFactory")
    private static ConnectionFactory myTestTopicFactory;
    private Topic topic = null;
    private Connection connection = null;
    private Session session = null;

    private MessageProducer producer = null;
    private MessageConsumer consumer = null;

    /**
     * Creates new form myTestTopicGUI
     */
    public myTestTopicGUI(Topic top , Connection conn , Session sess)
    {
        initComponents();

        topic = top ;
        connection = conn;
        session = sess;

        try
        {

            consumer = session.createConsumer(topic);
            consumer.setMessageListener((MessageListener) this);

            producer = session.createProducer(topic);
        } catch (JMSException ex)
        {
            Logger.getLogger(myTestTopicGUI.class.getName()).log(Level.SEVERE, null, ex);
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

        jTextFieldMessage = new javax.swing.JTextField();
        jLabelMessage = new javax.swing.JLabel();
        jButtonSend = new javax.swing.JButton();
        jLabelReception = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaReception = new javax.swing.JTextArea();
        cbMdb = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelMessage.setText("Message :");

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSendActionPerformed(evt);
            }
        });

        jLabelReception.setText("Reception :");

        jTextAreaReception.setColumns(20);
        jTextAreaReception.setRows(5);
        jScrollPane1.setViewportView(jTextAreaReception);

        cbMdb.setText("Send  to MDB");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelReception)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbMdb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelMessage)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSend)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMessage)
                    .addComponent(jButtonSend))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelReception)
                    .addComponent(cbMdb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSendActionPerformed
    {//GEN-HEADEREND:event_jButtonSendActionPerformed
        try
        {
            TextMessage tm = session.createTextMessage();
            tm.setText(jTextFieldMessage.getText());
            tm.setBooleanProperty("toMDB", cbMdb.isSelected());
            producer.send(tm);
        } catch (JMSException ex)
        {
            Logger.getLogger(myTestTopicGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSendActionPerformed
  
    public void onMessage (Message message)
    {
        try
        {
            TextMessage tm = (TextMessage) message;
            this.jTextAreaReception.append(" >> "+tm.getText()+"\n");
        } catch (JMSException ex)
        {
            Logger.getLogger(myTestTopicGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String args[])
    {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(myTestTopicGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(myTestTopicGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(myTestTopicGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(myTestTopicGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new myTestTopicGUI(null,null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbMdb;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelReception;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaReception;
    private javax.swing.JTextField jTextFieldMessage;
    // End of variables declaration//GEN-END:variables

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