package com.okhub.gui.vp;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Publicacion;
import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.threading.Cadete;
import com.okhub.oho.interfaz.threading.Jefe;
import com.okhub.oho.interfaz.threading.Tarea;

/**
 * Clase creadora de un JScrollPane con un panel que muestra 
 * las publicaciones del usuario.
 * 
 * @author Gseva
 *
 */

public class Ventana_Principal_Publicacion  extends JScrollPane implements Jefe {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * La sesion actual
	 */
	Sesion S;
	/**
	 * El usuario cuyas publicaciones se van a imprimir
	 */
	private String user;
	JPanel panelPublicacion;
	
	
	/**
	 * Crea un panel que muestra las publicaciones del usuario
	 * @param usuario - Nombre del usuario cuyas publicaciones se desea ver
	 * @param S - La sesion global
	 */
	public Ventana_Principal_Publicacion ( final String usuario , final Sesion S )  {
		this.user = usuario;
		if ( usuario == null ) {
			this.user = S.getUserStr();
		}
		this.S = S;
		setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
		setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		panelPublicacion = new JPanel( new MigLayout( "", "grow" , "15[c,pref]" ) );
		JLabel lblNombre = new JLabel( "Publicaciones de " + user );
		
		panelPublicacion.add(lblNombre , "growx,wrap" );
		
		JSeparator separador = new JSeparator( JSeparator.HORIZONTAL );
		separador.setBorder( new BevelBorder(BevelBorder.RAISED));
		
		panelPublicacion.add( separador , "growx,wrap" );
		S.agregarTarea( new Tarea("op PARA: imprimir_Publicaciones", this , new Object[] {user }));
		
		setViewportView( panelPublicacion );
		
	}
	
	public void imprimirPublicaciones(Publicacion[] publicaciones ) {
		
		for ( Publicacion p : publicaciones ) {
			panelPublicacion.add( new JLabel ( p.hora ) , "left,wrap" );
			panelPublicacion.add( new JLabel ( p.contenido ) , "growx,wrap" );
			panelPublicacion.add( new JLabel ( p.adjunto ) , "growx,wrap" );
			JSeparator separador2 = new JSeparator( JSeparator.HORIZONTAL );
			separador2.setBorder( new BevelBorder(BevelBorder.RAISED));
			panelPublicacion.add( separador2 , "growx,wrap" );
		}
		
		panelPublicacion.repaint();
		
	}
	
	public void entregarTarea(Tarea t) {
		
		if( t.nombre.contains( " PARA: " ) )
			if( t.nombre.split( " PARA: " ).length > 1 )
			{
				switch( ( t.nombre.split(" PARA: ") )[1] )
				{
					case "imprimir_Publicaciones": 
						imprimirPublicaciones((Publicacion[])t.resultado);
						break;
						
					default: break;
				}
			}				
		
	}
	
}
