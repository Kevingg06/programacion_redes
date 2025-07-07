package prueba;

public class Tuti {
	private String letra;
	private String color;
	private String animal;
	private String objeto;
	private String alimento;

	public Tuti(String letra, String color, String animal, String objeto, String alimento) {
		this.letra = letra.toUpperCase();
		this.color = color;
		this.animal = animal;
		this.objeto = objeto;
		this.alimento = alimento;
	}

	public String getAlimento() {
		return alimento;
	}

	public String getObjeto() {
		return objeto;
	}

	public String getColor() {
		return color;
	}

	public String getAnimal() {
		return animal;
	}

	public String getLetra() {
		return letra;
	}
	
	public String toCSV() {
		return letra + ";" + color + ";" + animal + ";" + objeto + ";" + alimento;
	}  
}


