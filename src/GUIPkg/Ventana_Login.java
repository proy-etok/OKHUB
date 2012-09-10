package GUIPkg;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.Image;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

//*los imports comentados el ecplipse me los marcó con error 
//cuando movi el .java a la carpeta del proyecto. Hay que ver 
//que onda

public class Ventana_Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordfieldContraseña;
	private JPanel buttonPane;
	private JButton okbuttonOK;
	private JButton cancelbuttonSalir;

	/**
	 * @author GSeva
	 * Metodo de llamada al constructor de ventana
	 * @return 
	 */

	
	
	public static void Crear_Ventana_Login () {
		
		try {
			Ventana_Login dialog = new Ventana_Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Metodo de confirmacion de contraseña
	 * @param usuario 
	 */
	
//	**************************************************************************
//	Metodo de ejemplo que confirma la contraseña - mas adelante se le deberá 
//	aplicar la parte de la base de datos
//	**************************************************************************
	
	public void confirmar_contraseña ( String usuario ) {
		
		
		char[] contraseña = passwordfieldContraseña.getPassword();
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

	public ImageIcon Cargar_Imagen( int width , int height ) {
		
		
		ImageIcon ImagenEscudoOKaux = new ImageIcon( "C:\\Documents and Settings\\Administrador\\Escritorio\\carpeta de java\\Java\\Prueba GUI\\src\\MainPkg\\Images\\EscudoOK.jpg" );	
		ImageIcon ImagenEscudoOK = new ImageIcon( ImagenEscudoOKaux.getImage().getScaledInstance( width , height , Image.SCALE_DEFAULT ) );
		return ImagenEscudoOK;
		
	}
	
	
	/**
	 * Constructor de la ventana
	 */
	
	
	public Ventana_Login() {
		
		
		setTitle( "OKbook 0.0.1" );
		setBounds( 100 , 100 , 300 , 400 );
		
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
		
		final JComboBox<?> comboboxUsuario = new JComboBox<Object>();
		comboboxUsuario.setEditable( true );
		comboboxUsuario.setBounds( 100 , 207 , 137 , 20 );
		contentPanel.add( comboboxUsuario );
		
		
//		**************************************************************************
//		Creacion de botones radio - si estan presionados hay que ver como hacer 
//		para que se guarden las usuario/contraseña para el proximo login
//		**************************************************************************		
		
		JRadioButton rbRecordarUsuario = new JRadioButton( "Recordar usuario" );
		rbRecordarUsuario.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		rbRecordarUsuario.setBounds( 100 , 234 , 109 , 23 );
		contentPanel.add( rbRecordarUsuario );
		
		JRadioButton rbRecordarContraseña = new JRadioButton( "Recordar contrase\u00F1a" );
		rbRecordarContraseña.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		rbRecordarContraseña.setBounds( 100 , 291 , 137 , 23 );
		contentPanel.add( rbRecordarContraseña );
		
//		**************************************************************************
//		Creacion de los labels con texto, nuff said
//		**************************************************************************		
		
		JLabel labelUsuario = new JLabel( "Usuario:" );
		labelUsuario.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		labelUsuario.setBounds( 50 , 210 , 40 , 17 );
		contentPanel.add( labelUsuario );
		
		
		JLabel labelContraseña = new JLabel( "Contrase\u00F1a:" );
		labelContraseña.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		labelContraseña.setBounds( 30 , 267 , 60 , 17 );
		contentPanel.add( labelContraseña );
		
//		**************************************************************************
//		Creacion del label con imagen del escudo OK
//		*Nota: hay que ver como guardar una imagen en el proyecto
//		**************************************************************************		
		
		JLabel labelImagenEscudo = new JLabel( "" );
		labelImagenEscudo.setBounds( 72 , 21 , 137 , 131 );
		labelImagenEscudo.setIcon( Cargar_Imagen( labelImagenEscudo.getWidth() , labelImagenEscudo.getHeight() ) );
		contentPanel.add( labelImagenEscudo );
		
		
		
		{
//			**************************************************************************
//			Creacion del button Pane - contiene 2 botones: OK y salir
//			**************************************************************************	
			
			buttonPane = new JPanel();
			buttonPane.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
			getContentPane().add( buttonPane , BorderLayout.SOUTH );
			
			
			{
				
				okbuttonOK = new JButton( "OK" );
				
//				**************************************************************************
//				Cuando se apreta el OK - se confirma la contraseña
//				**************************************************************************	
					
				okbuttonOK.addActionListener( new ActionListener() {
					public void actionPerformed( ActionEvent e ) {
						
						String usuario = (String) comboboxUsuario.getSelectedItem();
						confirmar_contraseña( usuario );
						
					}
				} ) ;
				
				okbuttonOK.setActionCommand( "OK" );
				buttonPane.add( okbuttonOK );
				getRootPane().setDefaultButton( okbuttonOK );
				
			}
			
			{
				cancelbuttonSalir = new JButton( "Salir" );
				
//				**************************************************************************
//				El de salir es un poco mas rebuscado, espero que se den cuenta solos
//				**************************************************************************	
				
				cancelbuttonSalir.addActionListener( new ActionListener() {
					public void actionPerformed( ActionEvent e ) {
						
						System.exit(0);
						
					}
				} ) ;
				
				cancelbuttonSalir.setActionCommand( "Cancel" );
				buttonPane.add( cancelbuttonSalir );
			}
		}
		
//		setFocusTraversalPolicy( new FocusTraversalPolicy( new Component[]{ getContentPane(), contentPanel 
//								, buttonPane , okbuttonOK , cancelbuttonSalir , passwordfieldContraseña } ) );
		
		
	} //Fin Ventana_Contraseña
} //Fin Clase
