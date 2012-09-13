package com.okhub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class Sesion
{
	private String conString = "jdbc:mysql://190.192.77.168:3306/okhub";
	
	private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
    public ResultSet result = null;
    
    private User user;
    public User getUser() { return this.user; };
    
    
	public int crearSesion()
	{
		int result = -1;
		
		try 
		{
			con = DriverManager.getConnection(conString, "root", "1234");
			
		} catch (SQLException e) 
		{
			result = - Math.abs( e.getErrorCode() );
			con = null;
		}
		
		if(con != null)
		{
			try
			{					
				st = con.createStatement();
	            rs = st.executeQuery("SELECT VERSION()");
	
	            while (rs.next())
	            {
	            	result = Math.abs( rs.getInt(1) );
	            }
	            
			}catch (SQLException e)
	        {
				result = - Math.abs( e.getErrorCode() );
				borrarSesion();
				con = null;
	        }
		}
		
		return result;
	}
	
	public int borrarSesion()
	{
		int result = 1;
		
		if(con != null)
		{
			try 
			{
				con.close();
				
			} catch (SQLException e) 
			{
				result = - Math.abs( e.getErrorCode() );
			}			
		}			
		return result;
	}

	
	
	

}
