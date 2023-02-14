package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Lventa {
	ArrayList<Lticket> listaventa;

	public Lventa(ResultSet datos) throws SQLException {
		String idticket, fecha;int pos;
		this.listaventa= new ArrayList<Lticket>();
		
		while (datos.next()) {
			idticket = datos.getString(1);
			Cproducto nodo = new Cproducto(datos.getString(2), datos.getString(3), datos.getString(4), datos.getString(5), datos.getString(6));
			fecha = datos.getString(7);
			
			pos = existe(fecha, idticket);
			if(pos == -1)
				this.crearticket(fecha, idticket, nodo);
			else
				this.insertar(pos, nodo);			
		}
	}

	public Lventa() {
		super();
		this.listaventa = new ArrayList<Lticket>();
	}
	
	public int existe(String fecha, String idticket) {
		int pos = 0;
		int enc = -1;
		for(Lticket info: this.listaventa) {
			System.out.println(info.getFecha()+" "+info.getIdticket()+" checando");
			if((info.getFecha().compareTo(fecha)==0)&&(info.getIdticket().compareTo(idticket)==0))
				enc = pos;
			pos++;
		}
		System.out.println(" lo encontro en la posicion "+enc);
		return enc;
	}
	
	public void crearticket(String fecha, String idticket, Cproducto nodo) {
		Lticket ticketlista = new Lticket(fecha, idticket);
		ticketlista.insertar(nodo);
		this.listaventa.add(ticketlista);
	}
	
	public void insertar(int pos, Cproducto info) {
		Lticket ticketlista = this.listaventa.get(pos);
		ticketlista.insertar(info);
		this.listaventa.set(pos, ticketlista);
	}
	
	/*
	public void eliminararticulo(int pos, Cproducto info) {
		Lticket ticketlista = this.listaventa.get(pos);
		if(ticketlista != null) {
			pos = ticketlista.existe(info);
			ticketlista.eliminar(pos);
			this.listaventa.set(pos, ticketlista);
		}
	}
	*/
	
	public String mostrarTicket(int pos) {
		return this.listaventa.get(pos).mostarlistaventa();
	}
	
	public String mostrarTicket(String fecha, String idticket) {
		String salida = "";
		int pos = -1;
		salida = "";
		if(this.listaventa != null)
			pos = existe(fecha, idticket);
		if(pos >= 0)
			salida =this.listaventa.get(pos).mostrarlistaproductos();
		return salida;
	}
	
	public String mostrarTicketlista(String fecha, String idticket) {
		String salida = "";
		int pos = -1;
		salida = "";
		if(this.listaventa != null)
			pos = existe(fecha, idticket);
		if(pos >= 0)
			salida = this.listaventa.get(pos).mostarlistaventa();
		return salida;
	}
	
	public String ultimoticket(String idfecha) {
		int pos;
		ArrayList<String> listadia = new ArrayList<String>();
		String idticket = "000";
		if (listaventa != null) {
			for (Lticket info : this.listaventa) {
				System.out.println("comparando " + info.getFecha() + " con" + idfecha);
				if (info.getFecha().compareTo(idfecha) == 0) {
					listadia.add(info.getIdticket());
					System.out.println("es igual¿");
				}

			}
			if (listadia.isEmpty()) {
				idticket = "000";
				System.out.println("arraylsit es null");
			} else {
				if (!listadia.isEmpty()) {
					pos = listadia.size();
					if (pos >= 0) {
						idticket = (String) listadia.get(pos - 1);
						System.out.println("no esta vacio");
					}
						//idticket = (String) listadia.get(pos - 1);
				} else
					idticket = "000";
			}
		} else {
			idticket = "000";
			System.out.println("listaventa es nulo");
		}

		System.out.println("idticket: " + idticket);

		return idticket;

	}
	
	public String tam() {
		return String.valueOf(this.listaventa.size());
	}
	
	public String guardarArchivo() {
		String ticket = "";
		int pos = 0;
		for(Lticket info: this.listaventa) {
			if (pos < this.listaventa.size())
				ticket = ticket+info.guardar()+"\n";
			else
				ticket = ticket+info.guardar();
		}
		
		return ticket;
	}

	public String getVentadia(String idfecha) {
		String ventadia = "";
		double total = 0;
		
		if(listaventa!=null) {
			for(Lticket info: this.listaventa) {
				System.out.println("comparando " +info.getFecha()+" con..."+ idfecha);
				if(info.getFecha().compareTo(idfecha)==0) {
					ventadia = ventadia+"No ticket -> "+info.getIdticket()+" venta total ->"+info.getSubtotal()+"\n";
					total = total + Double.parseDouble(info.getSubtotal());
				}
					
			}
		}
		
		ventadia = ventadia + "\n la venta total del dia fue "+String.valueOf(total);
		
		return ventadia;
	}
	
	
	/*
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
*/
	/*
	public DefaultTableModel getModelVentas() {
		String[] columnas = { "Id", "Nombre", "Precio", "Cantidad" };
		Object[][] datos = new Object[4][4];
		
		int pos = 0;
		for (Lticket prod : this.listaventa) {
			
			datos[pos][0] = prod.getId();
			datos[pos][1] = prod.getNombre();
			datos[pos][2] = prod.getPrecio();
			datos[pos][3] = prod.getCategoria();
			datos[pos][4] = prod.getCantidad();
			pos++;
		}
		
		return new DefaultTableModel(datos, columnas);
		
	}
	*/
}
