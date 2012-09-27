package com.okhub.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * @author GSeva
 * @version 0.0.1
 * @see Ventana_Registro_Utilidad
 * 
 * Clase inicial de creación de ventana:
 * Crea los paneles y los componentes y les asigna parametros basicos.
 * 
 * */

public class Ventana_Registro extends JDialog {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPanel = new JPanel();
	public JPanel buttonPane = new JPanel();
	JButton buttonRegistrarse = new JButton("Registrarse");
	

	public JComboBox<String> comboBoxSexo;
	
	JTextField textFieldUsuario;
	JPasswordField passwordFieldContraseña;
	JPasswordField passwordFieldRepetirContraseña;
	JTextField textFieldDirCorreo;
	JTextField textFieldRepetirDirCorreo;
	JTextField textFieldPais;
	JTextField textFieldFecha;
	
	JTextField textFieldUsuarioError;
	JTextField textFieldContraseñaError;
	JTextField textFieldDirCorreoError;
	JTextField textFieldFechaError;
	
	
	/**
	 * Constructor de ventana
	 */
	public Ventana_Registro() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Registro.class.getResource("/com/okhub/gui/EscudoOK.JPG")));
		
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
		textFieldUsuario.setBounds(23, 42, 103, 20);
		textFieldUsuario.setName( "tfUsuario");
		contentPanel.add(textFieldUsuario);
				
		JLabel labelUsuario = new JLabel("Nombre del usuario:");
		labelUsuario.setFont(new Font("Tahoma" , Font.PLAIN , 10 ));
		labelUsuario.setBounds(23, 23, 103, 14);
		contentPanel.add(labelUsuario);
		
		textFieldUsuarioError = new JTextField();
		textFieldUsuarioError.setBounds(136, 42, 235, 20);
		Ajustar_TextFieldError( textFieldUsuarioError );
		contentPanel.add(textFieldUsuarioError);
		
		
//	    ****************************************************
//		*				   CONTRASEÑA					   *
//		****************************************************
		
		JLabel labelContraseña = new JLabel("Contrase\u00F1a");
		labelContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelContraseña.setBounds(23, 73, 103, 14);
		contentPanel.add(labelContraseña);
		
		passwordFieldContraseña = new JPasswordField();
		passwordFieldContraseña.setToolTipText("Debe tener como minimo 8 caracteres");
		passwordFieldContraseña.setBounds(23, 95, 103, 20);
		passwordFieldContraseña.setName( "pfContraseña" );
		contentPanel.add(passwordFieldContraseña);
		
		textFieldContraseñaError = new JTextField();
		textFieldContraseñaError.setBounds(136, 95, 235, 20);
		Ajustar_TextFieldError( textFieldContraseñaError );
		contentPanel.add(textFieldContraseñaError);
		
		passwordFieldRepetirContraseña = new JPasswordField();
		passwordFieldRepetirContraseña.setBounds(381, 95, 103, 20);
		passwordFieldRepetirContraseña.setName( "pfRepetirContraseña" );
		contentPanel.add(passwordFieldRepetirContraseña);
		
		JLabel labelRepetirContraseña = new JLabel("Repetir Contrase\u00F1a");
		labelRepetirContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelRepetirContraseña.setBounds(381, 73, 103, 14);
		contentPanel.add(labelRepetirContraseña);
		
//	    ****************************************************
//		*					  CORREO					   *
//		****************************************************
		
		JLabel labelDirCorreo = new JLabel("Direccion de correo electr\u00F3nico:");
		labelDirCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelDirCorreo.setBounds(23, 126, 152, 14);
		contentPanel.add(labelDirCorreo);
		
		textFieldDirCorreo = new JTextField();
		textFieldDirCorreo.setBounds(23, 151, 152, 20);
		textFieldDirCorreo.setName( "tfDirCorreo" );
		contentPanel.add(textFieldDirCorreo);
		
		textFieldDirCorreoError = new JTextField();
		textFieldDirCorreoError.setBounds(185, 151, 186, 20);
		textFieldDirCorreoError.setName( "tfRepetirDirCorreo" );
		Ajustar_TextFieldError( textFieldDirCorreoError );
		contentPanel.add(textFieldDirCorreoError);
		
		JLabel labelRepetirDirCorreo = new JLabel("Repetir direccion de correo electr\u00F3nico:");
		labelRepetirDirCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelRepetirDirCorreo.setBounds(381, 126, 178, 14);
		contentPanel.add(labelRepetirDirCorreo);
		
		textFieldRepetirDirCorreo = new JTextField();
		textFieldRepetirDirCorreo.setBounds(381, 151, 152, 20);
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
		comboBoxSexo.setBounds(23, 207, 103, 20);
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
		textFieldFecha.setBounds( 185 , 207 , 103 , 20 );
		textFieldFecha.setName( "tfFecha" );
		contentPanel.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		textFieldFechaError = new JTextField();
		textFieldFechaError.setBounds(298, 207, 235, 20);
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
		textFieldPais.setBounds(23, 263, 103, 20);
		contentPanel.add(textFieldPais);
		
		
		contentPanel.setFocusTraversalPolicy( new FocusTraversalOnArray( new Component[]{ textFieldUsuario , labelUsuario, textFieldUsuarioError, labelContraseña, passwordFieldContraseña, textFieldContraseñaError, labelRepetirContraseña, passwordFieldRepetirContraseña, labelDirCorreo, textFieldDirCorreo, labelRepetirDirCorreo, textFieldDirCorreoError, textFieldRepetirDirCorreo, labelSexo, comboBoxSexo, LabelFecha, textFieldFecha, textFieldFechaError, labelPais, textFieldPais}));
		
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
						Ventana_Login_Utilidad vl = new Ventana_Login_Utilidad();
						vl.Crear_Ventana_Login();
						
						
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
	 * @return
	 * */
	
	public void Ajustar_TextFieldError ( JTextField tf ) {
		
		
			tf.setBackground( contentPanel.getBackground() );
			tf.setFont( new Font( "Tahoma" , Font.ITALIC , 10 ) );
			tf.setBorder( null );
			tf.setEditable( false );
			tf.setFocusable( false );
			tf.setColumns( 10 );
			
	}
} // fin clase Ventana_Registro
