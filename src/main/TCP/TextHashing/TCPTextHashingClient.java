package main.TCP.TextHashing;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class TCPTextHashingClient extends javax.swing.JFrame {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private File selectedFile;
    private boolean connected = false;

    public TCPTextHashingClient() {
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        btnHandle = new javax.swing.JButton();
        tfdServerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfdPort = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnHash = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cbxAlgorithm = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        taaInput = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taaResult = new javax.swing.JTextArea();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(650, 515));
        setMinimumSize(new java.awt.Dimension(650, 515));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Input:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TEXT HASHING");

        btnHash.setText("Hash");
        btnHash.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHash.setEnabled(false);
        btnHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHashActionPerformed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Algorithm:");

        cbxAlgorithm.setEnabled(false);

        taaInput.setColumns(20);
        taaInput.setRows(5);
        jScrollPane2.setViewportView(taaInput);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Result:");

        taaResult.setEditable(false);
        taaResult.setBackground(new java.awt.Color(255, 255, 255));
        taaResult.setColumns(20);
        taaResult.setRows(5);
        jScrollPane3.setViewportView(taaResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                            .addComponent(tfdPort)
                            .addComponent(tfdServerName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAlgorithm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHash, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(btnHandle, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
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
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnHandle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxAlgorithm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHash, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setLocation(new java.awt.Point(640, 100));
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
                    String msgIn = dataIn.readUTF();

                    if ("TYPES".equals(msgIn)) {
                        // Đọc số lượng thuật toán
                        int numOfAlgorithms = dataIn.readInt();

                        // Đọc và hiển thị danh sách các thuật toán
                        ArrayList<String> supportedAlgorithms = new ArrayList<>();
                        for (int i = 0; i < numOfAlgorithms; i++) {
                            String algorithm = dataIn.readUTF();
                            supportedAlgorithms.add(algorithm);
                        }

                        // Hiển thị danh sách thuật toán cho người dùng (cập nhật giao diện người dùng)
                        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(supportedAlgorithms.toArray(new String[0]));
                        cbxAlgorithm.setModel(model);
                        cbxAlgorithm.setEnabled(true);
                        btnHash.setEnabled(true);
                    }

                    while (connected) {
                        msgIn = dataIn.readUTF();

                        if ("HASHED".equals(msgIn)) {
                            // Đọc kết quả hash
                            String hashedText = dataIn.readUTF();
                            taaResult.setText("Text hashing is in progress ...");

                            // Giả lập độ trễ 4 giây
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt(); // Khôi phục trạng thái ngắt
                            }

                            taaResult.setText(hashedText);

                        } else if ("SERVER_SHUTDOWN".equals(msgIn)) {
                            connected = false;
                            JOptionPane.showMessageDialog(null, "Server has been shut down. You have been disconnected.\n", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
                            toggleInput(connected);
                            resetInput();
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveToServer();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHashActionPerformed
        // Lấy nội dung văn bản cần hash từ TextArea
        String content = taaInput.getText().trim();
        // Lấy thuật toán hash được chọn từ JComboBox
        String selectedAlgorithm = (String) cbxAlgorithm.getSelectedItem();

        if (!content.isEmpty() && selectedAlgorithm != null) {
            try {
                // Gửi lệnh HASH đến server
                dataOut.writeUTF("HASH");
                // Gửi thuật toán hash được chọn đến server
                dataOut.writeUTF(selectedAlgorithm);
                // Gửi nội dung cần hash đến server
                dataOut.writeUTF(content);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while sending the hash request! Please try again.", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No text to hash or no algorithm selected!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHashActionPerformed

    private void resetInput() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        cbxAlgorithm.setModel(model);
        cbxAlgorithm.setEnabled(false);
        btnHash.setEnabled(false);
    }

    private void toggleInput(boolean connected) {
        btnHandle.setText(connected ? "Leave" : "Connect");
        tfdServerName.setEnabled(!connected);
        tfdPort.setEnabled(!connected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHandle;
    private javax.swing.JButton btnHash;
    private javax.swing.JComboBox<String> cbxAlgorithm;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea taaInput;
    private javax.swing.JTextArea taaResult;
    private javax.swing.JTextField tfdPort;
    private javax.swing.JTextField tfdServerName;
    // End of variables declaration//GEN-END:variables
}
