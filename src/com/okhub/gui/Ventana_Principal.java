package com.okhub.gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.okhub.oho.interfaz.Sesion;
import com.okhub.pizarron.PizarronPanel;

import net.miginfocom.swing.MigLayout;

public class Ventana_Principal {

	JFrame frame;
	JList<String> list;
	JTabbedPane tabbedPane;
	JTextField prueba;
	JButton botonRefrescar;

	private Sesion Ses = null;


	/**
	 * Create the application.
	 */
	public Ventana_Principal(Sesion S) {
		
		Ses = S;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Principal.class.getResource("/com/okhub/gui/EscudoOK.JPG")));
		frame.setBounds(100, 100, 639, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener( new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				Ses.ponerOnline( Ses.getUserStr(), false );
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		JMenuItem miFile = new JMenuItem("File");
		mnMenu.add(miFile);
		JMenuItem miHola = new JMenuItem("Hola");
		mnMenu.add(miHola);
		
		
		frame.getContentPane().setLayout(new MigLayout("", "[300px,grow,fill][137px,growprio 50,grow,left]", "[315px,grow,fill]"));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		JPanel panelInicio = new JPanel();
		prueba = new JTextField();
		prueba.setEditable(false);
		prueba.setText( Ses.getUserStr() );
		panelInicio.add( prueba, "center");
		tabbedPane.addTab("Inicio", null, panelInicio, null);
		
		tabbedPane.addTab("Pizarron", new PizarronPanel(Ses));
		
		JPanel friendsPane = new JPanel();
		friendsPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(friendsPane, "cell 1 0,grow");
		MigLayout mlf = new MigLayout( "","grow,fill","[][grow,fill]");
		friendsPane.setLayout(mlf);
		
		JMenu menu = new JMenu("Menu");
//		menu.setBounds(10, 11, 70, 19);
		JMenuItem menuItem = new JMenuItem("File");
		menu.add(menuItem);
		JMenuItem menuItem_1 = new JMenuItem("Hola");
		menu.add(menuItem_1);
		friendsPane.add(menu , "top,growx,split 2");
		
		botonRefrescar = new JButton("R");
		friendsPane.add( botonRefrescar , "wrap");
		
		list = new JList<String>();
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setValueIsAdjusting(true);    
		JScrollPane scroll = new JScrollPane(list);
		scroll.setViewportView(list);
		friendsPane.add( scroll, "grow,center");
		
		

		
		
		miHola.addMouseListener(new MouseAdapter() {
			@Override 
			public void mousePressed (MouseEvent arg0) {
				

				
			}
		});		
		
		miFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed( MouseEvent e ) {
				

				
			}
		});
		
	}
	
	public boolean existeConversacion ( JTabbedPane tab , String title ){
		
		for ( int i = 0 ; i < tab.getTabCount() ; i++ )
			if ( tab.getTitleAt( i ).equals(title) )
				return true;
		
		return false;
		
	}
	
	public void agregar_botonX( final JPanel panel , final JTabbedPane tab , final String title ) {
		
		final int index = tab.indexOfTab(title);
		Color colorBack = tab.getBackground();
		panel.setBackground( colorBack );
		JButton botonX = new JButton( "x" );
		MigLayout ml = new MigLayout( "" , "[fill][]" , "[]" );
		panel.setLayout(ml);
		JLabel label = new JLabel(title);
		label.setBackground( colorBack);
		panel.add((label), "growx" );
		panel.add((botonX), "right,top");
		tab.setTabComponentAt( tab.getSelectedIndex() , panel );
		botonX.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				for ( int i = 0 ; i < tab.getTabCount() ; i++ )
					if( i == index )
						tab.removeTabAt( i );
			}
		});
		
		
	}
}
