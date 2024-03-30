package es.udc.redes.tutorial.tcp.server;

import java.net.*;
import java.io.*;

/**
 * MonoThread TCP echo server.
 */
public class MonoThreadTcpServer {

    public static void main(String[] argv) {
        ServerSocket serverSocket = null;
        int port;
        int timeout = 300000;

        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.tcp.server.MonoThreadTcpServer <port>");
            System.exit(-1);
        }
        try {
            // Create a server socket
            port = Integer.parseInt(argv[0]);
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(timeout);
            
            while (true) {
                System.out.println("SERVER: Listening on port " + port);
                serverSocket.accept();
                
                // Set the input channel

                // Set the output channel
                
                // Receive the client message
                
                // Send response to the client

                // Close the streams
            }
        // Uncomment next catch clause after implementing the logic            
        //} catch (SocketTimeoutException e) {
        //    System.err.println("Nothing received in 300 secs ");
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            try { serverSocket.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }
}
