package com.okhub.testing;

import java.awt.Event;


public class TareaFinalizada extends Event
{
	
	Tarea tarea;

	public TareaFinalizada(Object target, int id, Tarea arg) 
	{
		super(target, id, arg);
		this.tarea = arg;
		// TODO Auto-generated constructor stub
	}
}
