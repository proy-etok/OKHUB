package com.okhub.gui.vp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.okhub.oho.interfaz.Mensaje;
import com.okhub.oho.interfaz.Sesion;

import net.miginfocom.swing.MigLayout;

/**
 * 
 * Clase creadora de un panel del chat. 
 * Contiene un JTextPane para imprimir mensajes,
 * un JTextField para ingresar y 2 botones JButton, uno
 * para enviar y el otro para refrescar el JTextPane
 * 
 * @author Gseva
 * @version 0.0.1
 * @see Ventana_Principal
 */

public class Ventana_Principal_Chat extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Sesion S;
	JTextField tfenviar;
	JButton jbenviar;
	JTextPane chat;
	
	/**
	 * Metodo constructor del panel del chat
	 * 
	 * @param title
	 * El nombre del amigo con el que quiere chatear
	 * @param Ses
	 * La sesion del usuario
	 * @param tabbedPane
	 * El contenedor principal al que se va a agregar una pestaña
	 */
	
	public Ventana_Principal_Chat ( final String title , Sesion Ses , JTabbedPane tabbedPane ) 
	{
		this.S = Ses;
		
		
		if ( Ventana_Principal.existeTab( tabbedPane, title ) ) {
			tabbedPane.setSelectedIndex( tabbedPane.indexOfTab(title) );
			return;
		}
		
		MigLayout ml = new MigLayout("","grow,fill","[85%|15%]");
		JPanel panel = new JPanel( ml );
		chat = new JTextPane();
		chat.setEditable(false);
		JScrollPane scroll = new JScrollPane( chat , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		panel.add( scroll , "grow,wrap" );
		tfenviar = new JTextField( "" );
		panel.add( tfenviar , "split 3, bottom, growx" );
		jbenviar = new JButton("Enviar");
		panel.add( jbenviar , "bottom");
		JButton jbactualizar = new JButton("Actualizar");
		panel.add( jbactualizar , "bottom,right");
		tabbedPane.addTab( title , panel );
		jbenviar.addActionListener( new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ( !tfenviar.getText().equals( "" ) )
				S.enviarMensaje( title , tfenviar.getText() );
				tfenviar.setText("");
				actualizarListaMensajes( title  );
			}
		});
		jbactualizar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarListaMensajes( title  );
			}
		});
		
		tfenviar.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER )
					enviarMensaje(title);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		tabbedPane.setSelectedIndex( tabbedPane.indexOfTab( title ) );
	
	}
	
	/**
	 * Toma los datos del textfield y se los envia al amigo title.
	 * Luego borra el contenido del textfield y refresca el chat
	 * 
	 * @param title - nombre del amigo al que se va a enviar el mensaje
	 * @see Sesion#enviarMensaje(String, String)
	 */
	
	public void enviarMensaje ( String title ){
		
		S.enviarMensaje( title , tfenviar.getText() );
		tfenviar.setText("");
		actualizarListaMensajes( title );
		
	}
		
	/**
	 * Metodo que refresca la ventana del chat, borrandolo e
	 * imprimiendolo de nuevo.
	 * 
	 * @param amigo
	 * El nombre del amigo con el que se lleva a cabo el chat.
	 * @see Sesion#obtenerListaMensaje(String)
	 */
		
	public void actualizarListaMensajes ( String amigo ){
		
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
	



}
