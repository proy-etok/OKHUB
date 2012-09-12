package GUIPkg;

import java.awt.Image;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ventana_Login_Utilidad {
	
	/**
	 * Metodo de confirmacion de contraseña
	 * @param usuario 
	 */
	
//	**************************************************************************
//	Metodo de ejemplo que confirma la contraseña - mas adelante se le deberá 
//	aplicar la parte de la base de datos
//	**************************************************************************
	
	public static void confirmar_contraseña ( String usuario , char[] contraseña ) {
		
		
		String contraseñaString = "";
		for ( char simbolo : contraseña )
			contraseñaString += simbolo;						
	
		if ( contraseñaString.contentEquals( "12341234" ) && usuario.contentEquals( "pepe" ) ) {
			JOptionPane.showMessageDialog( null, "Cargando sus datos ..",
					"Bienvenido", JOptionPane.INFORMATION_MESSAGE );
			
		}
	
		else {
			JOptionPane.showMessageDialog( null, "Contraseña incorrecta",
					"Error", JOptionPane.INFORMATION_MESSAGE );
		}
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
