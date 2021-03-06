package com.okhub.gui.vp;

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
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Mensaje;
import com.okhub.oho.interfaz.Publicacion;
import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.User;
import com.okhub.oho.interfaz.threading.Cadete;
import com.okhub.oho.interfaz.threading.Jefe;
import com.okhub.oho.interfaz.threading.Tarea;


/**
 * Clase que completa la creacion de la aplicacion. Extiende Ventana_Principal.
 * <p>
 * Agrega a la ventana madre la mayoria de los metodos de interaccion.
 * 
 * @author Gseva
 * @version 0.0.1
 * @see Ventana_Principal
 */
public class Ventana_Principal_Utilidad extends Ventana_Principal implements Jefe{
	
	Sesion S;
	JTextField tfenviar;
	
	/**
	 * Crea la ventana.
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
	/**
	 * Clase constructora de la ventana principal. 
	 * Agrega todo tipo de listeners a los botones de distintas clases usadas.
	 * 
	 * @param Ses - la sesion actual con la que se logue� el usuario.
	 * @see Ventana_Principal#Ventana_Principal(Sesion)
	 */
	public Ventana_Principal_Utilidad(Sesion Ses) {
		super(Ses);
		this.S = Ses;
		jefe_principal = this;
		System.out.println( );
		
		
		
		friendsPane.botonRefrescar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(friendsPane.botonRefrescar.isEnabled())
					friendsPane.botonRefrescar.setEnabled(false);
				S.agregarTarea( new Tarea ( "ra PARA: imprimir_Amigos", jefe_principal ));
			}
		});
		Timer timerRefrescarAmigos = new Timer (40000, new ActionListener ()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("Refrescan2 amigos");
		    	if(friendsPane.botonRefrescar.isEnabled())
					friendsPane.botonRefrescar.setEnabled(false);
		    	S.agregarTarea( new Tarea ( "ra PARA: imprimir_Amigos", jefe_principal ));
		     }
		});
		timerRefrescarAmigos.start();
		
		friendsPane.botonRefrescar.doClick();
		
		friendsPane.miAgregarAmigo.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				agregarAmigo();
			}
		});
		
		friendsPane.miPublicaciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mostrarMiPublicacion();
				
			}
		});
		
		panelInicio.botonRefrescarInicio.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refrescarPanelInicio();
			}
		} );
		Timer timerRefrescarInicio = new Timer (60000, new ActionListener ()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	System.out.println("Refrescan2 inicio");
		    	refrescarPanelInicio();
		     }
		});
		timerRefrescarInicio.start();


	}
	
		
	/**
	 * Refresca el panel de amigos recreandolo.
	 * Warning: El codigo tiene un nivel elevado de hinduismo
	 * 
	 * @see Sesion
	 * @see Ventana_Principal_PanelAmigos
	 */
	
	public void RefrescarListaAmigos ( User[] amigos ) {
		JLabel[] amigosLbl = friendsPane.actualizarListaAmigos( amigos );
		if ( amigosLbl != null )
		for ( final JLabel lbl : amigosLbl ) {
			
			lbl.addMouseListener( new MouseAdapter( ) {
				
				public void mouseClicked ( MouseEvent e ) {
					
					if ( e.getButton() == MouseEvent.BUTTON3 ) {
						JPopupMenu popupMenu = new JPopupMenu();
						JMenuItem miChat = new JMenuItem("Abrir chat");
						miChat.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								Ventana_Principal_Chat vpc = new Ventana_Principal_Chat(lbl.getText(), S , tabbedPane );
								agregar_botonX(lbl.getText());
							}
						});
						popupMenu.add(miChat);
						JMenuItem miEliminar = new JMenuItem("Eliminar Amigo");
						miEliminar.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								if ( S.rechazarAmistad( lbl.getText() ) )  {
									if ( existeTab( tabbedPane , lbl.getText() ))
										tabbedPane.removeTabAt( tabbedPane.indexOfTab( lbl.getText() ) );
									JOptionPane.showMessageDialog( lbl , lbl.getText() + " eliminadisimo"   , "Tenes un amigo menos" , JOptionPane.INFORMATION_MESSAGE );
									if(friendsPane.botonRefrescar.isEnabled())
										friendsPane.botonRefrescar.setEnabled(false);
									S.agregarTarea( new Tarea ( "ra PARA: imprimir_Amigos", jefe_principal ));
								}
							}
						});
						popupMenu.add(miEliminar);
						
						final JMenuItem miPublicacion = new JMenuItem("Ver publicaciones");
						miPublicacion.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent arg0) {
								System.out.println( lbl.getText() );
								if ( existeTab(tabbedPane, "Publicaciones de " + lbl.getText() ) ) {
									tabbedPane.setSelectedIndex( tabbedPane.indexOfTab( "Publicaciones de " + lbl.getText() ) );
									return;
								}
								Publicacion[] publicaciones = S.obtenerPublicaciones( lbl.getText() );
								if ( publicaciones == null )
									return;
								
								Ventana_Principal_Publicacion vpp = new Ventana_Principal_Publicacion(lbl.getText(), S);
								
								tabbedPane.addTab("Publicaciones de " + lbl.getText(), vpp  );
								agregar_botonX("Publicaciones de " + lbl.getText() );
								tabbedPane.setSelectedIndex( tabbedPane.indexOfTab( "Publicaciones de " + lbl.getText() ) );
							}
						});
						popupMenu.add(miPublicacion);
						
						popupMenu.show(lbl, e.getX(), e.getY() );
						
					}
					if ( e.getClickCount() == 2 ) {
						
						Ventana_Principal_Chat vpc = new Ventana_Principal_Chat(lbl.getText(), S , tabbedPane );
						agregar_botonX(lbl.getText());
						
					}
				}
				
				public void mousePressed (MouseEvent e){
					
					lbl.setForeground( Color.blue );
					
				}
				
				public void mouseReleased (MouseEvent e){
					lbl.setForeground( Color.black );
				}
			} );
			if(!friendsPane.botonRefrescar.isEnabled())
				friendsPane.botonRefrescar.setEnabled(true);
		}
		
		friendsPane.friendsList.repaint();
	}
	
	/**
	 * Refresca el panel de inicio recreandolo. El programa lo crear� en el
	 * mismo index en el tabbedPane.
	 * @see Ventana_Principal_Inicio#Ventana_Principal_Inicio(Sesion, Ventana_Principal_PanelAmigos)
	 */
	
	public void refrescarPanelInicio () {
		int index = (tabbedPane.getSelectedIndex() == tabbedPane.indexOfTab("Inicio") ) 
				? tabbedPane.indexOfTab("Inicio") : -100;
		tabbedPane.removeTabAt(tabbedPane.indexOfTab("Inicio"));
		tabbedPane.insertTab("Inicio" , null , new JScrollPane( 
				new Ventana_Principal_Inicio(S , friendsPane ) , 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED ) , null , 0 );
		if ( index != -100 )
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Inicio"));
	}
	
	/**
	 * Le manda la invitacion de amistad a un amigo. 
	 * Se tiene que introducir el nombre mediante un InputDialog.
	 * @see Sesion#agregarAmigo(String)
	 */
	public void agregarAmigo () {
		String input = JOptionPane.showInputDialog( friendsPane.menu , "Ingrese su nombre " , "Agregar amigo" , JOptionPane.QUESTION_MESSAGE );
/*				if ( input.contains("@") )
					if ( S.existeCorreo( input ) ) {
						JOptionPane.showMessageDialog(menu, input + " Agregadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
						return;
					}*/
		if ( S.existeUsuario( input ) ) {
			S.agregarAmigo( input );
			JOptionPane.showMessageDialog(friendsPane.menu, input + " Agregadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
			if(friendsPane.botonRefrescar.isEnabled())
				friendsPane.botonRefrescar.setEnabled(false);
			S.agregarTarea( new Tarea ( "ra PARA: imprimir_Amigos", jefe_principal ));
			return;
		}
		
		JOptionPane.showMessageDialog(friendsPane.menu, input + " no existe" , "Error" , JOptionPane.INFORMATION_MESSAGE );
	}
	
	/**
	 * Agrega una pesta�a con las publicaciones del usuario logueado
	 * @see Ventana_Principal_Publicacion#Ventana_Principal_Publicacion(String, Sesion)
	 */
	
	public void mostrarMiPublicacion () {
		
		tabbedPane.addTab("Mis Publicaciones", new Ventana_Principal_Publicacion( null , S ) );
		agregar_botonX("Mis Publicaciones");
		tabbedPane.setSelectedIndex( tabbedPane.indexOfTab("Mis Publicaciones"));
		
	}
	
	@Override
	/**
	 * Sobreescritura del metodo de la interfaz Jefe: 
	 * describe los metodos que se efectuar�n cuando la Sesi�n entregue los resultados
	 * de la conecci�n con el servidor.
	 */
	public void entregarTarea(Tarea t) {
		
		if( t.nombre.contains( " PARA: " ) )
			if( t.nombre.split( " PARA: " ).length > 1 )
			{
				switch( ( t.nombre.split(" PARA: ") )[1] )
				{
				
					case "refrescar_Inicio":
						refrescarPanelInicio();
						break;
					
					case "imprimir_Amigos":
						RefrescarListaAmigos((User[])t.resultado);
						break;
						
					case "IMPRIMIR EN PANTALLA": System.out.println(t.resultado); break;
					
					case "deshacer":
						System.out.println(" MUERE: " + t.resultado); 
						try {
							((Cadete)t.resultado).join(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						} 
						break;
					default: break;
				}
			}				
		
	}
}
