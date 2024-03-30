package es.udc.redes.tutorial.udp.server;

import java.net.*;

/**
 * Implements a UDP echo server.
 */
public class UdpServer {

    public static void main(String[] argv) {
        DatagramSocket dSocket = null;
        DatagramPacket dPacketReq;
        DatagramPacket dPacketRes ;
        byte[] responseBuffer;
        int port;
        int timeout = 300000;

        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.udp.server.UdpServer <port_number>");
            System.exit(-1);
        }
        try {
            port = Integer.parseInt(argv[0]);
            dSocket = new DatagramSocket(port);
            dSocket.setSoTimeout(timeout);
            System.out.println("Server listening in port " + port + " with a timeout of " + timeout / 1000 + " secs\n");

            while (true) {
                dPacketReq = new DatagramPacket(new byte[2048], 2048);
                receiveRequest(dSocket, dPacketReq);

                responseBuffer = new byte[dPacketReq.getLength()];
                System.arraycopy(dPacketReq.getData(), 0, responseBuffer, 0, dPacketReq.getLength());

                dPacketRes = new DatagramPacket(
                        responseBuffer, responseBuffer.length,
                        dPacketReq.getAddress(), dPacketReq.getPort()
                );
                sendResponse(dSocket, dPacketRes);
                
            }
        }
        catch (SocketTimeoutException e) {
            System.err.println("No requests received in 300 secs ");
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close socket to release connection
            if (dSocket != null) dSocket.close();
        }
    }

    private static void sendResponse(DatagramSocket dSocket, DatagramPacket dPacketRes) throws Exception {
        dSocket.send(dPacketRes);
        System.out.println("SERVER: Sending "
                + new String(dPacketRes.getData(), 0, dPacketRes.getLength())
                + " to " + dPacketRes.getAddress().toString() + ":"
                + dPacketRes.getPort());
    }

    private static void receiveRequest(DatagramSocket dSocket, DatagramPacket dPacketReq) throws Exception {
        dSocket.receive(dPacketReq);
        System.out.println("SERVER: Received "
                + new String(dPacketReq.getData(), 0, dPacketReq.getLength())
                + " from " + dPacketReq.getAddress().toString() + ":"
                + dPacketReq.getPort());
    }
}
