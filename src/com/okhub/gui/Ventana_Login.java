package com.okhub.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.okhub.data.dataLogin;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

//*los imports comentados el ecplipse me los marcó con error 
//cuando movi el .java a la carpeta del proyecto. Hay que ver 
//que onda

/**
 * @author GSeva
 * @version 0.0.1
 * @see Ventana_Login_Utilidad
 * 
 * Clase de creacion de ventana de login
 * 
 * */

public class Ventana_Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JPanel contentPanel = new JPanel();
	JPasswordField passwordfieldContraseña;
	JPanel buttonPane;
	JButton buttonOK;
	JButton buttonSalir;
	JRadioButton rbRecordarUsuario;
	JRadioButton rbRecordarContraseña;
	
	final JComboBox<String> comboboxUsuario;
	JLabel labelRegistro;
	
	
	

	/**
	 * Metodo de llamada al constructor de ventana
	 * @return 
	 */

	public Ventana_Login dialog;
			
	
	/**
	 * Constructor de la ventana
	 */
	
	
	public Ventana_Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Login.class.getResource("/com/okhub/gui/EscudoOK.PNG")));
		
		
		setTitle( "OKbook 0.0.1" );
		setBounds( 100 , 100 , 300 , 420 );
		
		
		getContentPane().setLayout( new BorderLayout() );
		contentPanel.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );
		getContentPane().add( contentPanel, BorderLayout.CENTER );
		contentPanel.setLayout( null );
		
//		**************************************************************************
//		Creacion del password field - en cualquier momento se le puede pedir que 
//		devuelva el contenido del campo de texto como un array de chars
//		**************************************************************************		
		
		passwordfieldContraseña = new JPasswordField();
		passwordfieldContraseña.setBounds( 100 , 264 , 137 , 20 );
		contentPanel.add( passwordfieldContraseña );
		
//		**************************************************************************
//		Creacion del combobox - campo que permite ingresar texto o eligir opciones
//		dentro de un menu 
//		**************************************************************************		
		
		comboboxUsuario = new JComboBox<String>();
		comboboxUsuario.setEditable( true );
		comboboxUsuario.setBounds( 100 , 207 , 137 , 20 );
		String usuarios = dataLogin.devolver_listaUsuarios();
		System.out.println( usuarios );
		
		for ( String s : usuarios.split( " " ) )
			comboboxUsuario.addItem( s );
		contentPanel.add( comboboxUsuario );
		
		
//		**************************************************************************
//		Creacion de botones radio - si estan presionados hay que ver como hacer 
//		para que se guarden las usuario/contraseña para el proximo login
//		**************************************************************************		
		
		rbRecordarUsuario = new JRadioButton( "Recordar usuario" );
		rbRecordarUsuario.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		rbRecordarUsuario.setBounds( 100 , 234 , 109 , 23 );
		contentPanel.add( rbRecordarUsuario );
		
		rbRecordarContraseña = new JRadioButton( "Recordar contrase\u00F1a" );
		rbRecordarContraseña.setToolTipText("Su contrase\u00F1a quedar\u00E1 guardada");
		rbRecordarContraseña.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		rbRecordarContraseña.setBounds( 100 , 291 , 137 , 23 );
		contentPanel.add( rbRecordarContraseña );
		
	
//		**************************************************************************
//		Label-boton, cambia de fuente cuando entra y sale el mouse y es clickeable
//		**************************************************************************	
		
		labelRegistro = new JLabel("\u00BFNo est\u00E1 registrado?");
		labelRegistro.setToolTipText("Presione aca para registrarse");
		labelRegistro.setFont( new Font( "Tahoma" , Font.PLAIN, 11));
		labelRegistro.setBounds(10, 321, 99, 20);
		contentPanel.add(labelRegistro);
		

		
//		**************************************************************************
//		Creacion de los labels con texto, nuff said
//		**************************************************************************		
		
		JLabel labelUsuario = new JLabel( "Usuario:" );
		labelUsuario.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		labelUsuario.setBounds( 50 , 209 , 40 , 17 );
		contentPanel.add( labelUsuario );
		
		
		JLabel labelContraseña = new JLabel( "Contrase\u00F1a:" );
		labelContraseña.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		labelContraseña.setBounds( 29 , 266 , 60 , 17 );
		contentPanel.add( labelContraseña );
		
//		**************************************************************************
//		Creacion del label con imagen del escudo OK
//		*Nota: hay que ver como guardar una imagen en el proyecto
//		**************************************************************************		
		
		JLabel labelImagenEscudo = new JLabel( "" );
		labelImagenEscudo.setBounds( 72 , 21 , 137 , 131 );
		labelImagenEscudo.setIcon( Ventana_Login_Utilidad.Cargar_Imagen( labelImagenEscudo.getWidth() , labelImagenEscudo.getHeight() , 
																		 getClass().getResource( "EscudoOK.PNG" ) ) );
		contentPanel.add( labelImagenEscudo );
		
		
		
		
		{
//			**************************************************************************
//			Creacion del button Pane - contiene 2 botones: OK y salir
//			**************************************************************************	
			
			buttonPane = new JPanel();
			buttonPane.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
			getContentPane().add( buttonPane , BorderLayout.SOUTH );
			
			
			{
				
				buttonOK = new JButton( "OK" );
				
//				**************************************************************************
//				Cuando se apreta el OK - se confirma la contraseña
//				**************************************************************************	
						
				
						
				buttonOK.setActionCommand( "OK" );
				buttonPane.add( buttonOK );
				getRootPane().setDefaultButton( buttonOK );
				
			}
			
			{
				buttonSalir = new JButton( "Salir" );
				buttonSalir.setToolTipText("Salir del programa");
				
//				**************************************************************************
//				El de salir es un poco mas rebuscado, espero que se den cuenta solos
//				**************************************************************************	
				
				buttonSalir.setActionCommand( "Cancel" );
				buttonPane.add( buttonSalir );
			}
		}
		
		setFocusTraversalPolicy ( new FocusTraversalOnArray( new Component[]{ getContentPane(), contentPanel 
								, buttonPane , buttonOK , buttonSalir , passwordfieldContraseña , labelRegistro } ) );
		
		
	} //Fin Ventana_Contraseña
} //Fin Clase
