package Modelo;

public class Cproducto {
	String id, nombre, precio, categoria, cantidad;

	public Cproducto(String id, String nombre, String precio, String categoria, String cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}
	
	public Cproducto(String[] datos) {
		id = datos[0];
		nombre = datos[1];
		precio = datos[2];
		categoria = datos[3];
		cantidad = datos[4];
	}
	
	public Cproducto() {
		
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

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return id + ", " + nombre + ", " + precio + ", " + categoria + ", " + cantidad;
	}

}
