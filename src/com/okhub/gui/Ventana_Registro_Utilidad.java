package com.okhub.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.okhub.oho.interfaz.Sesion;

/**
 * @author GSeva
 * @see Ventana_Registro
 * @see Ventana_Registro_Verificacion
 * @version 0.0.1
 * 
 * Clase de ventana de registro con metodos de confirmacion de campos de texto
 * 
 * */

public class Ventana_Registro_Utilidad extends Ventana_Registro {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Ventana_Registro_Verificacion vrv = new Ventana_Registro_Verificacion();
	/**
	 * 
	 */

	public void Crear_Ventana_Registro() 
	{
		try {
			Ventana_Registro_Utilidad dialog = new Ventana_Registro_Utilidad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible( true );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Ventana_Registro_Utilidad() 
	{
		

		System.out.printf( comboBoxSexo.getSelectedItem().toString() );
		Agregar_Focus_Listener_textField( textFieldUsuario );
		Agregar_Focus_Listener_textField( textFieldDirCorreo );
		Agregar_Focus_Listener_textField( textFieldRepetirDirCorreo );
		Agregar_Focus_Listener_textField( textFieldFecha );
		Agregar_Focus_Listener_textField( passwordFieldContraseña );
		Agregar_Focus_Listener_textField( passwordFieldRepetirContraseña );
		

		
		buttonRegistrarse.addActionListener( new ActionListener() {
			
			public void actionPerformed( ActionEvent arg0 ) {
				
				Verificar_Datos_Registro();
				
			}
			
		} ) ;
	}
	
	
	
	
	public void Agregar_Focus_Listener_textField ( JTextComponent cf ) 
	{
		
		final String nombre = cf.getName();
		
		cf.addFocusListener( new FocusListener( ) {
			
			@Override
			public void focusLost( FocusEvent e ) {
				
				JTextField tferror = null;
				if ( nombre.contains( "Usuario" ) ) {	
					tferror = textFieldUsuarioError;
					tferror.setText( vrv.Verificar_Datos_Usuario( textFieldUsuario.getText() ) );
				}
				if ( nombre.contains( "DirCorreo" ) ) {	
					tferror = textFieldDirCorreoError;
					tferror.setText( vrv.Verificar_Datos_Correo( textFieldDirCorreo.getText() , textFieldRepetirDirCorreo.getText() ) );
				}
				if ( nombre.contains( "Fecha" ) ) {	
					tferror = textFieldFechaError;
					tferror.setText( vrv.Verificar_Datos_Fecha( textFieldFecha.getText() ) );
				}
				if ( nombre.contains( "Contraseña" ) ) {	
					tferror = textFieldContraseñaError;
					tferror.setText( vrv.Verificar_Datos_Contraseña( passwordFieldContraseña.getPassword() , passwordFieldRepetirContraseña.getPassword() ) );
				}
				
				
				if ( tferror.getText().startsWith( "*" ) ) 
					tferror.setForeground( Color.RED );
				else
					tferror.setForeground( Color.GREEN );
				

				
			}
			
			@Override	public void focusGained( FocusEvent arg0 ) {}
		});
		
	}
	
	public void Verificar_Datos_Registro ( ) 
	{
		vrv.Verificar_Datos_Usuario( textFieldUsuario.getText() );
		vrv.Verificar_Datos_Contraseña( passwordFieldContraseña.getPassword() , passwordFieldRepetirContraseña.getPassword() );
		vrv.Verificar_Datos_Correo( textFieldDirCorreo.getText() , textFieldRepetirDirCorreo.getText() );
		vrv.Verificar_Datos_Fecha( textFieldFecha.getText() );
		vrv.Verificar_Datos_Pais( textFieldPais.getText() );
//		vrv.AgregarAño()
		
		
		if ( verificar_campos( ) ) {
			
			if ( Sesion.registrarUsuario(  textFieldUsuario.getText() 
									, String.valueOf( passwordFieldContraseña.getPassword() )
									, textFieldDirCorreo.getText() 
									, textFieldPais.getText()
									, comboBoxSexo.getSelectedItem().toString().charAt(0)
									, textFieldFecha.getText()) )
				JOptionPane.showMessageDialog( null, "Felicidades!",
						"Ahora está registrado", JOptionPane.INFORMATION_MESSAGE );
			else
				JOptionPane.showMessageDialog( null, "Error",
						"Ha ocurrido un error", JOptionPane.INFORMATION_MESSAGE );
		}
		else
			JOptionPane.showMessageDialog( null, "¡Datos de registro incorrectos!",
					"Fuira", JOptionPane.INFORMATION_MESSAGE );

	}
	
	boolean verificar_campos() 
	{
		
		for ( boolean bool : vrv.ingresoValido )
			if ( !bool )
				return false;
		
		return true;
		
	}
	

	

}
