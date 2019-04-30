package board;

import java.awt.Color;
import java.awt.Graphics;

public class blackQueen extends Piece{
		
		private int height;
		private int length;
		public blackQueen(int height,int length) {
			this.height = height;
			this.length = length;
			this.setCanMove(false);
			setName("blackQueen");
		}
		@Override
		public void draw(Graphics g,int x,int y) {
			g.setColor(Color.GREEN);
			g.drawLine(x, y, length+x, height+y); 
			g.drawLine(length+x, y, x, height+y);
		}
	}
