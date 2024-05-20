package main.UDP;

import main.MainMenuFrame;
import main.UDP.ChatRoom.*;
import main.UDP.TicTacToe.*;
import main.UDP.LengthConverter.*;
import main.UDP.WeightConverter.*;
import main.UDP.CurrencyConverter.*;
import main.UDP.TemperatureConverter.*;

public class UDPProtocalFrame extends javax.swing.JFrame {

    private UDPProtocalFrame() {
        initComponents();
    }

    public static UDPProtocalFrame getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {

        private static final UDPProtocalFrame INSTANCE = new UDPProtocalFrame();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuChatServer = new javax.swing.JMenuItem();
        menuChatClient = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuGameServer = new javax.swing.JMenuItem();
        menuGameClient = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuLengthConverterServer = new javax.swing.JMenuItem();
        menuLengthConverterClient = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuWeightConverterServer = new javax.swing.JMenuItem();
        menuWeightConverterClient = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuCurrencyConverterServer = new javax.swing.JMenuItem();
        menuCurrencyConverterClient = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UDP Protocal");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jButton1.setText("Back to Main Menu");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setPreferredSize(new java.awt.Dimension(131, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, java.awt.BorderLayout.SOUTH);

        jMenu1.setText("UDP ChatRoom");

        menuChatServer.setText("1. Server");
        menuChatServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChatServerActionPerformed(evt);
            }
        });
        jMenu1.add(menuChatServer);

        menuChatClient.setText("2. Client");
        menuChatClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChatClientActionPerformed(evt);
            }
        });
        jMenu1.add(menuChatClient);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Other Applications");

        jMenu3.setText("1. Tic-Tac-Toe Game");

        menuGameServer.setText("Server");
        menuGameServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGameServerActionPerformed(evt);
            }
        });
        jMenu3.add(menuGameServer);

        menuGameClient.setText("Client");
        menuGameClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGameClientActionPerformed(evt);
            }
        });
        jMenu3.add(menuGameClient);

        jMenu2.add(jMenu3);

        jMenu4.setText("2. Length Converter");

        menuLengthConverterServer.setText("Server");
        menuLengthConverterServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLengthConverterServerActionPerformed(evt);
            }
        });
        jMenu4.add(menuLengthConverterServer);

        menuLengthConverterClient.setText("Client");
        menuLengthConverterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLengthConverterClientActionPerformed(evt);
            }
        });
        jMenu4.add(menuLengthConverterClient);

        jMenu2.add(jMenu4);

        jMenu5.setText("3. Weight Converter");

        menuWeightConverterServer.setText("Server");
        menuWeightConverterServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuWeightConverterServerActionPerformed(evt);
            }
        });
        jMenu5.add(menuWeightConverterServer);

        menuWeightConverterClient.setText("Client");
        menuWeightConverterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuWeightConverterClientActionPerformed(evt);
            }
        });
        jMenu5.add(menuWeightConverterClient);

        jMenu2.add(jMenu5);

        jMenu6.setText("4. Currency Converter");

        menuCurrencyConverterServer.setText("Server");
        menuCurrencyConverterServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCurrencyConverterServerActionPerformed(evt);
            }
        });
        jMenu6.add(menuCurrencyConverterServer);

        menuCurrencyConverterClient.setText("Client");
        menuCurrencyConverterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCurrencyConverterClientActionPerformed(evt);
            }
        });
        jMenu6.add(menuCurrencyConverterClient);

        jMenu2.add(jMenu6);

        jMenu7.setText("5. Temperature Converter");

        jMenuItem1.setText("Server");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem1);

        jMenuItem2.setText("Client");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem2);

        jMenu2.add(jMenu7);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(414, 307));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        MainMenuFrame.backToMainMenu(this);
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menuGameServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGameServerActionPerformed
        new UDPTicTacToeServer().setVisible(true);
    }//GEN-LAST:event_menuGameServerActionPerformed

    private void menuChatServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChatServerActionPerformed
        new UDPChatServer().setVisible(true);
    }//GEN-LAST:event_menuChatServerActionPerformed

    private void menuChatClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChatClientActionPerformed
        new UDPChatClient().setVisible(true);
    }//GEN-LAST:event_menuChatClientActionPerformed

    private void menuGameClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuGameClientActionPerformed
        new UDPTicTacToeClient().setVisible(true);
    }//GEN-LAST:event_menuGameClientActionPerformed

    private void menuLengthConverterServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLengthConverterServerActionPerformed
        new UDPLengthConverterServer().setVisible(true);
    }//GEN-LAST:event_menuLengthConverterServerActionPerformed

    private void menuLengthConverterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLengthConverterClientActionPerformed
        new UDPLengthConverterClient().setVisible(true);
    }//GEN-LAST:event_menuLengthConverterClientActionPerformed

    private void menuWeightConverterServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuWeightConverterServerActionPerformed
        new UDPWeightConverterServer().setVisible(true);
    }//GEN-LAST:event_menuWeightConverterServerActionPerformed

    private void menuWeightConverterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuWeightConverterClientActionPerformed
        new UDPWeightConverterClient().setVisible(true);
    }//GEN-LAST:event_menuWeightConverterClientActionPerformed

    private void menuCurrencyConverterServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCurrencyConverterServerActionPerformed
        new UDPCurrencyConverterServer().setVisible(true);
    }//GEN-LAST:event_menuCurrencyConverterServerActionPerformed

    private void menuCurrencyConverterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCurrencyConverterClientActionPerformed
        new UDPCurrencyConverterClient().setVisible(true);
    }//GEN-LAST:event_menuCurrencyConverterClientActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new UDPTemperatureConverterServer().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new UDPTemperatureConverterClient().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UDPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UDPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UDPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UDPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UDPProtocalFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem menuChatClient;
    private javax.swing.JMenuItem menuChatServer;
    private javax.swing.JMenuItem menuCurrencyConverterClient;
    private javax.swing.JMenuItem menuCurrencyConverterServer;
    private javax.swing.JMenuItem menuGameClient;
    private javax.swing.JMenuItem menuGameServer;
    private javax.swing.JMenuItem menuLengthConverterClient;
    private javax.swing.JMenuItem menuLengthConverterServer;
    private javax.swing.JMenuItem menuWeightConverterClient;
    private javax.swing.JMenuItem menuWeightConverterServer;
    // End of variables declaration//GEN-END:variables
}
