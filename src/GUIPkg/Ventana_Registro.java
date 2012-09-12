package GUIPkg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.Color;

public class Ventana_Registro extends JDialog {

	public final static JPanel contentPanel = new JPanel();
	public static JTextField textFieldUsuario;
	public static JPasswordField passwordFieldContraseña;
	public static JPasswordField passwordFieldRepetirContraseña;
	public static JTextField textFieldDirCorreo;
	public static JTextField textFieldRepetirDirCorreo;
	public static JTextField textFieldPais;
	public static JTextField textFieldFecha;
	
	public static JLabel labelDirCorreoError;
	public static JLabel labelUsuarioError;
	public static JLabel labelContraseñaError;
	public static JLabel labelFechaError;

	/**
	 * Launch the application.
	 */
	
	
	public static void Crear_Ventana_Registro() {
		
		try {
			Ventana_Registro dialog = new Ventana_Registro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Ventana_Registro() {
		
		setResizable(false);
		setTitle("Registro");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setToolTipText("No puede tener menos de 4 caracteres");
		textFieldUsuario.setBounds(23, 42, 103, 20);
		contentPanel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel labelUsuario = new JLabel("Nombre del usuario:");
		labelUsuario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelUsuario.setBounds(23, 23, 103, 14);
		contentPanel.add(labelUsuario);
		
		JLabel labelUsuarioError = new JLabel("");
		labelUsuarioError.setBounds(136, 45, 235, 14);
		contentPanel.add(labelUsuarioError);
		
		JLabel labelContraseña = new JLabel("Contrase\u00F1a");
		labelContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelContraseña.setBounds(23, 73, 103, 14);
		contentPanel.add(labelContraseña);
		
		JLabel labelContraseñaError = new JLabel("");
		labelContraseñaError.setBounds(136, 101, 235, 14);
		contentPanel.add(labelContraseñaError);
		
		passwordFieldContraseña = new JPasswordField();
		passwordFieldContraseña.setToolTipText("Debe tener como minimo 8 caracteres");
		passwordFieldContraseña.setBounds(23, 95, 103, 20);
		contentPanel.add(passwordFieldContraseña);
		
		passwordFieldRepetirContraseña = new JPasswordField();
		passwordFieldRepetirContraseña.setBounds(381, 95, 103, 20);
		contentPanel.add(passwordFieldRepetirContraseña);
		
		JLabel labelRepetirContraseña = new JLabel("Repetir Contrase\u00F1a");
		labelRepetirContraseña.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelRepetirContraseña.setBounds(381, 73, 103, 14);
		contentPanel.add(labelRepetirContraseña);
		
		JLabel labelDirCorreo = new JLabel("Direccion de correo electr\u00F3nico:");
		labelDirCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelDirCorreo.setBounds(23, 126, 152, 14);
		contentPanel.add(labelDirCorreo);
		
		textFieldDirCorreo = new JTextField();
		textFieldDirCorreo.setBounds(23, 151, 152, 20);
		contentPanel.add(textFieldDirCorreo);
		textFieldDirCorreo.setColumns(10);
		
		JLabel labelRepetirDirCorreo = new JLabel("Repetir direccion de correo electr\u00F3nico:");
		labelRepetirDirCorreo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelRepetirDirCorreo.setBounds(381, 126, 178, 14);
		contentPanel.add(labelRepetirDirCorreo);
		
		textFieldRepetirDirCorreo = new JTextField();
		textFieldRepetirDirCorreo.setColumns(10);
		textFieldRepetirDirCorreo.setBounds(381, 151, 152, 20);
		contentPanel.add(textFieldRepetirDirCorreo);
		
		JLabel labelDirCorreoError = new JLabel("");
		labelDirCorreoError.setBounds(185, 154, 186, 14);
		contentPanel.add(labelDirCorreoError);
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelSexo.setBounds(23, 182, 46, 14);
		contentPanel.add(labelSexo);
		
		JComboBox<String> comboBoxSexo = new JComboBox<String>();
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBoxSexo.setBounds(23, 207, 103, 20);
		comboBoxSexo.addItem( "Masculino" );
		comboBoxSexo.addItem( "Femenino" );
		contentPanel.add(comboBoxSexo);
		
		JLabel LabelFecha = new JLabel( "Fecha de nacimiento:" );
		LabelFecha.setFont(new Font("Tahoma", Font.PLAIN, 10));
		LabelFecha.setBounds(381, 182, 119, 14);
		contentPanel.add(LabelFecha);
		
		JLabel labelPais = new JLabel("Pais:");
		labelPais.setFont(new Font("Tahoma", Font.PLAIN, 10));
		labelPais.setBounds(23, 238, 46, 14);
		contentPanel.add(labelPais);
		
		textFieldPais = new JTextField();
		textFieldPais.setToolTipText("No puede tener menos de 4 caracteres");
		textFieldPais.setColumns(10);
		textFieldPais.setBounds(23, 263, 103, 20);
		contentPanel.add(textFieldPais);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(381, 207, 103, 20);
		contentPanel.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel labelFechaError = new JLabel("");
		labelFechaError.setBounds(494, 210, 90, 14);
		contentPanel.add( labelFechaError );
		
		contentPanel.setFocusTraversalPolicy ( new FocusTraversalOnArray(new Component[]{textFieldUsuario, 
				labelUsuario, labelUsuarioError, labelContraseña, labelContraseñaError, passwordFieldContraseña, 
				passwordFieldRepetirContraseña, labelRepetirContraseña, labelDirCorreo, textFieldDirCorreo, 
				labelRepetirDirCorreo, textFieldRepetirDirCorreo, labelDirCorreoError, labelSexo, comboBoxSexo, 
				
				LabelFecha, textFieldFecha, labelPais, textFieldPais}));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ButtonRegistrarse = new JButton("Registrarse");
				ButtonRegistrarse.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						
						setVisible( false );
						removeAll();
						Ventana_Login.Crear_Ventana_Login();
						
					}
					
				} ) ;
				
				ButtonRegistrarse.setActionCommand("OK");
				buttonPane.add(ButtonRegistrarse);
				getRootPane().setDefaultButton(ButtonRegistrarse);
			}
			{
				JButton ButtonSalir = new JButton("Salir");
				ButtonSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setVisible( false );
						removeAll();
						Ventana_Login.Crear_Ventana_Login();
						
						
					}
				});
				ButtonSalir.setActionCommand("Cancel");
				buttonPane.add(ButtonSalir);
			}
		}
	}
}
