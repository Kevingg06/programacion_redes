package Archivos;

public class Main {

	public static void main(String[] args) {
		Archivos arch = new Archivos("consorti.txt");
		
		arch.crearFileConBuffer(arch.getFiles(),"hola mundo");

	}

}
