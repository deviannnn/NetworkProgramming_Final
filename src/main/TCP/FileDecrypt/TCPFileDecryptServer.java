package main.TCP.FileDecrypt;

import java.io.*;
import java.net.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

public class TCPFileDecryptServer extends javax.swing.JFrame {

    private ServerSocket serverSocket;
    Socket clientSocket;
    DataInputStream dataIn;
    DataOutputStream dataOut;
    private boolean connected = false;

    public TCPFileDecryptServer() {
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

                                while (!clientSocket.isClosed()) {
                                    String msgIn = dataIn.readUTF();
                                    if ("DECRYPT".equals(msgIn)) {
                                        // Đọc secret key
                                        String encodedKey = dataIn.readUTF();

                                        // Kiểm tra xem secret key có hợp lệ không
                                        if (!isValidSecretKey(encodedKey)) {
                                            // Nếu secret key không hợp lệ, gửi thông báo về client và không tiếp tục quá trình giải mã
                                            dataOut.writeUTF("INVALID_KEY");
                                            continue; // Chuyển sang vòng lặp tiếp theo
                                        }

                                        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
                                        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                                        // Đọc tên file và kích thước file
                                        String fileName = dataIn.readUTF();
                                        long fileSize = dataIn.readLong();

                                        // Đọc dữ liệu file
                                        byte[] fileData = new byte[(int) fileSize];
                                        dataIn.readFully(fileData);

                                        // Giải mã dữ liệu
                                        byte[] decryptedData = decryptData(fileData, secretKey);

                                        // Tạo file mới để lưu dữ liệu giải mã
                                        File decryptedFile = new File(fileName.replace("encrypted", "decrypted"));
                                        try (FileOutputStream fos = new FileOutputStream(decryptedFile)) {
                                            fos.write(decryptedData);
                                        }

                                        // Gửi tên file và kích thước file đã giải mã
                                        dataOut.writeUTF("DECRYPTED");
                                        dataOut.writeUTF(decryptedFile.getName());
                                        dataOut.writeLong(decryptedFile.length());

                                        // Gửi dữ liệu file đã giải mã
                                        try (FileInputStream fis = new FileInputStream(decryptedFile)) {
                                            byte[] buffer = new byte[8192];
                                            int bytesRead;
                                            while ((bytesRead = fis.read(buffer)) != -1) {
                                                dataOut.write(buffer, 0, bytesRead);
                                            }
                                        }

                                        onlineUsersTextArea.append("Decrypted file sent back to client.\n");
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

    private static byte[] decryptData(byte[] encryptedData, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptedData);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValidSecretKey(String encodedKey) {
        // Kiểm tra độ dài của secret key (ở dạng base64)
        if (encodedKey.length() != 24 && encodedKey.length() != 32 && encodedKey.length() != 44) {
            return false;
        }

        // Kiểm tra xem secret key có đúng định dạng base64 không
        try {
            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
            // Nếu không có ngoại lệ nào được ném, secret key là hợp lệ
            return true;
        } catch (IllegalArgumentException e) {
            // Nếu có ngoại lệ IllegalArgumentException, secret key không hợp lệ
            return false;
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
