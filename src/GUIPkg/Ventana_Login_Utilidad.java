package GUIPkg;

import java.awt.Image;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ventana_Login_Utilidad {
	
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
