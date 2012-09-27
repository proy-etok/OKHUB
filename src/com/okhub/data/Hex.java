package com.okhub.data;

import java.io.ByteArrayOutputStream;

public class Hex {

	public static String toHex(String text)
	{
		byte[] byteData = text.getBytes();
		
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < byteData.length; i++) 
        {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return  sb.toString();
	}
	
	public static String fromHex(String hex)
	{
			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			for (int i = 0; i < hex.length(); i+=2) 
			{
			int b = Integer.parseInt(hex.substring(i, i + 2), 16);
			bas.write(b);
			}
			return bas.toString();		
	}
}
