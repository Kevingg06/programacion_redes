package Colecciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class colecciones {

	public static void main(String[] args) {
		// vector
		int[] vector = new int[5];

		// Crear una Lista
		ArrayList<String> nombres = new ArrayList<>();// clasica
		ArrayList comidas = new ArrayList<>();
		List<Integer> numeros = new ArrayList<>();

		LinkedList<Integer> otraLista = new LinkedList<>();
		List<String> productos;

		nombres.add("");
		nombres.set(4, "Pedro");
		nombres.addAll(comidas);
		nombres.clear();
		nombres.clone();
		nombres.contains("Juan");
		nombres.containsAll(comidas);
		nombres.get(2);
		nombres.indexOf("Juan");
		nombres.isEmpty();
		nombres.remove(5);
		nombres.remove("Juan");
		nombres.removeAll(comidas);
		nombres.size();
		nombres.sort(null);
		nombres.subList(0, 7);
		nombres.toArray(); // arrayList -> vec[]
	
		listaDNI
		HashMap<Integer, String> diccionario = new HashMap<>();
		
	}

}
