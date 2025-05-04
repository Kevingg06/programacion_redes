package guia_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class Ejercicio2 {

	public static void main(String[] args) {


	}

	//Ejercicio 2A
	public static void ordenarAlfabeticamente(PrintStream ps, PrintStream psErr, BufferedReader br) throws IOException {
		ps.print("Ingrese el primer apellido: ");
		String apellido1 = br.readLine();
		ps.print("Ingrese el segundo apellido: ");
		String apellido2 = br.readLine();
		ps.print("Ingrese el tercer apellido: ");
		String apellido3 = br.readLine();

		String[] apellidos = { apellido1, apellido2, apellido3 };

		Arrays.sort(apellidos);

		ps.println("Apellidos ordenados alfabéticamente:");
		for (String apellido : apellidos) {
			ps.println(apellido);
		}
	}

	// Ejercicio 2B
	public static void indicarMenor(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException, NumberFormatException {
		ps.print("Ingrese el primer numero: ");
		double num1 = Double.parseDouble(br.readLine());
		ps.print("Ingrese el segundo numero: ");
		double num2 = Double.parseDouble(br.readLine());
		ps.print("Ingrese el tercer numero: ");
		double num3 = Double.parseDouble(br.readLine());
		ps.print("Ingrese el cuarto numero: ");
		double num4 = Double.parseDouble(br.readLine());

		double[] numeros = { num1, num2, num3, num4 };

		Arrays.sort(numeros);

		ps.println("El numero mas chico ingresado es: " + numeros[0]);
	}

	// Ejercicio 2C
	public static void esPar(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException, NumberFormatException {
		ps.print("Ingrese un numero entero: ");
		int num = Integer.parseInt(br.readLine());

		if (num % 2 == 0) {
			ps.println("El numero ingresado es par");
		} else {
			ps.println("El numero ingresado es impar");
		}
	}

	// Ejercicio 2D
	public static void esDivisible(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException, NumberFormatException {
		ps.print("Ingrese el primer numero: ");
		double num1 = Double.parseDouble(br.readLine());
		ps.print("Ingrese el segundo numero: ");
		double num2 = Double.parseDouble(br.readLine());

		double mayor;
		double menor;

		if(num1 >= num2){
			mayor = num1;
			menor = num2;
		}else {
			mayor = num2;
			menor = num1;
		}

		if (mayor % menor == 0) {
			ps.println("El numero " + mayor + " es divisible por " + menor);
		} else {
			ps.println("El numero " + mayor + " no es divisible por " + menor);
		}
	}

	// Ejercicio 2E
	public static void calcularSigno(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException, NumberFormatException {
		ps.print("Ingresá tu día de nacimiento (1-31): ");
		int dia = Integer.parseInt(br.readLine());

		ps.print("Ingresá tu mes de nacimiento (1-12): ");
		int mes = Integer.parseInt(br.readLine());

		String signo = switch (mes) {
			case 1 -> (dia < 20) ? "Capricornio" : "Acuario";
			case 2 -> (dia < 19) ? "Acuario" : "Piscis";
			case 3 -> (dia < 21) ? "Piscis" : "Aries";
			case 4 -> (dia < 20) ? "Aries" : "Tauro";
			case 5 -> (dia < 21) ? "Tauro" : "Géminis";
			case 6 -> (dia < 21) ? "Géminis" : "Cáncer";
			case 7 -> (dia < 23) ? "Cáncer" : "Leo";
			case 8 -> (dia < 23) ? "Leo" : "Virgo";
			case 9 -> (dia < 23) ? "Virgo" : "Libra";
			case 10 -> (dia < 23) ? "Libra" : "Escorpio";
			case 11 -> (dia < 22) ? "Escorpio" : "Sagitario";
			case 12 -> (dia < 22) ? "Sagitario" : "Capricornio";
			default -> null;
		};

		if (signo != null) {
			ps.println("Tu signo zodiacal es: " + signo);
		} else {
			ps.println("Fecha inválida.");
		}
	}

	// Ejercicio 2F
	public static void apellidoMasLargo(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException {
		ps.print("Ingrese el nombre y apellido de la primer persona: ");
		String nombre1 = br.readLine();
		ps.print("Ingrese el nombre y apellido de la segunda persona: ");
		String nombre2 = br.readLine();

		String[] lista1 = nombre1.split(" ");
		String[] lista2 = nombre2.split(" ");

		if (lista1.length < 2 || lista2.length < 2) {
			ps.println("Por favor, ingresá nombre y apellido para ambas personas.");
		} else {
			if (lista1[1].length() > lista2[1].length()) {
				ps.println(nombre1 + " tiene el apellido más largo");
			} else if (lista2[1].length() > lista1[1].length()) {
				ps.println(nombre2 + " tiene el apellido más largo");
			} else {
				ps.println("Ambos apellidos tienen la misma longitud (:v)");
			}
		}
	}

	// Ejercicio 2G
	public static void mostrarTabla(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException, NumberFormatException {
		ps.print("Ingrese un número natural: ");
		int num = Integer.parseInt(br.readLine());

		if (num <= 0) {
			ps.println("0 no es valido");
		} else {
			ps.println("Tabla de multiplicar del " + num + ":");
			for (int i = 1; i <= 10; i++) {
				ps.println(num + " x " + i + " = " + (num * i));
			}
		}
	}

	// Ejercicio 2H
	public static void esPrimo(PrintStream ps, PrintStream psErr, BufferedReader br)
			throws IOException, NumberFormatException {
		ps.print("Ingrese un numero natural: ");
		int num = Integer.parseInt(br.readLine());

		boolean esPrimo = true;

		if (num <= 1) {
			esPrimo = false;
		} else {
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if (num % i == 0) {
					esPrimo = false;
					break;
				}
			}
		}

		if (esPrimo) {
			ps.println("El numero ingresado es primo");
		} else {
			ps.println("El numero ingresado no es primo");
		}
	}

}
