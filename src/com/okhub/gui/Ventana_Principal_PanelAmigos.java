package com.okhub.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.User;

import net.miginfocom.swing.MigLayout;

public class Ventana_Principal_PanelAmigos extends JPanel{

	JPanel friendsList;
	Sesion S;
	JButton botonRefrescar;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem miAgregarAmigo;
	JMenuItem miEliminarAmigo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Ventana_Principal_PanelAmigos( final Sesion S ) {
		
		this.S = S;
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		MigLayout mlf = new MigLayout( "","grow,fill","[][grow,fill]");
		setLayout(mlf);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuBar.add( menu );
		miAgregarAmigo = new JMenuItem("Agregar amigo");
		menu.add(miAgregarAmigo);
		miEliminarAmigo = new JMenuItem("bla");
		menu.add(miEliminarAmigo);
		menu.setFocusable( true );
		
		add( menuBar , "center,split 2");
		
		botonRefrescar = new JButton("R");
		add( botonRefrescar , "wrap");
		
		MigLayout mlfl = new MigLayout( "", "[]", "8:10:15,grow");
		friendsList = new JPanel(mlfl);
		friendsList.setEnabled( true );
		friendsList.setName("friendsList");
		friendsList.setBackground( Color.white );
		friendsList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		 
		JScrollPane scroll = new JScrollPane( friendsList );
		scroll.setViewportView( friendsList );

		add( scroll, "grow,center");
	
	}
	
	/**
	 * @param
	 * @return
	 * */
	
	public JLabel[] actualizarListaAmigos ( User[] amigos ) {
		
		for ( Component c : friendsList.getComponents())
			friendsList.remove(c);
		
		friendsList.revalidate();
		 
		for (User u : amigos) {
			System.out.println(u.nombre);
			System.out.println(u.online);
		}
		System.out.println("--------");
		if (amigos.length == 0)
			return null;
		
		JLabel amigosLbl[] = new JLabel[amigos.length];

		for ( int i = 0 ; i < amigosLbl.length ; i++ ) {
			amigosLbl[i] = new JLabel("");
			amigosLbl[i].setFont( new Font ( "Tahoma" , Font.PLAIN , 12 ) );
			amigosLbl[i].setBackground( getBackground() );
			amigosLbl[i].setEnabled(true);
			amigosLbl[i].setText(amigos[i].nombre );
			
			if ( amigos[i].online != 0 )
				amigosLbl[i].setIcon(new ImageIcon( getClass().getResource( "ball_green.png" ) ) );
			else
				amigosLbl[i].setIcon(new ImageIcon( getClass().getResource( "ball_red.png" ) ) );
			
			friendsList.add(amigosLbl[i] , "grow,top,wrap" );

		}	
		
		friendsList.revalidate();
		
		return amigosLbl;
		
			
	}
	

}
