package board;

import java.awt.Color;
import java.awt.Graphics;

public class whiteQueen extends Piece{
		
		private int height;
		private int length;
		public whiteQueen(int height,int length) {
			this.height = height;
			this.length = length;
			this.setCanMove(false);
			setName("whiteQueen");
		}
		@Override
		public void draw(Graphics g,int x,int y) {
			g.setColor(Color.BLUE);
			g.drawLine(x, y, length+x, height+y); 
			g.drawLine(length+x, y, x, height+y);
		}
	}

