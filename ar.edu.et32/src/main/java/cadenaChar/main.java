package cadenaChar;

public class main {
	public static void main(String[] args) {
		
		String palabra = "linKevin";
		
		palabra.charAt(5); // agarra caracter x
		palabra.compareTo("Kevin"); // false compara texto completo
		palabra.compareToIgnoreCase("linkevin"); // true
		palabra.concat("/profile"); //agrega texto al final
		palabra.contains(""); // devuelve si existe en el texto
		palabra.indexOf('K'); //charAt pero al revés
		palabra.indexOf("Ke");
		palabra.isBlank();
		palabra.isEmpty(); 
		palabra.length();
		palabra.lastIndexOf('i');
		palabra.replace("Kevin", "Roman"); // salida: linRoman
		palabra.replaceAll("i", "x.X"); // Lx.XKevx.Xn
		palabra.toString();
		palabra.trim(); // quita espacios en blanco adelante y al final
		palabra.toLowerCase();
		palabra.toUpperCase();
		String.valueOf(5); // transforma a ascii(5)
		palabra.toCharArray();
		palabra.split("K"); // vec[] => {"Lin", "evin"}
		palabra.substring(2, 4); // Linkevin -> nKev
		
		System.out.print("Hola");
	}
}
