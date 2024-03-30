package es.udc.redes.webserver.controllers;

import es.udc.redes.webserver.HttpRequest;
import es.udc.redes.webserver.HttpResponse;
import es.udc.redes.webserver.utils.ReadFile;

public interface HttpController {
    default HttpResponse get(HttpRequest request) { return new HttpResponse(request, 501, "text/plain"); }

    default HttpResponse head(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse post(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse put(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse delete(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse connect(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse options(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse trace(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

    default HttpResponse patch(HttpRequest request) {
        return new HttpResponse(request ,501, "text/plain");
    }

}
