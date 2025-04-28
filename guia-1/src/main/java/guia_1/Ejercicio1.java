package guia_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Ejercicio1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PrintStream ps = new PrintStream(System.out);
		PrintStream psErr = new PrintStream(System.err);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		calcularSueldo(ps, br);
		calcularAnguloRestante(ps, br);
		calcularPerimetroCuadrado(ps, br);
		conversionTemperatura(ps, br);

	}

	// Ejercicio 1A
	public static void calcularSueldo(PrintStream ps, BufferedReader br) throws NumberFormatException, IOException {

		ps.print("Ingrese el precio de su hora de trabajo: ");
		int precioHora = Integer.parseInt(br.readLine());

		ps.print("Ingrese cantidad de horas de trabajo: ");
		int horasTrabajadas = Integer.parseInt(br.readLine());
		
		ps.println(horasTrabajadas * precioHora);
	}

	// Ejercicio 1B
	public static void calcularAnguloRestante(PrintStream ps, BufferedReader br) throws NumberFormatException, IOException {
		
		ps.print("Ingrese del primer angulo: ");
		float angulo1 = Float.parseFloat(br.readLine());

		ps.print("Ingrese del segundo angulo: ");
		float angulo2 = Float.parseFloat(br.readLine());
		
		float total = 180;
		ps.println(total - (angulo1 + angulo2));
	}
	
	// Ejercicio 1C
	public static void calcularPerimetroCuadrado(PrintStream ps, BufferedReader br) throws NumberFormatException, IOException {
		ps.print("Ingrese area del cuadrado: ");
		float area = Float.parseFloat(br.readLine());
		
		double perimetro = 4 * Math.sqrt(area);
		
		ps.println(perimetro);
	}
	
	// Ejercicio 1D
	public static void conversionTemperatura(PrintStream ps, BufferedReader br) throws NumberFormatException, IOException {
		ps.print("Ingrese temperatura en Fahrenheit: ");
		float tempFahrenheit = Float.parseFloat(br.readLine());
		
		float tempCelsius = (tempFahrenheit - 32) * (5.0f/9.0f);
		
		ps.println(tempCelsius);
	}
	
	
}
