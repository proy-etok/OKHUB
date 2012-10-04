package com.okhub.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.okhub.oho.interfaz.Sesion;
import com.okhub.oho.interfaz.User;
import com.okhub.pizarron.PizarronPanel;

import net.miginfocom.swing.MigLayout;

public class Ventana_Principal {

	JFrame frame;
	JTabbedPane tabbedPane;
	JTextField prueba;
	Ventana_Principal_PanelAmigos friendsPane;

	int indexTab;
	
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana_Principal.class.getResource("/com/okhub/gui/EscudoOK.png")));
		frame.setBounds(100, 100, 639, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener( new WindowListener() {
			
			@Override public void windowOpened(WindowEvent arg0) {}
			@Override public void windowIconified(WindowEvent arg0) {}
			@Override public void windowDeiconified(WindowEvent arg0) {}
			@Override public void windowDeactivated(WindowEvent arg0) {}
			@Override public void windowActivated(WindowEvent arg0) {}
			@Override 
			public void windowClosing(WindowEvent arg0) {
				Ses.ponerOnline( Ses.getUserStr(), false );
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				Ses.ponerOnline( Ses.getUserStr(), false );
				
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
		
		agregar_PanelInicio();
		tabbedPane.addTab("Pizarron", new PizarronPanel(Ses));
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Inicio"));
		
		friendsPane = new Ventana_Principal_PanelAmigos( Ses );
		frame.getContentPane().add(friendsPane, "cell 1 0,grow");
		
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
	
	public void agregar_botonX( final String title ) {
		
		indexTab = tabbedPane.indexOfTab(title);
		JPanel panel = new JPanel();
		JButton botonX = new JButton( "" );
		botonX.setMaximumSize(new Dimension( 12 , 12));
		botonX.setToolTipText("Cerrar la pestaña");
		JLabel label = new JLabel(title);
		MigLayout ml = new MigLayout( "" , "[grow][15!]" , "[15!]" );
		panel.setLayout(ml);
		panel.add((label), "growx" );
		panel.add((botonX), "right,top");
		tabbedPane.setTabComponentAt( indexTab , panel );
		botonX.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent arg0 ) {
				
						tabbedPane.removeTabAt( tabbedPane.indexOfTab(title) );
			}
		});
	}
	
	public void agregar_PanelInicio () {
		
		final JPanel panelInicio = new JPanel(new MigLayout("","[grow,fill]","[][fill,grow]"));
		tabbedPane.addTab("Inicio", null, panelInicio, null);
		prueba = new JTextField();
		prueba.setEditable(false);
		prueba.setText( Ses.getUserStr() );
		panelInicio.add( prueba, "split 2,growx");
		
		JButton botonRefrescarInicio = new JButton( "R" );
		botonRefrescarInicio.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.removeTabAt(tabbedPane.indexOfTab("Inicio"));
				agregar_PanelInicio();
			}
		} );
		
		panelInicio.add( botonRefrescarInicio, "wrap");
		
		User[] amigosEspera = Ses.obtenerAmigosEspera();
		if ( amigosEspera != null )
		for (final User amigo : amigosEspera ) {
			final JPanel panelAmigo = new JPanel(new MigLayout("","[grow]"));
			JLabel labelAmigo = new JLabel( amigo.nombre );
			JButton aceptarAmigo = new JButton( "Aceptar" );
			JButton rechazarAmigo = new JButton( "Rechazar" );
			
			aceptarAmigo.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if ( Ses.aceptarAmistad( amigo.nombre ) ) {
						JOptionPane.showMessageDialog( panelAmigo , amigo.nombre + " agregadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
						friendsPane.botonRefrescar.doClick();
						panelAmigo.removeAll();
						panelInicio.remove(panelAmigo);
						panelInicio.repaint();
					}
				}
			});
			rechazarAmigo.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if ( Ses.rechazarAmistad( amigo.nombre) ) {
						JOptionPane.showMessageDialog( panelAmigo , amigo.nombre + " rechazadisimo" , "Felicitaciones!" , JOptionPane.INFORMATION_MESSAGE );
						friendsPane.botonRefrescar.doClick();
						panelAmigo.removeAll();
						panelInicio.remove(panelAmigo);
						panelInicio.repaint();
					}
				}
			});
			
			panelAmigo.add(labelAmigo , "split 2 , grow");
			panelAmigo.add(aceptarAmigo ,"split 2");
			panelAmigo.add(rechazarAmigo);
			
			panelInicio.add(panelAmigo , "grow,wrap");
			
		}
		
		tabbedPane.setSelectedIndex( tabbedPane.indexOfTab("Inicio"));
		
		
	}
}
