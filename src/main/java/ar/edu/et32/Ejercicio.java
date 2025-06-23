package ar.edu.et32;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicio {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    FileManager fileManager = new FileManager();

    public int[] resolution1(File f, int[] origen1, int[] origen2) {

        try {
            for (int i = 0; i < 5; i++) {
                System.out.printf("%s . Ingrese un numero: ", i + 1);
                origen1[i] = Integer.parseInt(br.readLine().trim());
            }

        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(Ejercicio.class.getName()).log(Level.WARNING, null, e);
        }

        if (Utils.tieneAlMenosDosCeros(origen1) && Utils.tieneAlMenosDosCeros(origen2)) {
            fileManager.ingresarArrayComoArchivo(f, origen2);
            System.out.println(Arrays.toString(origen1));
            System.out.println("Archivo origen 2 creado exitosamente");
            return origen1;
        } else {
            System.out.println("Input no valido, faltan ceros bb");
        }

        return null;
    }

    public void resolucion2(int[] nums) {

        File fileResultados = new File("resultados1.txt");
        File fileErrores = new File("error.txt");
        for (int i = 0; i < nums.length - 1; i++) {
            try {
                double result = (double) nums[i] / nums[i + 1] - 3;
                fileManager.guardarResultado(fileResultados, nums[i], nums[i + 1], Double.toString(result));
            } catch (ArithmeticException | NullPointerException e) {
                fileManager.guardarResultado(fileErrores, nums[i], nums[i + 1], e.getMessage());
            }
        }
    }

    public void resolucion3(int[] origen1, File file) {
        int[] origen2 = fileManager.leerArchivoComoArray(file);

        File fileResultados = new File("resultados2.txt");
        File fileErrores = new File("error.txt");
        for (int i = 0; i < origen1.length - 1; i++) {
            try {
                double result = (double) origen1[i] / origen2[i];
                fileManager.guardarResultado(fileResultados, origen1[i], origen2[i], Double.toString(result));
            } catch (ArithmeticException | NullPointerException e) {
                fileManager.guardarResultado(fileErrores, origen1[i], origen2[i], e.getMessage());
            }
        }
    }


}
