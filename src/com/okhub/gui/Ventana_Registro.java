package com.okhub.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.okhub.oho.interfaz.threading.Cadete;
import com.okhub.oho.interfaz.threading.Jefe;
import com.okhub.oho.interfaz.threading.Tarea;

/**
 * @author GSeva
 * @version 0.0.1
 * @see Ventana_Registro_Utilidad
 * 
 * Clase inicial de creaci�n de ventana:
 * Crea los paneles y los componentes y les asigna parametros basicos.
 * 
 * */


public class Ventana_Registro extends JDialog implements Jefe {
	

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	public JPanel contentPanel = new JPanel();
	public JPanel buttonPane = new JPanel();
	JButton buttonRegistrarse = new JButton("Registrarse");
	
	Cadete Carlos;
	Jefe jefe;
	

	public JComboBox<String> comboBoxSexo;
	
	JTextField textFieldUsuario;
	JPasswordField passwordFieldContrase�a;
	JPasswordField passwordFieldRepetirContrase�a;
	JTextField textFieldDirCorreo;
	JTextField textFieldRepetirDirCorreo;
	JTextField textFieldPais;
	JTextField textFieldFecha;
	
	JTextField textFieldUsuarioError;
	JTextField textFieldContrase�aError;
	JTextField textFieldDirCorreoError;
	JTextField textFieldFechaError;

	
	
	/**
	 * Constructor de ventana
	 */
	public Ventana_Registro() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Registro.class.getResource("/com/okhub/gui/EscudoOK.png")));
		Carlos = new Cadete();
		Carlos.start();
		setResizable(false);
		setTitle("Registro");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout( new BorderLayout() );
		contentPanel.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );
		getContentPane().add( contentPanel , BorderLayout.CENTER );
		contentPanel.setLayout( null );
		
//	    ****************************************************
//		*					  USUARIO					   *
//		****************************************************
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setToolTipText("No puede tener menos de 4 caracteres");
		textFieldUsuario.setBounds(23, 42, 103, 24);
		textFieldUsuario.setName( "tfUsuario");
		contentPanel.add(textFieldUsuario);
				
		JLabel labelUsuario = new JLabel("Nombre del usuario:");
		labelUsuario.setFont(new Font("Tahoma" , Font.PLAIN , 10 ));
		labelUsuario.setBounds(23, 23, 103, 14);
		contentPanel.add(labelUsuario);
		
		textFieldUsuarioError = new JTextField();
		textFieldUsuarioError.setBounds(136, 45, 235, 20);
		Ajustar_TextFieldError( textFieldUsuarioError );
		contentPanel.add(textFieldUsuarioError);
		
		
//	    ****************************************************
//		*				   CONTRASE�A					   *
//		****************************************************
		
		JLabel labelContrase�a = new JLabel("Contrase\u00F1a");
		labelContrase�a.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelContrase�a.setBounds(23, 73, 103, 14);
		contentPanel.add(labelContrase�a);
		
		passwordFieldContrase�a = new JPasswordField();
		passwordFieldContrase�a.setToolTipText("Debe tener como minimo 8 caracteres");
		passwordFieldContrase�a.setBounds(23, 95, 103, 24);
		passwordFieldContrase�a.setName( "pfContrase�a" );
		contentPanel.add(passwordFieldContrase�a);
		
		textFieldContrase�aError = new JTextField();
		textFieldContrase�aError.setBounds(136, 97, 235, 20);
		Ajustar_TextFieldError( textFieldContrase�aError );
		contentPanel.add(textFieldContrase�aError);
		
		passwordFieldRepetirContrase�a = new JPasswordField();
		passwordFieldRepetirContrase�a.setBounds(381, 95, 103, 24);
		passwordFieldRepetirContrase�a.setName( "pfRepetirContrase�a" );
		contentPanel.add(passwordFieldRepetirContrase�a);
		
		JLabel labelRepetirContrase�a = new JLabel("Repetir Contrase\u00F1a");
		labelRepetirContrase�a.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelRepetirContrase�a.setBounds(381, 73, 103, 14);
		contentPanel.add(labelRepetirContrase�a);
		
//	    ****************************************************
//		*					  CORREO					   *
//		****************************************************
		
		JLabel labelDirCorreo = new JLabel("Direccion de correo electr\u00F3nico:");
		labelDirCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelDirCorreo.setBounds(23, 126, 152, 14);
		contentPanel.add(labelDirCorreo);
		
		textFieldDirCorreo = new JTextField();
		textFieldDirCorreo.setBounds(23, 151, 152, 24);
		textFieldDirCorreo.setName( "tfDirCorreo" );
		contentPanel.add(textFieldDirCorreo);
		
		textFieldDirCorreoError = new JTextField();
		textFieldDirCorreoError.setBounds(185, 153, 186, 20);
		textFieldDirCorreoError.setName( "tfRepetirDirCorreo" );
		Ajustar_TextFieldError( textFieldDirCorreoError );
		contentPanel.add(textFieldDirCorreoError);
		
		JLabel labelRepetirDirCorreo = new JLabel("Repetir direccion de correo electr\u00F3nico:");
		labelRepetirDirCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelRepetirDirCorreo.setBounds(381, 126, 178, 14);
		contentPanel.add(labelRepetirDirCorreo);
		
		textFieldRepetirDirCorreo = new JTextField();
		textFieldRepetirDirCorreo.setBounds(381, 151, 152, 24);
		textFieldRepetirDirCorreo.setName( "tfRepetirDirCorreo" );
		contentPanel.add(textFieldRepetirDirCorreo);
		
