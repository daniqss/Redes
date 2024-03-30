package es.udc.redes.webserver.controllers;

import es.udc.redes.webserver.HttpRequest;
import es.udc.redes.webserver.HttpResponse;
import es.udc.redes.webserver.utils.ReadFile;

import java.util.ArrayList;

import static es.udc.redes.webserver.utils.ReadFile.readFile;
import static es.udc.redes.webserver.utils.ReadFile.readFileBytes;

public class IndexController implements HttpController {
    final private String filePath = "p1-files/index.html";

    @Override
    public HttpResponse get(HttpRequest request) {
        HttpResponse response;
        ArrayList<Byte> body = readFileBytes(filePath);

        if (HttpResponse.checkLastModified(request, filePath)) {
            response = new HttpResponse(request, 304, "text/html");
        } else {
            response = new HttpResponse(request, 200, "text/html");
            response.addBody(body);
        }

        return response;
    }

    @Override
    public HttpResponse head(HttpRequest request) {
        return new HttpResponse(request, 200, "text/html");
    }


}
