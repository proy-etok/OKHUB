package com.okhub;

import java.sql.ResultSet;
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
	
	
	public static User getUser(ResultSet rs)
	{
		
		return null;
	}
	
	public static User[] getuserList(ResultSet rs)
	{
		
		return null;
	}
}
