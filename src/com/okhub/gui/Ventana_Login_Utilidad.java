package com.okhub.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.okhub.data.dataLogin;
import com.okhub.gui.vp.Ventana_Principal_Utilidad;
import com.okhub.oho.interfaz.Sesion;


public class Ventana_Login_Utilidad extends Ventana_Login {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Sesion Ses = new Sesion();

	/**
	 * Metodo de confirmacion de contrase�a
	 * @param usuario 
	 */
public static void Crear_Ventana_Login () {
		
		try {
			Ventana_Login_Utilidad dialog = new Ventana_Login_Utilidad();
			dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
			dialog.setVisible( true );
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public Ventana_Login_Utilidad () {
		
		labelRegistro.addMouseListener(  new MouseListener () {

			@Override public void mouseReleased(MouseEvent arg0) {}
			
			@Override public void mousePressed(MouseEvent arg0) {}
			
			@Override public void mouseExited(MouseEvent arg0) {
				
				labelRegistro.setFont( new Font( "Tahoma" , Font.PLAIN, 11));
				labelRegistro.setForeground( new Color( 0, 0, 0 ) );
				
			}
			
			@Override public void mouseEntered(MouseEvent arg0) {
				
				labelRegistro.setFont( new Font("Tahoma", Font.ITALIC, 11));
				labelRegistro.setForeground( new Color( 0 , 0 , 153 ) );
			}
			
			public void mouseClicked(MouseEvent arg0) {
				
				dispose();
				Ventana_Registro_Utilidad.Crear_Ventana_Registro();
				
			}
		
		} );
		
		buttonSalir.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				dispose();
				
			}
		} ) ;
		
		buttonOK.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				if ( rbRecordarUsuario.isSelected() ) {
					String usuario = comboboxUsuario.getSelectedItem().toString();
					dataLogin.guardar_usuario( usuario );
//					if ( rbRecordarContrase�a.isSelected() )
//						dataLogin.guardar_contrase�a( passwordfieldContrase�a.getPassword() , usuario );
				}
				
				
				System.out.println( comboboxUsuario.getSelectedItem().toString() + String.valueOf( passwordfieldContrase�a.getPassword()));
				if ( !Ses.iniciarSesion( comboboxUsuario.getSelectedItem().toString() , 
									String.valueOf( passwordfieldContrase�a.getPassword() ) ) ) 
					JOptionPane.showMessageDialog( null, "Usuario o contrase�a incorrectos" ,
							"Error", JOptionPane.INFORMATION_MESSAGE);
					
					
				else {
					
					Ventana_Principal_Utilidad.Crear_Ventana_Principal(Ses);
					dispose();					
				}
			}
		} ) ;
		
	}
	
	
	/**
	 * Metodo que ajusta la imagen al tama�o del label
	 * @return
	 */
	
//	**************************************************************************
//	Toma una imagen por ahora de una carpeta local y ajusta su tama�o al de 
//	label
//	**************************************************************************
	
	public static ImageIcon Cargar_Imagen( int width , int height , URL url ) {
		
		ImageIcon ImagenEscudoOKaux = new ImageIcon( url );	
		ImageIcon ImagenEscudoOK = new ImageIcon( ImagenEscudoOKaux.getImage().getScaledInstance( width , height , Image.SCALE_DEFAULT ) );
		return ImagenEscudoOK;
		
	}
	

}
