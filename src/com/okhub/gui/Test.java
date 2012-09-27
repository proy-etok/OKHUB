package com.okhub.gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 */
	static SimpleDateFormat formatoDeFecha = new SimpleDateFormat( "dd-MM-yy HH:mm:ss" );
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		(new Ventana_Principal()).Crear_Ventana_Principal();
		formatoDeFecha.setLenient( false );
		Date fechaEnFormatoDate;
		try {
			
			fechaEnFormatoDate = formatoDeFecha.parse( "27-09-12 14:45:19" );
			System.out.println( fechaEnFormatoDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
