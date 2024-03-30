package es.udc.redes.webserver;

import es.udc.redes.webserver.controllers.*;

import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    
    public static void main(String[] args) {
        int port = getPort(args);
        HttpRouter routes = new HttpRouter();
        HttpController index = new IndexController();

        routes.addRoute("/", new IndexController());
        routes.addRoute("/index.html", new IndexController());
        routes.addRoute("/fic.png", new ImageController());
        routes.addRoute("/favicon.ico",new FaviconController());
        routes.addRoute("/LICENSE.txt",new LicenceController());
        routes.addRoute("/udc.gif", new GifController());
        routes.addRoute("/error404.html", new NotFoundController());
        routes.addRoute("/notFound.html", new NotFoundController());
        routes.addRoute("/error400.html", new BadRequestController());

        while (port < 65536) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server listening on http://localhost:" + port);
                while (true) {
                    // Wait for connections
                    Socket socket = serverSocket.accept();

                    // Create a ServerThread object, with the new connection as a parameter
                    ServerThread serverThread = new ServerThread(socket, new HttpRouter(routes));

                    // Initiate thread using the start() method
                    serverThread.start();
                }
            }
            catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                port ++;
            }
        }


    }

    private static int getPort(String[] args) {
        int port = 0;

        if (args.length != 1) {
            System.err.println("Format: es.udc.redes.webserver.WebServer <port>");
            System.exit(-1);
        }

        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid port number");
            System.exit(-1);
        }

        return port;
    }
}
