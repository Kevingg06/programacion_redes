package ar.edu.et32;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicios {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private static final String FILE_NAME = "Inventario.dat";
    private static final PrintStream ps = new PrintStream(System.out);
    private static final BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        FileManager.crearArchivoSiNoExiste();
        menuInfinito();
    }

    public static void menuInfinito() {
        int opcion = -1;
        while (opcion != 0) {
            ps.println("\n" + BLUE + "--- MENÚ DE INVENTARIO ---" + RESET);
            ps.println(CYAN + "1." + RESET + " Agregar Producto");
            ps.println(CYAN + "2." + RESET + " Mostrar Inventario");
            ps.println(CYAN + "3." + RESET + " Eliminar Producto");
            ps.println(CYAN + "4." + RESET + " Editar Producto");
            ps.println(CYAN + "0." + RESET + " Salir");
            ps.print(YELLOW + "Seleccione una opción: " + RESET);

            String input = leerTextoConsola();
            NumberType tipo = identificarTipoNumero(input);

            if (tipo == NumberType.ENTERO) {
                opcion = convertToInt(input);
                switch (opcion) {
                    case 1:
                        agregarProducto();
                        break;
                    case 2:
                        mostrarInventario();
                        break;
                    case 3:
                        eliminarProducto();
                        break;
                    case 4:
                        editarProducto();
                        break;
                    case 0:
                        ps.println(GREEN + "Saliendo del programa. ¡Hasta pronto!" + RESET);
                        break;
                    default:
                        ps.println(RED + "Opción no válida. Por favor, intente de nuevo." + RESET);
                }
            } else {
                ps.println(RED + "Entrada inválida. Por favor, ingrese un número." + RESET);
                opcion = -1;
            }
        }

        try {
            brConsole.close();
        } catch (IOException e) {
            Logger.getLogger(Ejercicios.class.getName()).log(Level.SEVERE, "Error closing console input", e);
        }
    }

    public static String leerTextoConsola() {
        try {
            return brConsole.readLine();
        } catch (IOException e) {
            Logger.getLogger(Ejercicios.class.getName()).log(Level.SEVERE, "Error reading from console", e);
            return "";
        }
    }

    public static NumberType identificarTipoNumero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return NumberType.NAN;
        }
        try {
            convertToInt(texto);
            return NumberType.ENTERO;
        } catch (NumberFormatException e) {
            try {
                convertToFloat(texto);
                return NumberType.NUM_CON_COMA;
            } catch (NumberFormatException ex) {
                return NumberType.NAN;
            }
        }
    }

    public static int convertToInt(String texto) {
        return Integer.parseInt(texto.trim());
    }

    public static float convertToFloat(String texto) {
        return Float.parseFloat(texto.trim());
    }

    public static void agregarProducto() {
        ps.println("\n" + BLUE + "--- AGREGAR PRODUCTO ---" + RESET);
        String nombreProducto;
        double precioCompra;
        double precioVenta;
        int stock;

        ps.print(GREEN + "Ingrese Nombre del Producto: " + RESET);
        nombreProducto = leerTextoConsola().trim();
        if (nombreProducto.isEmpty()) {
            ps.println(RED + "El nombre del producto no puede estar vacío." + RESET);
        }

        precioCompra = obtenerNumeroValido("   " + YELLOW + "Ingrese Precio de Compra: " + RESET, NumberType.NUM_CON_COMA);

        precioVenta = obtenerNumeroValido("   " + YELLOW + "Ingrese Precio de Venta: " + RESET, NumberType.NUM_CON_COMA);

        stock = (int) obtenerNumeroValido("   " + YELLOW + "Ingrese Stock: " + RESET, NumberType.ENTERO);

        String datosProducto = String.format(Locale.US, "%s;%.2f;%.2f;%d", nombreProducto, precioCompra, precioVenta, stock);
        FileManager.agregarDatosAArchivo(FILE_NAME, datosProducto);
        ps.println(GREEN + "Producto agregado exitosamente." + RESET);
    }

    private static float obtenerNumeroValido(String prompt, NumberType expectedType) {
        String input;
        NumberType tipo;
        float valor = 0;
        boolean valido = false;

        while (!valido) {
            ps.print(prompt);
            input = leerTextoConsola();
            tipo = identificarTipoNumero(input);

            if ((expectedType == NumberType.ENTERO && tipo == NumberType.ENTERO) ||
                    (expectedType == NumberType.NUM_CON_COMA && (tipo == NumberType.ENTERO || tipo == NumberType.NUM_CON_COMA))) {
                try {
                    valor = convertToFloat(input);
                    if (valor < 0) {
                        ps.println(RED + "El valor no puede ser negativo. Intente de nuevo." + RESET);
                    } else {
                        valido = true;
                    }
                } catch (NumberFormatException e) {
                    ps.println(RED + "Error al convertir el número. Intente de nuevo." + RESET);
                }
            } else {
                ps.println(RED + "Entrada inválida. Se esperaba un " +
                        (expectedType == NumberType.ENTERO ? "número entero." : "número (entero o con coma).") +
                        " Intente de nuevo." + RESET);
            }
        }
        return valor;
    }

    public static void mostrarInventario() {
        ps.println("\n" + BLUE + "--- INVENTARIO ACTUAL ---" + RESET);
        List<String> lineas = FileManager.leerFileLineaALinea();

        if (lineas.isEmpty()){
            ps.println(RED + "Inventario vacío" + RESET);
            return;
        }

        ps.printf("%-4s %-25s %-15s %-15s %-10s%n",
                CYAN + "ID" + RESET,
                CYAN + "   Producto" + RESET,
                CYAN + "           P. Compra" + RESET,
                CYAN + "           P. Venta" + RESET,
                CYAN + "           Stock" + RESET);
        ps.println(CYAN + "-------------------------------------------------------------------------------" + RESET);

        int id = 1;
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes.length == 4) {
                try {
                    ps.printf("%-4d %-25s %-15.2f %-15.2f %-10d%n",
                            id++,
                            partes[0],
                            Float.parseFloat(partes[1]),
                            Float.parseFloat(partes[2]),
                            Integer.parseInt(partes[3]));
                } catch (NumberFormatException e) {
                    ps.println(RED + "Error en formato de datos para línea: " + linea + RESET);
                }
            }
        }
        ps.println(CYAN + "-------------------------------------------------------------------------------" + RESET);
    }

    public static void eliminarProducto() {
        ps.println("\n" + BLUE + "--- ELIMINAR PRODUCTO. ELIJA LA LINEA DEL PRODUCTO A BORRAR---" + RESET);
        mostrarInventario();

        List<String> lineas = FileManager.leerFileLineaALinea();
        if (lineas.isEmpty()) {
            ps.println(YELLOW + "No hay productos para eliminar." + RESET);
            return;
        }

        int idEliminar = (int) obtenerNumeroValido("   " + YELLOW + "Ingrese el ID del producto a eliminar: " + RESET, NumberType.ENTERO);

        if (idEliminar <= 0 || idEliminar > lineas.size()) {
            ps.println(RED + "ID de producto inválido." + RESET);
            return;
        }

        List<String> nuevasLineas = new ArrayList<>();
        for (int i = 0; i < lineas.size(); i++) {
            if ((i + 1) != idEliminar) {
                nuevasLineas.add(lineas.get(i));
            }
        }

        String texto = "";
        for (String linea : nuevasLineas) {
            texto = texto.concat(linea + "\n");
            FileManager.crearFileConBuffer(texto);
        }

        ps.println(GREEN + "Producto eliminado exitosamente." + RESET);
    }

    public static void editarProducto() {
        mostrarInventario();

        ps.println("\n" + BLUE + "--- EDITAR PRODUCTO ---" + RESET);

        List<String> lineas = FileManager.leerFileLineaALinea();
        if (lineas.isEmpty()) {
            ps.println(YELLOW + "No hay productos para editar." + RESET);
            return;
        }

        int idEditar = (int) obtenerNumeroValido("   " + YELLOW + "Ingrese el ID del producto a editar: " + RESET, NumberType.ENTERO);

        if (idEditar <= 0 || idEditar > lineas.size()) {
            ps.println(RED + "ID de producto inválido." + RESET);
            return;
        }

        String lineaActual = lineas.get(idEditar - 1);
        String[] partes = lineaActual.split(";");

        if (partes.length != 4) {
            ps.println(RED + "Error: Formato de línea inválido en el archivo para el ID " + idEditar + RESET);
            return;
        }

        String nombreActual = partes[0];
        float precioCompraActual = Float.parseFloat(partes[1]);
        float precioVentaActual = Float.parseFloat(partes[2]);
        int stockActual = Integer.parseInt(partes[3]);

        ps.println(YELLOW + "\nEditando producto ID: " + idEditar + RESET);
        ps.println(YELLOW + "Nombre actual: " + nombreActual + RESET);
        ps.println(YELLOW + "Precio de compra actual: " + precioCompraActual + RESET);
        ps.println(YELLOW + "Precio de venta actual: " + precioVentaActual + RESET);
        ps.println(YELLOW + "Stock actual: " + stockActual + RESET);

        ps.println(CYAN + "\nIngrese los nuevos valores (deje vacío para mantener el actual):" + RESET);

        String nuevoNombre;
        do {
            ps.print("   " + YELLOW + "Nuevo Nombre del Producto (" + nombreActual + "): " + RESET);
            nuevoNombre = leerTextoConsola().trim();
            if (nuevoNombre.isEmpty()) {
                nuevoNombre = nombreActual;
            }
            if (nuevoNombre.isEmpty()) {
                ps.println(RED + "El nombre del producto no puede quedar vacío. Por favor, ingrese un nombre." + RESET);
            }
        } while (nuevoNombre.isEmpty());


        float nuevoPrecioCompra = obtenerNumeroOpcion("   " + YELLOW + "Nuevo Precio de Compra (" + precioCompraActual + "): " + RESET, NumberType.NUM_CON_COMA, precioCompraActual);
        float nuevoPrecioVenta = obtenerNumeroOpcion("   " + YELLOW + "Nuevo Precio de Venta (" + precioVentaActual + "): " + RESET, NumberType.NUM_CON_COMA, precioVentaActual);
        int nuevoStock = (int) obtenerNumeroOpcion("   " + YELLOW + "Nuevo Stock (" + stockActual + "): " + RESET, NumberType.ENTERO, stockActual);

        String lineaModificada = String.format(Locale.US, "%s;%.2f;%.2f;%d", nuevoNombre, nuevoPrecioCompra, nuevoPrecioVenta, nuevoStock);
        lineas.set(idEditar - 1, lineaModificada);

        String texto = "";
        for (String linea : lineas) {
            texto = texto.concat(linea + "\n");
        }
        FileManager.crearFileConBuffer(texto);
        ps.println(GREEN + "Producto editado exitosamente." + RESET);
    }

    private static float obtenerNumeroOpcion(String prompt, NumberType expectedType, float currentValue) {
        String input;
        NumberType tipo;
        float valor = currentValue;
        boolean valido = false;

        while (!valido) {
            ps.print(prompt);
            input = leerTextoConsola().trim();

            if (input.isEmpty()) {
                valido = true;
            } else {
                tipo = identificarTipoNumero(input);
                if ((expectedType == NumberType.ENTERO && tipo == NumberType.ENTERO) ||
                        (expectedType == NumberType.NUM_CON_COMA && (tipo == NumberType.ENTERO || tipo == NumberType.NUM_CON_COMA))) {
                    try {
                        valor = convertToFloat(input);
                        if (valor < 0) {
                            ps.println(RED + "El valor no puede ser negativo. Intente de nuevo." + RESET);
                        } else {
                            valido = true;
                        }
                    } catch (NumberFormatException e) {
                        ps.println(RED + "Error al convertir el número. Intente de nuevo." + RESET);
                    }
                } else {
                    ps.println(RED + "Entrada inválida. Se esperaba un " +
                            (expectedType == NumberType.ENTERO ? "número entero." : "número (entero o con coma).") +
                            " Deje vacío para mantener el valor actual. Intente de nuevo." + RESET);
                }
            }
        }
        return valor;
    }
}
