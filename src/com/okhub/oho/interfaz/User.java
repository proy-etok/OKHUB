package com.okhub.oho.interfaz;

import java.text.DateFormat;

public class User 
{
	public String nombre;
	public int edad;
	public String mail;
	public String pais;
	public char sexo;
	public DateFormat fecha_nacimiento;
	
	public boolean online;
	
	public User(){}
	
	public User( String nombre, String mail , int edad) {
		
		this.edad = edad;
		this.mail = mail;
		this.nombre = nombre;
		
	}
	
	

}
