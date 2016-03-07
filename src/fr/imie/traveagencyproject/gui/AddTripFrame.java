package fr.imie.traveagencyproject.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AddTripFrame extends JFrame{
	public AddTripFrame(){
		JPanel panel = new JPanel();
		panel.add(new JButton("My button"));
		
		this.setSize(300, 200); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Add a nex trip");
		this.setVisible(true);
		this.setContentPane(panel);
	}
	


}
