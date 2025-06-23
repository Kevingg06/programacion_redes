package ar.edu.et32;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    public int[] leerArchivoComoArray(File f) {
        int[] datos = new int[5];
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String linea;
            int i = 0;
            while ((linea = reader.readLine()) != null && i < datos.length) {
                datos[i] = Integer.parseInt(linea.trim());
                i++;
            }
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(Ejercicio.class.getName()).log(Level.WARNING, null, e);
        }
        return datos;
    }

    public void ingresarArrayComoArchivo(File f, int[] input) {
        FileOutputStream fos = null;
        PrintStream fs = null;
        try {
            fos = new FileOutputStream(f);
            fs = new PrintStream(fos);

            for (int i = 0; i < 5; i++) {
                fs.println(Integer.toString(input[i]));
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(Ejercicio.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fs != null)
                    fs.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                Logger.getLogger(Ejercicio.class.getName()).log(Level.WARNING, null, e);
            }
        }
    }

    public void guardarResultado(File f, int num1, int num2, String msg) {
        FileOutputStream fos = null;
        PrintStream fs = null;
        try {
            fos = new FileOutputStream(f, true);
            fs = new PrintStream(fos);

            fs.println( num1 + "/" + num2 + "=" + msg);

        } catch (FileNotFoundException e) {
            Logger.getLogger(Ejercicio.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fs != null)
                    fs.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                Logger.getLogger(Ejercicio.class.getName()).log(Level.WARNING, null, e);
            }
        }
    }
}
