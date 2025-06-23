package ar.edu.et32;

public class Utils {

    public static boolean tieneAlMenosDosCeros(int[] array) {
        int contadorCeros = 0;

        for (int num : array) {
            if (num == 0) {
                contadorCeros++;
                if (contadorCeros >= 2) {
                    return true;
                }
            }
        }

        return false;
    }
}
