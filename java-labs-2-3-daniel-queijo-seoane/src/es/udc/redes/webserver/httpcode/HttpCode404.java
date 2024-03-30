package es.udc.redes.webserver.httpcode;

public class HttpCode404 extends HttpCode {
    final private String documentPath = "p1-files/404.html";

    public HttpCode404(int code) { super(code); }

    @Override
    public String toString() {
        return "404 Not Found";
    }
}
