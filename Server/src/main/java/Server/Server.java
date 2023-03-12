package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    private DatagramSocket socket;
    private DatagramPacket in, out;
    private byte[] buf;

    public Server(int port) {
        this.buf = new byte[512];
        this.in = new DatagramPacket(buf, buf.length);
        this.out = new DatagramPacket(buf, buf.length);

        // Ew nested try/catch statements
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            try {
                this.socket = new DatagramSocket();
                port = socket.getPort();
                System.err.println("could not connect to that port, connected to port " + port + " instead");
            } catch (SocketException ex) {
                System.err.println("could not find a free port");
            }
        }
    }

    public void echo() {
        try {
            socket.receive(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String received = new String(in.getData());
        System.out.println(received);
    }
}
