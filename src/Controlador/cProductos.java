package Controlador;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import Modelo.Ccategoria;
import Modelo.Cproducto;
import Modelo.Lcategorias;
import Modelo.Lproductos;

public class cProductos {
	
	Conexion con = new Conexion();
	
	InstruccionesSQL sql = new InstruccionesSQL();
	
	Cbd base = new Cbd(con.getConnection());
	
	Lproductos lista;
	Lcategorias listaC;
	
	public DefaultTableModel listar() throws SQLException {
		lista = new Lproductos(base.consultaBD(sql.listaProductos()));
		
		return lista.getModelProductos();
	}
	
	public Lproductos lista() throws SQLException {
		return new Lproductos(base.consultaBD(sql.listaProductos()));
	}
	
	public Cproducto Consultar(String id) throws SQLException {
		lista = new Lproductos(base.consultaBD(sql.consultarProducto(id)));
		Cproducto producto = null;
		
		for (Cproducto dato : lista.getArray()) {
			System.out.println(dato.getNombre());
			producto = dato;
		}
		
		return producto;
	}
	
	public DefaultTableModel insertar(String id, String nombre, String precio, String categoria) throws SQLException {
		Cproducto dato = Consultar(id);
		if(dato == null) {
			base.insertar(sql.insertarProducto(id, nombre, precio, categoria, "0"));
		} else {
			System.out.println("EL codigo ya exite");
		}
		
		return listar();
	}
	
	public DefaultTableModel eliminar(String id) throws SQLException {
		Cproducto dato = Consultar(id);
		
		if(dato != null) {
			base.eliminar(sql.eliminarProducto(id));
		} else {
			System.out.println("EL codigo no exite");
		}
		
		return listar();
	}
	
	public DefaultTableModel modificar(String id, String nombre, String precio, String categoria) throws SQLException {
		Cproducto dato = Consultar(id);

		if (dato != null) {
			base.modificar(sql.modificarProducto(id, nombre, precio, categoria));
		} else {
			System.out.println("EL codigo no exite");
		}

		return listar();
	}

	public JComboBox comboBox() throws SQLException {
		listaC =  new Lcategorias(base.consultaBD(sql.listacategorias()));
		JComboBox combo = new JComboBox();
		
		for(Ccategoria dato : listaC.getArray()) {
			combo.addItem(dato.getNombre());
		}
		return combo;
	}
	
	public void cerrarConexion() {
		con.Desconexion();
	}
	
	public DefaultTableModel modificarCantidad(String id, String cantidad) throws SQLException {
		lista = new Lproductos(base.consultaBD(sql.listaProductos()));
		
		cantidad = String.valueOf(Integer.parseInt(cantidad) + Integer.parseInt(Consultar(id).getCantidad()));
		
		base.modificar(sql.setCantidad(id, cantidad));
		
		return listar();
	}

}
