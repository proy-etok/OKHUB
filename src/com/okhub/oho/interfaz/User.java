package com.okhub.oho.interfaz;


public class User 
{
	public String nombre;
	public String mail;
	public String pais;
	public char sexo;
	public String fecha_nacimiento;
	public int online;
	
	public User(){}
	
	public User( String nombre, String mail, 
				 String pais , char sexo , String fecha_nacimiento ) {
		
		this.mail = mail;
		this.nombre = nombre;
		
		
	}
	
	

}
