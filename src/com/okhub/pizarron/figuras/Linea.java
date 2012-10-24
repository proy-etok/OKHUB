package com.okhub.pizarron.figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Linea extends Figura{

	private float derivada ; 
	
	public Linea(){
		tipo_F= "LINEA";
		p1= new Punto();
		p2= new Punto();
		pto_Central = obtener_Centro();
	}
	
	public Linea(Punto pt1, Punto pt2){
		tipo_F		= "LINEA";
		p1			=new Punto(pt1);
		p2			=new Punto (pt2);
		pto_Central =obtener_Centro();
	}
	public Linea(int x1,int y1,int x2, int y2){
		tipo_F		= "LINEA";
		p1			= new Punto(x1,y1);
		p2			= new Punto(x2,y2);
		pto_Central = this.obtener_Centro();
	}
	private void calcular_Derivada(){
		
	}
	@Override
	public void mover(Punto p_Nuevo) {
		
		int deltaX = p_Nuevo.x - pto_Central.x;
		int deltaY = p_Nuevo.y - pto_Central.y;
		
		pto_Central.x += deltaX;
		pto_Central.y += deltaY;
		p1.x		  += deltaX;
		p2.x		  += deltaX;
		p2.y		  += deltaY;
		p1.y 		  += deltaY;
	}	
	@Override
	public Punto obtener_Centro(){
		int x = p1.x + ((int) (p2.x-p1.x)/2);
		int y = p1.y + ((int) (p2.y-p1.y)/2);
		pto_Central= new Punto(x,y);
		
		return pto_Central;
	}
	public boolean calcular_pertenencia(Punto p) {
		
		return false;
	}
	public void dibujar(Graphics g) {
		
		g.setColor(Color.RED);
		g.drawLine(p1.x,p1.y,p2.x,p2.y);
	}

	
}
