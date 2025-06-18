package ar.edu.et32;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    PrintStream ps;

    File file;

    // Constructor
    public FileManager(String ruta) {
        ps = new PrintStream(System.out);
        // "c:\\user\\Redes-04\\omg.txt"
        file = new File(ruta);// crea un nuevo archivo
    }

    public File getFiles() {
        return this.file;
    }

    public void crearFileConPrintStreamEasy(File f) {
        FileOutputStream fos = null;
        PrintStream fs = null;
        try {
            fos = new FileOutputStream(f);
            fs = new PrintStream(fos);
            fs.print("Una linea");
            fs.println("Nueva linea");
            fs.write('d');
            fs.append(("HAIII"));
            fs.flush();
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (fs != null)
                    fs.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void crearFileConPrinter(File f) {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
                }
            }

            fw = new FileWriter(f);
            pw = new PrintWriter(fw);

            pw.print("Una linea");
            pw.println("Nueva linea");
            pw.write('d');// escribe
            pw.append(("HAIII"));// es como un print, pero escribe en la posición del cursor
            pw.flush();// limpia todo lo que hay en el canal
        } catch (FileNotFoundException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.WARNING, null, e);
        } finally {
            try {
                if (pw != null)
                    pw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void crearFileConBuffer(File f, String texto) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fw = new FileWriter(f, false); // true = append
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

    public String LeerFileConBuffer(File f) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            String line = "";
            String texto = "";
            while ((line = br.readLine()) != null) {
                texto = texto.concat(line);
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

    public String leerFileCaracterCaracter(File f) {
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

    public void modificarArchivoTemporalLinea(File archivoOriginal, String buscar, String reemplazar) {
        File archTemp = new File(archivoOriginal.getAbsolutePath() + ".tmp");
        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
                BufferedWriter bw = new BufferedWriter(new FileWriter(archTemp));
        ) {
            String linea = "";
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

    public void modificarArchivoConLinkedList(File archivoOriginal, String buscar, String reemplazar) {
        List<String> textoCompleto = new LinkedList<>();

        //Leer archivo y volcar los datos en memoria VOLATIL (un array)
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String lineas = "";
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
