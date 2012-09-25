package GUIPkg;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import dataPkg.dataLogin;

public class Ventana_Login_Utilidad extends Ventana_Login {
	
	Conector_Mysql mc = new Conector_Mysql();
	
	/**
	 * Metodo de confirmacion de contraseña
	 * @param usuario 
	 */
public void Crear_Ventana_Login () {
		
		try {
			Ventana_Login_Utilidad dialog = new Ventana_Login_Utilidad();
			dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
			dialog.setVisible( true );
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public Ventana_Login_Utilidad () {
		
		buttonOK.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				if ( rbRecordarUsuario.isSelected() ) {
					String usuario = comboboxUsuario.getSelectedItem().toString();
					dataLogin.guardar_usuario( usuario );
//					if ( rbRecordarContraseña.isSelected() )
//						dataLogin.guardar_contraseña( passwordfieldContraseña.getPassword() , usuario );
				}
				
				
				System.out.println( comboboxUsuario.getSelectedItem().toString() + String.valueOf( passwordfieldContraseña.getPassword()));
				if ( !mc.verificar_usuario( comboboxUsuario.getSelectedItem().toString() , String.valueOf( passwordfieldContraseña.getPassword() ) ) )
					JOptionPane.showMessageDialog( null, "Usuario o contraseña incorrectos" ,
							"Error", JOptionPane.INFORMATION_MESSAGE);
						
			}
		} ) ;
		
	}
	
	
	/**
	 * Metodo que ajusta la imagen al tamaño del label
	 * @return
	 */
	
//	**************************************************************************
//	Toma una imagen por ahora de una carpeta local y ajusta su tamaño al de 
//	label
//	**************************************************************************
	
	public static ImageIcon Cargar_Imagen( int width , int height , URL url ) {
		
		ImageIcon ImagenEscudoOKaux = new ImageIcon( url );	
		ImageIcon ImagenEscudoOK = new ImageIcon( ImagenEscudoOKaux.getImage().getScaledInstance( width , height , Image.SCALE_DEFAULT ) );
		return ImagenEscudoOK;
		
	}
	

}
