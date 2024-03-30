package es.udc.redes.webserver;

import es.udc.redes.webserver.HttpMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import java.util.TimeZone;

import static es.udc.redes.webserver.HttpMethod.*;

public class HttpRequest {
    private final HttpMethod method;
    private final String path;
    private final String httpVersion;
    private final Map<String, String> headers;

    private HttpRequest(HttpMethod method, String path, String httpVersion, Map<String, String> headers) {
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
        this.headers = headers;
    }

    public HttpMethod getMethod() { return method; }
    public String getPath() { return path; }
    public String getHttpVersion() { return httpVersion; }

    public String getHeader(String key) {
        return headers.getOrDefault(key, "Not key found");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(method.toString()).append(" ")
                .append(path).append(" ")
                .append(httpVersion).append("\n");


        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
        }
        
        return builder.toString();
    }

    public boolean existsHeader(String key) {
        return headers.containsKey(key);
    }

    public static HttpRequest parseRequest(String rawRequest) throws IllegalArgumentException {
        String[] rawLines = rawRequest.split("\n");
        String[] requestLine = rawLines[0].split(" ");

        if (requestLine.length != 3) {
            throw new IllegalArgumentException("Invalid HTTP request line");
        }

        HttpMethod method = fromString(requestLine[0]);
        String path = requestLine[1];
        String httpVersion = requestLine[2];

        Map<String, String> headers = new HashMap<>();

        for (int i = 1; i < rawLines.length; i++) {
            String[] headerParts = rawLines[i].split(": ");
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            }
        }

        return new HttpRequest(method, path, httpVersion, headers);
    }

    private Date parseDateHeader(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));

        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle the parsing exception as needed
            e.printStackTrace();
            return null;
        }
    }
}