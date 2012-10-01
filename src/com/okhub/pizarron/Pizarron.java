package com.okhub.pizarron;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.okhub.oho.interfaz.Sesion;

public class Pizarron extends Canvas implements MouseListener,MouseMotionListener
{
	Sesion S = null;
	
//	Motion Listener
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
//	MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	Pizarron (Sesion S){ this.S = S; }
	
	public void paint(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(0, 0, getSize().width, getSize().height);
	}
	

}
