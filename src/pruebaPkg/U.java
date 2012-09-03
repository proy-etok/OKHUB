package pruebaPkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Admin
 */
public class U
{
    static BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
    public static String leerLinea()
    {
	String str;
        try
        {
             str = U.lector.readLine();
        }catch (Exception e)
        {
            System.out.print( e.toString() + e.getStackTrace().toString());
	    str = "";
        }        
	str = str.trim();
	return str;
    }
    
    public static int leerInt()
    {
    	int i;
    	String s;
        try
        {
             s = U.lector.readLine();
        }catch (Exception e)
        {
            System.out.print( e.toString() + e.getStackTrace().toString());
            s = "";
        }        
        s = s.trim();
		try
		{
			i = Integer.parseInt(s);
		}catch (Exception e)
		{
			i = -1000;
		}
		return i; 
			
    }
    
    public static char leerChar()
    {
	char c;
	
	try
        {
            c = (char) U.lector.read();
	    U.lector.readLine();
        }catch (Exception e)
        {
            System.out.print( e.toString() + e.getStackTrace().toString());
	    c = '-';
        }     	
	return c;
    }
}
