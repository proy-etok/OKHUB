package com.okhub.pizarron;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.okhub.oho.interfaz.Sesion;
import com.okhub.pizarron.figuras.*;

public class Pizarron extends Canvas implements MouseListener,MouseMotionListener{

	
	private Herramienta herramienta = new Herramienta();
	private Sesion t = null;
	ArrayList<Figura> list_F = new ArrayList<Figura>() ;
	Figura f_R=null;
	
 	Pizarron (Sesion S){ 
		this.t = S;
		herramienta.set_Lista_Figuras(list_F);
	}
 	
	public void set_Herramienta(String h){
		herramienta.set_Herramienta(h);
	}
	
	public void mouseDragged(MouseEvent e) {
		//System.out.println("2°: "+e.getPoint().getX()+" "+e.getPoint().getY());
		//if(herramienta.get_Tipo()!="MOVER"&&herramienta.get_Tipo()!="BORRAR")
			//f_R = herramienta.continuar_Dibujo(e.getPoint());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("pos"+e.getPoint().getX()+" "+e.getPoint().getY());
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
			herramienta.set_Punto1(e.getPoint());
			//System.out.println("1°: "+e.getPoint().getX()+" "+e.getPoint().getY());
			repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
			f_R = herramienta.hacer(e.getPoint());
			
			if(f_R!=null){
				list_F.add(f_R);
				System.out.println("numero de figuras:"+list_F.size());
				for(int i= 0;i<list_F.size();i++){
					System.out.println("FIGURA N°: "+(i+1)+" pt1="+list_F.get(i).p1.x+" "+list_F.get(i).p1.y);
					System.out.println("FIGURA N°: "+(i+1)+" pt2="+list_F.get(i).p2.x+" "+list_F.get(i).p2.y);
					System.out.println("FIGURA N°: "+(i+1)+" pto_Cent="+list_F.get(i).pto_Central.x+" "+list_F.get(i).pto_Central.y);
				}
				
			}
			repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
	public void paint(Graphics g)
	{
		if(f_R!=null)
			f_R.dibujar(g);
			
		g.setColor(Color.green);
		g.fillRect(0, 0,getSize().width, getSize().height);
		g.setColor(Color.BLACK);
		g.drawString("HOLA PIZARRON", 5, 12);
		
		for(int i=0; i<list_F.size(); i++){
			list_F.get(i).dibujar(g);
		}
	}
}
