package com.okhub.oho.interfaz;

import com.okhub.data.JSON;
import com.okhub.data.MD5;


public class Sesion
{
    private User user = null;
    
    public User getUser() { return this.user; }
    public String getUserStr() { return this.user.nombre; };
    public boolean getOnline() { return tomarResultado(String.valueOf(this.user.online)); };

    public boolean iniciarSesion(String usuario, String password)
    {
    	if(comprobarPassword( usuario,  password))
    	{
    		user = obtenerUsuario(usuario);
    		if( user.online == 0) {
	    		user.online = ponerOnline( usuario , true )?1:0;
	    		return true;
    		}
    	}
    	user = null;
    	return false;
    }
    
    public boolean ponerOnline( String usuario , boolean bool )
    {
    	return tomarResultado( PHPConnector.funcion_PHP( "Poner_Online" , usuario , String.valueOf( (bool) ?  1 : 0 ) ) );
    }
    
    public boolean comprobarPassword(String usuario, String password)
    {
    	return tomarResultado( PHPConnector.funcion_PHP("comprobar_Pass",usuario, MD5.getHash( password ) ) );
    }	
    
    public static boolean existeUsuario(String usuario)
    {
    	return tomarResultado( PHPConnector.funcion_PHP("existe_Usuario" , usuario) );
    }
    
    public static boolean existeCorreo( String correo )
    {
    	return tomarResultado( PHPConnector.funcion_PHP("existe_Correo", correo ) );
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
    
    public User[] obtenerListaAmigos ( ) {
    	
    	String usuariosStr = (PHPConnector.funcion_PHP("obtener_Lista_Amigos" , user.nombre ));
    	return JSON.getUserArray(usuariosStr);
    	
    }
    
    public Mensaje[] obtenerListaMensaje ( String destino ) {
    	
    	String mensajesStr = (PHPConnector.funcion_PHP("leer_Mensajes" , user.nombre , destino ) );
    	System.out.println(mensajesStr);
    	Mensaje[] mensajes =  JSON.getMsgArray( mensajesStr );
    	String mensaje = "";
    	for ( int i = mensajes.length - 1 ; i > -1 ; i-- ) {
			
			mensaje += mensajes[i].origen + " [" ;
			mensaje += mensajes[i].hora + "] : \n";
			mensaje += mensajes[i].mensaje + "\n";
			
			System.out.println(mensaje);
			mensaje = "";
			
		}
    	return mensajes;
    }
    
    public boolean enviarMensaje ( String destino , String mensaje ) {
    	
    	return tomarResultado( PHPConnector.funcion_PHP( "enviar_Mensaje" , user.nombre , destino , mensaje ) );
    	
    }

    public boolean acusarRecibo ( String destino , String hora ){
    	
    	return tomarResultado( PHPConnector.funcion_PHP( "acusar_Recibo" , user.nombre , destino , hora ) );
    	
    }
    
    public User obtenerUsuario (  String usuario ) {
    	
    	String usuarioStr = PHPConnector.funcion_PHP("obtener_Usuario" , usuario );
    	System.out.println( usuarioStr );
    	return JSON.getUser( usuarioStr );
    	
    }
}
