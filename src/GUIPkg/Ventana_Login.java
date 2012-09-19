package GUIPkg;
import java.awt.BorderLayout;
import java.awt.Dialog;
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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
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

	Conector_Mysql mc = new Conector_Mysql();
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordfieldContraseña;
	private JPanel buttonPane;
	private JButton buttonOK;
	private JButton buttonSalir;
	

	/**
	 * Metodo de llamada al constructor de ventana
	 * @return 
	 */

	public Ventana_Login dialog;
	
	public void Crear_Ventana_Login () {
		
		try {
			Ventana_Login dialog = new Ventana_Login();
			dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
			dialog.setVisible( true );
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
			
	
	/**
	 * Constructor de la ventana
	 */
	
	
	public Ventana_Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Login.class.getResource("/guiPkg/EscudoOK.JPG")));
		
		
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
		
		final JComboBox<String> comboboxUsuario = new JComboBox<String>();
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
		rbRecordarContraseña.setToolTipText("Su contrase\u00F1a quedar\u00E1 guardada");
		rbRecordarContraseña.setFont( new Font ( "Tahoma" , Font.PLAIN , 10 ) );
		rbRecordarContraseña.setBounds( 100 , 291 , 137 , 23 );
		contentPanel.add( rbRecordarContraseña );
		
	
//		**************************************************************************
//		Label-boton, cambia de fuente cuando entra y sale el mouse y es clickeable
//		**************************************************************************	
		
		final JLabel labelRegistro = new JLabel("\u00BFNo est\u00E1 registrado?");
		labelRegistro.setToolTipText("Presione aca para registrarse");
		labelRegistro.setFont( new Font( "Tahoma" , Font.PLAIN, 11));
		labelRegistro.setBounds(10, 313, 99, 20);
		contentPanel.add(labelRegistro);
		
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
				Ventana_Registro_Utilidad vru = new Ventana_Registro_Utilidad();
				vru.Crear_Ventana_Registro();
				
			}
		
		} );
		
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
																		 getClass().getResource( "EscudoOK.jpg" ) ) );
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
						
				buttonOK.addActionListener( new ActionListener() {
					public void actionPerformed( ActionEvent e ) {
							
						System.out.println( comboboxUsuario.getSelectedItem().toString() + String.valueOf( passwordfieldContraseña.getPassword()));
						if ( !mc.verificar_usuario( comboboxUsuario.getSelectedItem().toString() , String.valueOf( passwordfieldContraseña.getPassword() ) ) )
							JOptionPane.showMessageDialog( null, "Usuario o contraseña incorrectos" ,
									"Error", JOptionPane.INFORMATION_MESSAGE);
								
					}
				} ) ;
						
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
				
				buttonSalir.addActionListener( new ActionListener() {
					public void actionPerformed( ActionEvent e ) {
						
						dispose();
						
					}
				} ) ;
				
				buttonSalir.setActionCommand( "Cancel" );
				buttonPane.add( buttonSalir );
			}
		}
		
		setFocusTraversalPolicy ( new FocusTraversalOnArray( new Component[]{ getContentPane(), contentPanel 
								, buttonPane , buttonOK , buttonSalir , passwordfieldContraseña , labelRegistro } ) );
		
		
	} //Fin Ventana_Contraseña
} //Fin Clase
