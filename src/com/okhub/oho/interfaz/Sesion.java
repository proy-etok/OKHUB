package com.okhub.oho.interfaz;

import com.okhub.data.MD5;


public class Sesion
{
	
    private User user;
    public User getUser() { return this.user; };
    
    public static boolean existeUsuario(String usuario)
    {
    	return tomarResultado( PHPConnector.funcion_PHP("existe_Usuario" , usuario) );
    }
    
    public static boolean existeCorreo( String correo )
    {
    	return tomarResultado( PHPConnector.funcion_PHP("existe_Correo", correo ) );
    }
    
    public static boolean comprobarPassword(String usuario, String password)
    {
    	return tomarResultado( PHPConnector.funcion_PHP("comprobar_Pass",usuario, MD5.getHash( password ) ) );
    }	
    
	
    
    public static boolean registrarUsuario ( String nombre, String password , String mail , String pais , char sexo , String Fecha ) {
    	
    	return tomarResultado( PHPConnector.funcion_PHP("registrar_Usuario" , nombre , MD5.getHash(password) , mail , pais , String.valueOf(sexo) , Fecha ));
    	
    }
    
    
    private static boolean tomarResultado( String resultado ){
    	
    	System.out.println(resultado);
    	if(Integer.parseInt(resultado.trim()) == 1)
    		return true;
    	
    	return false;
    	
    	
    }
    
    
  
}
