package com.okhub.pizarron.figuras;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	public Punto p1,p2;
	public Color Co;
	protected String tipo_F;
	public Punto pto_Central;
	public abstract Punto obtener_Centro();
	public abstract void mover(Punto centro_Nuevo);
	public abstract void dibujar(Graphics g);
	public abstract boolean calcular_pertenencia(Punto p);
}
