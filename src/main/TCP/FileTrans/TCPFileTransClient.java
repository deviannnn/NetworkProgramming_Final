package main.TCP.FileTrans;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TCPFileTransClient extends javax.swing.JFrame {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private File selectedFile;
    private boolean connected = false;
    DefaultListModel<String> listModel;
    private ArrayList<FileInfo> fileList = new ArrayList<>();

    public TCPFileTransClient() {
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

        tfdServerName.getDocument().addDocumentListener(validateInput);
        tfdPort.getDocument().addDocumentListener(validateInput);
        tfdName.getDocument().addDocumentListener(validateInput);

        btnCancelFile.setVisible(false);
        lblFilename.setVisible(false);

        listModel = new DefaultListModel<>();
        listFileReceived.setModel(listModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnChooseFile = new javax.swing.JLabel();
        lblFilename = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listFileReceived = new javax.swing.JList<>();
        btnCancelFile = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(750, 455));
        setResizable(false);
        setSize(new java.awt.Dimension(750, 408));
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

        msgArea.setColumns(29);
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
        jScrollPane4.setViewportView(chatDataPane);

        btnChooseFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnChooseFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/icons8-attach-24.png"))); // NOI18N
        btnChooseFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChooseFile.setEnabled(false);
        btnChooseFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChooseFileMouseClicked(evt);
            }
        });

        lblFilename.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblFilename.setText("filename");

        listFileReceived.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listFileReceived.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listFileReceivedValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listFileReceived);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addComponent(tfdPort))
                            .addComponent(tfdName)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCancelFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChooseFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addComponent(lblFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(btnHandle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFilename, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(btnCancelFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setLocation(new java.awt.Point(650, 100));
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

            socket = new Socket(SERVER, PORT);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());

            // Gửi tên người dùng đến server để đăng ký
            dataOut.writeUTF(USERNAME);
            String response = dataIn.readUTF();

            if ("USERNAME_EXISTS".equals(response)) {
                JOptionPane.showMessageDialog(null, "Username already taken. Please choose another name.", "Username Error", JOptionPane.ERROR_MESSAGE);
                socket.close();
                return;
            } else if ("USERNAME_ACCEPTED".equals(response)) {
                appendToPane(chatDataPane, "Connected to the server as \'" + USERNAME + "\'\n", StyleConstants.ALIGN_LEFT);
            }

            // Tạo luồng để nhận tin nhắn từ server
            connected = true;
            Thread clientThread = new Thread(() -> {
                try {
                    while (connected) {
                        // Đọc loại dữ liệu từ máy chủ
                        String dataType = dataIn.readUTF();

                        // Nếu dữ liệu là tin nhắn
                        if ("MESSAGE".equals(dataType)) {
                            String msgIn = dataIn.readUTF();
                            // Hiển thị tin nhắn
                            appendToPane(chatDataPane, msgIn + "\n", StyleConstants.ALIGN_LEFT);
                        } // Nếu dữ liệu là file
                        else if ("FILE".equals(dataType)) {
                            // Đọc tên file và kích thước file
                            String fileName = dataIn.readUTF();
                            long fileSize = dataIn.readLong();

                            // Đọc dữ liệu file
                            byte[] fileData = new byte[(int) fileSize];
                            dataIn.readFully(fileData);

                            // Đọc tên người gửi
                            String fileOwner = dataIn.readUTF();

                            fileList.add(new FileInfo(fileOwner, fileName, fileData));

                            appendToPane(chatDataPane, "\n" + fileOwner + ": attached file - " + fileName + "\n", StyleConstants.ALIGN_LEFT);

                            // Thêm tên file vào JList
                            listModel.addElement(fileOwner + ": " + fileName);

                        } else if ("SERVER_SHUTDOWN".equals(dataType)) {
                            connected = false;
                            appendToPane(chatDataPane, "Server has been shut down. You have been disconnected.\n", StyleConstants.ALIGN_LEFT);
                            toggleInput(connected);
                            socket.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    // Xử lý lỗi khi giao tiếp với server
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
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No corresponding server found", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leaveChatRoom() {
        try {
            // Gửi tin nhắn rời phòng chat tới server
            dataOut.writeUTF("MESSAGE");
            dataOut.writeUTF("LEAVE_CHAT_ROOM");
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
            // Gửi tin nhắn
            if (!inputMsg.isEmpty()) {
                dataOut.writeUTF("MESSAGE"); // Gửi loại dữ liệu là MESSAGE
                dataOut.writeUTF(inputMsg); // Gửi tin nhắn
                appendToPane(chatDataPane, inputMsg + "\n", StyleConstants.ALIGN_RIGHT);
            }

            // Gửi file nếu có
            if (selectedFile != null) {
                // Gửi loại dữ liệu là FILE
                dataOut.writeUTF("FILE");
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
                appendToPane(chatDataPane, "attached file \'" + selectedFile.getName() + "\' has been sent\n", StyleConstants.ALIGN_RIGHT);
            }

        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra
            JOptionPane.showMessageDialog(null, "An error occurred while sending the message or file! Please try again.", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }

        // Xóa nội dung trong msgArea sau khi đã gửi xong
        selectedFile = null;
        btnCancelFile.setVisible(false);
        lblFilename.setVisible(false);
        msgArea.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveChatRoom();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnChooseFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChooseFileMouseClicked
        if (connected) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName(); // Lấy tên của file
                lblFilename.setText(fileName);
                lblFilename.setVisible(true);
                btnCancelFile.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnChooseFileMouseClicked

    private void listFileReceivedValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listFileReceivedValueChanged
        if (!evt.getValueIsAdjusting()) {
            int selectedIndex = listFileReceived.getSelectedIndex();
            if (selectedIndex != -1) {
                // Lấy dữ liệu file từ danh sách cục bộ tương ứng với vị trí được chọn
                FileInfo fileInfo = fileList.get(selectedIndex);
                // Hiển thị hộp thoại xác nhận
                String msg = "Do you want to download the file: " + fileInfo.getFileName() + " from " + fileInfo.getFileOwner() + "?";
                int option = JOptionPane.showConfirmDialog(null, msg, "Confirm Download", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng chọn Yes, thực hiện tải xuống
                    downloadFile(fileInfo);
                }
            }
            listFileReceived.clearSelection();
        }
    }//GEN-LAST:event_listFileReceivedValueChanged

    private void btnCancelFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelFileMouseClicked
        selectedFile = null;
        btnCancelFile.setVisible(false);
        lblFilename.setVisible(false);
    }//GEN-LAST:event_btnCancelFileMouseClicked

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

    private void toggleInput(boolean connected) {
        btnHandle.setText(connected ? "Leave" : "Join");
        tfdServerName.setEnabled(!connected);
        tfdPort.setEnabled(!connected);
        tfdName.setEnabled(!connected);
        btnChooseFile.setEnabled(connected);
        msgArea.setText("");
        msgArea.setEnabled(connected);
        btnSend.setEnabled(connected);
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
    private javax.swing.JLabel btnCancelFile;
    private javax.swing.JLabel btnChooseFile;
    private javax.swing.JButton btnHandle;
    private javax.swing.JButton btnSend;
    private javax.swing.JTextPane chatDataPane;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFilename;
    private javax.swing.JList<String> listFileReceived;
    private javax.swing.JTextArea msgArea;
    private javax.swing.JTextField tfdName;
    private javax.swing.JTextField tfdPort;
    private javax.swing.JTextField tfdServerName;
    // End of variables declaration//GEN-END:variables
}
