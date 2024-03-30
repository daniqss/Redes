package es.udc.redes.webserver;

import es.udc.redes.webserver.httpcode.*;
import es.udc.redes.webserver.utils.ReadFile;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static es.udc.redes.webserver.httpcode.HttpCode.*;

public class HttpResponse {
    final private HttpCode code;
    final private StringBuilder header = new StringBuilder();
    private ArrayList<Byte> body = new ArrayList<>();

    public HttpResponse(HttpRequest request, int code, String contentType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));

        this.code = createHttpCode(code);
        header.append("HTTP/1.0 ").append(this.code.toString()).append("\n")
            .append("Date: ").append(dateFormat.format(new Date())).append("\n")
            .append("Server: Redes HTTP/1.0 Server").append("\n")
            .append("Content-Type: ").append(contentType).append("\n")
            .append("Last-Modified: ").append(ReadFile.getLastModified(request.getPath())).append("\n")
            .append("Connection: close").append("\n");
    }

    public void addHeader(String key, String value) {
        header.append(key).append(": ").append(value).append("\n");
    }

    public void addBody(ArrayList<Byte> body) {
        header.append("Content-Length: ").append(body.toArray().length).append("\n");

        this.body = new ArrayList<>(body);
    }

    public void addBody(String body) {
        header.append("Content-Length: ").append(body.length()).append("\n");

        for (char c : body.toCharArray()) {
            this.body.add((byte) c);
        }
    }

    /**
     * Check if the resource has been modified since the last request.
     *
     * @param request Http request that may contain the If-Modified-Since header.
     * @param filePath Wanted resource path.
     * @return true if the resource has been modified since the last request, false otherwise.
     */
    public static boolean checkLastModified(HttpRequest request, String filePath) {
        if (request.existsHeader("If-Modified-Since")) {
            String lastModified = ReadFile.getLastModified(filePath);
            String ifModifiedSince = request.getHeader("If-Modified-Since");

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));

                Date lastModifiedDate = dateFormat.parse(lastModified);
                Date ifModifiedSinceDate = dateFormat.parse(ifModifiedSince);

                return lastModifiedDate.after(ifModifiedSinceDate);
            } catch (ParseException e) {
                System.err.println("Error parsing date");
                return false;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return header.toString() + "\n" + body.toString();
    }

    public void sendResponse(OutputStream outputStream) throws Exception {
        outputStream.write(header.toString().getBytes());
        outputStream.write("\n".getBytes());
        for (byte b : body) {
            outputStream.write(b);
        }
        outputStream.flush();
    }
}
