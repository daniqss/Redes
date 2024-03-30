package es.udc.redes.webserver.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReadFile {
    public static String readFile(String filePath) {
        StringBuilder documentContent = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            documentContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                documentContent.append(line).append("\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return documentContent.toString();
    }

    public static ArrayList<Byte> readFileBytes(String filePath) {
        ArrayList<Byte> documentContent = new ArrayList<>();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            int byteRead;

            while ((byteRead = bis.read()) != -1) {
                documentContent.add((byte) byteRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return documentContent;
    }

    public static String getLastModified(String filePath) {
        try {
            File file = new File(filePath);
            Date lastModifiedDate = new Date(file.lastModified());
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));

            return dateFormat.format(lastModifiedDate);
        }
        catch (Exception e) {
            return "01/01/1900 00:00:00";
        }
    }
}
