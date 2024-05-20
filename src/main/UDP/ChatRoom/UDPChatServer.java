package main.UDP.ChatRoom;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class UDPChatServer extends javax.swing.JFrame {

    private DatagramSocket serverSocket;
    private boolean connected = false;
    private final Set<UDPClientInfo> connectedClients = new HashSet<>();

    public UDPChatServer() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        onlineUsersTextArea = new javax.swing.JTextArea();
        textFieldPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnHandle = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Server");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(600, 352));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        onlineUsersTextArea.setEditable(false);
        onlineUsersTextArea.setBackground(new java.awt.Color(255, 255, 255));
        onlineUsersTextArea.setColumns(20);
        onlineUsersTextArea.setRows(10);
        onlineUsersTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        onlineUsersTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        onlineUsersTextArea.setEnabled(false);
        onlineUsersTextArea.setMaximumSize(new java.awt.Dimension(600, 352));
        onlineUsersTextArea.setMinimumSize(new java.awt.Dimension(600, 352));
        jScrollPane2.setViewportView(onlineUsersTextArea);

        textFieldPort.setText("2024");
        textFieldPort.setToolTipText("protocol");

        jLabel4.setText("Server Port No:");

        btnHandle.setText("Start");
        btnHandle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldPort, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHandle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        setBounds(20, 100, 504, 387);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHandleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandleActionPerformed
        if (!connected) {
            startServer();
        } else {
            stopServer();
        }
    }//GEN-LAST:event_btnHandleActionPerformed

    private void startServer() {
        String port = textFieldPort.getText().trim();

        try {
            int Iport = Integer.parseInt(port);

            if (Iport < 1 || Iport > 65535) {
                throw new IllegalArgumentException("Port number must be between 1 and 65535");
            }

            serverSocket = new DatagramSocket(Iport);
            byte[] buf = new byte[256];
            DatagramPacket packetIn = new DatagramPacket(buf, buf.length);

            connected = true;
            Thread serverThread = new Thread(() -> {
                try {
                    while (connected) {
                        serverSocket.receive(packetIn);
                        String msgIn = new String(packetIn.getData(), 0, packetIn.getLength(), "UTF-8");
                        InetAddress clientAddress = packetIn.getAddress();
                        int clientPort = packetIn.getPort();
                        String clientUsername = findUsername(clientAddress, clientPort);

                        if (msgIn.startsWith("[JOIN]")) {
                            String username = msgIn.substring(6);

                            boolean usernameExists = connectedClients.stream()
                                    .anyMatch(client -> client.getUsername().equals(username));

                            if (usernameExists) {
                                sendMessage("USERNAME_EXISTS", clientAddress, clientPort);
                            } else {
                                sendMessage("USERNAME_ACCEPTED", clientAddress, clientPort);

                                // Gửi danh sách các tên người dùng hiện tại cho client mới
                                StringBuilder usersList = new StringBuilder();
                                if (!connectedClients.isEmpty()) {
                                    synchronized (connectedClients) {
                                        for (UDPClientInfo client : connectedClients) {
                                            usersList.append(client.getUsername()).append(", ");
                                        }
                                    }
                                    usersList.setLength(usersList.length() - 2);
                                    usersList.append(" & You are already in the chat room");
                                } else {
                                    usersList.append("You are the only one in the chat room");
                                }

                                sendMessage(usersList.toString(), clientAddress, clientPort);

                                // Tạo một ClientInfo mới và thêm vào danh sách connectedClients
                                UDPClientInfo client = new UDPClientInfo(username, clientAddress, clientPort);
                                connectedClients.add(client);

                                // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                                onlineUsersTextArea.append(username + ": " + clientAddress + " at port " + clientPort + " has connected to the chat room\n");

                                // Thông báo client mới đã tham gia cho các client trước đó
                                broadcastMessage(username, "has joined the chat room");

                            }
                        } else if (msgIn.startsWith("[CHAT]")) {
                            String content = msgIn.substring(6);

                            if ("LEAVE_CHAT_ROOM".equals(content)) {
                                // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                                onlineUsersTextArea.append(clientUsername + ": " + clientAddress + " at port " + clientPort + " has disconnected to the chat room\n");

                                // Thông báo client đã rời khỏi phòng cho các client khác
                                broadcastMessage(clientUsername, "has left the chat room");

                                // Ngắt kết nối khi client thoát
                                synchronized (connectedClients) {
                                    connectedClients.removeIf(client -> client.getAddress().equals(clientAddress) && client.getPort() == clientPort);
                                }

                                continue;
                            }

                            broadcastMessage(clientUsername, content);
                        }
                    }
                } catch (IOException ex) {
                    if (connected) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            serverThread.start();

            btnHandle.setText("Stop");
            textFieldPort.setEnabled(false);
            onlineUsersTextArea.setText("Server was started ...\n\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Malformed port: " + port, "Invalid Port Number", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Port Number", JOptionPane.WARNING_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopServer() {
        try {
            connected = false;

            // Gửi yêu cầu ngắt kết nối đến tất cả các client
            synchronized (connectedClients) {
                for (UDPClientInfo client : connectedClients) {
                    sendMessage("SERVER_SHUTDOWN", client.getAddress(), client.getPort());
                }
                connectedClients.clear();
            }

            // Đóng ServerSocket
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            btnHandle.setText("Start");
            textFieldPort.setEnabled(true);
            onlineUsersTextArea.append("Server has been stopped.\n");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error while stopping the server: " + e.getMessage(), "Server Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendMessage(String message, InetAddress address, int port) throws IOException {
        byte[] buf = message.getBytes("UTF-8");
        DatagramPacket packetOut = new DatagramPacket(buf, buf.length, address, port);
        serverSocket.send(packetOut);
    }

    private String findUsername(InetAddress address, int port) {
        synchronized (connectedClients) {
            for (UDPClientInfo client : connectedClients) {
                if (client.getAddress().equals(address) && client.getPort() == port) {
                    return client.getUsername();
                }
            }
            return null;
        }
    }

    private void broadcastMessage(String username, String message) {
        synchronized (connectedClients) {
            for (UDPClientInfo client : connectedClients) {
                if (!client.getUsername().equals(username)) {
                    try {
                        sendMessage("\n" + username + ": " + message, client.getAddress(), client.getPort());
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error broadcasting message: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (serverSocket != null && !serverSocket.isClosed()) {
            stopServer();
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHandle;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea onlineUsersTextArea;
    private javax.swing.JTextField textFieldPort;
    // End of variables declaration//GEN-END:variables
}
