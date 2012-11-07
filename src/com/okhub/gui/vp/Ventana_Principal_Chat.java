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
import javax.swing.Timer;

import com.okhub.oho.interfaz.Mensaje;
import com.okhub.oho.interfaz.Publicacion;
import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.threading.Jefe;
import com.okhub.oho.interfaz.threading.Tarea;

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

public class Ventana_Principal_Chat extends JPanel implements Jefe {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Sesion S;
	JTextField tfenviar;
	JButton jbenviar;
	JTextPane chat;
	private Timer timerRefrescarChat;
	int contadorRefrescar = 0;
	JButton jbActualizar;
	Jefe jefe_chat;
	
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
	
	public Ventana_Principal_Chat ( final String title , Sesion Ses , final JTabbedPane tabbedPane ) 
	{
		this.S = Ses;
		jefe_chat = this;
		
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
		jbActualizar = new JButton("Actualizar");
		panel.add( jbActualizar , "bottom,right");
		tabbedPane.addTab( title , panel );
		jbenviar.addActionListener( new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if ( !tfenviar.getText().equals( "" ) )
				enviarMensaje( title );
			}
		});
		jbActualizar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbActualizar.isEnabled())
					jbActualizar.setEnabled(false);
				S.agregarTarea( new Tarea ( "olm PARA: actualizar_Lista" , jefe_chat , new Object[]{ title }));
			}
		});
		
		timerRefrescarChat = new Timer (5000, new ActionListener ()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	while(!( tabbedPane.getSelectedIndex() == tabbedPane.indexOfTab(title) ) ) {
		    		contadorRefrescar++;
		    		if(contadorRefrescar >= 10)
		    			break;
		    		System.out.println("la ventana de " + title + " no está seleccionada, refrescando en " + (50000 - (contadorRefrescar * 5000)));
		    		return;
		    	}
		    	System.out.println("refrescan2 mensajes de " + title);
		    	if(jbActualizar.isEnabled())
					jbActualizar.setEnabled(false);
		    	S.agregarTarea( new Tarea ( "olm PARA: actualizar_Lista" , jefe_chat , new Object[]{ title }));
		    	contadorRefrescar = 0;
		     }
		});
		timerRefrescarChat.start();
		
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
		
		S.agregarTarea(new Tarea( "em PARA: default" , jefe_chat , new Object[]{ title , tfenviar.getText() } ) );
		tfenviar.setText("");
		if(jbActualizar.isEnabled())
			jbActualizar.setEnabled(false);
		S.agregarTarea( new Tarea ( "olm PARA: actualizar_Lista" , jefe_chat , new Object[]{ title }));
		
	}
		
	/**
	 * Metodo que refresca la ventana del chat, borrandolo e
	 * imprimiendolo de nuevo.
	 * 
	 * @param amigo
	 * El nombre del amigo con el que se lleva a cabo el chat.
	 * @see Sesion#obtenerListaMensaje(String)
	 */
		
	public void actualizarListaMensajes ( Mensaje[] mensajes ){
		
//		Mensaje[] mensajes = S.obtenerListaMensaje( amigo );
		String mensaje = "";
		
		if ( mensajes.length == 0 ) return;
		
		chat.setText("");
		for ( int i = mensajes.length - 1 ; i > -1 ; i-- ) {
	
			mensaje += mensajes[i].origen + " [" ;
			mensaje += mensajes[i].hora + "] : \n";
			mensaje += ">>" + mensajes[i].mensaje + "\n";
	
//			System.out.println(mensajes[i].destino);
//			System.out.println(S.getUser());
			if ( mensajes[i].destino.toLowerCase().contentEquals( S.getUserStr().toLowerCase() ) ) {
				
//				S.agregarTarea( new Tarea( "ar PARA: default" , jefe_chat , new Object[]{mensajes[i].origen , mensajes[i].origen} ));
//				S.acusarRecibo( mensajes[i].origen , mensajes[i].hora );
				mensajes[i].recibido = 1;
			}	
		
		chat.setText( mensaje );
		}
		if(!jbActualizar.isEnabled())
			jbActualizar.setEnabled(true);
		timerRefrescarChat.restart();
	}

	@Override
	public void entregarTarea(Tarea t) {
		
		if( t.nombre.contains( " PARA: " ) )
			if( t.nombre.split( " PARA: " ).length > 1 )
			{
				switch( ( t.nombre.split(" PARA: ") )[1] )
				{
				case "actualizar_Lista":
					actualizarListaMensajes( (Mensaje[])t.resultado  );
					break;
				default: break;
				}
			}			
		
	}
	



}
