package es.udc.redes.webserver.httpcode;

public class HttpCode304 extends HttpCode {
    public HttpCode304(int code) {
        super(code);
    }

    @Override
    public String toString() {
        return "304 Not Modified";
    }
}
