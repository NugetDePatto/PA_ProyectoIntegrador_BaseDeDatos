package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cbd {
	Connection conexion;

	public Cbd(Connection conexion) {
		this.conexion = conexion;
	}

	public ResultSet consultaBD(String cadena) {
		System.out.println(cadena);
		ResultSet datosRegistros = null;
		Statement st;
		String cadenaSQL = cadena;
		try {
			st = conexion.createStatement();
			datosRegistros = st.executeQuery(cadenaSQL);
			//conexion.close();
		} catch (Exception e) {
			System.out.println("E R R O R : " + e.getCause());
		}
		return datosRegistros;
	}

	public boolean insertar(String cadena) {
		boolean realizo = true;
		Statement st;
		String cadenaSQL = cadena;
		System.out.println(cadena);
		try {

			st = conexion.createStatement();
			System.out.println(cadenaSQL);
			int i = st.executeUpdate(cadenaSQL);

			//conexion.close();
		} catch (Exception e) {
			realizo = false;
			System.out.println("E R R O R : " + e.getCause());
		}
		return realizo;
	}

	public boolean eliminar(String cadena) {
		boolean realizo = true;
		Statement st;
		String cadenaSQL = cadena;
		System.out.println(cadenaSQL);
		try {
			st = conexion.createStatement();
			int i = st.executeUpdate(cadenaSQL);
			System.out.println(i);
			//conexion.close();
		} catch (Exception e) {
			realizo = false;
			System.out.println("E R R O R : " + e.getCause());
		}
		return realizo;
	}

	public boolean modificar(String cadena) {
		boolean realizo = true;
		Statement st;
		String cadenaSQL = cadena;
		System.out.println(cadena);
		try {
			st = conexion.createStatement();
			int i = st.executeUpdate(cadenaSQL);
			//conexion.close();
		} catch (Exception e) {
			realizo = false;
			System.out.println("E R R O R : " + e.getCause());
		}
		return realizo;
	}
}
