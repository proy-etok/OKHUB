package com.okhub.data;

import java.security.MessageDigest;

public class MD5 {

	public static String getHash( String password ) 
	{ 
		try
    	{
	 
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString( (byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	        System.out.println( "Digest(in hex format):: " + sb.toString() );
	        return sb.toString();
	        
	    } catch( Exception e ) {
	    	
	    	System.out.println(e.getMessage());
	    	e.printStackTrace();
	    	return "";
	    	
	    }
	}
}
