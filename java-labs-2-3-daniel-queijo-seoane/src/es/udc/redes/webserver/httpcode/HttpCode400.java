package es.udc.redes.webserver.httpcode;

public class HttpCode400 extends HttpCode {
    final private String documentPath = "p1-files/400.html";

    HttpCode400(int code) { super(code); }

    @Override
    public String toString() { return "400 Bad Request"; }
}
