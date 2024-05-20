package main.UDP.TemperatureConverter;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class UDPTemperatureConverterServer extends javax.swing.JFrame {

    private DatagramSocket serverSocket;
    private boolean connected = false;

    public UDPTemperatureConverterServer() {
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

                        if ("[JOIN]".equals(msgIn)) {
                            // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                            onlineUsersTextArea.append(clientAddress + " at port " + clientPort + " has connected to the Length Converter room\n");

                            // Gửi danh sách các đơn vị nhiệt độ mà server hỗ trợ cho client
                            String unitsStr = "C,F,K";
                            sendMessage(unitsStr, clientAddress, clientPort);

                        } else if (msgIn.startsWith("[CONVERT]")) {
                            // Đọc thông tin chuyển đổi từ client
                            String[] parts = msgIn.split(",");
                            if (parts.length == 4) {
                                String fromUnit = parts[1];
                                String toUnit = parts[2];
                                String valueStr = parts[3];

                                try {
                                    double value = Double.parseDouble(valueStr);
                                    // Chuyển đổi nhiệt độ
                                    double result = convertTemperature(fromUnit, toUnit, value);
                                    String resultStr = "" + result;
                                    sendMessage("[RESULT]", clientAddress, clientPort);
                                    sendMessage(resultStr, clientAddress, clientPort);

                                    String reqMsg = "Convert " + valueStr + " " + fromUnit + " to " + toUnit;
                                    onlineUsersTextArea.append(clientAddress + " at port " + clientPort + " has requested " + reqMsg + "\n");

                                } catch (NumberFormatException e) {
                                    // Gửi thông báo lỗi về cho client nếu dữ liệu không phải là số
                                    String errorMsg = "[ERROR],Invalid number format";
                                    sendMessage("[ERROR]", clientAddress, clientPort);
                                    sendMessage(errorMsg, clientAddress, clientPort);
                                } catch (IllegalArgumentException e) {
                                    sendMessage("[ERROR]", clientAddress, clientPort);
                                    sendMessage(e.getMessage(), clientAddress, clientPort);
                                }
                            }
                        } else if ("LEAVE".equals(msgIn)) {
                            // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                            onlineUsersTextArea.append(clientAddress + " at port " + clientPort + " has disconnected to the Length Converter room\n");
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
        connected = false;
        // Đóng ServerSocket
        if (serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
        }
        btnHandle.setText("Start");
        textFieldPort.setEnabled(true);
        onlineUsersTextArea.append("Server has been stopped.\n");
    }

    private void sendMessage(String message, InetAddress address, int port) throws IOException {
        byte[] buf = message.getBytes("UTF-8");
        DatagramPacket packetOut = new DatagramPacket(buf, buf.length, address, port);
        serverSocket.send(packetOut);
    }

    private double convertTemperature(String fromUnit, String toUnit, double value) {
        double result = 0;
        if (fromUnit.equals(toUnit)) {
            // Nếu đơn vị đầu vào và đầu ra giống nhau, kết quả là giá trị ban đầu
            result = value;
        } else if (fromUnit.equals("C") && toUnit.equals("F")) {
            result = (value * 9 / 5) + 32;
        } else if (fromUnit.equals("F") && toUnit.equals("C")) {
            result = (value - 32) * 5 / 9;
        } else if (fromUnit.equals("C") && toUnit.equals("K")) {
            result = value + 273.15;
        } else if (fromUnit.equals("K") && toUnit.equals("C")) {
            result = value - 273.15;
        } else if (fromUnit.equals("F") && toUnit.equals("K")) {
            result = (value - 32) * 5 / 9 + 273.15;
        } else if (fromUnit.equals("K") && toUnit.equals("F")) {
            result = (value - 273.15) * 9 / 5 + 32;
        } else {
            throw new IllegalArgumentException("Invalid temperature unit");
        }
        return Math.round(result * 100.0) / 100.0; // Làm tròn đến 2 chữ số thập phân
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
