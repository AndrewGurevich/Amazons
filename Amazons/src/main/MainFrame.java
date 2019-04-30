package main;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	//private JButton randomBTN;
	
	public MainFrame(int width,int height) {
		super("Amazons");
		
		
		//Exit on close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//Create components
		//randomBTN = new JButton("random button");
		//add(randomBTN, BorderLayout.SOUTH); 
				
		//Size frame
		setSize(width, height);
		setLocationRelativeTo(null);
				
		//Show the window
		setVisible(true);
				
		//Resizable to false
		//setResizable(false);
	}
}
