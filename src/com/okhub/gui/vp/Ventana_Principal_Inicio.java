package com.okhub.gui.vp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.User;

import net.miginfocom.swing.MigLayout;

/**
 * 
 * Clase creadora del panel de Inicio. Extiende JPanel.
 * Contiene un panel de bienvenida, las notificaciones de amigos
 * y un panel para agregar publicacion.
 * @author Gseva
 * @version 0.0.1
 * @see Ventana_Principal
 *
 */
public class Ventana_Principal_Inicio extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Copia de la sesion. Es usada por esta clase.
	 */
	Sesion S;

	JTextField tfPublicar;
	JTextField tfAdjunto;
	
	/**
	 * Boton de la ventana de inicio
	 */
	JButton botonRefrescarInicio;
	
	
	/**
	 * Crea un panel de inicio con las notificaciones de amistad,
	 * panel de bienvenida y panel de publicacion.
	 * 
	 * @param Ses Sesion con la que se loguea el usuario
	 * @param pa Puntero al panel de amigos
	 * @see Sesion#obtenerAmigosEspera()
	 * @see Sesion#agregarPublicacion(String, String)
	 */
	public Ventana_Principal_Inicio ( final Sesion Ses , final Ventana_Principal_PanelAmigos pa ) {
		
		this.S = Ses;
		setLayout(new MigLayout("fillx","[grow]","5[pref]"));
		JTextField panelBienvenida = new JTextField();
		panelBienvenida.setEditable(false);
		panelBienvenida.setText( "Bienvenido, " + S.getUserStr() + "!" );
		add( panelBienvenida, "growx,split 2");
		
		botonRefrescarInicio = new JButton( "R" );
		
		add( botonRefrescarInicio, "wrap");
		
		User[] amigosEspera = Ses.obtenerAmigosEspera();
		if ( amigosEspera != null ) {
			for (final User amigo : amigosEspera ) {
				final JPanel panelAmigo = new JPanel(new MigLayout("fillx","[grow]","10[]"));
				JLabel labelAmigo = 
						new JLabel( "Tiene una invitacion de "+amigo.nombre );
				JButton aceptarAmigo = new JButton( "Aceptar" );
				JButton rechazarAmigo = new JButton( "Rechazar" );
				
				aceptarAmigo.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if ( S.aceptarAmistad( amigo.nombre ) ) {
							JOptionPane.showMessageDialog( panelAmigo , amigo.nombre + " agregadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
							pa.botonRefrescar.doClick();
							panelAmigo.removeAll();
							remove(panelAmigo);
							repaint();
						}
					}
				});
				rechazarAmigo.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if ( S.rechazarAmistad( amigo.nombre) ) {
							JOptionPane.showMessageDialog( panelAmigo , amigo.nombre + " rechazadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
							pa.botonRefrescar.doClick();
							panelAmigo.removeAll();
							remove(panelAmigo);
							repaint();
						}
					}
				});
				
				panelAmigo.add(labelAmigo , "growx,split 3");
				panelAmigo.add(aceptarAmigo ,"right");
				panelAmigo.add(rechazarAmigo , "right");
				panelAmigo.add(new JSeparator(JSeparator.HORIZONTAL) 
				, "newline,growx");
				
				add(panelAmigo , "top,growx,wrap");
				
			}
		}
		
		tfPublicar = new JTextField();
		tfAdjunto = new JTextField();
		JButton btnPublicar = new JButton("Publicar");
		
		add(new JLabel("Hacer una Publicacion"), "width max(30% , pref),split 2");
		add(tfPublicar , "wrap,growx");
		add(new JLabel("Agregar contenido"), "width max(30% , pref),split 2");
		add(tfAdjunto , "wrap,growx");
		
		btnPublicar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				
				if ( S.agregarPublicacion( tfPublicar.getText(), tfAdjunto.getText() ) ) {
					tfPublicar.setText("");
					tfAdjunto.setText("");
				}

			}
		});

		add(btnPublicar , "right" );

	}

}
