package es.udc.redes.webserver.httpcode;

import java.io.BufferedReader;
import java.io.FileReader;

public class HttpCode {
    int code;

    HttpCode(int code) {
        this.code = code;
    }

    public static HttpCode createHttpCode(int code) {
        HttpCode httpCode = null;

        switch (code) {
            case 200 -> httpCode = new HttpCode200(code);
            case 304 -> httpCode = new HttpCode304(code);
            case 400 -> httpCode = new HttpCode400(code);
            case 404 -> httpCode = new HttpCode404(code);
            case 501 -> httpCode = new HttpCode501(code);
            default -> httpCode = new HttpCode(code);
        }

        return httpCode;
    }

    // Should return the code
    public int getHttpCode() {
        return code;
    }

    @Override
    public String toString() {
        return getHttpCode() + "";
    }
}