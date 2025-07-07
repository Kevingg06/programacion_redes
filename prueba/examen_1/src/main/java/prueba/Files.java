package prueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Files {

	static File fileOriginal = new File("datos.dat");
	static File fileModified = new File("tuti_fruti.csv");
	static Set<Character> letrasUsadas = new HashSet<>();

	public static void main(String[] args) {
		formatFile();
	}

	public static void formatFile() {
		List<String> textoFormateado = leerFileConBuffer(fileOriginal);
		PrintStream ps = new PrintStream(System.out);
		PrintStream psErr = new PrintStream(System.err);

		if (!fileModified.exists()) {
			crearFileConPrinter(fileModified, textoFormateado);
			ps.printf(Colors.ANSI_GREEN + "Archivo modificado correctamente. \n" + Colors.ANSI_RESET);
		}

		System.setErr(psErr);
	}

	public static List<String> leerFileConBuffer(File f) {
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String line = "";
			List<String> texto = new ArrayList<>();

			while ((line = br.readLine()) != null) {
				char letraJuego = line.charAt(1);
				letrasUsadas.add(letraJuego);
				line = Character.toString(letraJuego).toUpperCase() + line.replaceAll("\\.", ";");
				texto.add(line);
			}

			return texto;

		} catch (FileNotFoundException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} catch (IOException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
			}
		}

		return null;
	}

	public static void crearFileConPrinter(File f, List<String> texto) {
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (IOException e) {
					Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
				}
			}

			fw = new FileWriter(f);
			pw = new PrintWriter(fw);

			for (String linea : texto) {
				pw.println(linea);
			}

		} catch (FileNotFoundException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} catch (IOException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} finally {
			try {
				if (pw != null)
					pw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	public static void mostrarContenidoFile() {
		FileReader fr = null;
		BufferedReader br = null;
		PrintStream ps = new PrintStream(System.out);

		try {
			fr = new FileReader(fileModified);
			br = new BufferedReader(fr);

			String line = "";
			List<String> texto = new ArrayList<>();
			int count = 1;

			ps.printf("N° Letra	Color	Animal		Objeto		Alimento\n");

			while ((line = br.readLine()) != null) {

				line = count + "   " + line;

				line = line.replaceAll(";", "\t\t | ");

				texto.add(line);

				if (count % 2 == 0) {
					ps.printf(Colors.ANSI_YELLOW + line + "\n");
				} else {
					ps.printf(Colors.ANSI_BLUE + line + "\n");
				}

				count++;
			}

		} catch (FileNotFoundException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} catch (IOException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
			}
		}
	}

	public static void eliminarElemento() {
		FileReader fr = null;
		BufferedReader br = null;
		int elementoEliminado = -1;
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintStream ps = new PrintStream(System.out);

		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

		try {
			ps.println("Ingrese la linea a eliminar: ");
			elementoEliminado = Integer.parseInt(br2.readLine().trim());

			fr = new FileReader(fileModified);
			br = new BufferedReader(fr);

			fw = new FileWriter(fileModified);
			bw = new BufferedWriter(fw);

			String line = "";
			String texto = "";
			int count = 1;
			while ((line = br.readLine()) != null) {
				if (count == elementoEliminado) {
					texto.concat(line + "\n");
				}
				count++;
			}

			bw.append(texto);

		} catch (FileNotFoundException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} catch (IOException e) {
			Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				Logger.getLogger(Files.class.getName()).log(Level.WARNING, null, e);
			}
		}

	}

	public static void agregarDatos() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintStream ps = new PrintStream(System.out);
		boolean notTerminado = true;
		while (notTerminado) {
			try {
				ps.printf(Colors.ANSI_GREEN + "Ingrese la letra de juego: ", Colors.ANSI_RESET);
				String letra = br.readLine().trim();
				if (!letrasUsadas.contains(letra)) {
					ps.printf(Colors.ANSI_GREEN + "Ingrese nuevamente: ", Colors.ANSI_RESET);
					String color = br.readLine().trim();

					
					if (Character.toString(color.charAt(0)) == letra) {
						
						String animal = br.readLine().trim();
						if (Character.toString(animal.charAt(0)) == letra) {
							String objeto = br.readLine().trim();
							if(Character.toString(objeto.charAt(0)) == letra) {
								String alimento = br.readLine().trim();
								if(Character.toString(alimento.charAt(0)) == letra) {
									break;
								}
							}
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
