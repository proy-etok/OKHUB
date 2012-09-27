package com.okhub.gui;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class Ventana_Principal {

	JFrame frame;
	JList<String> list;
	JTabbedPane tabbedPane;
	

	/**
	 * Launch the application.
	 */
	public void Crear_Ventana_Principal() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Principal window = new Ventana_Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana_Principal() {
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
		tabbedPane.addTab("Inicio", null, panelInicio, null);
		
		JPanel friendsPane = new JPanel();
		friendsPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(friendsPane, "cell 1 0,grow");
		friendsPane.setLayout(null);
		
		String[] nombres = new String[] {"Josue", "Jorge", "Rolando", "wachin" , "Jorge2" , "jorge3","jorge4", "jorge5"};
		list = new JList<String>();
		list.setListData( nombres );
		
		
//		list.get
		
		list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(10, 38, 117, 332);
		friendsPane.add(list);
		
		JMenu menu = new JMenu("Menu");
		menu.setBounds(10, 11, 70, 19);
		friendsPane.add(menu);
		JMenuItem menuItem = new JMenuItem("File");
		menu.add(menuItem);
		JMenuItem menuItem_1 = new JMenuItem("Hola");
		menu.add(menuItem_1);
		

		list.addMouseListener( new MouseAdapter( ) {
			public void mouseClicked ( MouseEvent e ){
				
				if ( e.getClickCount() == 2 ) {
					
					String title = list.getSelectedValue();
					while( !existeConversacion( tabbedPane, title ) ){
						MigLayout ml = new MigLayout("","grow,fill","[80%|20%]");
						JPanel panel = new JPanel( ml );
						final JTextArea chat = new JTextArea();
						chat.setEditable(false);
						JScrollPane scroll = new JScrollPane(chat,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						panel.add( scroll , "grow,wrap" );
						final JTextField tfenviar = new JTextField( "" );
						panel.add( tfenviar , "split 2, bottom, growx" );
						JButton jbenviar = new JButton("Enviar");
						panel.add( jbenviar , "bottom");
						tabbedPane.addTab( title , panel );
						jbenviar.addActionListener( new ActionListener() {
							
						@Override
						public void actionPerformed(ActionEvent e) {
							if ( !tfenviar.getText().equals( "" ) )
								chat.append( "yo: " + tfenviar.getText() + "\n" );
							tfenviar.setText("");
							}
						});
					}
					tabbedPane.setSelectedIndex( tabbedPane.indexOfTab(title));
					JPanel panelTop = new JPanel();
					agregar_botonX( panelTop, tabbedPane , title );
						
					
				}
				
			}
		});
		
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
