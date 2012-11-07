package com.okhub.oho.interfaz.threading;

import java.util.PriorityQueue;

import com.okhub.gui.Ventana_Registro_Verificacion;
import com.okhub.oho.interfaz.Sesion;

public class Cadete extends Thread{

	
		/**
		 * La lista de tareas, ordenada por prioridad
		 */
		PriorityQueue<Tarea> tareas = new PriorityQueue<Tarea>() ;
		
		protected boolean despedido = false;
		
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
						
						if( terminada || (reintentos > actual.maxReintentos))
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
			System.out.println("CHau");
		}
		
		/**
		 * Agrega una tarea a la lista de tareas del cadete explotado
		 * 
		 * @param t - tarea a realizar, se guardara en la lista de tareas
		 * @return - devuelve la cantidad de elementos actual en la lista
		 * 
		 * @see Cadete#tareas
		 */
		
		public int agregarTarea( Tarea t )
		{
			System.out.println("Agregando tarea");
			
			tareas.add(t);
			return tareas.size();
		}
		
		/**
		 * Lleva a cabo la tarea mediante un switch monstruoso que verifica 
		 * 
		 * @param a - Tarea a realizar
		 * @return - true si se pudo realizar la tarea, sino false
		 * 
		 * @see Tarea#nombre
		 */
		
		public boolean realizarTarea(Tarea a)
		{
			boolean realizada = false; 
			
			try
			{
				Ventana_Registro_Verificacion vrv = null;
				
				switch((a.nombre.split(" PARA: "))[0])
				{
				case "NOOP": Cadete.sleep(4000); realizada = true; break;
				
				case "e": Cadete.sleep((int)a.parametros[0]); 
					a.resultado = true; 
					a.jefe.entregarTarea(a); 
					realizada = true; 
					break;
				
				case "eu":
					a.resultado = Sesion.existeUsuario(((String)a.parametros[0]));
					realizada = true;
					a.jefe.entregarTarea(a);
					break;
				
				case "vu":
					vrv = new Ventana_Registro_Verificacion();
					a.resultado = vrv.Verificar_Datos_Usuario((String)a.parametros[1]);
					realizada = true;
					a.jefe.entregarTarea(a);
					
					break;
					
				case "vc":
					vrv = new Ventana_Registro_Verificacion();
					a.resultado = vrv.Verificar_Datos_Correo( (String)a.parametros[1] , (String)a.parametros[2] );
					realizada = true;
					a.jefe.entregarTarea(a);
					break;
					
				case "d":  System.out.println("NOOoOoO!!!"); 
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
