package ar.edu.et32.Chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    private int port = 2006;
    private String ip = "127.0.0.1";

    protected String msg = "";

    protected PrintStream ps;

    protected Socket sockClient;
    protected ServerSocket sockServer;

    private final InetAddress direction;
    protected DataOutputStream dosServer, dosClient;

    public Connection(enumType type) throws IOException, UnknownHostException {
        ps = new PrintStream(System.out);

        direction = InetAddress.getByName(ip);

        switch (type) {
            case CLIENT -> {
                sockClient = new Socket(direction, port);
            }
            case SERVER -> {
                sockServer = new ServerSocket(port);
                sockClient = new Socket();
            }
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}

enum enumType {
    SERVER,
    CLIENT
}
