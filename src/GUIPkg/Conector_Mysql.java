package GUIPkg;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conector_Mysql {

	
	private Connection conexion = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	
	public Conector_Mysql () {
		

		try {
			
			
//			conexion = DriverManager.getConnection( "jdbc:mysql://3hb.com.ar:3306/okhub" , "root" , "1234");
			conexion = DriverManager.getConnection( "jdbc:mysql://localhost:3306/okhub" , "root" , "1234");
			System.out.println( "conexion exitosa" );
	        
		
		} catch (Exception e) {

			e.printStackTrace();
		}		
	
	
	}
	
	

	/**
	 * Metodo que verifica si hay un usuario con el nombre y contraseña ingresados.
	 * @param
	 * */
	
	public boolean verificar_usuario ( String usuario , String contraseña ) {
		
		
		try {
//			Conectar();
			String mostrar = "";
			st = conexion.createStatement();
			rs = st.executeQuery( "SELECT mail,Fecha_Nacimiento,Pais,Sexo FROM usuarios " +
									 "WHERE id='" + usuario + "' AND pass ='" + contraseña + "'");
			while (rs.next()) {
				mostrar = rs.getString( "mail" ) + " " +  rs.getString( "fecha_nacimiento" ) + "\n"
						 + rs.getString( "pais" ) + " " + rs.getString( "Sexo" );
				JOptionPane.showMessageDialog( null, mostrar ,
						usuario , JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
			return false;
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	/**
	 * Metodo que agrega un usuario a la base de datos
	 * @param
	 * */
	
	public boolean agregar_usuario ( String usuario , String contraseña , String correo ,
										 String fecha , char sexo , String pais )
	{
		
		try {
			st = conexion.createStatement();
			String datos = "'"+usuario+"','"+contraseña+"','"+correo+"'"+
					  ",'"+fecha+"','"+sexo+"','"+pais+"'";
			st.executeUpdate( "INSERT INTO Usuarios(id,pass,mail,fecha_nacimiento,sexo,pais) VALUES (" +
							  datos +")");
			JOptionPane.showMessageDialog( null, "Se ha registrado con exito:\n" + datos ,
					"Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE);
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, "No se ha podido completar el registro:\n" + e.getMessage() ,
					"Error" , JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		
	}
	
	
}
