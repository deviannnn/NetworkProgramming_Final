package main.TCP.FileEncrypt;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.*;
import javax.swing.*;
import javax.swing.event.*;
import main.TCP.FileTrans.FileInfo;

public class TCPFileEncryptClient extends javax.swing.JFrame {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private File selectedFile;
    private String secretKey;
    private FileInfo receiveFile;
    private boolean connected = false;

    public TCPFileEncryptClient() {
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

                if (!inputServerName.equals("") && !inputPort.equals("")) {
                    btnHandle.setEnabled(true);
                } else {
                    btnHandle.setEnabled(false);
                }
            }
        };

        tfdServerName.getDocument().addDocumentListener(validateInput);
        tfdPort.getDocument().addDocumentListener(validateInput);
        btnCancelFile.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        btnSelectFile = new javax.swing.JButton();
        btnHandle = new javax.swing.JButton();
        tfdServerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdPort = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblFilename = new javax.swing.JLabel();
        btnCancelFile = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEncrypt = new javax.swing.JButton();
        tbxResult = new javax.swing.JTextField();
        txbSecretKey = new javax.swing.JTextField();
        btnSecretKey = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(650, 350));
        setMinimumSize(new java.awt.Dimension(650, 350));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnSelectFile.setText("Select File");
        btnSelectFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSelectFile.setEnabled(false);
        btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectFileActionPerformed(evt);
            }
        });

        btnHandle.setText("Connect");
        btnHandle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandleActionPerformed(evt);
            }
        });

        tfdServerName.setText("localhost");
        tfdServerName.setToolTipText("protocol");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PortNo:");

        tfdPort.setText("2024");
        tfdPort.setToolTipText("protocol");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("ServerName:");

        lblFilename.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblFilename.setText("There is no file selected ...");

        btnCancelFile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelFile.setForeground(new java.awt.Color(255, 0, 0));
        btnCancelFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelFile.setText("x");
        btnCancelFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelFileMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Result:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("AES FILE ENCYPTION");

        btnEncrypt.setText("Encrypt");
        btnEncrypt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEncrypt.setEnabled(false);
        btnEncrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncryptActionPerformed(evt);
            }
        });

        tbxResult.setEditable(false);
        tbxResult.setBackground(new java.awt.Color(255, 255, 255));
        tbxResult.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbxResult.setEnabled(false);
        tbxResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbxResultMouseClicked(evt);
            }
        });

        txbSecretKey.setEditable(false);
        txbSecretKey.setBackground(new java.awt.Color(255, 255, 255));

        btnSecretKey.setText("Generate");
        btnSecretKey.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSecretKey.setEnabled(false);
        btnSecretKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecretKeyActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Secret Key:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCancelFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbxResult)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFilename, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                            .addComponent(tfdPort)
                            .addComponent(txbSecretKey)
                            .addComponent(tfdServerName, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSelectFile, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(btnEncrypt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSecretKey, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHandle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel7)
                            .addComponent(tfdServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnHandle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txbSecretKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSecretKey, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblFilename, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelectFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEncrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCancelFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbxResult, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setLocation(new java.awt.Point(650, 100));
    }// </editor-fold>//GEN-END:initComponents

    private void btnHandleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandleActionPerformed
        if (!connected) {
            connectToServer();
        } else {
            leaveToServer();
        }
    }//GEN-LAST:event_btnHandleActionPerformed

    private void connectToServer() {
        String SERVER = tfdServerName.getText().trim();
        String inputPORT = tfdPort.getText().trim();

        try {
            int PORT = Integer.parseInt(inputPORT);

            if (PORT < 1 || PORT > 65535) {
                throw new IllegalArgumentException("Port number must be between 1 and 65535");
            }

            socket = new Socket(SERVER, PORT);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());

            // Tạo luồng để file mã hóa từ từ server
            connected = true;
            Thread clientThread = new Thread(() -> {
                try {
                    while (connected) {
                        String msgIn = dataIn.readUTF();

                        if ("ENCRYPTED".equals(msgIn)) {
                            JOptionPane.showMessageDialog(null, "File sent to server for encryption..", "Successfully", JOptionPane.INFORMATION_MESSAGE);
                            tbxResult.setText("File encryption is in progress ...");

                            // Đọc tên file và kích thước file
                            String fileName = dataIn.readUTF();
                            long fileSize = dataIn.readLong();

                            // Đọc dữ liệu file
                            byte[] fileData = new byte[(int) fileSize];
                            dataIn.readFully(fileData);

                            // Giả lập độ trễ 4 giây
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt(); // Khôi phục trạng thái ngắt
                            }

                            receiveFile = new FileInfo(null, fileName, fileData);
                            tbxResult.setText(fileName + " (click here to download)");
                            tbxResult.setEnabled(true);

                        } else if ("SERVER_SHUTDOWN".equals(msgIn)) {
                            connected = false;
                            JOptionPane.showMessageDialog(null, "Server has been shut down. You have been disconnected.\n", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
                            toggleInput(connected);
                            socket.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    // Xử lý lỗi khi giao tiếp với server
                    if (connected) {
                        JOptionPane.showMessageDialog(null, "An error occurred while receiving result! Please try connecting again..\n", "Server Shutdown", JOptionPane.ERROR_MESSAGE);
                        leaveToServer();
                    }
                }
            });

            clientThread.start();
            toggleInput(connected);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Malformed port: " + inputPORT, "Invalid Port Number", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Port Number", JOptionPane.ERROR_MESSAGE);
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Unknown server: " + SERVER, "Unknown Host", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No corresponding server found", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leaveToServer() {
        try {
            // Gửi tin nhắn rời phòng chat tới server
            dataOut.writeUTF("LEAVE");
            connected = false; // Dừng luồng nhận tin nhắn

            JOptionPane.showMessageDialog(null, "You have successfully disconnected.", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            socket.close(); // Đóng socket đến server
        } catch (IOException e) {
            e.printStackTrace();
        }

        toggleInput(connected);
        resetInput();
    }

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        if (connected) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName(); // Lấy tên của file
                lblFilename.setText(fileName);
                btnCancelFile.setVisible(true);
                if (selectedFile != null && secretKey != null) {
                    btnEncrypt.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveToServer();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnCancelFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelFileMouseClicked
        selectedFile = null;
        btnCancelFile.setVisible(false);
        lblFilename.setText("There is no file selected ...");
        btnEncrypt.setEnabled(false);
    }//GEN-LAST:event_btnCancelFileMouseClicked

    private void btnEncryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncryptActionPerformed
        // Gửi request yêu cầu mã hóa đến server
        if (selectedFile != null && secretKey != null) {
            try {
                // Gửi mgsIn
                dataOut.writeUTF("ENCRYPT");
                // Gửi secret key 
                dataOut.writeUTF(secretKey);
                // Gửi tên file
                dataOut.writeUTF(selectedFile.getName());
                // Gửi kích thước file
                dataOut.writeLong(selectedFile.length());

                // Mở FileInputStream để đọc dữ liệu từ file
                try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {
                    // Tạo một byte array để lưu trữ dữ liệu từ file
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        // Gửi dữ liệu file
                        dataOut.write(buffer, 0, bytesRead);
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while sending file! Please try again.", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected or secret key generated!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEncryptActionPerformed

    private void btnSecretKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecretKeyActionPerformed
        try {
            secretKey = generateSecretKey();
            txbSecretKey.setText(secretKey);
            if (selectedFile != null && secretKey != null) {
                btnEncrypt.setEnabled(true);
            }
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while generating secret key! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSecretKeyActionPerformed

    private void tbxResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbxResultMouseClicked
        if (receiveFile != null) {
            downloadFile(receiveFile);
            resetInput();
        }
    }//GEN-LAST:event_tbxResultMouseClicked

    private void downloadFile(FileInfo fileInfo) {
        try {
            // Tạo đường dẫn tới thư mục "Download"
            Path downloadDir = Paths.get(System.getProperty("user.home"), "Downloads");

            // Tạo đường dẫn tới file trong thư mục "Download"
            Path filePath = downloadDir.resolve(fileInfo.getFileName());

            // Ghi dữ liệu file vào file trong thư mục "Download"
            Files.write(filePath, fileInfo.getFileData());

            JOptionPane.showMessageDialog(null, "File saved to: " + filePath.toString(), "Successfully", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fail to save file! Please try again.", "Fail", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return encodedKey;
    }

    private void resetInput() {
        secretKey = null;
        selectedFile = null;
        receiveFile = null;
        txbSecretKey.setText("");
        btnCancelFile.setVisible(false);
        lblFilename.setText("There is no file selected ...");
        btnEncrypt.setEnabled(false);
        tbxResult.setText("");
        tbxResult.setEnabled(false);
    }

    private void toggleInput(boolean connected) {
        btnHandle.setText(connected ? "Leave" : "Connect");
        tfdServerName.setEnabled(!connected);
        tfdPort.setEnabled(!connected);
        btnSelectFile.setEnabled(connected);
        btnSecretKey.setEnabled(connected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCancelFile;
    private javax.swing.JButton btnEncrypt;
    private javax.swing.JButton btnHandle;
    private javax.swing.JButton btnSecretKey;
    private javax.swing.JButton btnSelectFile;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilename;
    private javax.swing.JTextField tbxResult;
    private javax.swing.JTextField tfdPort;
    private javax.swing.JTextField tfdServerName;
    private javax.swing.JTextField txbSecretKey;
    // End of variables declaration//GEN-END:variables
}
