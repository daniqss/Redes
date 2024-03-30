package es.udc.redes.webserver;

import es.udc.redes.webserver.controllers.BadRequestController;
import es.udc.redes.webserver.controllers.HttpController;
import es.udc.redes.webserver.controllers.NotFoundController;

import java.net.*;
import java.io.*;
import java.util.Objects;


public class ServerThread extends Thread {

    final private Socket socket;
    final private HttpRouter routes;

    public ServerThread(Socket socket, HttpRouter routes) {
        this.socket = socket;
        this.routes = routes;
    }

    @Override
    public void run() {
        InputStream inputStream;
        OutputStream outputStream = null;
        HttpRequest request;
        HttpResponse response;

        try {
            // This code processes HTTP requests and generates
            // HTTP responses
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            // Read the request
            request = manageRequest(inputStream);
            // System.out.println(request);

            // Send the response
            manageResponse(request, routes, outputStream);


        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " " + e.getClass());
        } finally {
            try {
                if (outputStream != null) outputStream.close();
                socket.close();
                // System.out.println("Connection closed");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage() + " " + e.getClass());
            }
        }
    }

    private static HttpRequest manageRequest(InputStream inputStream) throws Exception{
        BufferedReader bufferReader;
        String requestBuffer;
        StringBuilder requestBuilder = new StringBuilder();
        HttpRequest request;

        bufferReader = new BufferedReader(new InputStreamReader(inputStream));
        requestBuffer = bufferReader.readLine();

        // Check if the request is not null and not empty
        while (requestBuffer != null && !requestBuffer.isEmpty()) {
            // Append the line to the StringBuilder
            requestBuilder.append(requestBuffer).append("\n");

            // Read the next line
            requestBuffer = bufferReader.readLine();
        }
        requestBuilder.append("\n");
        request = HttpRequest.parseRequest(requestBuilder.toString());

        return request;
    }

    private static void manageResponse(
            HttpRequest request, HttpRouter routes, OutputStream outputStream
    ) throws Exception {
        HttpController controller;
        HttpResponse response;

        try {
            controller = routes.getController(request.getPath());

            switch (request.getMethod()) {
                case GET -> response = controller.get(request);
                case HEAD -> response = controller.head(request);
                default -> {
                    controller = new BadRequestController();
                    response = controller.get(request);
                }
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            controller = new NotFoundController();
            response = controller.get(request);
        }

        try {
            response.sendResponse(outputStream);
            // System.out.println(response);
        }
        catch (Exception e) {
            System.err.println("Error sending response: \n" + e.getMessage() + " " + e.getClass());
        }
    }

}
