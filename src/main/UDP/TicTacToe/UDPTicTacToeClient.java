package main.UDP.TicTacToe;

import java.io.*;
import java.net.*;
import javax.swing.*;

public class UDPTicTacToeClient extends javax.swing.JFrame {

    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private boolean connected = false;
    private final String SERVER = "localhost";
    private final JButton[][] board = new JButton[3][3];
    private String turn;

    public UDPTicTacToeClient() {
        initComponents();
        board[0][0] = btn0;
        board[0][1] = btn1;
        board[0][2] = btn2;
        board[1][0] = btn3;
        board[1][1] = btn4;
        board[1][2] = btn5;
        board[2][0] = btn6;
        board[2][1] = btn7;
        board[2][2] = btn8;
        toggleBoard(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel4 = new javax.swing.JLabel();
        tfdPort = new javax.swing.JTextField();
        btnHandle = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("UDP TicTacToe Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(364, 440));
        setMinimumSize(new java.awt.Dimension(364, 440));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setText("Port No:");

        tfdPort.setText("2024");
        tfdPort.setToolTipText("protocol");

        btnHandle.setText("Start");
        btnHandle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHandle.setPreferredSize(new java.awt.Dimension(72, 20));
        btnHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandleActionPerformed(evt);
            }
        });

        btn0.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });

        btn1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

        btn7.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });

        btn8.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });

        lblMessage.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn0, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        setLocation(new java.awt.Point(650, 100));
    }// </editor-fold>//GEN-END:initComponents

    private void btnHandleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandleActionPerformed
        if (!connected) {
            joinGameRoom();
        } else {
            leaveGameRoom();
        }
    }//GEN-LAST:event_btnHandleActionPerformed

    private void joinGameRoom() {
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
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.setSoTimeout(5000);

            try {
                socket.receive(packet);
                String response = new String(packet.getData(), 0, packet.getLength(), "UTF-8");

                switch (response) {
                    case "CONNECTION_REFUSED" -> {
                        lblMessage.setText("Sorry, there are currently enough players!");
                        return;
                    }
                    case "[WAIT]" -> {
                        socket.receive(packet);
                        turn = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                        lblMessage.setText("Your turn is \'" + turn + "\' ~ Waiting for your opponent...");
                    }
                    case "[START]" -> {
                        socket.receive(packet);
                        turn = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                    }
                    default -> {
                    }
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

                        if (msgIn.startsWith("[GO]")) {
                            String currentTurn = msgIn.substring(msgIn.length() - 1);

                            if (turn.equals(currentTurn)) {
                                lblMessage.setText("Now is your turn.");
                                toggleBoard(true);
                            } else {
                                lblMessage.setText("Wait for \'" + currentTurn + "\' turn.");
                                toggleBoard(false);
                            }
                        }
                        if (msgIn.startsWith("[FILL]")) {
                            String[] positionStr = msgIn.split(" ");
                            int position1 = Integer.parseInt(positionStr[1]);
                            int position2 = Integer.parseInt(positionStr[2]);
                            String opponentTurn = turn.equals("X") ? "O" : "X";
                            board[position1][position2].setText(opponentTurn);
                        }
                        if (msgIn.startsWith("[END]")) {
                            char winner = msgIn.charAt(msgIn.length() - 1);
                            String message;
                            if (winner == '-') {
                                message = "The game ended in a draw.";
                            } else {
                                message = "Player " + winner + " wins!";
                            }
                            lblMessage.setText(message);
                            toggleBoard(false);
                        }
                        if ("SERVER_SHUTDOWN".equals(msgIn)) {
                            connected = false;
                            JOptionPane.showMessageDialog(null, "\nServer has been shut down. You have been disconnected.\n", "Server Shutdown", JOptionPane.ERROR_MESSAGE);
                            toggleInput(connected);
                            socket.close();
                            break;
                        }

                    }
                } catch (IOException e) {
                    if (connected) {
                        leaveGameRoom();
                    }
                }
            });

            clientThread.start();
            this.setTitle(turn);
            toggleInput(connected);
            clearBoard();

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

    private void leaveGameRoom() {
        try {
            sendMessage("LEAVE_CHAT_ROOM");
            connected = false; // Dừng luồng nhận tin nhắn
            JOptionPane.showMessageDialog(null, "You have successfully left the chat room.", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            socket.close(); // Đóng socket đến server
        } catch (IOException e) {
            e.printStackTrace();
        }

        toggleInput(connected);
        clearBoard();
    }

    private void sendMessage(String message) throws IOException {
        byte[] buf = message.getBytes("UTF-8");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, serverPort);
        socket.send(packet);
    }

    private void toggleInput(boolean connected) {
        tfdPort.setEnabled(!connected);
        btnHandle.setText(connected ? "Leave" : "Start");
        lblMessage.setText("");
    }

    private void clearBoard() {
        for (JButton[] subList : board) {
            for (JButton board : subList) {
                board.setText("");
            }
        }
    }

    private void toggleBoard(boolean status) {
        for (JButton[] subList : board) {
            for (JButton board : subList) {
                board.setEnabled(status);
            }
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveGameRoom();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        sendPosition(0, 0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        sendPosition(0, 1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        sendPosition(0, 2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        sendPosition(1, 0);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        sendPosition(1, 1);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        sendPosition(1, 2);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        sendPosition(2, 0);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        sendPosition(2, 1);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        sendPosition(2, 2);
    }//GEN-LAST:event_btn8ActionPerformed

    private void sendPosition(int position1, int position2) {
        // Kiểm tra vị trí đã đánh dấu chưa
        if (!board[position1][position2].getText().isEmpty()) {
            return;
        }
        // Gửi vị trí đánh cho server
        try {
            // Tạo thông điệp chứa vị trí đánh và Gửi thông điệp đến server
            sendMessage("[PLAY] " + position1 + " " + position2);
            board[position1][position2].setText(turn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btnHandle;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JTextField tfdPort;
    // End of variables declaration//GEN-END:variables
}
