package ar.edu.et32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Punto4 {

    private static final PrintStream PS = new PrintStream(System.out);

    public static void main(String[] args){
        analisisValoresNumericos();
        gestionarColegio();
        manipularDiasSemana();
        manipularJugadores();
        generarBolasDosColores();
    }

    public static void analisisValoresNumericos() {

        List<Integer> valores = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = "";

        PS.println("\n--- Ejercicio 1: Análisis de Valores Numéricos ---");
        PS.println("Ingrese valores numéricos enteros. Ingrese '-99' para terminar.");

        while (true) {
            PS.print("Ingrese un número: ");
            try {
                inputLine = br.readLine();
            } catch (IOException e) {
                Logger.getLogger(Punto3.class.getName()).log(Level.SEVERE, null, e);
            }

            if (inputLine == null || inputLine.trim().equals("-99")) {
                PS.println("Entrada '-99' detectada. Terminando lectura.");
                break;
            }

            try {
                int valor = Integer.parseInt(inputLine.trim());
                valores.add(valor);
            } catch (NumberFormatException e) {
                PS.println("Entrada inválida: **'" + inputLine + "'**. Por favor, ingrese un número entero.");
            }
        }
        mostrarResultadosAnalisis(valores);
    }


    private static int calcularSuma(List<Integer> valores) {
        int suma = 0;
        for (int valor : valores) {
            suma += valor;
        }
        return suma;
    }

    private static double calcularMedia(List<Integer> valores) {
        if (valores.isEmpty()) {
            return 0.0;
        }
        return (double) calcularSuma(valores) / valores.size();
    }

    private static void mostrarResultadosAnalisis(List<Integer> valores) {
        PS.println("\n--- Resultados del Análisis ---");
        PS.println("Número de valores leídos: **" + valores.size() + "**");
        PS.println("Suma de los valores: **" + calcularSuma(valores) + "**");
        PS.printf("Media de los valores: **%.2f**\n", calcularMedia(valores));

        if (!valores.isEmpty()) {
            double media = calcularMedia(valores);
            int contadorMayoresMedia = 0;
            PS.print("Valores leídos: ");
            for (int valor : valores) {
                PS.print(valor + " ");
                if (valor > media) {
                    contadorMayoresMedia++;
                }
            }
            PS.println("\nCantidad de valores mayores que la media: **" + contadorMayoresMedia + "**");
        } else {
            PS.println("No se ingresaron valores para analizar.");
        }
        PS.println("------------------------------");
    }

    public static void gestionarColegio() {
        PS.println("\n--- Ejercicio 2: Gestión de Alumnos por Nacionalidad ---");
        ColegioManager miColegio = new ColegioManager();

        miColegio.addAlumno("Argentina");
        miColegio.addAlumno("Española");
        miColegio.addAlumno("Argentina");
        miColegio.addAlumno("Francesa");
        miColegio.addAlumno("Española");
        miColegio.addAlumno("Argentina");

        miColegio.showAll();
        miColegio.showNacionalidad("Argentina");
        miColegio.showNacionalidad("Italiana");
        miColegio.cuantos();

        miColegio.borra();
        miColegio.showAll();
    }

    public static void manipularDiasSemana() {
        PS.println("\n--- Ejercicio 3: Manipulación de Días de la Semana ---");


        List<String> listDias = new ArrayList<>(Arrays.asList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"));
        mostrarLista("Lista original de días", listDias);

        listDias.add(4, "Juernes");
        mostrarLista("Lista con 'Juernes' insertado", listDias);

        List<String> listaDos = crearCopiaLista(listDias);
        mostrarLista("Copia de la lista (listaDos)", listaDos);

        anadirContenidoALista(listDias, listaDos);
        mostrarLista("listDias después de añadir listaDos", listDias);


        mostrarElementosPorPosicion(listDias, 3, 4);

        mostrarPrimerYUltimoElemento(listDias);


        eliminarElementoYComprobar(listDias, "Juernes");
        mostrarLista("Lista después de eliminar 'Juernes' (todas las ocurrencias)", listDias);


        recorrerConIterador(listDias);

        buscarElementoIgnorarCase(listDias, "Lunes");

        ordenarLista(listDias);
        mostrarLista("Lista ordenada", listDias);
    }

    private static void mostrarLista(String title, List<String> list) {
        PS.println(title + ": **" + list + "**");
    }

    private static List<String> crearCopiaLista(List<String> sourceList) {
        return new ArrayList<>(sourceList);
    }

    private static void anadirContenidoALista(List<String> destinationList, List<String> sourceList) {
        destinationList.addAll(sourceList);
    }

    private static void mostrarElementosPorPosicion(List<String> list, int pos1, int pos2) {
        if (list.size() > pos1 && list.size() > pos2) {
            PS.println("Contenido de la posición **" + pos1 + "**: **" + list.get(pos1) + "**");
            PS.println("Contenido de la posición **" + pos2 + "**: **" + list.get(pos2) + "**");
        } else {
            PS.println("Una o ambas posiciones (" + pos1 + ", " + pos2 + ") están fuera de los límites de la lista.");
        }
    }

    private static void mostrarPrimerYUltimoElemento(List<String> list) {
        if (!list.isEmpty()) {
            PS.println("Primer elemento: **" + list.get(0) + "**");
            PS.println("Último elemento: **" + list.get(list.size() - 1) + "**");
        } else {
            PS.println("La lista está vacía, no hay primer ni último elemento.");
        }
    }

    private static void eliminarElementoYComprobar(List<String> list, String element) {
        boolean removedOnce = list.remove(element);
        PS.println("¿Se eliminó '" + element + "' (primera ocurrencia)? **" + removedOnce + "**");

        while(list.remove(element));
    }

    private static void recorrerConIterador(List<String> list) {
        PS.println("Recorrido con Iterador:");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            PS.println(it.next());
        }
    }

    private static void buscarElementoIgnorarCase(List<String> list, String elementToFind) {
        boolean encontrado = false;
        for (String item : list) {
            if (item.equalsIgnoreCase(elementToFind)) {
                encontrado = true;
                break;
            }
        }
        PS.println("¿Existe '" + elementToFind + "' (ignorando mayúsculas/minúsculas)? **" + encontrado + "**");
    }

    private static void ordenarLista(List<String> list) {
        Collections.sort(list);
    }

    public static void manipularJugadores() {
        PS.println("\n--- Ejercicio 4: Manipulación de Jugadores con Conjuntos ---");

        Set<String> jugadores = crearConjuntoJugadores("Jordi Alba", "Pique", "Busquets", "Iniesta", "Messi");
        mostrarConjunto("Jugadores del FC Barcelona", jugadores);

        iterarYMostrarJugadores(jugadores);

        comprobarExistenciaJugador(jugadores, "Neymar JR");

        Set<String> jugadores2 = crearConjuntoJugadores("Pique", "Busquets");
        mostrarConjunto("Jugadores del conjunto 2", jugadores2);

        comprobarSubconjunto(jugadores, jugadores2);

        realizarUnionConjuntos(jugadores, jugadores2);

        intentarAnadirDuplicado(jugadores, "Pique");
        mostrarConjunto("Jugadores (después de intentar añadir 'Pique' de nuevo)", jugadores);
    }

    private static Set<String> crearConjuntoJugadores(String... nombres) {
        return new HashSet<>(Arrays.asList(nombres));
    }

    private static void mostrarConjunto(String title, Set<String> set) {
        PS.println(title + ": **" + set + "**");
    }

    private static void iterarYMostrarJugadores(Set<String> jugadoresSet) {
        PS.println("\nIterando sobre los jugadores:");
        Iterator<String> it = jugadoresSet.iterator();
        while (it.hasNext()) {
            PS.println(it.next());
        }
    }

    private static void comprobarExistenciaJugador(Set<String> jugadoresSet, String jugadorBuscado) {
        if (jugadoresSet.contains(jugadorBuscado)) {
            PS.println("El jugador **'" + jugadorBuscado + "'** existe en el conjunto.");
        } else {
            PS.println("El jugador **'" + jugadorBuscado + "'** NO existe en el conjunto.");
        }
    }

    private static void comprobarSubconjunto(Set<String> mainSet, Set<String> subSet) {
        if (mainSet.containsAll(subSet)) {
            PS.println("Todos los elementos de **" + subSet + "** existen en **" + mainSet + "**.");
        } else {
            PS.println("No todos los elementos de **" + subSet + "** existen en **" + mainSet + "**.");
        }
    }

    private static void realizarUnionConjuntos(Set<String> set1, Set<String> set2) {
        Set<String> unionConjuntos = new HashSet<>(set1);
        unionConjuntos.addAll(set2);
        PS.println("Unión de " + set1 + " y " + set2 + ": **" + unionConjuntos + "**");
    }

    private static void intentarAnadirDuplicado(Set<String> set, String element) {
        boolean added = set.add(element);
        PS.println("Intentando añadir **'" + element + "'** de nuevo a la primera colección: **" + added + "**");
    }

    public static void generarBolasDosColores() {
        PS.println("\n--- Ejercicio 5: Reglas de Bolas de Dos Colores ---");
        generarYMostrarNumerosBolas();
    }

    private static void generarYMostrarNumerosBolas() {
        PS.println("Generando números aleatorios para la bola de dos colores:");

        Set<Integer> bolasRojas = generarBolasRojas(6, 33);
        mostrarBolasRojas(bolasRojas);

        int bolaAzul = generarBolaAzul(16);
        mostrarBolaAzul(bolaAzul);
    }

    private static Set<Integer> generarBolasRojas(int count, int maxRedBallValue) {
        Set<Integer> bolasRojas = new HashSet<>();
        Random random = new Random();
        while (bolasRojas.size() < count) {
            bolasRojas.add(random.nextInt(maxRedBallValue) + 1);
        }
        return bolasRojas;
    }

    private static void mostrarBolasRojas(Set<Integer> bolasRojasSet) {
        List<Integer> bolasRojasList = new ArrayList<>(bolasRojasSet);
        Collections.sort(bolasRojasList);

        PS.print("Bolas Rojas: ");
        for (int i = 0; i < bolasRojasList.size(); i++) {
            PS.print(bolasRojasList.get(i));
            if (i < bolasRojasList.size() - 1) {
                PS.print(", ");
            }
        }
        PS.println();
    }

    private static int generarBolaAzul(int maxBlueBallValue) {
        Random random = new Random();
        return random.nextInt(maxBlueBallValue) + 1;
    }

    private static void mostrarBolaAzul(int bolaAzul) {
        PS.println("Bola Azul: **" + bolaAzul + "**");
    }
}
