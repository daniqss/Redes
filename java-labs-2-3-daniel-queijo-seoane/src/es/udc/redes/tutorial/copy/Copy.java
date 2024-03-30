package es.udc.redes.tutorial.copy;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copy {

    public static void main(String[] argv) throws IOException {
        if (argv.length != 2) {
            System.err.println("Uso: java Copy <origen> <destino>");
            System.exit(1);
        }

        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader(argv[0]);
            out = new FileWriter(argv[1]);
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }


    }
    
}
