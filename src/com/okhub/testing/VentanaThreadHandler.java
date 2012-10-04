package com.okhub.testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.security.auth.callback.Callback;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class VentanaThreadHandler extends JFrame implements Callback  {

	private JPanel contentPane;
	private JTextField FieldA;
	private JTextField FieldB;

	private Cadete carlos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadete nuevo = new Cadete();
					nuevo.start();
					VentanaThreadHandler frame = new VentanaThreadHandler(nuevo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *  El 'frame' recibe un cadete que actualmente este trabajando
	 */
	public VentanaThreadHandler(Cadete c) {
		
		carlos = c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][]", "[][][][]"));
		
		JButton P1 = new JButton("P1");
		contentPane.add(P1, "cell 1 1");
		
		FieldA = new JTextField();
		FieldA.setEditable(false);
		contentPane.add(FieldA, "cell 3 1,growx");
		FieldA.setColumns(10);
		
		JButton P2 = new JButton("P2");
		contentPane.add(P2, "cell 1 3");
		
		FieldB = new JTextField();
		FieldB.setEditable(false);
		contentPane.add(FieldB, "cell 3 3,growx");
		FieldB.setColumns(10);
	}
	


}
