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
	
	public static void conversionTiempo(PrintStream ps, BufferedReader br) throws NumberFormatException, IOException {
        ps.print("Ingrese el tiempo en segundos: ");
        float totalSegundos = Float.parseFloat(br.readLine());

        
        float dias = totalSegundos / 86400;
        float horas = (totalSegundos % 86400) / 3600;
        float minutos = (totalSegundos % 3600) / 60;
        float segundos = totalSegundos % 60;

        ps.println(dias + " días, " + horas + " horas, " + minutos + " minutos, " + segundos + " segundos.");
    }
	
	public static void calcularPlanes(PrintStream ps, BufferedReader br) throws NumberFormatException, IOException {
        ps.print("Ingrese el precio del artículo: ");
        float precio = Float.parseFloat(br.readLine());


        float precioPlan1 = precio - (precio * 0.10f);

        
        float precioPlan2 = precio + (precio * 0.10f);
        float cuotaPlan2 = (precioPlan2 / 2);

       
        float precioPlan3 = precio + (precio * 0.15f);
        float cuotaPlan3 = (precioPlan3 * 0.75f) / 5;

        
        float precioPlan4 = precio + (precio * 0.25f);
        float cuotaPlan4PrimeraParte = (precioPlan4 * 0.60f) / 4;
        float cuotaPlan4SegundaParte = (precioPlan4 * 0.40f) / 4;

        
        ps.println("Plan 1: 100% al contado con un 10% de descuento.");
        ps.println("Precio final: $" + precioPlan1);

        ps.println("Plan 2: 50% al contado y el resto en 2 cuotas iguales con un 10% de incremento.");
        ps.println("Precio final: $" + precioPlan2);
        ps.println("Cuota 1 y 2: $" + cuotaPlan2);

        ps.println("Plan 3: 25% al contado y el resto en 5 cuotas iguales con un 15% de incremento.");
        ps.println("Precio final: $" + precioPlan3);
        ps.println("Cuotas 1 a 5: $" + cuotaPlan3);

        ps.println("Plan 4: Totalmente financiado en 8 cuotas con un 25% de incremento.");
        ps.println("Precio final: $" + precioPlan4);
        ps.println("Cuotas 1 a 4: $" + cuotaPlan4PrimeraParte);
        ps.println("Cuotas 5 a 8: $" + cuotaPlan4SegundaParte);
    } 
	
	public static void mostrarMesNacimiento(PrintStream ps, BufferedReader br) throws IOException {
        ps.print("Ingrese su signo zodiacal: ");
        String signo = br.readLine().toLowerCase();

        // Determinamos el mes aproximado según el signo
        switch (signo) {
            case "aries":
                ps.println("Mes de nacimiento aproximado: Marzo - Abril");
                break;
            case "tauro":
                ps.println("Mes de nacimiento aproximado: Abril - Mayo");
                break;
            case "géminis":
                ps.println("Mes de nacimiento aproximado: Mayo - Junio");
                break;
            case "cáncer":
                ps.println("Mes de nacimiento aproximado: Junio - Julio");
                break;
            case "leo":
                ps.println("Mes de nacimiento aproximado: Julio - Agosto");
                break;
            case "virgo":
                ps.println("Mes de nacimiento aproximado: Agosto - Septiembre");
                break;
            case "libra":
                ps.println("Mes de nacimiento aproximado: Septiembre - Octubre");
                break;
            case "escorpio":
                ps.println("Mes de nacimiento aproximado: Octubre - Noviembre");
                break;
            case "sagitario":
                ps.println("Mes de nacimiento aproximado: Noviembre - Diciembre");
                break;
            case "capricornio":
                ps.println("Mes de nacimiento aproximado: Diciembre - Enero");
                break;
            case "acuario":
                ps.println("Mes de nacimiento aproximado: Enero - Febrero");
                break;
            case "piscis":
                ps.println("Mes de nacimiento aproximado: Febrero - Marzo");
                break;
            default:
                ps.println("Signo zodiacal no reconocido.");
                break;
        }
    }
}

