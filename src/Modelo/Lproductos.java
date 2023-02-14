package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;


public class Lproductos {
	ArrayList<Cproducto> lProd;

	public Lproductos() {
		this.lProd = new ArrayList<Cproducto>();
	}
	
	//Este es el constructor agregado en clase, recibe un ResultSet y va fila por fila obteniendo los datos de fila y añadiendolos al ArrayList
	//En caso de que el ResultSet este vacio, el ArrayList quedara vacio
	public Lproductos(ResultSet datos) throws SQLException {
		this.lProd = new ArrayList<Cproducto>();
		while (datos.next()) {
			Cproducto nodo = new Cproducto(datos.getString(1), datos.getString(2), datos.getString(3), datos.getString(4), datos.getString(5));
			lProd.add(nodo);
		}
	}

	public Lproductos(ArrayList<String[]> datos) {
		this.lProd = new ArrayList<Cproducto>();
		for (String[] info : datos) {
			Cproducto nodo = new Cproducto(info);
			lProd.add(nodo);
		}
	}

	public ArrayList<Cproducto> getArray() {
		return this.lProd;
	}
	
	public int posicion(String id) {
		int enc = -1;
		int pos = 0;
		for (Cproducto info : this.lProd) {
			if (info.getId().compareTo(id) == 0) {
				enc = pos;
			}
			pos++;
		}
		return enc;
	}
	

	public int existe(Cproducto nodo) {
		int enc = -1;
		int pos = 0;
		for(Cproducto info: this.lProd)
			if(info.getId().compareTo(nodo.getId()) == 0) {
				enc = pos;
				pos++;
			}
		return enc;
	}
	
	public boolean existe(String id) {
		for (Cproducto info : this.lProd) {
			if (info.getId().compareTo(id) == 0)
				return true;
		}
		return false;
	}

	public boolean insertarb (Cproducto nodo) {
		boolean exito = true;
		if (existe(nodo) == -1)
			lProd.add(nodo);
		else exito = false;
		
		return exito;
	}
	
	public void insertar(Cproducto nodo) {
		this.lProd.add(nodo);
	}

	public void eliminar(String id) {
		this.lProd.remove(posicion(id));
	}

	public void modificar(String id, Cproducto datos) {
		Cproducto info = this.lProd.get(posicion(id));
		info.setNombre(datos.getNombre());
		info.setPrecio(datos.getPrecio());
		info.setCategoria(datos.getCategoria());
		info.setCantidad(datos.getCantidad());
		this.lProd.set(posicion(id), info);
	}

	public String guardar() {
		String archivo = "";
		for (Cproducto info : this.lProd) {
			archivo += info + "\n";
		}
		return archivo;
	}
	
	public Cproducto getProducto(String id) {
		for (Cproducto info : this.lProd) {
			if (info.getId().compareTo(id)==0) {
				return info;
			}
		}
		return null;
	}

	public DefaultTableModel getModelProductos() {
		String[] columnas = { "Id", "Nombre", "Precio", "Categoria", "Cantidad" };
		Object[][] datos = new Object[this.lProd.size()][5];
		int pos = 0;
		for (Cproducto prod : this.lProd) {
			datos[pos][0] = prod.getId();
			datos[pos][1] = prod.getNombre();
			datos[pos][2] = prod.getPrecio();
			datos[pos][3] = prod.getCategoria();
			datos[pos][4] = prod.getCantidad();
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
		for (Cproducto prod : this.lProd) {
			if (prod.getNombre().startsWith(desc)) {
				datos[0] = prod.getId();
				datos[1] = prod.getNombre();
				datos[2] = prod.getPrecio();
				datos[3] = prod.getCategoria();
				datos[4] = prod.getCantidad();
				modelo.addRow(datos);
			}
		}
		return modelo;
	}
	
	public String buscarIdProd(String desc) {
		for (Cproducto info : this.lProd) {
			if (info.getNombre().compareTo(desc)==0) {
				return info.getId();
			}
		}
		return null;
	}

	public boolean vender(String id, String cant) { 
		System.out.println(id);
		Cproducto info = lProd.get(posicion(id));
		//int c = Integer.parseInt(info.getCantidad()) - Integer.parseInt(cant);
		
		if(Integer.parseInt(cant) <= Integer.parseInt(info.getCantidad())) {
			//info.setCantidad(String.valueOf(Integer.parseInt(info.getCantidad()) - Integer.parseInt(cant)));
			//modificar(id, info);
			return true;
		}else {
			return false;
		}
		
	}
	
	public String cantidad(String id, String cant) {
		Cproducto info = lProd.get(posicion(id));
		int c = Integer.parseInt(info.getCantidad()) - Integer.parseInt(cant);

		return String.valueOf(c);
	}

	public void agregar(String id, String cant) { 
		Cproducto info = lProd.get(posicion(id));
		info.setCantidad(String.valueOf(Integer.parseInt(info.getCantidad()) + Integer.parseInt(cant)));
		modificar(id, info);
	}
	
	public String mostrarLista() {
		String salida = "";
		for(Cproducto info: this.lProd)
			salida = salida + info + "\n";
		return salida;
	}
	
	public int cantidadRegistro() {
		return this.lProd.size();
	}
	
	public Cproducto getProducto(int pos) {
		return this.lProd.get(pos);
	}
	
}
