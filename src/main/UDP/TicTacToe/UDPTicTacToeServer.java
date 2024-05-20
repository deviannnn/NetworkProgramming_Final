package main.UDP.TicTacToe;

import main.UDP.ChatRoom.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class UDPTicTacToeServer extends javax.swing.JFrame {

    private DatagramSocket serverSocket;
    private boolean connected = false;
    private char[][] board = new char[3][3];
    private int connectedCount = 0; // Số lượng client đã kết nối

    private final Set<UDPClientInfo> connectedClients = new HashSet<>();

    public UDPTicTacToeServer() {
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

                        // Chỉ chấp nhận kết nối từ hai client cùng lúc
                        if (connectedCount == 2 && clientUsername == null) {
                            // Gửi thông báo từ chối kết nối đến client mới
                            sendMessage("CONNECTION_REFUSED", clientAddress, clientPort);
                            continue;
                        }

                        if ("[JOIN]".equals(msgIn)) {
                            String username;
                            // Gửi danh sách các tên người dùng hiện tại cho client mới
                            if (connectedCount == 0) {
                                username = "X";
                                sendMessage("[WAIT]", clientAddress, clientPort);
                                // Gửi thông tin lượt chơi cho client 1 là 'X'
                                sendMessage(username, clientAddress, clientPort);
                            } else {
                                username = "O";
                                sendMessage("[START]", clientAddress, clientPort);
                                // Gửi thông tin lượt chơi cho client 2 là 'O'
                                sendMessage(username, clientAddress, clientPort);
                            }

                            // Tạo một ClientInfo mới và thêm vào danh sách connectedClients
                            UDPClientInfo client = new UDPClientInfo(username, clientAddress, clientPort);
                            connectedClients.add(client);
                            connectedCount++;

                            if (connectedCount == 2) {
                                // Gửi tín hiệu bắt đầu trò chơi đến cả 2 client
                                broadcastMessage(null, "[GO]X");
                            }

                            // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                            onlineUsersTextArea.append(username + ": " + clientAddress + " at port " + clientPort + " has connected to the game room\n");

                        } else if (msgIn.startsWith("[PLAY]")) {
                            String[] positionStr = msgIn.split(" ");
                            int position1 = Integer.parseInt(positionStr[1]);
                            int position2 = Integer.parseInt(positionStr[2]);

                            // Gửi thông điệp vị trí đánh cho client còn lại
                            broadcastMessage(clientUsername, "[FILL] " + position1 + " " + position2);

                            //Thực hiện nước đi và kiểm tra kết quả
                            boolean gameEnded = makeMoveAndCheckResult(clientUsername, position1, position2);
                            if (gameEnded) {
                                // Gửi thông điệp kết thúc trò chơi cho cả hai người chơi
                                broadcastMessage(null, "[END]" + getWinner());
                            } else {
                                // Nếu trò chơi chưa kết thúc, gửi thông điệp lượt đánh cho cả hai người chơi
                                String nextTurn = clientUsername.equals("X") ? "O" : "X";
                                broadcastMessage(null, "[GO]" + nextTurn);
                            }

                            // Cập nhật giao diện để hiển thị lượt đánh
                            onlineUsersTextArea.append(clientUsername + ": " + clientAddress + " at port " + clientPort
                                    + " has filled to " + position1 + " " + position2 + "\n");

                        } else if ("LEAVE_CHAT_ROOM".equals(msgIn)) {
                            // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                            onlineUsersTextArea.append(clientUsername + ": " + clientAddress + " at port " + clientPort + " has disconnected to the game room\n");

                            // Thông báo client đã rời khỏi phòng cho client khác
                            String absoluteWinner = clientUsername.equals("X") ? "O" : "X";
                            broadcastMessage(null, "[END]" + absoluteWinner);

                            // Ngắt kết nối khi client thoát
                            synchronized (connectedClients) {
                                connectedClients.removeIf(client -> client.getAddress().equals(clientAddress) && client.getPort() == clientPort);
                                connectedCount--;
                            }
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
                        sendMessage(message, client.getAddress(), client.getPort());
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error broadcasting message: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    // Lưu vị trí nước đi vào mảng 2 chiều và kiểm tra kết quả sau mỗi nước đi
    private boolean makeMoveAndCheckResult(String clientUsername, int position1, int position2) {
        // Kiểm tra xem nước đi có hợp lệ không (nằm trong phạm vi mảng và ô đó chưa được đánh)
        if (position1 < 0 || position1 > 2 || position2 < 0 || position2 > 2 || board[position1][position2] != '\u0000') {
            // Nước đi không hợp lệ, trả về false
            return false;
        }

        // Lưu nước đi vào bảng
        board[position1][position2] = (clientUsername.equals("X")) ? 'X' : 'O';

        // Kiểm tra xem có ai chiến thắng không sau mỗi nước đi
        char winner = getWinner();

        // Nước đi hợp lệ và không có ai chiến thắng, trả về false
        return winner != '-';
    }

    // Hàm kiểm tra kết quả của trò chơi sau mỗi nước đi
    private char getWinner() {
        // Kiểm tra theo các hàng, cột và đường chéo
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '\u0000' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return board[i][0]; // Người chiến thắng
            }
            if (board[0][i] != '\u0000' && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return board[0][i]; // Người chiến thắng
            }
        }
        // Kiểm tra đường chéo chính
        if (board[0][0] != '\u0000' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0]; // Người chiến thắng
        }
        // Kiểm tra đường chéo phụ
        if (board[0][2] != '\u0000' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2]; // Người chiến thắng
        }

        // Nếu không có ai chiến thắng
        return '-';
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
