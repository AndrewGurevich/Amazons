package board;

import java.awt.Color;
import java.awt.Graphics;

public class Piece {
	private String name = "";
	private boolean canMove = false;
	private boolean checkerPlaced = false;
	public void draw(Graphics g,int x,int y) {
		
	}
	public void setCanMove(boolean x) {
		this.canMove = x;
	}
	public boolean getCanMove() {
		return this.canMove;
	}
	public void setCheckerPlaced(boolean x) {
		this.checkerPlaced = x;
	}
	public boolean getCheckerPlaced() {
		return this.checkerPlaced;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

class Checker extends Piece{
	private int height;
	private int length;
	public Checker(int height,int length) {
		this.height = height;
		this.length = length;
		this.setCanMove(false);
	}
	@Override
	public void draw(Graphics g,int x,int y) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, length, height);
	}
	
	
}

