package board;

import java.awt.Graphics;

public class Piece {
	private boolean canMove = true;
	public void draw(Graphics g,int x,int y) {
		
	}
	public void setCanMove(boolean x) {
		this.canMove = x;
	}
	public boolean getCanMove() {
		return this.canMove;
	}
}

class Queen extends Piece{
	private int height;
	private int length;
	public Queen(int height,int length) {
		this.height = height;
		this.length = length;
	}
	@Override
	public void draw(Graphics g,int x,int y) {
		g.drawLine(x, y, length+x, height+y); //Learn Graphics
		g.drawLine(length+x, y, x, height+y);
	}
	
	
}
