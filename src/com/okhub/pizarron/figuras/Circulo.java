package com.okhub.pizarron.figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Figura{

	Linea l = null ;
	public Circulo(Punto pt1 ,Punto pt2){
		tipo_F  ="CIRCULO";
		this.p1= pt1; 
		this.p2= pt2;
		reagrupar();
		calcular_Centro();
		calcular_Ancho_Alto();
		
	}
	private void reagrupar(){
		Punto pivote;
		if(p2.x<p1.x&&p2.y<p1.y){
			pivote = p2;
			p2=p1;
			p1= pivote;
		}
		if(p2.x<p1.x&&p2.y>p1.y){
			int delta_Y = p2.y-p1.y;
			p2.y-= delta_Y;
			p1.y+= delta_Y;
			
			pivote = p2;
			p2=p1;
			p1= pivote;
			
		}
		if(p2.y<p1.y&&p2.x>p1.x){
			int delta_X = p2.x-p1.x;
			p2.x-= delta_X;
			p1.x+= delta_X;
			
			pivote = p2;
			p2=p1;
			p1= pivote;
			
		}
	}
	private void calcular_Centro() {
		
		int delta_X= p2.x-p1.x;
		int delta_Y= p2.y-p1.y;
		delta_X = delta_X/2;
		delta_Y = delta_Y/2;
		pto_Central= new Punto((p1.x+delta_X),(p1.y+delta_Y));
	}

	public  void mover(Punto p_Nuevo) {
		int deltaX = p_Nuevo.x - pto_Central.x;
		int deltaY = p_Nuevo.y - pto_Central.y;
		
		pto_Central.x += deltaX;
		pto_Central.y += deltaY;
		p1.x		  += deltaX;
		p1.y 		  += deltaY;
		
	}
	public void calcular_Ancho_Alto(){
		p2.x -= p1.x;
		p2.y -= p1.y;
	}
	public Punto obtener_Centro() {
		return pto_Central;
	}

	public void dibujar(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawOval(this.p1.x, this.p1.y, this.p2.x, this.p2.y);
		
		g.setColor(Color.blue);
		g.drawLine(pto_Central.x,pto_Central.y,pto_Central.x,pto_Central.y);
		
	}
	public boolean calcular_pertenencia(Punto p) {
		return false;
	}

}
