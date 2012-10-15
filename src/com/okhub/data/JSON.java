package com.okhub.data;


import com.google.gson.*;
import com.okhub.oho.interfaz.Mensaje;
import com.okhub.oho.interfaz.Publicacion;
import com.okhub.oho.interfaz.User;

public class JSON
{

	/**
	 * @param args
	 */
	public static User getUser ( String usuarioStr ){
		
		Gson g = new Gson();
		return g.fromJson(usuarioStr, User.class);
		
		
	}
	
	public static String getStrUsuario ( User usuario ) {
		
		Gson g = new Gson();
		return g.toJson( usuario );
		
	}
	
	public static User[] getUserArray ( String usuariosStr ) {
		
		Gson g = new Gson();
		return g.fromJson(usuariosStr, User[].class);
		
	}
	
	public static String getUserStringArray ( User[] usuarios ) {
		
		Gson g = new Gson();
		return g.toJson( usuarios );
		
	}
	
	public static Mensaje[] getMsgArray ( String mensajesStr ) {
		
		Gson g = new Gson();
		return g.fromJson( mensajesStr, Mensaje[].class);
		
	}
	
	public static String getMsgStringArray ( Mensaje[] mensajes ) {
		
		Gson g = new Gson();
		return g.toJson( mensajes );
		
	}

	public static Publicacion[] getPublicacionesArray(String publicacionesStr) {
		Gson g = new Gson();
		return g.fromJson( publicacionesStr, Publicacion[].class);
	}
	
/*public static void main(String[] args) throws Exception	
	{
		User u1 = new User();
		User u2 = new User( "josue","josue@mail.com",20);
		User u3 = new User( "jorge","josrge@hotmail.com",30);
		
		System.out.println(u1.nombre);
		System.out.println(u2.nombre);
		System.out.println(u3.nombre);
		System.out.println("__________________");
		
		System.out.println(getStrUsuario(u1));
		System.out.println(getStrUsuario(u2));
		System.out.println(getStrUsuario(u3));
		System.out.println("__________________");
		System.out.println(getUser(getStrUsuario(u2)).nombre);
		System.out.println(getUser(getStrUsuario(u3)).nombre);
		
		User[] arrayU= new User[] {u1,u2,u3};
		
		System.out.println("__________________");
		System.out.println( getUserStringArray(arrayU));
		System.out.println( getUserArray(getUserStringArray(arrayU)));
		
		System.out.println("__________________");
		String hex = Hex.toHex(getUserStringArray(arrayU));
		System.out.println(hex);
		System.out.println(Hex.fromHex(hex));
		
		User[] arrayU2 = getUserArray(getUserStringArray(arrayU));
		
		System.out.println(arrayU2[1].nombre);
	}*/

}
