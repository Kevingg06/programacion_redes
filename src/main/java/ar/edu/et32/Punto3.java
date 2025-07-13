package ar.edu.et32;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Punto3 {
    private static final String RUTA_ARCHIVO_CLIMA = System.getProperty("user.home") + File.separator + "datos_clima.txt";
    private static final FileManager GESTOR_ARCHIVOS = new FileManager(RUTA_ARCHIVO_CLIMA);
    private static final PrintStream PS = new PrintStream(System.out);

    public static void main(String[] args) {
        mantenerUltimaLinea();
        escribirNumeros();
        escribirPares();
        leerNumeros();
        borrarMultiplos3();
        guardarPrimos();
        modificarCaracter();
        modificarLorem();
        climaMenuOption();
    }


    public static void mantenerUltimaLinea() {
        String input = "";
        FileManager gestor = new FileManager("ultimoDato.txt");
        PrintStream ps = new PrintStream(System.out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ps.print("Ingrese el dato a guardar: ");

        try {
            input = br.readLine();
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
        }


        File file = gestor.getFiles();
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(input);
            ps.println("El dato **'" + input + "'** se ha guardado en: " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al crear o escribir en el archivo.", e);
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
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al escribir en el archivo.", e);
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
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al escribir en el archivo 'numeros.txt'.", e);
        }
    }

    public static void leerNumeros() {
        // Same path as in the previous exercise
        String rutaArchivoNumeros = System.getProperty("user.home") + File.separator + "numeros.txt";
        FileManager gestorArchivos = new FileManager(rutaArchivoNumeros);

        PrintStream ps = new PrintStream(System.out);
        File file = gestorArchivos.getFiles();

        if (file.exists()) {
            String contenido = gestorArchivos.LeerFileConBuffer(file);
            if (contenido != null) {
                ps.println("--- Contenido de 'numeros.txt' ---");
                ps.println(contenido);
                ps.println("---------------------------------");
            } else {
                ps.println("No se pudo leer el contenido de 'numeros.txt'.");
            }
        } else {
            ps.println("El archivo 'numeros.txt' no existe en la ruta: " + file.getAbsolutePath());
        }
    }

    public static void borrarMultiplos3() {
        String rutaArchivoNumeros = System.getProperty("user.home") + File.separator + "numeros.txt";
        FileManager gestorArchivos = new FileManager(rutaArchivoNumeros);

        PrintStream ps = new PrintStream(System.out);
        File file = gestorArchivos.getFiles();

        if (file.exists()) {

            File archTemp = new File(file.getAbsolutePath() + ".tmp");

            try (BufferedReader br = new BufferedReader(new FileReader(file));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(archTemp))) {

                String linea;

                while ((linea = br.readLine()) != null) {
                    try {
                        int num = Integer.parseInt(linea.trim());
                        if (num % 3 != 0) {
                            bw.write(linea);
                            bw.newLine();
                        }
                    } catch (NumberFormatException e) {
                        Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
                    }
                }

            } catch (IOException e) {
                Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al procesar el archivo 'numeros.txt'.", e);
            }

            if (file.delete()) {
                if (!archTemp.renameTo(file)) {
                    ps.println("Error: No se pudo renombrar el archivo temporal.");
                } else {
                    ps.println("Múltiplos de 3 eliminados de **'numeros.txt'**.");
                }
            } else {
                ps.println("Error: No se pudo borrar el archivo original.");
            }

            String contenidoModificado = gestorArchivos.LeerFileConBuffer(file);
            if (contenidoModificado != null) {
                ps.println("--- Contenido de 'numeros.txt' después de la eliminación ---");
                ps.println(contenidoModificado);
                ps.println("----------------------------------------------------------");
            }

        } else {
            ps.println("El archivo 'numeros.txt' no existe en la ruta: " + file.getAbsolutePath());
        }
    }

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void guardarPrimos() {
        String rutaArchivoNumeros = System.getProperty("user.home") + File.separator + "numeros.txt";

        String rutaArchivoPrimos = System.getProperty("user.home") + File.separator + "data" + File.separator + "primos.dat";

        File dirData = new File(System.getProperty("user.home") + File.separator + "data");
        if (!dirData.exists()) {
            dirData.mkdir();
        }

        FileManager gestorArchivosNumeros = new FileManager(rutaArchivoNumeros);
        FileManager gestorArchivosPrimos = new FileManager(rutaArchivoPrimos);

        PrintStream ps = new PrintStream(System.out);
        File fileNumeros = gestorArchivosNumeros.getFiles();
        File filePrimos = gestorArchivosPrimos.getFiles();

        if (fileNumeros.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileNumeros));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(filePrimos))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    try {
                        int num = Integer.parseInt(linea.trim());
                        if (esPrimo(num)) {
                            bw.write(String.valueOf(num));
                            bw.newLine();
                        }
                    } catch (NumberFormatException e) {
                        Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                ps.println("Números primos extraídos a: **" + filePrimos.getAbsolutePath() + "**");

            } catch (IOException e) {
                Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al procesar archivos para primos.dat.", e);
            }
        } else {
            ps.println("El archivo 'numeros.txt' no existe en la ruta: " + fileNumeros.getAbsolutePath());
        }
    }

    public static void modificarCaracter() {
        String rutaArchivoCaracteres = System.getProperty("user.home") + File.separator + "caracteres.dat";
        FileManager gestorArchivos = new FileManager(rutaArchivoCaracteres);
        BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps = new PrintStream(System.out);
        File file = gestorArchivos.getFiles();
        String input = "";

        List<String> words = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            try {
                ps.print("\n Ingrese una palabra: ");
                input = brConsole.readLine().trim();
                words.add(input);
            } catch (IOException e) {
                Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        ps.println("Simulando la carga de 10 palabras que contienen la letra 'ñ':");
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (String palabra : words) {
                pw.println(palabra);
                ps.println("Simulando que se ingresó: **" + palabra + "**");
            }
            ps.println("Palabras guardadas en: " + file.getAbsolutePath());
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al escribir en 'caracteres.dat'.", e);
        }

        ps.println("\n--- Fichero original: ---");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                ps.println(linea);
            }
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al leer 'caracteres.dat'.", e);
        }

        gestorArchivos.modificarArchivoConLinkedList(file, "ñ", "nie-nio");
        gestorArchivos.modificarArchivoConLinkedList(file, "Ñ", "Nie-nio");

        ps.println("\n--- Fichero arreglado: ---");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                ps.println(linea);
            }
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al leer el archivo 'caracteres.dat' modificado.", e);
        }
    }

    public static void modificarLorem() {
        String rutaArchivoHTML = "index.html";
        FileManager gestorArchivos = new FileManager(rutaArchivoHTML);

        PrintStream ps = new PrintStream(System.out);
        File htmlFile = gestorArchivos.getFiles();

        if (htmlFile.exists()) {
            ps.println("Intentando borrar 'lorem ipsum' del archivo: " + htmlFile.getAbsolutePath());

            String loremToReplace = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";


            gestorArchivos.modificarArchivoConLinkedList(htmlFile, loremToReplace, "");
            ps.println("**'Lorem ipsum'** eliminado del archivo HTML.");
        } else {
            ps.println("El archivo HTML **'" + htmlFile.getName() + "'** no existe en la ruta: " + htmlFile.getAbsolutePath());
            ps.println("Por favor, cree el archivo y agregue contenido 'lorem ipsum' manualmente para probar este ejercicio.");
        }
    }

    public static void climaMenuOption() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String option = "consor.log";

        while (option != "0") {
            try {
                option = br.readLine().trim();
            } catch (IOException e) {
                Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
            }

            switch (option) {
                case "1":
                    PS.println("\n--- Menú de Gestión de Clima ---");
                    cargarDatos();
                    break;
                case "2":
                    PS.println("\n--- Menú de Gestión de Clima ---");
                    mostrarTodosLosDatosClima();
                    break;
                case "3":
                    PS.println("\n--- Menú de Gestión de Clima ---");
                    borrarRegistro();
                    break;
                case "0":
                    PS.println("\n--- Menú de Gestión de Clima ---");
                    PS.println("Saliendo del programa.");
                    break;
                default:
                    PS.println("\n--- Menú de Gestión de Clima ---");
                    PS.println("Simulando opción: **" + option + "**. Opción no válida. Intente de nuevo.");
            }
            PS.println();
        }
    }

    private static void cargarDatos() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PS.print("Ingrese la fecha: ");
        String fecha = "";

        PS.print("\nIngrese la temperatura: ");
        String temperatura = "";

        PS.print("\nIngrese la humedad: ");
        String humedad = "";

        PS.print("\nIngrese la descripcion: ");
        String descripcion = "";

        try {
            fecha = br.readLine().trim();
            temperatura = br.readLine().trim();
            humedad = br.readLine().trim();
            descripcion = br.readLine().trim();
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
        }

        String registro = String.format("%s,%s,%s,%s", fecha, temperatura, humedad, descripcion);

        File file = GESTOR_ARCHIVOS.getFiles();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) { // true for append
            bw.write(registro);
            bw.newLine();
            PS.println("Registro de clima guardado: **" + registro + "**");
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al cargar datos de clima.", e);
        }
    }

    private static void mostrarTodosLosDatosClima() {
        File file = GESTOR_ARCHIVOS.getFiles();
        if (!file.exists() || file.length() == 0) {
            PS.println("No hay datos de clima para mostrar.");
            return;
        }

        PS.println("--- Datos de Clima ---");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                PS.println(linea);
            }
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al leer datos de clima.", e);
        }
        PS.println("----------------------");
    }

    private static void borrarRegistro() {
        BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
        String fechaBorrar = "";

        PS.println("Ingrese la fecha a borrar: ");
        try {
            fechaBorrar = brConsole.readLine().trim();
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al leer datos de clima.", e);
        }

        File archivoOriginal = GESTOR_ARCHIVOS.getFiles();
        List<String> lineas = new LinkedList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(fechaBorrar + ",")) {
                    encontrado = true;
                } else {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al leer el archivo para borrar.", e);
            return;
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoOriginal, false))) { // false for overwrite
                for (String linea : lineas) {
                    bw.write(linea);
                    bw.newLine();
                }
                PS.println("Registro para la fecha **'" + fechaBorrar + "'** borrado exitosamente.");
            } catch (IOException e) {
                Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, "Error al reescribir el archivo después de borrar.", e);
            }
        } else {
            PS.println("No se encontró ningún registro para la fecha **'" + fechaBorrar + "'**.");
        }
    }

}



