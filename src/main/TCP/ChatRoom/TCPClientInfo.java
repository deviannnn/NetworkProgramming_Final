package main.TCP.ChatRoom;

import java.io.DataOutputStream;
import java.net.Socket;

public class TCPClientInfo {

    private String username;
    private DataOutputStream dataOut;
    private Socket socket;

    public TCPClientInfo(String username, DataOutputStream dataOut, Socket socket) {
        this.username = username;
        this.dataOut = dataOut;
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public DataOutputStream getDataOut() {
        return dataOut;
    }

    public Socket getSocket() {
        return socket;
    }
}
