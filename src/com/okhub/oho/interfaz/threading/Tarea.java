package com.okhub.oho.interfaz.threading;



/**
 * Clase de Tarea. Para crear una necesita un nombre, parametros y un Jefe.
 * La prioridad y el maximo de reintentos son opcionales.
 * 
 * @author Gseva
 * @see Tarea#nombre - Para mas información sobre las funciones utilizadas.
 *
 */

public class Tarea implements Comparable<Tarea>
{
	
	/**
	 * Nombre de la funcion a llamar
	 * <p>
	 * Funciones soportadas:
	 * <p>
	 * "e" : esperar parametros[0] milisegundos <br>
	 * "d" : join del thread (terminarlo) <br>
	 * "eu" : existeUsuario <br>
	 * "vu" : Verificar_Datos_Usuario <br>
	 * "vc" : Verificar_Datos_Correo <br>
	 * "op" : obtenerPublicaciones <br>
	 * "em" : enviarMensaje <br>
	 * "ar" : acusarRecibo <br>
	 * "ar" : acusarRecibo <br>
	 * 
	 */
	public String nombre = "NOOP";
		
	public Object resultado = null;
		
	public Object[] parametros = null;
		
	public Jefe jefe = null;
		
	public int maxReintentos = 1;
		
	public float prioridad = 0.42f;
	
	
	public Tarea(){}
	
	public Tarea(String n, Jefe j)
	{
		nombre = n;
		jefe = j;
	}
	
	public Tarea(String n, Jefe j, Object c)
	{
		nombre = n;
		resultado = c;
		jefe = j;
	}
	
	public Tarea(String n, Jefe j, Object c, int r)
	{
		nombre = n;
		resultado = c;
		maxReintentos = r;
		jefe = j;
	}
	
	public Tarea(String n, Jefe j, int r)
	{
		nombre = n;
		maxReintentos = r;
		jefe = j;
	}
	
	public Tarea(String n, Jefe j, Object[] p)
	{
		nombre = n;
		jefe = j;
		parametros = p;
	}
	
	public Tarea(String n, Jefe j, Object[] p, Object c)
	{
		jefe = j;
		nombre = n;
		resultado = c;
		parametros = p;
	}
	
	public Tarea(String n, Jefe j, Object[] p, Object c, int r)
	{
		nombre = n;
		jefe = j;
		parametros = p;
		resultado = c;
		maxReintentos = r;
	}
	
	public Tarea(String n, Jefe j, Object[] p, int r)
	{
		nombre = n;
		jefe = j;
		parametros = p;
		maxReintentos = r;
	}

	
	public Tarea(String n, Jefe j, float pr)
	{
		nombre = n;
		jefe = j;
		prioridad = pr;
	}
	
	public Tarea(String n, Jefe j, Object c, float pr)
	{
		nombre = n;
		resultado = c;
		prioridad = pr;
		jefe = j;
	}
	
	public Tarea(String n, Jefe j, Object c, int r, float pr)
	{
		nombre = n;
		resultado = c;
		jefe = j;
		prioridad = pr;
		maxReintentos = r;
	}
	
	public Tarea(String n, Jefe j, int r, float pr)
	{
		nombre = n;
		jefe = j;
		maxReintentos = r;
		prioridad = pr;
	}
	
	public Tarea(String n, Jefe j, Object[] p, float pr)
	{
		nombre = n;
		parametros = p;
		jefe = j;
		prioridad = pr;
	}
	
	public Tarea(String n, Jefe j, Object[] p, Object c, float pr)
	{
		nombre = n;
		resultado = c;
		parametros = p;
		jefe = j;
		prioridad = pr;
	}
	
	public Tarea(String n, Jefe j, Object[] p, Object c, int r, float pr)
	{
		nombre = n;
		jefe = j;
		parametros = p;
		resultado = c;
		maxReintentos = r;
		prioridad = pr;
	}
	
	public Tarea(String n, Jefe j, Object[] p, int r, float pr)
	{
		jefe = j;
		nombre = n;
		parametros = p;
		prioridad = pr;
		maxReintentos = r;
	}
	
	
	
	
	@Override
	public int compareTo(Tarea otra) 
	{
		return (int)((otra.prioridad - this.prioridad)*100);		
	}
	
	
}
