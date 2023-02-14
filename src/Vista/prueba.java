package Vista;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controlador.Cbd;
import Controlador.Conexion;
import Controlador.InstruccionesSQL;
import Modelo.Cproducto;
import Modelo.Lproductos;

public class prueba {

	public static void main(String[] args) throws SQLException {
		Conexion con = new Conexion();
		
		InstruccionesSQL sql = new InstruccionesSQL();
		
		Cbd base = new Cbd(con.getConnection());

		//INSERTAR PRODUCTO------------------------------------------------------------------
		// base.insertar(sql.getinsertarProducto("0003", "SUSTITUTO CORINA DE CREMA
		// PARA CAFÉ 1KG - CORINA", "15.70", "Cafes, Te y Sustitutos", "100"));

		// base = new Cbd(con.getConnection());
		
		//CONSULTAR TODOS LOS PRODUCTOS DE LA BASE DE DATOS

		Lproductos listaProductos = new Lproductos(base.consultaBD(sql.listaProductos()));

		for (Cproducto dato : listaProductos.getArray()) {
			System.out.println(dato);
		}

		
		//CONSULTA UN PRODUCTO POR EL ID
		base = new Cbd(con.getConnection());

		listaProductos = new Lproductos(base.consultaBD(sql.consultarProducto("0003")));

		for (Cproducto dato : listaProductos.getArray()) {
			System.out.println(dato.getNombre());
		}
		
		//CONSULTA SI EXISTE UN PRODUCTO CON EL ID

		base = new Cbd(con.getConnection());

		listaProductos = new Lproductos(base.consultaBD(sql.consultarProducto("0004")));

		if (listaProductos.getArray().size() == 0) {
			base = new Cbd(con.getConnection());

			base.insertar(sql.insertarProducto("0004", "EJEMPLO", "00", "EJEMPLO", "00"));

		} else {
			JOptionPane.showMessageDialog(null, "El codigo ya existe");
		}
		
		//VUELVE A CONSULTAR TODOS LOS DATOS DEL LA BASE DE DATOS
		
		base = new Cbd(con.getConnection());

		listaProductos = new Lproductos(base.consultaBD(sql.listaProductos()));

		for (Cproducto dato : listaProductos.getArray()) {
			System.out.println(dato);
		}
		
		// ELIMINA PRODCUTO POR EL ID

		base = new Cbd(con.getConnection());

		System.out.println(base.eliminar(sql.eliminarProducto("0004")));
		
		// MODIFICAR NOMBRE Y PRECIO POR ID
		
		base = new Cbd(con.getConnection());
		
		System.out.println(base.modificar(sql.modificarProducto("0003", "JUGO", "15.5", "EJEMPLO")));

		// CUANDO SE CONSULTAN LAS CATEGORIAS SE INSERTA EN CANTIDAD Y LAS CANTIDADES SE
		// INSERTAN EN CATEGORIAS

		base = new Cbd(con.getConnection());

		listaProductos = new Lproductos(base.consultaBD(sql.consultarIdCateogoria()));

		for (Cproducto dato : listaProductos.getArray()) {
			System.out.println(dato.getCantidad());
		}
		
		

		// System.out.println(listaProductos);

		/*
		 * Libreria lb = new Libreria(); System.out.println(lb.getFecha());
		 * System.out.println(lb.getIdticketsiguiente("001"));
		 * 
		 * String a = "Sim"; String b = "bsimilitudsim"; String c = "no";
		 * 
		 * String[] cadena = { "sim", "similitud", "nose", "kjdsalsim", "SIMILITUD" };
		 * 
		 * for (int i = 0; i < sql.length; i++) { System.out.println(cadena[i]); if
		 * (cadena[i].toLowerCase().indexOf(a.toLowerCase()) >= 0) {
		 * System.out.println("Se encontro"); } else {
		 * System.out.println("No se encontro"); } }
		 * System.out.println("-----------------------------------------------");
		 * ArrayList<String> lista = new ArrayList<String>();
		 * 
		 * lista.add("sim"); lista.add("SIM"); lista.add("sIm");
		 * lista.add("DKFAJSIMDJALSK"); lista.add("dsfadsjfsimasfdjka");
		 * lista.add("noo se ");
		 * 
		 * for (String aux : lista) { if (aux.toLowerCase().indexOf(a.toLowerCase()) >=
		 * 0) { System.out.println("Se encontro"); } else {
		 * System.out.println("No se encontro"); } }
		 * 
		 */

	}

}
