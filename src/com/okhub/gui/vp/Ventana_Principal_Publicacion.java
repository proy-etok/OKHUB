package com.okhub.gui.vp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

import com.okhub.oho.interfaz.Publicacion;
import com.okhub.oho.interfaz.Sesion;

import net.miginfocom.swing.MigLayout;

public class Ventana_Principal_Publicacion  extends JPanel{
	
	
	
	public static JScrollPane verPublicaciones ( final String amigo , final Publicacion[] publicaciones ) 
	{
		
		JPanel panelPublicacion = new JPanel( new MigLayout("","[grow]","15[c,pref]") );
		JLabel lblNombre = new JLabel( "Publicaciones de " + amigo );
		
		
		panelPublicacion.add(lblNombre , "growx,wrap" );
		
		JSeparator separador = new JSeparator( JSeparator.HORIZONTAL );
		separador.setBorder( new BevelBorder(BevelBorder.RAISED));
		
		panelPublicacion.add( separador , "growx,wrap" );
		
		for ( Publicacion p : publicaciones ) {
			panelPublicacion.add( new JLabel (p.hora) , "left,wrap" );
			panelPublicacion.add( new JLabel (p.contenido) , "growx,wrap" );
			panelPublicacion.add( new JLabel (p.adjunto) , "growx,wrap" );
			JSeparator separador2 = new JSeparator( JSeparator.HORIZONTAL );
			separador2.setBorder( new BevelBorder(BevelBorder.RAISED));
			panelPublicacion.add( separador2 , "growx,wrap" );
		}
		
		JScrollPane spPublicacion = new JScrollPane(panelPublicacion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		return spPublicacion;
		
	}

}
