package fr.imie.traveagencyproject.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddPlaceFrame extends JFrame{
	
	public AddPlaceFrame(){
		
		JPanel panel = new JPanel();
		JPanel borderPanel = new JPanel(new BorderLayout());
		borderPanel.add(new JButton("My button"),BorderLayout.SOUTH);
		panel.add(new JButton("button num 2"));
		panel.add(new JTextArea("je suis le test",20,20));
		panel.add(new JTextField(10));
		panel.add(new JRadioButton("vert",true));
		panel.add(new JRadioButton("rouge",false));
		panel.add(new JRadioButton("bleu",false));
		panel.setBackground(Color.GREEN);
		this.setSize(300, 200); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Add a new place");
		this.setLocation(1500, 0);
		this.setContentPane(panel);
		
		this.setVisible(true);
	}
	


}
