package ar.edu.et32;

import java.io.IOException;
import java.io.PrintStream;

public class MainClass {
	public static void main(String[] args) {
		byte[] array = {60, 45, 100, 59, 77, -41, 78} ;
	
		System.out.write(77);
		System.out.write(13);
		System.out.flush();
		
		try {
			System.out.write(array);
			System.out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
