package com.okhub.pizarron.figuras;

public class Punto {

	public int x,y;
	public Punto(){
		this.x=y=0;
	}
	public Punto(int x,int y){
		this.x=x;	
		this.y=y;
	}
	public Punto(Punto p){

		this.x=p.x;
		this.y=p.y;
	}
	public void  set_Punto(Punto p){

		this.x = p.x;
		this.y=  p.y;
	}
	public void  set_Punto(int x, int y){
		this.x= x;
		this.y= y;
	}
	public Punto get_Punto(){
		return this;
	}
}
