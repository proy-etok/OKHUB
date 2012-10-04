package com.okhub.main;

import javax.swing.UIManager;

import com.okhub.gui.*;


public class Application 
{

	/**
	 * @param args
	 * Comentario absurdo e innecesario 
	 */
	public static void main(String[] args) 
	{
		try {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e)
		{
			//nothing
			e.printStackTrace();
		}
		
		(new Ventana_Login_Utilidad()).Crear_Ventana_Login();
		/*
		Ventana_Login vl = new Ventana_Login();
		vl.Crear_Ventana_Login();
		*/
	}

}
