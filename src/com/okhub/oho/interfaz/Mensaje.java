package com.okhub.oho.interfaz;

public class Mensaje {
	
	public String origen;
	public String destino;
	public String mensaje;
	public String hora;
	public int recibido;
	
	public Mensaje( String o , String d , String m , String h , int r ) {
		
		this.origen = o;
		this.destino = d;
		this.mensaje = m;
		this.hora = h;
		this.recibido = r;
		
	}
	

}
