package main.TCP.AudioStreaming;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.*;

public class TCPAudioStreamingClient extends javax.swing.JFrame {

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private boolean connected = false;
    private boolean isPlaying = false;
    private String selectedSong = null;
    DefaultListModel<String> listModel;

    public TCPAudioStreamingClient() {
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
        listModel = new DefaultListModel<>();
        listSongsReceived.setModel(listModel);

        lblNameSong.setVisible(false);
        btnPrevious.setVisible(false);
        btnPause.setVisible(false);
        btnPlay.setVisible(false);
        btnStop.setVisible(false);
        btnNext.setVisible(false);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        listSongsReceived = new javax.swing.JList<>();
        btnPlay = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblNameSong = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(570, 350));
        setMinimumSize(new java.awt.Dimension(570, 350));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnHandle.setText("Start");
        btnHandle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandleActionPerformed(evt);
            }
        });

        tfdServerName.setText("localhost");
        tfdServerName.setToolTipText("protocol");

        jLabel6.setText("PortNo:");

        tfdPort.setText("2024");
        tfdPort.setToolTipText("protocol");

        jLabel7.setText("ServerName:");

        listSongsReceived.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listSongsReceived.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSongsReceivedValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listSongsReceived);

        btnPlay.setText("⏵");
        btnPlay.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnPause.setText("⏸");
        btnPause.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnStop.setText("⏹");
        btnStop.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnPrevious.setText("⏮");
        btnPrevious.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnNext.setText("⏭");
        btnNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblNameSong.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lblNameSong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameSong.setText("name song");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNameSong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdServerName, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfdPort)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHandle, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 186, Short.MAX_VALUE)
                .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel7)
                        .addComponent(tfdServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHandle)
                        .addComponent(tfdPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNameSong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(650, 100, 584, 303);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHandleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandleActionPerformed
        if (!connected) {
            startClient();
        } else {
            leaveAudioStreamRoom();
        }
    }//GEN-LAST:event_btnHandleActionPerformed

    private void startClient() {
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

            // Gửi yêu cầu lấy danh sách bài hát
            dataOut.writeUTF("GET_SONG_LIST");

            // Nhận danh sách bài hát từ server
            String songListStr = dataIn.readUTF();
            String[] songList = songListStr.split("\n");
            for (String song : songList) {
                listModel.addElement(song);
            }

            connected = true;
            playSongThread();
            toggleInput();

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

    private void playSongThread() {
        // Tạo luồng để nhận dữ liệu và phát nhạc từ server
        Thread audioStreamingThread = new Thread(() -> {
            try {
                // Tạo buffer để lưu dữ liệu âm thanh từ server
                byte[] buffer = new byte[4096]; // hoặc kích thước buffer phù hợp với nhu cầu của bạn

                // Mở SourceDataLine để phát âm thanh
                AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 2, 4, 44100.0f, false);
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                // Đọc dữ liệu từ server và ghi vào SourceDataLine để phát nhạc
                while (connected) {
                    int bytesRead = dataIn.read(buffer);
                    if (bytesRead == -1) {
                        break; // Kết thúc luồng nếu không còn dữ liệu nữa
                    }
                    sourceDataLine.write(buffer, 0, bytesRead); // Ghi dữ liệu vào SourceDataLine
                }

                // Khi kết thúc, đóng SourceDataLine
                sourceDataLine.drain();
                sourceDataLine.close();
            } catch (IOException ex) {
                // Xử lý các ngoại lệ
            } catch (LineUnavailableException ex) {
                Logger.getLogger(TCPAudioStreamingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // Khởi chạy luồng mới để nhận dữ liệu và phát nhạc
        audioStreamingThread.start();
    }

    private void leaveAudioStreamRoom() {
        connected = false;
        toggleInput();
        listModel.clear();
        try {
            // Gửi tin nhắn rời phòng chat tới server
            dataOut.writeUTF("LEAVE_CHAT_ROOM");
            // Dừng luồng nhận tin nhắn
            JOptionPane.showMessageDialog(null, "You have successfully left the chat room.", "Successfully", JOptionPane.INFORMATION_MESSAGE);
            socket.close(); // Đóng socket đến server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (socket != null && !socket.isClosed()) {
            leaveAudioStreamRoom();
        }
    }//GEN-LAST:event_formWindowClosing

    private void listSongsReceivedValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSongsReceivedValueChanged
        if (!evt.getValueIsAdjusting()) {
            String selection = listSongsReceived.getSelectedValue();
            if (selection != null && !selection.isEmpty()) {
                selectedSong = selection;
                lblNameSong.setText(selectedSong);
                isPlaying = true;
                toggleAudio();
            }
            listSongsReceived.clearSelection();
        }
    }//GEN-LAST:event_listSongsReceivedValueChanged

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // Gửi yêu cầu lấy dữ liệu bài hát để phát trực tiếp
        if (selectedSong != null) {
            try {
                dataOut.writeUTF("PLAY_SONG|" + selectedSong);
                btnPlay.setEnabled(false);
            } catch (IOException ex) {
                Logger.getLogger(TCPAudioStreamingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void toggleAudio() {
        lblNameSong.setVisible(isPlaying);
        btnPrevious.setVisible(isPlaying);
        btnPause.setVisible(isPlaying);
        btnPlay.setVisible(isPlaying);
        btnStop.setVisible(isPlaying);
        btnNext.setVisible(isPlaying);
    }

    private void toggleInput() {
        btnHandle.setText(connected ? "Leave" : "Start");
        tfdServerName.setEnabled(!connected);
        tfdPort.setEnabled(!connected);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHandle;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnStop;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNameSong;
    private javax.swing.JList<String> listSongsReceived;
    private javax.swing.JTextField tfdPort;
    private javax.swing.JTextField tfdServerName;
    // End of variables declaration//GEN-END:variables
}
