package com.okhub.pizarron;

import java.awt.Point;
import java.util.ArrayList;

import com.okhub.pizarron.figuras.Circulo;
import com.okhub.pizarron.figuras.Figura;
import com.okhub.pizarron.figuras.Linea;
import com.okhub.pizarron.figuras.Punto;

public class Herramienta {

	
	private String tipo;
	private Punto pt1=null;	
	private Punto pt2=null;
	Punto 	pt_Ref	 =null;
	Figura  figura_Ref=null;
	Figura	f_R= null;
	int delta_Y_de_referencia=1000;
	int delta_X_de_referencia=1000;
	
	ArrayList<Figura> list_F;
	public Herramienta(){
		tipo= null;
	}
	
	public void set_Herramienta(String tipo){
		this.tipo= tipo;
	}
	public void set_Lista_Figuras(ArrayList<Figura> list_F){
		this.list_F=  list_F; 
	}
	public String get_Tipo(){
		return tipo;
	}
	private boolean verificar_proximidad(int delta_X,int delta_Y){
		if(delta_X < this.delta_X_de_referencia && delta_Y<this.delta_Y_de_referencia){
			return true;
		}
		return false;
	}
	private boolean sacar_Cercania(Punto centro){
		int delta_Y= pt1.y-centro.y;
		int delta_X= pt1.x-centro.x;
		if(delta_Y<0){
			delta_Y = delta_Y * -1;
		}
		if(delta_X<0){
			delta_X= delta_X * -1;
		}
		if(delta_Y<10&&delta_X<10){
			boolean x;
			x= this.verificar_proximidad(delta_X, delta_Y);
			return x;
		}
		return false;
		
	}
	public void set_Punto1(Point p){
		pt1 = new Punto ((int) p.getX(),(int) p.getY());
		switch(tipo)
		{
			case "LINEA":
							f_R = (Figura) new Linea(pt1,pt1);
				break;
				
			case "MOVER":
				
						for(int i=0; i< list_F.size();i++){
							Punto centro = list_F.get(i).pto_Central;
							if(sacar_Cercania(centro)){
								figura_Ref= list_F.get(i);
							}
						}
				break;
			case "CIRCULO":
							
							f_R= (Figura) new Circulo(pt1,(new Punto (4,4)));
				break;
			case "BORRAR":
						for(int i=0; i< list_F.size();i++){
							Punto centro = list_F.get(i).pto_Central;
							if(sacar_Cercania(centro)){
								list_F.remove(i);
								break;
							}
						}
				break;

		}
	}
	public Figura continuar_Dibujo(Point cont){
		Punto pr = new Punto ((int)cont.getX(),(int)cont.getY());
		f_R.p2= pr;
		if(tipo == "CIRCULO"){
			f_R.p2.x -= f_R.p1.x;
			f_R.p2.y -= f_R.p1.y;
		}
		return f_R;
	}
	public Figura hacer(Point aux){

		
		pt2 = new Punto((int)aux.getX(),(int)aux.getY());	
		Figura a = null;
		switch(tipo)
		{
			case "LINEA":
							a = new Linea(pt1,pt2);
							
							
				break;
				
			case "MOVER":
							figura_Ref.mover(pt2);
							figura_Ref= null;
							delta_Y_de_referencia=1000;
							delta_X_de_referencia=1000;			
				break;
			case "CIRCULO":
							a= new Circulo(pt1,pt2);
				break;
			
			default: 
							
				break;
		}
		f_R =null;
		return a;
	}

}
