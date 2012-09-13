package mysqlPkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Sesion
{
	private String conString = "jdbc:mysql://190.192.77.168:3306/okhub";
	
	private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    
	public Sesion()
	{
		
		
	}

}
