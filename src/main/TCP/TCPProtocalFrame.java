package main.TCP;

import main.MainMenuFrame;
import main.TCP.ChatRoom.*;
import main.TCP.FileTrans.*;
import main.TCP.AudioStreaming.*;
import main.TCP.FileEncrypt.*;
import main.TCP.FileDecrypt.*;
import main.TCP.TextHashing.*;

public class TCPProtocalFrame extends javax.swing.JFrame {

    private TCPProtocalFrame() {
        initComponents();
    }

    public static TCPProtocalFrame getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {

        private static final TCPProtocalFrame INSTANCE = new TCPProtocalFrame();
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
        menuFileTransServer = new javax.swing.JMenuItem();
        menuFileTransClient = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuAudioServer = new javax.swing.JMenuItem();
        menuAudioClient = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuEncryptServer = new javax.swing.JMenuItem();
        menuEncryptClient = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuDecryptServer = new javax.swing.JMenuItem();
        menuDecryptClient = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        menuHashServer = new javax.swing.JMenuItem();
        menuHashClient = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TCP Protocal");
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

        jMenu1.setText("TCP ChatRoom");

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

        jMenu3.setText("1. File Transfer");

        menuFileTransServer.setText("Server");
        menuFileTransServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileTransServerActionPerformed(evt);
            }
        });
        jMenu3.add(menuFileTransServer);

        menuFileTransClient.setText("Client");
        menuFileTransClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileTransClientActionPerformed(evt);
            }
        });
        jMenu3.add(menuFileTransClient);

        jMenu2.add(jMenu3);

        jMenu4.setText("2. Audio Streaming");

        menuAudioServer.setText("Server");
        menuAudioServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAudioServerActionPerformed(evt);
            }
        });
        jMenu4.add(menuAudioServer);

        menuAudioClient.setText("Client");
        menuAudioClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAudioClientActionPerformed(evt);
            }
        });
        jMenu4.add(menuAudioClient);

        jMenu2.add(jMenu4);

        jMenu5.setText("3. Encrypt File using AES");

        menuEncryptServer.setText("Server");
        menuEncryptServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEncryptServerActionPerformed(evt);
            }
        });
        jMenu5.add(menuEncryptServer);

        menuEncryptClient.setText("Client");
        menuEncryptClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEncryptClientActionPerformed(evt);
            }
        });
        jMenu5.add(menuEncryptClient);

        jMenu2.add(jMenu5);

        jMenu6.setText("4. Decrypt File using AES");

        menuDecryptServer.setText("Server");
        menuDecryptServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDecryptServerActionPerformed(evt);
            }
        });
        jMenu6.add(menuDecryptServer);

        menuDecryptClient.setText("Client");
        menuDecryptClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDecryptClientActionPerformed(evt);
            }
        });
        jMenu6.add(menuDecryptClient);

        jMenu2.add(jMenu6);

        jMenu7.setText("5. Text Hashing");

        menuHashServer.setText("Server");
        menuHashServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHashServerActionPerformed(evt);
            }
        });
        jMenu7.add(menuHashServer);

        menuHashClient.setText("Client");
        menuHashClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHashClientActionPerformed(evt);
            }
        });
        jMenu7.add(menuHashClient);

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

    private void menuChatServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChatServerActionPerformed
        new TCPChatServer().setVisible(true);
    }//GEN-LAST:event_menuChatServerActionPerformed

    private void menuChatClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChatClientActionPerformed
        new TCPChatClient().setVisible(true);
    }//GEN-LAST:event_menuChatClientActionPerformed

    private void menuFileTransClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileTransClientActionPerformed
        new TCPFileTransClient().setVisible(true);
    }//GEN-LAST:event_menuFileTransClientActionPerformed

    private void menuFileTransServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileTransServerActionPerformed
        new TCPFileTransServer().setVisible(true);
    }//GEN-LAST:event_menuFileTransServerActionPerformed

    private void menuAudioServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAudioServerActionPerformed
        new TCPAudioStreamingServer().setVisible(true);
    }//GEN-LAST:event_menuAudioServerActionPerformed

    private void menuAudioClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAudioClientActionPerformed
        new TCPAudioStreamingClient().setVisible(true);
    }//GEN-LAST:event_menuAudioClientActionPerformed

    private void menuEncryptServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEncryptServerActionPerformed
        new TCPFileEncryptServer().setVisible(true);
    }//GEN-LAST:event_menuEncryptServerActionPerformed

    private void menuEncryptClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEncryptClientActionPerformed
        new TCPFileEncryptClient().setVisible(true);
    }//GEN-LAST:event_menuEncryptClientActionPerformed

    private void menuDecryptServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDecryptServerActionPerformed
        new TCPFileDecryptServer().setVisible(true);
    }//GEN-LAST:event_menuDecryptServerActionPerformed

    private void menuDecryptClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDecryptClientActionPerformed
        new TCPFileDecryptClient().setVisible(true);
    }//GEN-LAST:event_menuDecryptClientActionPerformed

    private void menuHashServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuHashServerActionPerformed
        new TCPTextHashingServer().setVisible(true);
    }//GEN-LAST:event_menuHashServerActionPerformed

    private void menuHashClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuHashClientActionPerformed
        new TCPTextHashingClient().setVisible(true);
    }//GEN-LAST:event_menuHashClientActionPerformed

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
            java.util.logging.Logger.getLogger(TCPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TCPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TCPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TCPProtocalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TCPProtocalFrame().setVisible(true);
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
    private javax.swing.JMenuItem menuAudioClient;
    private javax.swing.JMenuItem menuAudioServer;
    private javax.swing.JMenuItem menuChatClient;
    private javax.swing.JMenuItem menuChatServer;
    private javax.swing.JMenuItem menuDecryptClient;
    private javax.swing.JMenuItem menuDecryptServer;
    private javax.swing.JMenuItem menuEncryptClient;
    private javax.swing.JMenuItem menuEncryptServer;
    private javax.swing.JMenuItem menuFileTransClient;
    private javax.swing.JMenuItem menuFileTransServer;
    private javax.swing.JMenuItem menuHashClient;
    private javax.swing.JMenuItem menuHashServer;
    // End of variables declaration//GEN-END:variables
}
