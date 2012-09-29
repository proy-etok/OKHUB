package com.okhub.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Mensaje;
import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.User;

public class Ventana_Principal_Utilidad extends Ventana_Principal {
	
	Sesion S;
	JTextArea chat;
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
		list.addMouseListener( new MouseAdapter( ) {
			
			public void mouseClicked ( MouseEvent e ){
				
				if ( e.getClickCount() == 2 ) {
					
					String[] title = list.getSelectedValue().split(" ");
					while( !existeConversacion( tabbedPane, title[0] ) ){
						agregarTabChat( title[0] );
					}
				}
				
			}
		});
		
		actualizarListaAmigos( S.getUserStr() );
		botonRefrescar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualizarListaAmigos(S.getUserStr());
			}
		});
	}
	
	public void actualizarListaAmigos ( String usuario ) {
		
		User[] amigos = S.obtenerListaAmigos();
		for (User u : amigos) {
			System.out.println(u.nombre);
			System.out.println(u.online);
		}
		if (amigos.length == 0) {
			System.out.println("4ever alone");
			return;
		}
		
		String[] amigosStr = new String[amigos.length];
		
		for ( int i = 0 ; i < amigos.length ; i++ ) {
			amigosStr[i] = amigos[i].nombre + " ";
			if ( amigos[i].online != 0 )
				amigosStr[i] += "- Online";
			else
				amigosStr[i] += "- Offline";
		}	
		
		list.setListData(amigosStr);
			
	}
	
	public void actualizarListaMensajes ( String usuario , String amigo, JTextArea chat ){
		
		Mensaje[] mensajes = S.obtenerListaMensaje( amigo );
		String mensaje = "";
		if ( mensajes.length == 0 ) return;
		
		chat.setText("");
		
			for ( int i = mensajes.length - 1 ; i > -1 ; i-- ) {
		
				mensaje += mensajes[i].origen + " [" ;
				mensaje += mensajes[i].hora + "] : \n";
				mensaje += "   " + mensajes[i].mensaje + "\n";
		
				chat.append( mensaje );
				System.out.println(mensajes[i].destino);
				System.out.println(S.getUser());
				if ( mensajes[i].destino.toLowerCase().contentEquals( S.getUserStr().toLowerCase() ) ) {
					S.acusarRecibo( mensajes[i].origen , mensajes[i].hora );
					mensajes[i].recibido = 1;
				}	
			
			mensaje = "";
		}
		
	}
	
	public void agregarTabChat ( final String title )
	{
		MigLayout ml = new MigLayout("","grow,fill","[80%|20%]");
		JPanel panel = new JPanel( ml );
		chat = new JTextArea();
		chat.setEditable(false);
		chat.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(chat,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
				if ( S.enviarMensaje( title , tfenviar.getText() ) );
			}
		});
		jbactualizar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarListaMensajes( S.getUserStr(), title , chat );
			}
		});
		
		tabbedPane.setSelectedIndex( tabbedPane.indexOfTab(title));
		JPanel panelTop = new JPanel();
		agregar_botonX( panelTop, tabbedPane , title );
	}
}
