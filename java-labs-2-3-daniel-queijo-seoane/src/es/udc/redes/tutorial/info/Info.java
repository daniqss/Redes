package es.udc.redes.tutorial.info;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Info {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: java Info <fichero>");
            System.exit(1);
        }

        Path path = Paths.get(args[0]);

        try {
            File file = path.toFile();
            String fileName = file.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            Date lastModifiedDate = new Date(file.lastModified());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(lastModifiedDate);

            System.out.println("Nombre: " + fileName);
            System.out.println("Extensión: " + extension);
            System.out.println("Fecha de última modificación " + formattedDate);
            System.out.println("Tamaño: " + file.length() + " B");
            System.out.println("Tipo de fichero: " + extension.toUpperCase());
            System.out.println("Ruta absoluta: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
