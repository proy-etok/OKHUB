package com.okhub.testing;



public class ThreadTest
{
	
	public static void main(String[] args) 
	{
		try {
			
			Jefazo jef = new Jefazo();
			
			jef.comenzar();
			
			jef.terminar();
			
		}catch(Exception e)
		{
			//nothing
			e.printStackTrace();
		}
	}

}
