package GUIPkg;

import java.awt.Image;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ventana_Login_Utilidad {
	
	/**
	 * Metodo de confirmacion de contrase�a
	 * @param usuario 
	 */
	
//	**************************************************************************
//	Metodo de ejemplo que confirma la contrase�a - mas adelante se le deber� 
//	aplicar la parte de la base de datos
//	**************************************************************************
	
	public static void confirmar_contrase�a ( String usuario , char[] contrase�a ) {
		
		
		String contrase�aString = "";
		for ( char simbolo : contrase�a )
			contrase�aString += simbolo;						
	
		if ( contrase�aString.contentEquals( "12341234" ) && usuario.contentEquals( "pepe" ) ) {
			JOptionPane.showMessageDialog( null, "Cargando sus datos ..",
					"Bienvenido", JOptionPane.INFORMATION_MESSAGE );
			
		}
	
		else {
			JOptionPane.showMessageDialog( null, "Contrase�a incorrecta",
					"Error", JOptionPane.INFORMATION_MESSAGE );
		}
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
