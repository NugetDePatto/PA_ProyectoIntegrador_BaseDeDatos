package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Lcategorias {
	ArrayList<Ccategoria> lProd;

	public Lcategorias() {
		this.lProd = new ArrayList<Ccategoria>();
	}
	
	//Este es el constructor agregado en clase, recibe un ResultSet y va fila por fila obteniendo los datos de fila y añadiendolos al ArrayList
	//En caso de que el ResultSet este vacio, el ArrayList quedara vacio
	public Lcategorias(ResultSet datos) throws SQLException {
		this.lProd = new ArrayList<Ccategoria>();
		while (datos.next()) {
			Ccategoria nodo = new Ccategoria(datos.getString(1), datos.getString(2));
			lProd.add(nodo);
		}
	}

	public Lcategorias(ArrayList<String[]> datos) {
		this.lProd = new ArrayList<Ccategoria>();
		for (String[] info : datos) {
			Ccategoria nodo = new Ccategoria(info);
			lProd.add(nodo);
		}
	}

	public ArrayList<Ccategoria> getArray() {
		return this.lProd;
	}
	
	public int getPosProducto(String id) {
		int pos = 0;
		for (Ccategoria info : this.lProd) {
			if (info.getId().compareTo(id) == 0) {
				break;
			}
			pos++;
		}
		return pos;
	}
	
	public boolean existeProducto(String id) {
		for (Ccategoria info : this.lProd) {
			if (info.getId().compareTo(id) == 0)
				return true;
		}
		return false;
	}

	public void insertar(Ccategoria nodo) {
		this.lProd.add(nodo);
	}

	public void eliminar(String id) {
		this.lProd.remove(getPosProducto(id));
	}

	public void modificar(String id, Ccategoria datos) {
		Ccategoria info = this.lProd.get(getPosProducto(id));
		info.setNombre(datos.getNombre());
		this.lProd.set(getPosProducto(id), info);
	}

	public String guardar() {
		String archivo = "";
		for (Ccategoria info : this.lProd) {
			archivo += info + "\n";
		}
		return archivo;
	}
	
	public Ccategoria getProducto(String id) {
		for (Ccategoria info : this.lProd) {
			if (info.getId().compareTo(id)==0) {
				return info;
			}
		}
		return null;
	}

	public DefaultTableModel getModeLcategorias() {
		String[] columnas = { "Id", "Nombre", "Precio", "Categoria", "Cantidad" };
		Object[][] datos = new Object[this.lProd.size()][5];
		int pos = 0;
		for (Ccategoria prod : this.lProd) {
			datos[pos][0] = prod.getId();
			datos[pos][1] = prod.getNombre();
			pos++;
		}
		return new DefaultTableModel(datos, columnas);
	}
	
	public DefaultTableModel getModelFiltrado(String desc) {
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Codigo");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Precio");
		modelo.addColumn("Categoria");
		modelo.addColumn("Cantidad");
		Object[] datos = new Object[5];
		for (Ccategoria prod : this.lProd) {
			if (prod.getNombre().startsWith(desc)) {
				datos[0] = prod.getId();
				datos[1] = prod.getNombre();
				modelo.addRow(datos);
			}
		}
		return modelo;
	}
	
	public String buscarIdProd(String desc) {
		for (Ccategoria info : this.lProd) {
			if (info.getNombre().compareTo(desc)==0) {
				return info.getId();
			}
		}
		return null;
	}

	/*
	public void vender(String id, String cant) { 
		Ccategoria info = lProd.get(getPosProducto(id));
		info.setCantidad(String.valueOf(Integer.parseInt(info.getCantidad()) - Integer.parseInt(cant)));
		modificar(id, info);
	}

	public void agregar(String id, String cant) { 
		Ccategoria info = lProd.get(getPosProducto(id));
		info.setCantidad(String.valueOf(Integer.parseInt(info.getCantidad()) + Integer.parseInt(cant)));
		modificar(id, info);
	}
	*/

}
