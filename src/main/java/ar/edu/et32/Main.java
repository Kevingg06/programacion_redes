package ar.edu.et32;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

    }


    public static void mantenerUltimaLinea() {
        String input;
        FileManager gestor = new FileManager("ultimoDato.txt");
        PrintStream ps = new PrintStream(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ps.print("Ingrese el dato a guardar: ");

        try {
            input = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        File file = gestor.getFiles();
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(input);
            ps.println("El dato **'" + input + "'** se ha guardado en: " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error al crear o escribir en el archivo.", e);
        }
    }

    public static void escribirNumeros() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps = new PrintStream(System.out);
        String input = "";
        List<String> inputs = new ArrayList<>();

        ps.print("Ingrese el dato a guardar (ingrese FIN para terminar): ");

        while (input != "FIN") {
            try {
                input = br.readLine().trim();
                inputs.add(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileManager gestorArchivos = new FileManager("numerosIngresados.txt");

        File file = gestorArchivos.getFiles();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) { // 'true' para append
            for (String entrada : inputs) {

                try {
                    int numero = Integer.parseInt(entrada);
                    bw.write(String.valueOf(numero));
                    bw.newLine();
                    ps.println("Número **'" + numero + "'** guardado.");
                } catch (NumberFormatException e) {
                    ps.println("Entrada **'" + entrada + "'** no es un número válido. Ignorando.");
                }
            }
            ps.println("Todos los números se han guardado en: " + file.getAbsolutePath());
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error al escribir en el archivo.", e);
        }
    }

    public static void escribirPares() {
        String rutaArchivoNumeros = System.getProperty("user.home") + File.separator + "numeros.txt";
        FileManager gestorArchivos = new FileManager(rutaArchivoNumeros);

        PrintStream ps = new PrintStream(System.out);
        File file = gestorArchivos.getFiles();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i <= 1000; i += 2) {
                bw.write(String.valueOf(i));
                bw.newLine();
            }
            ps.println("Archivo **'" + file.getName() + "'** creado con números pares del 0 al 1000 en: " + file.getAbsolutePath());
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error al escribir en el archivo 'numeros.txt'.", e);
        }
    }
}



