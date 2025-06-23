package ar.edu.et32;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Ejercicio ejercicio = new Ejercicio();
        FileManager fileManager = new FileManager();
        File file1 = new File("archivo1.txt");

        int[] origen1 = new int[5];
        int[] origen2 = {1, 3, 0, 6, 0};

        origen1 = ejercicio.resolution1(file1, origen1, origen2);

        ejercicio.resolucion2(origen1);
        ejercicio.resolucion3(origen1, file1);
    }
}