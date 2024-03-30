package es.udc.redes.webserver.httpcode;

public class HttpCode200 extends HttpCode {
    public HttpCode200(int code) { super(code); }

    @Override
    public String toString() { return "200 OK"; }
}
