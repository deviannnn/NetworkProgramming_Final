package main.UDP.ChatRoom;

import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class UDPChatClient extends javax.swing.JFrame {

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private boolean connected = false;

    public UDPChatClient() {
        initComponents();
        DocumentListener validateInput = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateTextField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateTextField();
            }

            protected void validateTextField() {
                String inputServerName = tfdServerName.getText().trim();
                String inputPort = tfdPort.getText().trim();
                String inputName = tfdName.getText().trim();

                if (!inputServerName.equals("") && !inputPort.equals("") && !inputName.equals("")) {
                    btnHandle.setEnabled(true);
                } else {
                    btnHandle.setEnabled(false);
                }
            }
        };

        DocumentListener validateMsg = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validateTextField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateTextField();
            }

            protected void validateTextField() {
                String inputMsg = msgArea.getText().trim();
                if (!inputMsg.equals("")) {
                    btnSend.setEnabled(true);
                } else {
                    btnSend.setEnabled(false);
                }
            }
        };

        tfdServerName.getDocument().addDocumentListener(validateInput);
        tfdPort.getDocument().addDocumentListener(validateInput);
        tfdName.getDocument().addDocumentListener(validateInput);
        msgArea.getDocument().addDocumentListener(validateMsg);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        btnSend = new javax.swing.JButton();
        btnHandle = new javax.swing.JButton();
        tfdServerName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        msgArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        tfdPort = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfdName = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        chatDataPane = new javax.swing.JTextPane();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSend.setEnabled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnHandle.setText("Join");
        btnHandle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandleActionPerformed(evt);
            }
        });

        tfdServerName.setText("localhost");
        tfdServerName.setToolTipText("protocol");

        jLabel5.setText("DisplayName:");

        msgArea.setColumns(34);
        msgArea.setRows(5);
        msgArea.setEnabled(false);
        jScrollPane3.setViewportView(msgArea);

        jLabel6.setText("PortNo:");

        tfdPort.setText("2024");
        tfdPort.setToolTipText("protocol");

        jLabel7.setText("ServerName:");

        tfdName.setText("Devian");
        tfdName.setToolTipText("protocol");

        chatDataPane.setEditable(false);
        chatDataPane.setBackground(new java.awt.Color(255, 255, 255));
        chatDataPane.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        chatDataPane.setEnabled(false);
        jScrollPane4.setViewportView(chatDataPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfdServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfdName, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(tfdServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(tfdName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        setBounds(650, 100, 504, 425);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHandleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandleActionPerformed
        if (!connected) {
            joinChatRoom();
        } else {
            leaveChatRoom();
        }
    }//GEN-LAST:event_btnHandleActionPerformed

    private void joinChatRoom() {
        String SERVER = tfdServerName.getText().trim();
        String inputPORT = tfdPort.getText().trim();
        String USERNAME = tfdName.getText().trim();

        try {
            int PORT = Integer.parseInt(inputPORT);
            if (PORT < 1 || PORT > 65535) {
                throw new IllegalArgumentException("Port number must be between 1 and 65535");
            }

            serverAddress = InetAddress.getByName(SERVER);
            serverPort = PORT;
            socket = new DatagramSocket();

            // Send username to server
            sendMessage("[JOIN]" + USERNAME);
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            socket.setSoTimeout(5000);
            try {
                socket.receive(packet);
                String response = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

                if ("USERNAME_EXISTS".equals(response)) {
                    JOptionPane.showMessageDialog(null, "Username already taken. Please choose another name.", "Username Error", JOptionPane.ERROR_MESSAGE);
                    socket.close();
                    return;
                } else if ("USERNAME_ACCEPTED".equals(response)) {
                    appendToPane(chatDataPane, "Connected to the server as \'" + USERNAME + "\'\n", StyleConstants.ALIGN_LEFT);
                }

                // Đặt lại thời gian chờ của socket sau khi kết nối thành công
                socket.setSoTimeout(0);

            } catch (SocketTimeoutException e) {
                JOptionPane.showMessageDialog(null, "Server not responding. Please try again later.", "Connection Timeout", JOptionPane.ERROR_MESSAGE);
                socket.close();
                return;
            }

            connected = true;
            Thread clientThread = new Thread(() -> {
                try {
                    byte[] bufIn = new byte[256];
                    DatagramPacket packetIn = new DatagramPacket(bufIn, bufIn.length);
                    while (connected) {
                        socket.receive(packetIn);
                        String msgIn = new String(packetIn.getData(), 0, packetIn.getLength(), "UTF-8");

                        if ("SERVER_SHUTDOWN".equals(msgIn)) {
                            connected = false;
                            appendToPane(chatDataPane, "\nServer has been shut down. You have been disconnected.\n", StyleConstants.ALIGN_LEFT);
                            toggleInput(connected);
                            socket.close();
                            break;
                        }

                        appendToPane(chatDataPane, msgIn + "\n", StyleConstants.ALIGN_LEFT);
                    }
                } catch (IOException e) {
                    if (connected) {
                        appendToPane(chatDataPane, "\nAn error occurred while receiving the message! Please try connecting again.\n", StyleConstants.ALIGN_LEFT);
                        leaveChatRoom();
                    }
                }
            });

            clientThread.start();
            toggleInput(connected);
            this.setTitle(USERNAME);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Malformed port: " + inputPORT, "Invalid Port Number", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Port Number", JOptionPane.ERROR_MESSAGE);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Unknown server: " + SERVER, "Unknown Host", JOptionPane.ERROR_MESSAGE);
        } catch (SocketTimeoutException e) {
            JOptionPane.showMessageDialog(null, "Server not responding. Please try again later.", "Connection Timeout", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No corresponding server found", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leaveChatRoom() {
        try {
            sendMessage("[CHAT]LEAVE_CHAT_ROOM");
            connected = false; // Dừng luồng nhận tin nhắn
            chatDataPane.setText("");
            JOptionPane.showMessageDialog(null, "You have successfully left the chat room.", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            socket.close(); // Đóng socket đến server
        } catch (IOException e) {
            e.printStackTrace();
        }

        toggleInput(connected);
    }

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String inputMsg = msgArea.getText().trim();

        try {
            sendMessage("[CHAT]" + inputMsg);
            appendToPane(chatDataPane, inputMsg + "\n", StyleConstants.ALIGN_RIGHT);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while sending the message! Please try connecting again.", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        msgArea.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    private void sendMessage(String message) throws IOException {
        byte[] buf = message.getBytes("UTF-8");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, serverPort);
        socket.send(packet);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveChatRoom();
        }
    }//GEN-LAST:event_formWindowClosing

    private void toggleInput(boolean connected) {
        btnHandle.setText(connected ? "Leave" : "Join");
        tfdServerName.setEnabled(!connected);
        tfdPort.setEnabled(!connected);
        tfdName.setEnabled(!connected);
        msgArea.setText("");
        msgArea.setEnabled(connected);
    }

    private void appendToPane(JTextPane tp, String msg, int alignment) {
        StyledDocument doc = tp.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, alignment);

        try {
            doc.insertString(doc.getLength(), msg, attr);
            doc.setParagraphAttributes(doc.getLength() - msg.length(), msg.length(), attr, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHandle;
    private javax.swing.JButton btnSend;
    private javax.swing.JTextPane chatDataPane;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea msgArea;
    private javax.swing.JTextField tfdName;
    private javax.swing.JTextField tfdPort;
    private javax.swing.JTextField tfdServerName;
    // End of variables declaration//GEN-END:variables
}
