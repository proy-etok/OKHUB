package com.okhub.oho.interfaz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PHPConnector {
	
	private static String conStrURL = "http://okhub.net76.net/OHO/index.php?";

	
	  public static String funcion_PHP(String f)
	  {
		  	return funcion_PHP( f, null, null, null, null, null, null, null);
	  }
	  public static String funcion_PHP(String f, String p1)
	  {
		  	return funcion_PHP( f, p1, null, null, null, null, null, null);
	  }
	  public static String funcion_PHP(String f, String p1, String p2)
	  {
		  	return funcion_PHP( f, p1, p2, null, null, null, null, null);
	  }
	  public static String funcion_PHP(String f, String p1, String p2, String p3)
	  {
	  		return funcion_PHP( f, p1, p2, p3, null, null, null, null );
	  }
	  public static String funcion_PHP(String f, String p1, String p2, String p3, String p4)
	  {
	   		return funcion_PHP( f, p1, p2, p3, p4, null, null, null);
	  }
	  public static String funcion_PHP(String f, String p1, String p2, String p3, String p4, String p5)
	  {
		  	return funcion_PHP( f, p1, p2, p3, p4, p5, null, null);
	  }
	  public static String funcion_PHP(String f, String p1, String p2, String p3, String p4, String p5, String p6)
	  {
		  	return funcion_PHP( f, p1, p2, p3, p4, p5, p6, null);
	  }
	  public static String funcion_PHP(String f, String p1, String p2, String p3, String p4 , String p5 , String p6 , String p7) {try
	  {
	    	String comStr = conStrURL + "f=" + f + ((p1!=null)?("&p1="+p1):"")+ 
	    										((p2!=null)?("&p2="+p2):"")+ 
	    										((p3!=null)?("&p3="+p3):"")+ 
	    										((p4!=null)?("&p4="+p4):"")+
	    										((p5!=null)?("&p5="+p5):"")+
	    										((p6!=null)?("&p6="+p6):"")+
	    										((p7!=null)?("&p7="+p7):"");
	    	
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
//	    	System.out.print(e.getMessage());
	    	e.printStackTrace();
	    	//Trololololololo
	    } 
	    	return "";
	    }
	    
	
}
