package com.okhub.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Mensaje;
import com.okhub.oho.interfaz.Sesion;

public class Ventana_Principal_Utilidad extends Ventana_Principal {
	
	Sesion S;
	JTextField tfenviar;
	
	/**
	 * Launch the application.
	 * @return 
	 */
	static public void Crear_Ventana_Principal( final Sesion Ses) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Principal_Utilidad window = new Ventana_Principal_Utilidad(Ses);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ventana_Principal_Utilidad(Sesion Ses) {
		super(Ses);
		this.S = Ses;
		System.out.println( );
		
		friendsPane.botonRefrescar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RefrescarListaAmigos();
			}
		});
		
		friendsPane.botonRefrescar.doClick();
		
		friendsPane.miAgregarAmigo.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog( friendsPane.menu , "Ingrese su nombre " , "Agregar amigo" , JOptionPane.QUESTION_MESSAGE );
/*				if ( input.contains("@") )
					if ( S.existeCorreo( input ) ) {
						JOptionPane.showMessageDialog(menu, input + " Agregadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
						return;
					}*/
				if ( S.existeUsuario( input ) ) {
					S.agregarAmigo( input );
					JOptionPane.showMessageDialog(friendsPane.menu, input + " Agregadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
					RefrescarListaAmigos();
					return;
				}
				
				JOptionPane.showMessageDialog(friendsPane.menu, input + " no existe" , "Error" , JOptionPane.INFORMATION_MESSAGE );
				
			}
		});

	}
	
	
	public void RefrescarListaAmigos (  ) {
		JLabel[] amigosLbl = friendsPane.actualizarListaAmigos( S.obtenerListaAmigos() );
		
		if ( amigosLbl != null )
		for ( final JLabel lbl : amigosLbl ) {
			
			lbl.addMouseListener( new MouseAdapter( ) {
				
				public void mouseClicked ( MouseEvent e ) {
					
					if ( e.getButton() == MouseEvent.BUTTON3 ) {
						JPopupMenu popupMenu = new JPopupMenu();
						JMenuItem miChat = new JMenuItem("Abrir chat");
						miChat.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								agregarTabChat( lbl.getText() );
							}
						});
						popupMenu.add(miChat);
						JMenuItem miEliminar = new JMenuItem("Eliminar Amigo");
						miEliminar.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								if ( S.rechazarAmistad( lbl.getText() ) )  {
									JOptionPane.showMessageDialog( lbl , lbl.getText() + " eliminadisimo"   , "Tenes un amigo menos" , JOptionPane.INFORMATION_MESSAGE );
									RefrescarListaAmigos();
								}
							}
						});
						popupMenu.add(miEliminar);
						
						popupMenu.show(lbl, e.getX(), e.getY() );
						
					}
					if ( e.getClickCount() == 2 ) {
						
						agregarTabChat( lbl.getText() );
					}
				}
				
				public void mousePressed (MouseEvent e){
					
					lbl.setForeground( Color.blue );
					
				}
				
				public void mouseReleased (MouseEvent e){
					lbl.setForeground( Color.black );
				}
			} );
		}
		
		friendsPane.friendsList.repaint();
	}
	
	public void actualizarListaMensajes ( String amigo, JTextPane chat ){
		
		Mensaje[] mensajes = S.obtenerListaMensaje( amigo );
		String mensaje = "";
		if ( mensajes.length == 0 ) return;
		
		chat.setText("");
		for ( int i = mensajes.length - 1 ; i > -1 ; i-- ) {
	
			mensaje += mensajes[i].origen + " [" ;
			mensaje += mensajes[i].hora + "] : \n";
			mensaje += ">>" + mensajes[i].mensaje + "\n";
	
			System.out.println(mensajes[i].destino);
			System.out.println(S.getUser());
			if ( mensajes[i].destino.toLowerCase().contentEquals( S.getUserStr().toLowerCase() ) ) {
				S.acusarRecibo( mensajes[i].origen , mensajes[i].hora );
				mensajes[i].recibido = 1;
			}	
		
		chat.setText( mensaje );
		}
		
	}
	
	public void agregarTabChat ( final String title )
	{
		if ( existeConversacion(tabbedPane, title ) ){
			tabbedPane.setSelectedIndex( tabbedPane.indexOfTab(title) );
			return;
		}
		MigLayout ml = new MigLayout("","grow,fill","[80%|20%]");
		JPanel panel = new JPanel( ml );
		final JTextPane chat = new JTextPane();
		chat.setEditable(false);
		JScrollPane scroll = new JScrollPane( chat , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		panel.add( scroll , "grow,wrap" );
		tfenviar = new JTextField( "" );
		panel.add( tfenviar , "split 3, bottom, growx" );
		JButton jbenviar = new JButton("Enviar");
		panel.add( jbenviar , "bottom");
		JButton jbactualizar = new JButton("Actualizar");
		panel.add( jbactualizar , "bottom,right");
		tabbedPane.addTab( title , panel );
		jbenviar.addActionListener( new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ( !tfenviar.getText().equals( "" ) )
				S.enviarMensaje( title , tfenviar.getText() );
			}
		});
		jbactualizar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarListaMensajes( title , chat );
			}
		});
		
		tabbedPane.setSelectedIndex( tabbedPane.indexOfTab( title ) );
		agregar_botonX( title );
	}
}