//	    ****************************************************
//		*					  	SEXO					   *
//		****************************************************
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelSexo.setBounds(23, 182, 46, 14);
		contentPanel.add(labelSexo);
		
		comboBoxSexo = new JComboBox<String>();
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxSexo.setBounds(23, 207, 103, 24);
		comboBoxSexo.addItem( "Masculino" );
		comboBoxSexo.addItem( "Femenino" );
	
		contentPanel.add(comboBoxSexo);
		
		
//	    ****************************************************
//		*					   FECHA					   *
//		****************************************************
		
		JLabel LabelFecha = new JLabel( "Fecha de nacimiento: aaaa-mm-dd" );
		LabelFecha.setFont( new Font( "Tahoma" , Font.PLAIN , 10 ) );
		LabelFecha.setBounds(185, 182, 186, 14);
		contentPanel.add(LabelFecha);
		
		textFieldFecha = new JFormattedTextField(  );
		textFieldFecha.setBounds( 185 , 207 , 103 , 24 );
		textFieldFecha.setName( "tfFecha" );
		contentPanel.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		textFieldFechaError = new JTextField();
		textFieldFechaError.setBounds(298, 209, 235, 20);
		Ajustar_TextFieldError( textFieldFechaError );
		contentPanel.add(textFieldFechaError);
		
//	    ****************************************************
//		*					  PAIS  					   *
//		****************************************************
		
		JLabel labelPais = new JLabel( "Pais:" );
		labelPais.setFont( new Font( "Tahoma" , Font.PLAIN , 10 ) );
		labelPais.setBounds( 23 , 238 , 46 , 14 );
		contentPanel.add( labelPais );
		
		textFieldPais = new JTextField();
		textFieldPais.setColumns(10);
		textFieldPais.setBounds(23, 263, 103, 24);
		contentPanel.add(textFieldPais);
		
		
		contentPanel.setFocusTraversalPolicy( new FocusTraversalOnArray( new Component[]{ textFieldUsuario , labelUsuario, textFieldUsuarioError, labelContrase�a, passwordFieldContrase�a, textFieldContrase�aError, labelRepetirContrase�a, passwordFieldRepetirContrase�a, labelDirCorreo, textFieldDirCorreo, labelRepetirDirCorreo, textFieldDirCorreoError, textFieldRepetirDirCorreo, labelSexo, comboBoxSexo, LabelFecha, textFieldFecha, textFieldFechaError, labelPais, textFieldPais}));
		
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				buttonPane.add(buttonRegistrarse);
				getRootPane().setDefaultButton(buttonRegistrarse);
			}
			
			{
				JButton ButtonSalir = new JButton("Salir");
				ButtonSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						dispose();
						Carlos.agregarTarea( new Tarea ("d", jefe ) );
						Ventana_Login_Utilidad.Crear_Ventana_Login();
						
						
					}
				});
				ButtonSalir.setActionCommand("Cancel");
				buttonPane.add(ButtonSalir);
			}
		}
	} //Fin Ventana_Registro
	
	
	/**
	 * Metodo que les asiga parametros a los textfields de error,
	 * como el color de fondo - para que no se vean inicialmente.
	 * */
	
	public void Ajustar_TextFieldError ( JTextField tf ) {
		
		tf.setBackground( new Color(214,217,223) );
		tf.setBorder( null );
		tf.setFont( new Font( "Tahoma" , Font.ITALIC , 10 ) );
		tf.setEditable( false );
		tf.setFocusable( false );
		tf.setColumns( 10 );
			
	}
	
	@Override
	public void entregarTarea(Tarea tarea) 
	{
		if( tarea.nombre.contains( " PARA: " ) )
			if( tarea.nombre.split( " PARA: " ).length > 1 )
			{
				switch( ( tarea.nombre.split(" PARA: ") )[1] )
				{
				case "IMPRIMIR TFERROR": 
				((JTextField) tarea.parametros[0]).setText( (String) tarea.resultado ); 
				if ( ( (JTextField) tarea.parametros[0] ).getText().startsWith( "*" ) )
						( (JTextField) tarea.parametros[0] ).setForeground( Color.RED );
					else
						( (JTextField) tarea.parametros[0] ).setForeground( Color.GREEN );
				break;
				case "IMPRIMIR LINEA": System.out.println(":-:---------------------------------------------------------:-:"); break;
				case "IMPRIMIR EN PANTALLA": System.out.println(tarea.resultado); break;
				case "MI MORIR ES VIVIR":
					System.out.println(" MUERE: " + tarea.resultado); try {
						((Cadete)tarea.resultado).join(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} break;
				default: break;
				}
			}					
	}
	
} // fin clase Ventana_Registro
