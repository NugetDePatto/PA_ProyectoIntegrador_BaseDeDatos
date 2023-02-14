package Controlador;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Libreria {

	public static String getFecha() {
		Date fecha = new Date();
		SimpleDateFormat formatodia = new SimpleDateFormat("dd-MM-yyyy");
		
		return formatodia.format(fecha);
	}

	public static String getIdticketsiguiente(String idticket) {
		String idticketnext = "";
		int num = Integer.parseInt(idticket) + 1;
		if (num < 10)
			idticketnext = "00" + String.valueOf(num).trim();
		else if ((num > 9) && (num < 100))
			idticketnext = "0" + String.valueOf(num).trim();
		else
			idticketnext = String.valueOf(num).trim();
		return idticketnext;
	}
}
