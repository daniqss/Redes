package es.udc.redes.webserver;

public enum HttpMethod {
    GET, HEAD, UNKNOWN;

    public static HttpMethod fromString(String method) {
        return switch (method) {
            case "GET" -> GET;
            case "HEAD" -> HEAD;
            default -> UNKNOWN;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case GET -> "GET";
            case HEAD -> "HEAD";
            default -> "UNKNOWN";
        };
    }
}