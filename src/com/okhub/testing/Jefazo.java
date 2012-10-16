package com.okhub.testing;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Jefazo implements Jefe
{
	boolean algoCambio = false;
	
	Timer timer = new Timer();
	
	int delay = 500;
	
	boolean running = true;
	
	Cadete carlos;
	
	List<Tarea> tareasCompletadas = new ArrayList<Tarea>();
	
	Callback check = new Callback();
	
	public Jefazo()
	{
		timer.scheduleAtFixedRate(check, 500, delay);
	}
	
	
	class Callback extends TimerTask 
	{
		public void run() 
		{
			atenderTareas();
//			timer.purge();
			
//			if(running)
//			timer.schedule( check, delay);

			if(!running)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.cancel();
				timer.purge();
				timer.cancel();
			}
		}
	}
	
	public void comenzar()
	{
		carlos = new Cadete();
	    
		
	    System.out.println("                            Contrato a Carlos");
	    
	    carlos.start ();
	    
	    System.out.println("                            Le doy una tarea");
	    
	    carlos.agregarTarea(new Tarea("EXISTEUSUARIO PARA: IMPRIMIR EN PANTALLA", this ,new Object[]{"josue"}, false,0.33f));
	    
	    
	    
	    System.out.println("                            Le doy otra tarea");
	    
	    carlos.agregarTarea(new Tarea("EXISTEUSUARIO PARA: IMPRIMIR EN PANTALLA", this ,new Object[]{"gseva"}, false,0.33f));
	    
	    
	    try
	    {
		    while(!algoCambio)
			{
				System.out.println("                            Espero");
				Thread.sleep(1000);
				//NOTING, TILL WAITIN' PA
			}
		    
		    algoCambio = false;
		    
		    System.out.println("                            Excelente");
		    
		    
		    while(!algoCambio)
		    {
		    	System.out.println("                            Espero otra vez");
		    	Thread.sleep(1000);
		    	//NOTING, TILL WAITIN' PA
		    }
		    		    
		    carlos.agregarTarea(new Tarea("PILLO",this));
		    
		    System.out.println("                            Despido a Carlos");
		    
		    Thread.sleep(1000);
		    
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    }		
	}
	
	public void terminar()
	{
		running = false;		
		try {
			timer.cancel();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void entregarTarea(Tarea tarea) 
	{
		tareasCompletadas.add(tarea);		
	}

	@Override
	public void atenderTareas() 
	{
		try 
		{
			System.out.print(".");
			
			if( !tareasCompletadas.isEmpty())
			{
				algoCambio = true;
				
				List<Tarea> aEliminar = new ArrayList<Tarea>();
				
				for( Tarea tarea : tareasCompletadas)
				{
					if(tarea.nombre.contains(" PARA: "))
					if(tarea.nombre.split(" PARA: ").length > 1)
					{
						switch((tarea.nombre.split(" PARA: "))[1])
						{
						case "IMPRIMIR LINEA": System.out.println(":-:---------------------------------------------------------:-:"); break;
						case "IMPRIMIR EN PANTALLA": System.out.println(tarea.resultado); break;
						case "MI MORIR ES VIVIR": System.out.println("                            MUERE: " + tarea.resultado); ((Cadete)tarea.resultado).join(0); break;
						default: break;
						}
					}				
					
					aEliminar.add(tarea);
				}
				
				for( Tarea tarea : aEliminar)
				{
					tareasCompletadas.remove(tarea);
				}
			}		
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


}
