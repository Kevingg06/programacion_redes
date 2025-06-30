package ar.edu.et32;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    static File file = new File("Inventario.dat");

    public static void crearFileConPrintStreamEasy(File f) {
        FileOutputStream fos = null;
        PrintStream fs = null;
        try {
            fos = new FileOutputStream(f);
            fs = new PrintStream(fos);
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fs != null)
                    fs.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
            }
        }
    }

    public static void agregarDatosAArchivo(String nombreArchivo, String datos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) { // true for append mode
            writer.write(datos);
            writer.newLine();
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "Error al escribir en el archivo '" + nombreArchivo + "'", e);
        }
    }

    public static void crearArchivoSiNoExiste() {
        PrintStream ps = new PrintStream(System.out);
        try {
            if (file.createNewFile()) {
                ps.println("Archivo '{0}' creado exitosamente.");
            } else {
                ps.println("El archivo '{0}' ya existe." + file.getName());
            }
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "Error al crear el archivo '" + file.getName() + "'", e);
        }
    }

    public static void crearFileConBuffer( String texto) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(file); // true = append
            bw = new BufferedWriter(fw);

            bw.append(texto);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fw != null)
                    fw.close();
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
            }
        }

    }

    public static List<String> leerFileLineaALinea(){
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            List<String> texto = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                texto.add(line);
            }
            return texto;
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fr != null)
                    fr.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
                Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
            }
        }

        return new ArrayList<String>();
    }

    public static String leerFileConBuffer() {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            String texto = "";
            while ((line = br.readLine()) != null) {
                texto = texto.concat("\n" + line);
            }

            return texto;
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fr != null)
                    fr.close();
                if (br != null)
                    br.close();
            } catch (IOException e) {
                Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
            }
        }

        return null;
    }

    public static String leerFileCaracterCaracter(File f) {
        FileReader fr = null;

        try {
            fr = new FileReader(f);

            int caracter, EOF = -1;
            String texto = "";
            while ((caracter = fr.read()) != EOF) {

                if (caracter == '\n') {
                    texto = texto.concat("\n");
                } else {
                    texto = texto.concat(String.valueOf(caracter));
                }
            }
            return texto;
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fr != null)
                    fr.close();
            } catch (IOException e) {
                Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
            }
        }
        return null;
    }

    public static void modificarArchivoTemporalLinea(File archivoOriginal, String buscar, String reemplazar) {
        File archTemp = new File(archivoOriginal.getAbsolutePath() + ".tmp");
        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter bw = new BufferedWriter(new FileWriter(archTemp))
        ) {
            String linea;
            String EOF = null;
            while ((linea = br.readLine()) != EOF) {
                //edicion necesaria
                if (linea.contains(buscar)) {
                    linea = linea.replace(buscar, reemplazar);
                }
                bw.write(linea);
                bw.newLine();
            }
            if (!archivoOriginal.delete()) {
                throw new IOException("No se pudo borrar el archivo");
            }

            if (!archTemp.renameTo(archivoOriginal)) {
                throw new IOException("no se pudo renombrar el archivo");
            }

        } catch (Exception e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        }

    }

    public static void modificarArchivoConLinkedList(File archivoOriginal, String buscar, String reemplazar) {
        List<String> textoCompleto = new LinkedList<>();

        //Leer archivo y volcar los datos en memoria VOLATIL (un array)
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String lineas;
            String EOF = null;
            while ((lineas = br.readLine()) != EOF) {
                //puede tener logica para filtrar qué entra al Array o no
                textoCompleto.add(lineas);
            }
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        }

        // Modificar líneas que contengan el texto a buscar
        //for completo linea a linea
        for (int i = 0; i < textoCompleto.size(); i++) {
            //aca logica para modificar lo que necesitemos
            if (textoCompleto.get(i).contains(buscar))
                textoCompleto.set(i, textoCompleto.get(i).replace(buscar, reemplazar));
        }

        for (String linea : textoCompleto) {
            if (linea.contains(buscar))
                textoCompleto.set(
                        textoCompleto.indexOf(linea), //(index)
                        linea.replace(buscar, reemplazar));//(datomodificado)
        }

        // Escribir de nuevo todo el contenido modificado en el archivo original
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoOriginal))) {
            for (String linea : textoCompleto) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        }

    }
}
