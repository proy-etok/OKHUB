package com.okhub.gui.vp;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
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
	JSplitPane splitPane;
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
		
		
		frame.getContentPane().setLayout(new MigLayout( "","[437px,grow,fill]", "[315px,grow,fill]" ) );
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		splitPane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT );
		splitPane.setVisible( true );
		
		splitPane.setLeftComponent(tabbedPane);
	    splitPane.setOneTouchExpandable(true);
		
		agregar_PanelInicio();
		tabbedPane.addTab("Pizarron", new PizarronPanel(Ses));
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Inicio"));
		
		friendsPane = new Ventana_Principal_PanelAmigos( Ses );
		splitPane.setRightComponent(friendsPane);
		
		splitPane.setDividerLocation(450);
	    splitPane.setDividerSize(10);
		
		friendsPane.setMinimumSize(new Dimension ( 100 , 50 ));
		tabbedPane.setMinimumSize(new Dimension ( 137 , 50 ));
		
		friendsPane.setPreferredSize(new Dimension (300,400));
		tabbedPane.setPreferredSize(new Dimension (137,400));
		
		frame.getContentPane().add(splitPane, "grow");
		
		
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
	
	/**
	 * @author Gseva
	 * 
	 * 
	 */
	
	public void agregar_PanelInicio () {
		
		final JPanel panelInicio = new JPanel(new MigLayout("fillx","[grow]","5[pref]"));
		JScrollPane sp = new JScrollPane( panelInicio , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		tabbedPane.addTab("Inicio", null, sp, null);
		prueba = new JTextField();
		prueba.setEditable(false);
		prueba.setText( "Bienvenido, " + Ses.getUserStr() + "!" );
		panelInicio.add( prueba, "growx,split 2");
		
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
			final JPanel panelAmigo = new JPanel(new MigLayout("fillx","[grow]","10[]"));
			JLabel labelAmigo = new JLabel( "Tiene una invitacion de "+amigo.nombre );
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
			
			panelAmigo.add(labelAmigo , "growx,split 3");
			panelAmigo.add(aceptarAmigo ,"right");
			panelAmigo.add(rechazarAmigo , "right");
			panelAmigo.add(new JSeparator(JSeparator.HORIZONTAL) , "newline,growx");
			
			panelInicio.add(panelAmigo , "top,growx,wrap");
			
		}
		
		tabbedPane.setSelectedIndex( tabbedPane.indexOfTab("Inicio"));
		
		
	}
}
