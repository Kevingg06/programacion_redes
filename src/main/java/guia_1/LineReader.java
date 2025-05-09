package guia_1;

import java.io.IOException;

public class LineReader {
	
	public static String leerLinea() throws IOException {
	    StringBuilder resultado = new StringBuilder();
	    int caracter = System.in.read();
	    /*este funciona para sistemas windows, sino cambiar a caracter != 10 en sistemas unix-like*/
	    while (caracter != 13) {
	        resultado.append((char) caracter); 
	        caracter = System.in.read();
	    }

	    return resultado.toString().trim();
	}
}
