package com.okhub.data;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class dataLogin {
	
	
	final static String path = System.getProperty("user.dir")+ File.separator;
	final static File dir = new File ( path , "Data" );
	final static File opciones_login = new File( dir , "Datos.dat");
	static RandomAccessFile data;
	
	
	public static boolean verificar_existeData () {
		
		if ( !dir.exists() ) dir.mkdirs();
		if ( !opciones_login.exists() ) {
			try {
		        System.out.println( "---------------------- " );         
		        System.out.println( "getAbsolutePath: " + dir.getAbsolutePath() ); 
		        System.out.println( "isDirectory: " + dir.isDirectory() ); 
		        System.out.println( "---------------------- " ); 
		        System.out.println( "getName: " + opciones_login.getName() );
	            System.out.println( "isFile: " + opciones_login.isFile() );  
				opciones_login.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return opciones_login.exists();
		
	}
	
	public static boolean crear_accesoData () {
		
		if ( !verificar_existeData() )
			return false;
		
		try {
			data = new RandomAccessFile( opciones_login , "rw" );
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	
	
	public static boolean guardar_usuario ( String usuario ) {
		
		if ( !crear_accesoData() )
			return false;
		while (true) {
			try {
				if ( data.readUTF().contains( usuario ) )
					return false;
			} catch ( EOFException e ) {
				break;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		try {
			data.skipBytes( (int) data.length() );
			data.writeUTF( usuario );
			return true;
		} catch ( EOFException e) {
			System.out.println("EOF");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static String devolver_listaUsuarios () {
		
		StringBuilder sb = new StringBuilder();
		if ( !crear_accesoData() ) {
			System.out.println( "piyo" );
			return "";
		}
		
		try {
			data.seek(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while ( true ) {
			try {
				sb.append( data.readUTF() + " " );
				System.out.println( sb.toString() );
			} catch (EOFException e) {
				System.out.println("EOF");
				return sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			} catch (Exception e ) {
                System.out.println(e.getMessage());
                break;
            }
		}
		return "";
		
	}

	public static boolean guardar_contraseña ( char[] contraseña , String usuario ) {
		
		if (!crear_accesoData())
			return false;
		
		while ( true ) {
			try {
				System.out.println( data.readUTF() );
				if ( data.readUTF().contains( usuario) )
					break;
				
			} catch (EOFException e ){
				System.out.println( "EOF" );
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} 
		}
		
		try {
			for ( char c : contraseña )
				data.writeChar(c);
			data.writeBoolean( true );
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
//	public static char[] devolver_contraseña ( String usuario ) {
		
//		if ( data.)
		
//	}
}
