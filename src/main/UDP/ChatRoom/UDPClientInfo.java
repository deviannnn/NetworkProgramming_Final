package main.UDP.ChatRoom;

import java.net.InetAddress;

public class UDPClientInfo {

    private final String username;
    private final InetAddress address;
    private final int port;

    public UDPClientInfo(String username, InetAddress address, int port) {
        this.username = username;
        this.address = address;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
