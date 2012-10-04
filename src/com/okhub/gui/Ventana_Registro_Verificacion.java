package com.okhub.gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.okhub.oho.interfaz.Sesion;

/**
 * @Author GSeva
 * @see Ventana_Registro_Utilidad
 * @version 0.0.1
 * 
 * Clase de verificacion de contenido de componentes de texto
 * 
 * */

public class Ventana_Registro_Verificacion  {
	
	
	
// 	Valores 
	
	final int USUARIO = 0;
	final int CONTRASEÑA = 1;
	final int DIRCORREO = 2;
	final int FECHA = 3;
	final int PAIS = 4;
	
	boolean[] ingresoValido = { false, false, false, false , false };
	
//	Formato de fecha simple
	
	SimpleDateFormat formatoDeFecha = new SimpleDateFormat( "yyyy-MM-dd" );
	
	
//	Expresion regular que comprueba el string del correo
//	Ver clases java.util.regex. Matcher y Pattern
//	En mi repositorio de github en wiki deje un link con explicacion de las
//	expresiones regulares.
	
	String regularCorreo = 
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	
	public String Verificar_Datos_Contraseña( char[] password , char[] repetir_password ) {
		
		while ( password.length > 0 ) {
			if ( password.length <= 7 ) {
				ingresoValido[CONTRASEÑA] = false;
				return  "*La contraseña debe tener 8 o más caracteres";
			}
			
			for ( char c : password ) {
				if ( !Character.isLetterOrDigit( c ) ) {
					
					ingresoValido[CONTRASEÑA] = false;
					return  "*La contraseña solo puede tener letras o numeros";
					
				}
			}
			
			System.out.println(String.valueOf( password ).hashCode());
			System.out.println(String.valueOf( repetir_password ).hashCode());
			
			if ( !String.valueOf( password ).contentEquals( String.valueOf( repetir_password ) ) ) {
				
				ingresoValido[CONTRASEÑA] = false;
				return  "*Las contraseñas no coinciden";
				
			}
			
			
			ingresoValido[CONTRASEÑA] = true;
			return  "Contraseña valida";
		}
		
		ingresoValido[CONTRASEÑA] = false;
		return "";
		
	}
	
	public String Verificar_Datos_Usuario( String usuario )
	{
		
		while ( !usuario.isEmpty() ) {
			
//			
			if ( usuario.length() < 4 ) {
				ingresoValido[USUARIO] = false;
				return "*El usuario debe tener mas de 4 caracteres";
			}
			for ( char c : usuario.toCharArray() ) {
				if ( !Character.isLetterOrDigit( c ) ) {
					ingresoValido[USUARIO] = false;
					return "*El usuario solo puede tener letras o numeros";
					
				}
			}
			
			if ( Sesion.existeUsuario(usuario) ) {
				ingresoValido[USUARIO] = false;
				return "*El nombre ya está ocupado";
			}
				
			
			ingresoValido[USUARIO] = true;
			return "Nombre de usuario valido";
		}
		
		ingresoValido[USUARIO] = false;
		return "";
		
	}
	
	public String Verificar_Datos_Correo( String correo , String repetir_correo) {
		
		
		while ( !correo.isEmpty() ) {
			
			Pattern patternCorreo = Pattern.compile( regularCorreo );
			Matcher matcherCorreo = patternCorreo.matcher( correo );
			if ( !matcherCorreo.matches() ) {
				ingresoValido[DIRCORREO] = false;
				return "*Formato de correo invalido";
			}
			
			if ( Sesion.existeCorreo(correo) ) {
				ingresoValido[DIRCORREO] = false;
				return "*El correo ya está en uso";
			}

			if ( !correo.contentEquals( repetir_correo ) ) {
				ingresoValido[DIRCORREO] = false;
				return "*Las direcciones de correo no coinciden";
			}
			ingresoValido[DIRCORREO] = true;
			return "Direccion de correo valida";
		}
		
		ingresoValido[DIRCORREO] = false;
		return "";
		
	}
	
	public String Verificar_Datos_Fecha( String fecha )
	{
		String aux[] = fecha.split( "-" );
		for ( int i = 0 ; i < aux.length ; i++ )
			System.out.println( aux[i]);
			
		
		while ( !fecha.isEmpty() ) {
			
			
			try {
				formatoDeFecha.setLenient( false );
				Date fechaEnFormatoDate = formatoDeFecha.parse( fecha );
				System.out.println( fechaEnFormatoDate.toString() );
				
				int año = Integer.parseInt( aux[0] );
				
				if  ( año < 1900 || año > 2012 ) {
					ingresoValido[FECHA] = false;
					return "*Asi que naciste en el " + año + ", contame más";
				}
				
			} catch ( ParseException e ) {
				ingresoValido[FECHA] = false;
				return "*Fecha invalida";
			}
			
			ingresoValido[FECHA] = true;
			return "Fecha valida";
		}
		
		ingresoValido[FECHA] = true;
		return "";
		
	}
	
	public void Verificar_Datos_Pais ( String pais ) 
	{
		
		if ( pais.isEmpty() )
			ingresoValido[PAIS] = false;
		
		ingresoValido[PAIS] = true;
	
	}
	
} // Fin Clase Ventana_Registro_Verificacion
