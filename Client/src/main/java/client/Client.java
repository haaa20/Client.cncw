package client;

import java.io.IOException;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private DatagramPacket in, out;
    private byte[] buf;

    // Instancing the socket, which will bind itself to a free port
    public Client(){
        this.buf = new byte[512];
        this.in = new DatagramPacket(buf, buf.length);
        this.out = new DatagramPacket(buf, buf.length);

        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            System.err.println("There was a problem finding a free port");
        }
    }

    // Sends the out packet to the connected address, returns true if successful
    private boolean sendOut() {
        try {
            socket.send(out);
            return true;
        } catch (IOException e) {
            System.err.println("There was a problem sending the packet");
            return false;
        }
    }

    // Tries to connect to the given address
    public boolean connect(String address, int port) {
        try {
            socket.connect(InetAddress.getByName(address), port);
            return true;
        } catch (UnknownHostException e) {
            System.err.println("There was a problem connecting to " + address);
            return false;
        }
    }

    public void sendText(String text) {
        // Naive implementation : we're just assuming textAsBytes.length < 512
        byte [] textAsBytes = text.getBytes();
        System.arraycopy(textAsBytes, 0, buf, 0, textAsBytes.length);
        sendOut();

    }

    public void sayHello() {
        sendText("Hello!!");
    }
}
