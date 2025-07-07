package prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resolucion {
	
	public static void main(String[] args) {
		menuInfinito();
	}

	public static void menuInfinito() {

		PrintStream ps = new PrintStream(System.out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = 0;

		try {
			while (input != 4) {

				ps.print( Colors.ANSI_PURPLE  + "        1.       Agregar datos nuevos al archivo de texto.\r\n"
						+ "        2.       Eliminar datos del archivo de texto.\r\n"
						+ "        3.       Mostrar los datos existentes.\r\n" + "        " 
						+         "4.       Salir. \n" + Colors.ANSI_RESET);

				ps.print("Seleccione una opción: ");

				input = Integer.parseInt(br.readLine().trim());

				switch (input) {
				case 1:
					ps.println("imprimir 1");
					break;

				case 2:
					Files.mostrarContenidoFile();
					Files.eliminarElemento();
					break;

				case 3:
					Files.mostrarContenidoFile();
					break;

				case 4:
					ps.println(Colors.ANSI_RED + "\n Saliendo del programa..." + Colors.ANSI_RESET);
					break;

				default:
					ps.println("Numero invalido");
				}

			}
		} catch (IOException e) {
			Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
