package ar.edu.et32;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ColegioManager { // Renamed to avoid conflict if you have a class Colegio
    private Map<String, Integer> alumnosPorNacionalidad;
    private static final PrintStream PS = PrintStream(System.out);

    public ColegioManager() {
        this.alumnosPorNacionalidad = new HashMap<>();
    }

    public void addAlumno(String nacionalidad) {
        alumnosPorNacionalidad.put(nacionalidad, alumnosPorNacionalidad.getOrDefault(nacionalidad, 0) + 1);
        PS.println("Alumno de nacionalidad **'" + nacionalidad + "'** añadido.");
    }

    public void showAll() {
        PS.println("\n--- Alumnos por Nacionalidad ---");
        if (alumnosPorNacionalidad.isEmpty()) {
            PS.println("No hay alumnos registrados.");
            return;
        }

        for (Map.Entry<String, Integer> entry : alumnosPorNacionalidad.entrySet()) {
            PS.println("Nacionalidad: **" + entry.getKey() + "** - Alumnos: **" + entry.getValue() + "**");
        }
        PS.println("-------------------------------");
    }

    public void showNacionalidad(String nacionalidad) {
        PS.println("\n--- Consulta de Nacionalidad: " + nacionalidad + " ---");
        Integer count = alumnosPorNacionalidad.get(nacionalidad);
        if (count != null) {
            PS.println("Nacionalidad: **" + nacionalidad + "** - Alumnos: **" + count + "**");
        } else {
            PS.println("No hay alumnos registrados de nacionalidad **'" + nacionalidad + "'**.");
        }
        PS.println("-------------------------------------");
    }

    public void cuantos() {
        PS.println("\n--- Resumen de Nacionalidades ---");
        PS.println("Número de nacionalidades diferentes: **" + alumnosPorNacionalidad.size() + "**");
        PS.println("---------------------------------");
    }

    public void borra() {
        alumnosPorNacionalidad.clear();
        PS.println("Todos los datos de alumnos han sido eliminados.");
    }
}

