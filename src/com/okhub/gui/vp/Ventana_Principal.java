package com.okhub.gui.vp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import net.miginfocom.swing.MigLayout;

import com.okhub.oho.interfaz.Sesion;
import com.okhub.pizarron.PizarronPanel;

/**
 * La clase madre de las ventanas. Crea la ventana general,
 * le agrega los elementos indispensables.
 * 
 * @author Gseva
 * @see Ventana_Principal_Utilidad
 */
public class Ventana_Principal {

	JFrame frame;
	
	/**
	 * El tabbed pane es el elemento contenedor general 
	 * al cual se le agregan tabs con el contenido
	 * como puede ser el chat, las publicaciones, el pizarron, etc.
	 * Very importante y usado.
	 */
	JTabbedPane tabbedPane;
	JTextField panelBienvenida;
	JSplitPane splitPane;
	/**
	 * El panel de amigos, contenedor de la lista de amigos ( TODO y grupos ).
	 * Contiene un menu de proposito general con herramientas
	 * de navegacion por el programa.
	 */
	Ventana_Principal_PanelAmigos friendsPane;
	Ventana_Principal_Inicio panelInicio;

	int indexTab;
	
	private Sesion Ses = null;


	/**
	 * Crea la ventana madre.
	 * 
	 * @param S Sesion con la que se logueó el usuario
	 * @see Sesion
	 */
	public Ventana_Principal(Sesion S) {
		
		Ses = S;
		initialize();
		
	}

	/**
	 * Inicializa los componentes de la ventana.
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
		
		
		friendsPane = new Ventana_Principal_PanelAmigos( Ses );
		splitPane.setRightComponent(friendsPane);
		
		panelInicio = new Ventana_Principal_Inicio( Ses , friendsPane  );
		JScrollPane sp = new JScrollPane( panelInicio , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		tabbedPane.addTab("Inicio", sp );
		
		tabbedPane.addTab("Pizarron", new PizarronPanel(Ses));
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab("Inicio"));
		
		splitPane.setDividerLocation(450);
	    splitPane.setDividerSize(10);
		
		friendsPane.setMinimumSize(new Dimension ( 100 , 50 ));
		tabbedPane.setMinimumSize(new Dimension ( 137 , 50 ));
		
		friendsPane.setPreferredSize(new Dimension (300,400));
		tabbedPane.setPreferredSize(new Dimension (137,400));
		
		frame.getContentPane().add(splitPane, "grow");
		
		
	}
	
	/**
	 * El metodo se fija si existe una pestaña en tab con el titulo title
	 * 
	 * @param tab
	 * - TabbedPane en cuestion
	 * 
	 * @param title
	 * - Titulo de la pestaña
	 */
	
	public static boolean existeTab ( JTabbedPane tab , String title ){
		
		for ( int i = 0 ; i < tab.getTabCount() ; i++ )
			if ( tab.getTitleAt( i ).equals(title) )
				return true;
		
		return false;
		
	}
	
	/**
	 * Le agrega a una pestaña del tabbedPane un panel con el
	 * titulo title y un boton en el costado derecho superior 
	 * que cerrará dicha pestaña.
	 * 
	 * 
	 * @param title - title titulo a imprimir en la pestaña.
	 */
	
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
	
}
