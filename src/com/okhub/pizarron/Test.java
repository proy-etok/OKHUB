package com.okhub.pizarron;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.okhub.oho.interfaz.Sesion;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		JFrame window = new JFrame("prueba");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		window.add(new PizarronPanel(new Sesion()));
		window.setBounds(100, 100, 400, 400);
		window.setPreferredSize(new Dimension (400,400));
		

	}

}
