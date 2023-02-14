package Controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modelo.Cproducto;
import Modelo.Lproductos;
import Modelo.Lventa;

public class cVenta {
	Conexion con = new Conexion();

	InstruccionesSQL sql = new InstruccionesSQL();

	Cbd base = new Cbd(con.getConnection());

	Lproductos lista;
	Lventa listaventa;
	Libreria lb = new Libreria();

	public void listar() throws SQLException {
		lista = new Lproductos(base.consultaBD(sql.listaProductos()));

	}
	
	public void ventas() throws SQLException {
		listaventa = new Lventa(base.consultaBD(sql.listaVentas()));
	}

	public DefaultTableModel añadir(DefaultTableModel dt, String id) throws SQLException {
		listar();
		int pos = -1;
		for (int i = 0; i < dt.getRowCount(); i++) {
			if (dt.getValueAt(i, 0).equals(id)) {
				pos = i;
			}
		}

		if (pos > -1) {
			dt.setValueAt(String.valueOf(Integer.parseInt((String) dt.getValueAt(pos, 4)) + 1), pos, 4);
		} else {
			Cproducto p = lista.getProducto(id);

			Object a[] = new Object[5];

			if (p != null) {
				p = lista.getProducto(id);
				a[0] = p.getId();
				a[1] = p.getNombre();
				a[2] = p.getPrecio();
				a[3] = p.getCategoria();
 				a[4] = "1";

				dt.addRow(a);
			} else {
				JOptionPane.showMessageDialog(null, "Sin coincidencias");
			}
		}

		return dt; 
	}
	
	public String pagar(DefaultTableModel dt) throws SQLException {	
		//listar();
		ventas();
		String fecha = Libreria.getFecha();
		String ticket = Libreria.getIdticketsiguiente(listaventa.ultimoticket(fecha));
		System.out.println(ticket);
		String id = null, nombre, precio, categoria, cantidad;
		
		System.out.println("tama�o: "+dt.getRowCount());
		
		int tam = dt.getRowCount();
		
		for(int i = 0; i < tam; i++) {
			id = (String) dt.getValueAt(i, 0);
			nombre  = (String) dt.getValueAt(i, 1);
			precio = (String) dt.getValueAt(i, 2);
			categoria = (String) dt.getValueAt(i, 3);
			cantidad = (String) dt.getValueAt(i, 4);
			if(lista.vender(id, cantidad)) {
				base.insertar(sql.insertarTicket(ticket, id, nombre, precio, categoria, cantidad, fecha));
				base.modificar(sql.modificarCantidad(id, lista.cantidad(id, cantidad)));
				listar();
			}else {
				JOptionPane.showMessageDialog(null, "No existe inventario del producto: " + nombre);
			}
			
		}
		
		ventas();
		
		return listaventa.mostrarTicketlista(fecha, ticket);
	}

}
