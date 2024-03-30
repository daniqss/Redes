package es.udc.redes.tutorial.udp.client;

import java.net.*;


public class UdpClient {

    public static void main(String[] argv) {
        DatagramSocket dSocket = null;
        DatagramPacket dPacketRes ;
        DatagramPacket dPacketReq;
        InetAddress ip;
        byte[] requestBuffer;
        byte[] responseBuffer;
        int port;

        if (argv.length != 3) {
            System.err.println("Format: es.udc.redes.tutorial.udp.client.UdpClient <server_address> <message>");
            System.exit(-1);
        }

        try {
            // Get port
            port = Integer.parseInt(argv[1]);
            // Open socket
            dSocket = new DatagramSocket();

            // Get ip address
            ip = Inet4Address.getByName(argv[0]);
            requestBuffer = argv[2].getBytes();

            // Send the request datagram
            dPacketReq = new DatagramPacket(requestBuffer, requestBuffer.length, ip, port);
            sendRequest(dSocket, dPacketReq);

            // Receive the response datagram
            responseBuffer = new byte[32];
            dPacketRes = new DatagramPacket(responseBuffer, responseBuffer.length);
            receiveResponse(dSocket, dPacketRes);

        }
        // catch (SocketTimeoutException e) {
           //  System.err.println("Nothing received in 300 secs");
        //}
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        finally {
            // Close socket to release connection
            if (dSocket != null) dSocket.close();
        }
    }
    private static void sendRequest(DatagramSocket dSocket, DatagramPacket dPacketReq) throws Exception {
        dSocket.send(dPacketReq);
        System.out.println("CLIENT: Sending "
                + new String(dPacketReq.getData()) + " to "
                + dPacketReq.getAddress().toString() + ":"
                + dPacketReq.getPort());
    }

    private static void receiveResponse(DatagramSocket dSocket, DatagramPacket dPacketRes) throws Exception {
        dSocket.receive(dPacketRes);
        System.out.println("CLIENT: Received "
                + new String(dPacketRes.getData(), 0, dPacketRes.getLength())
                + " from " + dPacketRes.getAddress().toString() + ":"
                + dPacketRes.getPort());
    }
    /*
    private static byte[] checkForDoubleQuotes(String str) throws IOException {
        if (str.contains(" ")) {
            if (str.charAt(0) != '"' || str.charAt(str.length() - 1) != '"') {
                throw new IOException("Invalid message format");
            }
        }
        return str.getBytes();
    }
     */
}
