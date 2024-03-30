package es.udc.redes.webserver.controllers;

import es.udc.redes.webserver.HttpRequest;
import es.udc.redes.webserver.HttpResponse;

import java.util.ArrayList;

import static es.udc.redes.webserver.utils.ReadFile.readFile;
import static es.udc.redes.webserver.utils.ReadFile.readFileBytes;

public class NotFoundController implements HttpController {

    @Override
    public HttpResponse get(HttpRequest request) {
        HttpResponse response = new HttpResponse(request, 404, "text/html");
        ArrayList<Byte> body = readFileBytes("p1-files/error404.html");
        response.addBody(body);

        return response;
    }

    @Override
    public HttpResponse head(HttpRequest request) {
        return new HttpResponse(request, 404, "text/html");
    }

}
