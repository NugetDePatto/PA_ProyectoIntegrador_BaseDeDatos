package Modelo;

public class Ccategoria {
	private String id, nombre;

	public Ccategoria(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Ccategoria(String[]datos) {
		id = datos[0];
		nombre = datos[1];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return id + ", " + nombre;
	}

}
