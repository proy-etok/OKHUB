package com.okhub.pizarron.figuras;

import java.awt.Graphics;
import java.util.ArrayList;

public class Libre extends Figura{

	private ArrayList<Punto> lista_Puntos;
	public Libre(){
		tipo_F= "LIBRE";
		ArrayList<Punto> lista_Puntos= new ArrayList<Punto>();
	}
	public void agregar_Punto(Punto p_Nuevo){
		lista_Puntos.add(p_Nuevo);
	}
	public Punto obtener_Centro() {
		return null;
	}
	public void mover(Punto centro_Nuevo) {

		
	}
	public void dibujar(Graphics g) {
				
	}
	public boolean calcular_pertenencia(Punto p) {
		return false;
	}

}
