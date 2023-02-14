package Controlador;

public class InstruccionesSQL {

	private String cadena;
	
	//PRODUCTOS----------------------------------------------------------------------------------------------------------------------------

	public String listaProductos() {
		cadena = "SELECT Productos.Id, Productos.Nombre, Productos.Precio, Productos.Categoria, Productos.Cantidad FROM Productos;";
		return cadena;
	}

	public String consultarProducto(String id) {
		cadena = "SELECT Productos.Id, Productos.Nombre, Productos.Precio, Productos.Categoria, Productos.Cantidad FROM Productos WHERE Id = '"
				+ id + "';";
		return cadena;
	}

	public String consultarIdCateogoria() {
		cadena = "SELECT Productos.Id, Productos.Nombre, Productos.Precio, Productos.Cantidad, Categorias.Id "
				+ "FROM Categorias INNER JOIN Productos ON Categorias.Nombre = Productos.Categoria;";
		return cadena;
	}

	public String insertarProducto(String id, String nombre, String precio, String categoria, String cantidad) {
		cadena = "INSERT into Productos(Id,Nombre,Precio,Categoria,Cantidad) VALUES" + "(" + "'" + id + "','" + nombre
				+ "','" + precio + "','" + categoria + "','" + cantidad + "');";
		return cadena;
	}

	public String eliminarProducto(String id) {
		cadena = "DELETE FROM Productos WHERE Id = '" + id + "';";
		return cadena;
	}

	public String modificarProducto(String id, String nombre, String precio, String categoria) {
		cadena = "UPDATE Productos SET Nombre = '" + nombre + "', Precio = '" + precio + "', Categoria = '" + categoria
				+ "' WHERE Id = '" + id + "';";
		return cadena;
	}
	
	public String setCantidad(String id, String cantidad) {
		cadena = "UPDATE Productos SET Cantidad = '" + cantidad + "' WHERE Id = '" + id + "';";
		return cadena;
	}
	
	public String getCantiad(String id) {
		cadena = "SELECT Productos.Cantidad FROM Productos WHERE Id = '" + id + "';";
		return cadena;
	}
	
	//CATEGORIAS----------------------------------------------------------------------------------------------------------------------------

	public String listacategorias() {
		cadena = "SELECT Categorias.Id, Categorias.Nombre FROM Categorias;";
		return cadena;
	}
	
	public String consultarcategoria(String id) {
		cadena = "SELECT Categorias.Id, Categorias.Nombre FROM Categorias WHERE Id = '"
				+ id + "';";
		return cadena;
	}
	
	public String insertarcategoria(String id, String nombre) {
		cadena = "INSERT into Categorias(Id,Nombre) VALUES" + "(" + "'" + id + "','" + nombre
				+ "',);";
		return cadena;
	}
	
	public String eliminarcategoria(String id) {
		cadena = "DELETE FROM Categorias WHERE Id = '" + id + "';";
		return cadena;
	}
	
	public String modificarcategoria(String id, String nombre) {
		cadena = "UPDATE Categorias SET Nombre = '" + nombre + "'" + "' WHERE Id = '" + id + "';";
		return cadena;
	}
	
	//PROVEEDORES----------------------------------------------------------------------------------------------------------------------------
	
	
	public String listaproveedores() {
		cadena = "SELECT Proveedores.Id, Proveedores.Nombre, Proveedores.Direccion FROM Proveedores;";
		return cadena;
	}
	
	public String consultaproveedores(String id) {
		cadena = "SELECT Proveedores.Id, Proveedores.Nombre, Proveedores.Direccion FROM Proveedores WHERE Id = '"
				+ id + "';";
		return cadena;
	}
	
	public String insertarproveedores(String id, String nombre, String direccion) {
		cadena = "INSERT into Proveedores(Id,Nombre,Direccion) VALUES" + "(" + "'" + id + "','" + nombre+ "','" + direccion + "' );";
		return cadena;
	}
	
	public String eliminarproveedores(String id) {
		cadena = "DELETE FROM Proveedores WHERE Id = '" + id + "';";
		return cadena;
	}
	
	public String modificarproveedores(String id, String nombre, String direccion) {
		cadena = "UPDATE Proveedores SET Nombre = '" + nombre + "', Direccion = '" + direccion + "' WHERE Id = '" + id + "';";
		return cadena;
	}
	
	//VENTAS--------------------------------------------------------------------------------------------------------------------------------
	
	public String listaVentas() {
		cadena = "SELECT Ventas.Ticket, Ventas.id, Ventas.nombre, Ventas.precio, Ventas.categoria, Ventas.cantidad, Ventas.Fecha FROM Ventas";
		
		return cadena;
	}
	
	public String insertarTicket(String ticket, String id, String nombre, String precio, String categoria, String cantidad, String fecha) {
		cadena = "INSERT into Ventas(Ticket, id, nombre, precio, categoria, cantidad, Fecha) VALUES"+ "('" + ticket + "','" + id + "','" + nombre
				+ "','" + precio + "','" + categoria + "','" + cantidad + "','" + fecha + "');";
		return cadena;
	}

	public String modificarCantidad(String id, String cantidad) {

		cadena = "UPDATE Productos SET Cantidad = '" + cantidad + "' WHERE Id = '" + id + "';";

		return cadena;
	}
	
	//PAGAR
	
}
