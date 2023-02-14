package Controlador;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Modelo.Cproducto;
import Modelo.Lventa;

public class cPagar {

	Conexion con = new Conexion();
	
	InstruccionesSQL sql = new InstruccionesSQL();
	
	Cbd base = new Cbd(con.getConnection());
	
	Lventa lista;
	

	
	public DefaultTableModel listar() throws SQLException {
		lista = new Lventa(base.consultaBD(sql.listaVentas()));
		
		return null;
	}
}
