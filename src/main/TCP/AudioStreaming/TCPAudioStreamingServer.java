package main.TCP.AudioStreaming;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import javax.sound.sampled.*;
import javax.swing.*;

public class TCPAudioStreamingServer extends javax.swing.JFrame {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private boolean connected = false;
    private final String MUSIC_FOLDER_PATH = "/resource/music/";

    public TCPAudioStreamingServer() {
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

            Thread serverThread = new Thread(() -> {
                while (!serverSocket.isClosed()) {
                    try {
                        clientSocket = serverSocket.accept();

                        Thread clientHandlerThread = new Thread(() -> {
                            try {
                                dataIn = new DataInputStream(clientSocket.getInputStream());
                                dataOut = new DataOutputStream(clientSocket.getOutputStream());

                                // Đọc yêu cầu từ client
                                String request = dataIn.readUTF();

                                if ("GET_SONG_LIST".equals(request)) {
                                    onlineUsersTextArea.append("A new client has requested to send a playlist\n");

                                    // Gửi danh sách bài hát về cho client
                                    ArrayList<String> songList = getSongListFromResources();
                                    StringBuilder songListBuilder = new StringBuilder();
                                    for (String song : songList) {
                                        songListBuilder.append(song).append("\n");
                                    }
                                    dataOut.writeUTF(songListBuilder.toString());
                                }

                                while (connected) {
                                    request = dataIn.readUTF();

                                    if ("LEAVE_CHAT_ROOM".equals(request)) {
                                        // Ngắt kết nối khi client thoát
                                        clientSocket.close();
                                        // Cập nhật giao diện để hiển thị danh sách người dùng đang kết nối trên server
                                        onlineUsersTextArea.append("Client has disconnected to the audio streaming room\n");
                                        break;
                                    }

                                    // Phát nhạc tương ứng với yêu cầu từ client
                                    String songName = request.substring("PLAY_SONG|".length());
                                    File file = new File(MUSIC_FOLDER_PATH + songName);
                                    if (!file.exists()) {
                                        dataOut.writeUTF("File not found");
                                        continue;
                                    }

                                    // Gửi thông báo tìm thấy file và dữ liệu của file cho client
                                    try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
                                        AudioFormat format = audioInputStream.getFormat();
                                        System.out.println(format);

                                        byte[] buffer = new byte[4096];
                                        int bytesRead;
                                        while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                                            dataOut.write(buffer, 0, bytesRead);
                                        }
                                    } catch (UnsupportedAudioFileException ex) {
                                        ex.printStackTrace();
                                    }
                                }

                            } catch (IOException e) {
                                // Xử lý lỗi khi giao tiếp với client
                                if (connected) {
                                    onlineUsersTextArea.append("An error occurred while receiving the message\n");
                                }
                            }
                        });

                        connected = true;
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

    private ArrayList<String> getSongListFromResources() {
        ArrayList<String> songList = new ArrayList<>();
        try {
            // Lấy đường dẫn tới thư mục hoặc JAR file chứa class này
            String jarDirPath = new File(TCPAudioStreamingServer.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            File jarDir = new File(jarDirPath);
            File musicFolder;

            if (jarDir.isDirectory()) {
                // Nếu chạy từ IDE (thư mục)
                musicFolder = new File(jarDir, MUSIC_FOLDER_PATH);
                if (musicFolder.exists() && musicFolder.isDirectory()) {
                    File[] musicFiles = musicFolder.listFiles();
                    if (musicFiles != null) {
                        for (File musicFile : musicFiles) {
                            if (musicFile.isFile()) {
                                songList.add(musicFile.getName());
                            }
                        }
                    }
                }
            } else {
                // Nếu chạy từ JAR (tệp nén)
                URI jarURI = new URI("jar:file:" + jarDirPath);
                try (FileSystem fileSystem = FileSystems.newFileSystem(jarURI, Collections.emptyMap())) {
                    Path musicFolderPath = fileSystem.getPath(MUSIC_FOLDER_PATH);
                    try (Stream<Path> paths = Files.walk(musicFolderPath, 1)) {
                        paths.filter(Files::isRegularFile).forEach(path -> songList.add(path.getFileName().toString()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songList;
    }

    private void stopServer() {
        connected = false;

        // Đóng ServerSocket
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error while stopping the server: " + e.getMessage(), "Server Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        btnHandle.setText("Start");
        textFieldPort.setEnabled(true);
        onlineUsersTextArea.append("Server has been stopped...\n");
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
