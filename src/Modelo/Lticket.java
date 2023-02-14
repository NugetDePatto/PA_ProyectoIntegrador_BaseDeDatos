package Modelo;

import java.util.ArrayList;


public class Lticket extends Lproductos{
	String idticket;
	String fecha;
	
	public Lticket(String fecha, String idticket) {
		super();
		this.idticket = idticket;
		this.fecha = fecha;
		this.lProd = new ArrayList<Cproducto>();
	}
	
	@Override
	public boolean insertarb(Cproducto nodo) {
		lProd.add(nodo);
		return true;
	}
	
	public String mostrarlistaproductos() {
		String salida = "";
		salida = salida + this.mostrarLista();
		salida = salida + "\n el total de la venta --> " + this.getSubtotal();
		
		return salida;
	}
	
	public String mostarlistaventa() {
		String salida = "";
		salida = "Fecha " + this.fecha + " - Ticket No. " + this.idticket;
		salida = salida + "\n " + this.mostrarLista();
		salida = salida + "\n \n El total es: " + this.getSubtotal();
		/*
		salida = salida + "\n el iva total es " + this.getIva();
		salida = salida + "\n el total de la venta fue " + this.getTotal();
		*/
		return salida;
	}
	
	public String getSubtotal() {
		double suma = 0;
		for(Cproducto info: this.lProd)
			suma = suma + (Double.parseDouble(info.getPrecio()) * Double.parseDouble(info.getCantidad()));
		return String.valueOf(suma);
	}
	
	/*
	public String getIva() {
		Double iva= Double.parseDouble(this.getSubtotal())*0.16;
		return String.valueOf(iva);
	}
	
	public String getTotal() {
		Double total = Double.parseDouble(this.getSubtotal()) + Double.parseDouble(getIva());
		return String.valueOf(total);
		
	}
	*/
	public String getIdticket() {
		return idticket;
	}
	public void setIdticket(String idticket) {
		this.idticket = idticket;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	public String guardar() {
		String archivo = "";
		int tam = this.lProd.size();
		int pos = 1;
		for(Cproducto info: this.lProd)
			if(pos < tam) {
				archivo = archivo + this.fecha.trim() + ", "+ this.idticket.trim()+", "+info+"\n";
				pos++;
			}else
				archivo = archivo+this.fecha.trim()+", "+this.idticket.trim()+", "+info;
		return archivo;
	}
}
