package board;

import java.awt.Graphics;

public class Square {
	private int squareSize;
	private boolean isEmpty = true;
	private Piece pieceOnSquare = null;
	public Square(int squareSize) {
		this.squareSize = squareSize;
	}
	
	public void putPieceOnSquare(Piece piece) {
		this.pieceOnSquare = piece;
	}
	public void draw(Graphics g,int x,int y) {
		if (pieceOnSquare!=null) {
			pieceOnSquare.draw(g,x,y);
		}
	}
	public Piece getPiece() {
		return this.pieceOnSquare;
	}
}
