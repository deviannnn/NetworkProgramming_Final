package main.UDP.LengthConverter;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;

public class UDPLengthConverterClient extends javax.swing.JFrame {

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private boolean connected = false;

    public UDPLengthConverterClient() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        btnConvert = new javax.swing.JButton();
        tfdPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfdServerName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnHandle = new javax.swing.JButton();
        cbxUnit1 = new javax.swing.JComboBox<>();
        cbxUnit2 = new javax.swing.JComboBox<>();
        tfdInput = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfdResult = new javax.swing.JTextField();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TCP Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(338, 300));
        setMinimumSize(new java.awt.Dimension(338, 300));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnConvert.setText("Convert");
        btnConvert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConvert.setEnabled(false);
        btnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertActionPerformed(evt);
            }
        });

        tfdPort.setText("2024");
        tfdPort.setToolTipText("protocol");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("PortNo:");

        tfdServerName.setText("localhost");
        tfdServerName.setToolTipText("protocol");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("ServerName:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Length Converter");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnHandle.setText("Connect");
        btnHandle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandleActionPerformed(evt);
            }
        });

        cbxUnit1.setEnabled(false);

        cbxUnit2.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("to");

        tfdResult.setEditable(false);
        tfdResult.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdPort, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(tfdServerName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdInput, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxUnit1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxUnit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnConvert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfdServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnHandle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxUnit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxUnit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfdInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfdResult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

            serverAddress = InetAddress.getByName(SERVER);
            serverPort = PORT;
            socket = new DatagramSocket();

            // Gửi yêu cầu tham gia trò chơi đến máy chủ
            sendMessage("[JOIN]");

            // Nhận phản hồi từ máy chủ
            byte[] buf = new byte[256];
            DatagramPacket packetIn = new DatagramPacket(buf, buf.length);
            socket.setSoTimeout(5000);

            try {
                socket.receive(packetIn);
                String response = new String(packetIn.getData(), 0, packetIn.getLength(), "UTF-8");

                if (!response.isEmpty()) {
                    ArrayList<String> unitList = parseUnits(response);

                    // Điền các đơn vị độ dài vào hai JComboBox
                    fillComboBoxes(unitList);

                    // Kích hoạt nút Convert
                    btnConvert.setEnabled(true);
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
                    while (connected) {
                        socket.receive(packetIn);
                        String msgIn = new String(packetIn.getData(), 0, packetIn.getLength(), "UTF-8");

                        if ("[RESULT]".equals(msgIn)) {
                            // Nhận kết quả từ máy chủ và hiển thị lên trường kết quả
                            socket.receive(packetIn);
                            String result = new String(packetIn.getData(), 0, packetIn.getLength(), "UTF-8");
                            tfdResult.setText(result);

                        } else if ("[ERROR]".equals(msgIn)) {
                            socket.receive(packetIn);
                            String errorMsg = new String(packetIn.getData(), 0, packetIn.getLength(), "UTF-8");
                            JOptionPane.showMessageDialog(null, "Error: " + errorMsg, "Error", JOptionPane.ERROR_MESSAGE);

                        } else if ("SERVER_SHUTDOWN".equals(msgIn)) {
                            connected = false;
                            JOptionPane.showMessageDialog(null, "\nServer has been shut down. You have been disconnected.\n", "Server Shutdown", JOptionPane.ERROR_MESSAGE);
                            toggleInput(connected);
                            socket.close();
                            break;
                        }

                    }
                } catch (IOException e) {
                    if (connected) {
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
        } catch (SocketTimeoutException e) {
            JOptionPane.showMessageDialog(null, "Server not responding. Please try again later.", "Connection Timeout", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No corresponding server found", "Server Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveToServer();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertActionPerformed
        String value = tfdInput.getText().trim();
        if (!value.isEmpty()) {
            // Lấy đơn vị từ JComboBox thứ nhất
            String fromUnit = (String) cbxUnit1.getSelectedItem();

            // Lấy đơn vị từ JComboBox thứ hai
            String toUnit = (String) cbxUnit2.getSelectedItem();

            String message = "[CONVERT]," + fromUnit + "," + toUnit + "," + value;
            try {
                sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Input value cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_btnConvertActionPerformed

    private ArrayList<String> parseUnits(String units) {
        ArrayList<String> unitList = new ArrayList<>();
        String[] unitArray = units.split(",");
        for (String unit : unitArray) {
            unitList.add(unit.trim());
        }
        return unitList;
    }

    private void fillComboBoxes(ArrayList<String> units) {
        DefaultComboBoxModel<String> modelFrom = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> modelTo = new DefaultComboBoxModel<>();
        for (String unit : units) {
            modelFrom.addElement(unit);
            modelTo.addElement(unit);
        }
        cbxUnit1.setModel(modelFrom);
        cbxUnit2.setModel(modelTo);
    }

    private void sendMessage(String message) throws IOException {
        try {
            byte[] buf = message.getBytes("UTF-8");
            DatagramPacket packetOut = new DatagramPacket(buf, buf.length, serverAddress, serverPort);
            socket.send(packetOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void leaveToServer() {
        try {
            sendMessage("LEAVE");
            connected = false; // Dừng luồng nhận tin nhắn
            JOptionPane.showMessageDialog(null, "You have successfully left the Length Converter room.", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            socket.close(); // Đóng socket đến server
        } catch (IOException e) {
            e.printStackTrace();
        }

        toggleInput(connected);
    }

    private void toggleInput(boolean connected) {
        btnHandle.setText(connected ? "Leave" : "Connect");
        tfdServerName.setEnabled(!connected);
        tfdPort.setEnabled(!connected);
        btnConvert.setEnabled(connected);
        cbxUnit1.setEnabled(connected);
        cbxUnit2.setEnabled(connected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConvert;
    private javax.swing.JButton btnHandle;
    private javax.swing.JComboBox<String> cbxUnit1;
    private javax.swing.JComboBox<String> cbxUnit2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfdInput;
    private javax.swing.JTextField tfdPort;
    private javax.swing.JTextField tfdResult;
    private javax.swing.JTextField tfdServerName;
    // End of variables declaration//GEN-END:variables
}
