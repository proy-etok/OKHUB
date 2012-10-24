package com.okhub.pizarron;

import java.awt.Color;
import java.awt.Menu;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Sesion;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.Button;

public class PizarronPanel extends JPanel {
	private JButton btnMover = new JButton("Mover");
	static Pizarron okPizarron;
	/**
	 * Create the panel.
	 */
	public PizarronPanel(Sesion S) {
		setLayout( new MigLayout("", "[0:100:500,grow,fill]", "[0:100:300,grow,fill][][]") );
		
		JTabbedPane piza1 = new JTabbedPane(JTabbedPane.TOP);
		add(piza1, "flowx,cell 0 0");
		okPizarron = new Pizarron(S);
		piza1.addTab("New tab", null, okPizarron, null);
		okPizarron.addMouseListener((MouseListener) okPizarron);
		okPizarron.addMouseMotionListener((MouseMotionListener) okPizarron);
		
		okPizarron.setBackground(Color.GREEN);
		
		JButton btn_Linea = new JButton("Linea");
		add(btn_Linea, "flowx,cell 0 1");
		btn_Linea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okPizarron.set_Herramienta("LINEA");
			}
		});
		
		Button button = new Button("Circulo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okPizarron.set_Herramienta("CIRCULO");
			}
		});
		add(button, "cell 0 1");
		add(btnMover, "cell 0 1");
		
		JButton btnEditar = new JButton("Borrar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okPizarron.set_Herramienta("BORRAR");
			}
		});
		add(btnEditar, "cell 0 2");
		
		btnMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				okPizarron.set_Herramienta("MOVER");
								
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem herramientas = new JMenuItem("herramientas");
		JMenuItem color = new JMenuItem("color");
		
		menu.add(herramientas);
		menu.add(color);
		
		herramientas.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		add(menuBar,"cell 0 1");
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				okPizarron.set_Herramienta("EDITAR");
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
