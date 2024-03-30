package es.udc.redes.webserver.controllers;

import es.udc.redes.webserver.HttpRequest;
import es.udc.redes.webserver.HttpResponse;

import java.util.ArrayList;

import static es.udc.redes.webserver.utils.ReadFile.readFile;
import static es.udc.redes.webserver.utils.ReadFile.readFileBytes;

public class FaviconController implements HttpController {
    @Override
    public HttpResponse get(HttpRequest request) {
        HttpResponse response = new HttpResponse(request, 200, "image/x-icon");
        ArrayList<Byte> body = readFileBytes("p1-files/favicon.ico");
        response.addBody(body);

        return response;
    }

    @Override
    public HttpResponse head(HttpRequest request) {
        return new HttpResponse(request, 200, "image/x-icon");
    }


}
