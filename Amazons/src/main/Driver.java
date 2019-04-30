package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import board.Board;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int width = 800, height = 600;
		
		MainFrame mainFrame = new MainFrame(width,height+40);
		Board board = new Board(8,width,height,50,50);
		mainFrame.add(board);
		
		boolean running = true;
		//board.movePieceTest();
		board.setBackground(Color.GRAY);
		while(running ==true) {
			
			board.repaint();
		}
	}

}
