package com.okhub.pizarron;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Sesion;

public class PizarronPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PizarronPanel(Sesion S) {
		
		Pizarron okPizarron = new Pizarron(S);
		okPizarron.addMouseListener(okPizarron);
		okPizarron.addMouseMotionListener(okPizarron);
		
		okPizarron.setBackground(Color.red);
		setLayout( new MigLayout( "","0:100:500,fill,grow","0:100:300,fill,grow" ) );
		add(okPizarron,"center,grow");
		
		
	}

}
