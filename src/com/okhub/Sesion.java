package com.okhub;

import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;
import java.io.*;


public class Sesion
{
	private String conStrURL = "http://okhub.net76.net/OHO/index.php?";
	
    private User user;
    public User getUser() { return this.user; };
    
    boolean existe_Usuario(String Nombre)
    {
    	boolean existe = false;
    	
    	String resultado = funcion_PHP("existe_Usuario",Nombre);
    	
    	resultado = resultado.trim();
    	
    	int resultadoInt = Integer.parseInt(resultado);
    	
    	if(resultadoInt == 1)
    		existe = true;
    	
    	return existe;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private String funcion_PHP(String f)
    {
    	return funcion_PHP( f, null, null, null, null);
    }
    
    public String funcion_PHP(String f, String p1)
    {
    	return funcion_PHP( f, p1, null, null, null);
    }
    
    private String funcion_PHP(String f, String p1, String p2)
    {
    	return funcion_PHP( f, p1, p2, null, null);
    }
    
    private String funcion_PHP(String f, String p1, String p2, String p3)
    {
    	return funcion_PHP( f, p1, p2, p3, null);
    }
    
    private String funcion_PHP(String f, String p1, String p2, String p3, String p4) {try
    {
    	String comStr = conStrURL + "f=" + f + ((p1!=null)?("&p1="+p1):"")+ 
    										((p2!=null)?("&p2="+p2):"")+ 
    										((p3!=null)?("&p3="+p3):"")+ 
    										((p4!=null)?("&p4="+p4):"");
    	
    	URL comURL = new URL(comStr);
    	URLConnection connURL = comURL.openConnection();
    	
    	BufferedReader BffR = new BufferedReader(new InputStreamReader(connURL.getInputStream()));
    	
    	String retorno = "";
    	String line = "";
    	do
    	{
    		retorno += (line + "\n");
    		line = BffR.readLine();
    		
    	}while(!line.contains("EOF"));
    	
    	return retorno;
    } catch(Exception e)
    {
//    	System.out.print(e.getMessage());
    	e.printStackTrace();
    	//Trololololololo
    } 
    	return "";
    }
    
}
