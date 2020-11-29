package segmentedfilesystem;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class FileRetriever {

        String server;
        int port;

        public FileRetriever(String server, int port) {
                // Save the server and port for use in `downloadFiles()`
                this.server = server;
                this.port = port;
        }

        public void downloadFiles() throws IOException {
                InetAddress address = InetAddress.getByName(server);
                byte[] buf = new byte[1028]; // Buffer with the size 1028

                DatagramSocket sock = new DatagramSocket();

                DatagramPacket pack = new DatagramPacket(buf, buf.length, address, port); // Starts connection
                System.out.println("Connection established!");

                sock.send(pack);

                PacketManager manager = new PacketManager();

                while (!manager.allPacketsRecieved()) { // When all files are received...
                        byte[] buffer = new byte[1028];

                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        sock.receive(packet);

                        System.out.println("Data received!");

                        manager.newPacket(packet);
                }
                
                sock.close();
        }

}
