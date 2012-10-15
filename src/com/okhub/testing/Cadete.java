package com.okhub.testing;

import java.util.PriorityQueue;

import com.okhub.gui.Ventana_Registro_Verificacion;
import com.okhub.oho.interfaz.Sesion;

public class Cadete extends Thread 
{
	PriorityQueue<Tarea> tareas = new PriorityQueue<Tarea>() ;
	
	boolean despedido = false;
	
	int reintentos = 0;
	
	boolean terminada = false;
	
	
	public void run ()
	{
		try 
		{
			
			while( ! despedido )
			{
				Tarea actual = tareas.peek();
				System.out.println("Peekeando tarea");
				
				if(actual != null)
				{
					terminada = false;
					
					
					try
					{
						System.out.println("Realizando tarea");
						
						terminada = realizarTarea(actual);
						
					} catch (Exception e)
					{
						e.printStackTrace();
						terminada = false;
						reintentos++;
						Cadete.sleep(100);
					}
					
					if(terminada || (reintentos > actual.maxReintentos))
					{
						System.out.println("Terminando tarea");
						
						tareas.remove(actual);
						reintentos = 0;
					}					
					
				}
				else
					System.out.println("No hay tarea");
				
					Cadete.sleep(1000);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int agregarTarea( Tarea t)
	{
		System.out.println("Agregando tarea");
		
		tareas.add(t);
		return tareas.size();
	}
	
	public boolean realizarTarea(Tarea a)
	{
		boolean realizada = false; 
		
		try
		{
			Ventana_Registro_Verificacion vrv = null;
			
			switch((a.nombre.split(" PARA: "))[0])
			{
			case "NOOP": Cadete.sleep(4000); realizada = true; break;
			
			case "ESPERAR": Cadete.sleep((int)a.parametros[0]); 
			a.resultado = true; 
			a.jefe.entregarTarea(a); 
			realizada = true; 
			break;
			
			case "EXISTEUSUARIO": Cadete.sleep(4000);
			a.resultado = Sesion.existeUsuario(((String)a.parametros[0]));
			realizada = true;
			a.jefe.entregarTarea(a);
			break;
			
			case "VERIFICARUSUARIO":
				vrv = new Ventana_Registro_Verificacion();
				a.resultado = vrv.Verificar_Datos_Usuario((String)a.parametros[1]);
				realizada = true;
				a.jefe.entregarTarea(a);
				
				break;
				
			case "VERIFICARCORREO":
				vrv = new Ventana_Registro_Verificacion();
				a.resultado = vrv.Verificar_Datos_Correo( (String)a.parametros[1] , (String)a.parametros[2] );
				realizada = true;
				a.jefe.entregarTarea(a);
				break;

			// TODO Aca se deben agregar las funciones con sus nombres y la forma en que se llaman;
			
			
			case "SUICIDARSE":
			case "MATATE":
			case "DESPEDIDO":
			case "PILLO":  System.out.println("NOOoOoO!!!"); 
				a.nombre = "* PARA: MI MORIR ES VIVIR";
				a.resultado = this;
				a.jefe.entregarTarea(a);
				realizada = true;
				despedido = true;
			break;
			
			default: Cadete.sleep(100); realizada = true; a.resultado = true; break;
			}
			
		} catch (Exception e )
		{
			e.printStackTrace();
		}
		
		return realizada;
	}
}
