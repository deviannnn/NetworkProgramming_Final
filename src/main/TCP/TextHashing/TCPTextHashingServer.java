package main.TCP.TextHashing;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.*;

public class TCPTextHashingServer extends javax.swing.JFrame {

    private ServerSocket serverSocket;
    Socket clientSocket;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    private boolean connected = false;
    private static final ArrayList<String> supportedAlgorithms = new ArrayList<>();
        
    public TCPTextHashingServer() {
        initComponents();
        supportedAlgorithms.add("MD2");
        supportedAlgorithms.add("MD5");
        supportedAlgorithms.add("SHA-1");
        supportedAlgorithms.add("SHA-224");
        supportedAlgorithms.add("SHA-256");
        supportedAlgorithms.add("SHA-384");
        supportedAlgorithms.add("SHA-512");
        supportedAlgorithms.add("SHA-512/224");
        supportedAlgorithms.add("SHA-512/256");
        supportedAlgorithms.add("SHA3-224");
        supportedAlgorithms.add("SHA3-256");
        supportedAlgorithms.add("SHA3-384");
        supportedAlgorithms.add("SHA3-512");
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

            serverSocket = new ServerSocket(Iport);

            connected = true;
            Thread serverThread = new Thread(() -> {
                while (!serverSocket.isClosed()) {
                    try {
                        clientSocket = serverSocket.accept();

                        Thread clientHandlerThread = new Thread(() -> {
                            try {
                                dataIn = new DataInputStream(clientSocket.getInputStream());
                                dataOut = new DataOutputStream(clientSocket.getOutputStream());

                                dataOut.writeUTF("TYPES");
                                dataOut.writeInt(supportedAlgorithms.size());
                                for (String algorithm : supportedAlgorithms) {
                                    dataOut.writeUTF(algorithm);
                                }

                                while (!clientSocket.isClosed()) {
                                    String msgIn = dataIn.readUTF();

                                    if ("HASH".equals(msgIn)) {
                                        // Đọc loại mã hóa hash được chọn từ client
                                        String hashAlgorithm = dataIn.readUTF();

                                        // Đọc dữ liệu cần hash từ client
                                        String text = dataIn.readUTF();

                                        // Tính toán hash cho dữ liệu văn bản
                                        String hashedText = hashText(text, hashAlgorithm);

                                        // Gửi kết quả hash về client
                                        dataOut.writeUTF("HASHED");
                                        dataOut.writeUTF(hashedText);

                                        onlineUsersTextArea.append("Client request hash using " + hashAlgorithm + " algorithm.\n");
                                        onlineUsersTextArea.append("Hashed text sent back to client.\n");

                                    } else if ("LEAVE".equals(msgIn)) {
                                        clientSocket.close();
                                        onlineUsersTextArea.append("Client has disconnected to the decrypt room\n");
                                    }
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        onlineUsersTextArea.append("New client connected.\n");
                        clientHandlerThread.start();
                    } catch (IOException e) {
                        if (!serverSocket.isClosed()) {
                            JOptionPane.showMessageDialog(null, "Server connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
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

    public static String hashText(String text, String algorithm) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán được chỉ định
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // Cập nhật đối tượng MessageDigest với dữ liệu văn bản đầu vào
            digest.update(text.getBytes());

            // Lấy giá trị hash dưới dạng mảng byte
            byte[] hashedBytes = digest.digest();

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Xử lý ngoại lệ nếu thuật toán không tồn tại
            e.printStackTrace();
            return null;
        }
    }

    private void stopServer() {
        try {
            connected = false;

            // Đóng ClientSocket
            if (clientSocket != null && !clientSocket.isClosed()) {
                // Gửi mgsIn
                dataOut.writeUTF("SERVER_SHUTDOWN");
                dataIn.close();
                clientSocket.close();
                dataOut.close();
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
