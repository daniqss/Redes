package es.udc.redes.webserver.httpcode;

public class HttpCode501 extends HttpCode {
    public HttpCode501(int code) { super(code); }

    @Override
    public String toString() { return "501 Not Implemented"; }
}
