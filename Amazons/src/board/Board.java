package board;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Board extends JPanel{
	private Square[] squares;
	private Piece[] pieces;
	private int boardSize;
	private int queenHeight;
	private int queenLength;
	private int width;
	private int height;
	private boolean queenSelected = false;
	private Square selected = null;
	private Square chosenSquare = null;
	private int oldIndex;
	
	public Board(int boardSize,int width,int height,int queenHeight,int queenLength) {
		squares = new Square[boardSize*boardSize];
		this.boardSize = boardSize;
		this.queenHeight = queenHeight;
		this.queenLength = queenLength;
		this.width = width;
		this.height = height;
		fillBoard();
	
	}
	private boolean moveIsValid(int old,int index) {
		int y = old/boardSize;
		int x = old-y*boardSize;
		
		//diagonals
		//up and left
		while(x>=0 && y>=0) {
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x--;
			y--;
		}
		
		//up and right
		y = old/boardSize;
		x = old-y*boardSize;
		while(x<boardSize && y>=0) {
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x++;
			y--;
		}
		
		//down and left
		y = old/boardSize;
		x = old-y*boardSize;
		while(x>=0 && y<boardSize) {
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x--;
			y++;
		}
		
		//down and right
		y = old/boardSize;
		x = old-y*boardSize;
		while(x<boardSize && y<boardSize) {
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x++;
			y++;
		}
		
		//up and down
		y=0;
		x = old-(old/boardSize)*boardSize;
		while(y<boardSize) {
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			y++;
		}
		//left and right
		y = old/boardSize;
		x = 0;
		while(x<boardSize) {
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x++;
		}
		
		
		return false;
	}
	public void movePieceTest() {
		movePiece(squares[12],squares[20]);
	}	
	private void movePiece(Square oldLocation,Square newLocation) {
		//get square,new square
		//move piece to new square
		//set old square to null
		Piece piece = oldLocation.getPiece();
		newLocation.putPieceOnSquare(piece);
		oldLocation.putPieceOnSquare(null);
	}
	private void fillBoard() {
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new Square(50); //change this hardcoded value
		}
		//starting position
		int[][] list = {{0,2},{1,2}};
		loadQueens(list);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Game logic
		
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	int index = convertXYToIndex(e.getX(),e.getY());
            	chosenSquare = squares[index];
            	if (selected== null && 
            			chosenSquare.getPiece()!=null && 
            			chosenSquare.getPiece().getCanMove()==true) {
            			System.out.println("square selected");
            			selected = squares[convertXYToIndex(e.getX(),e.getY())];
            			oldIndex = convertXYToIndex(e.getX(),e.getY());
            	}
            	else if(chosenSquare.getPiece()==null && 
            			selected!=null &&
            			moveIsValid(oldIndex,index)==true){
            		
            		System.out.println("old square:"+oldIndex+" new square:"+index);
            		movePiece(selected,chosenSquare);
            		System.out.println("piece moved!");
            		selected = null;
            		chosenSquare.getPiece().setCanMove(false);
            		
            			
            		
            	}
            	else {
            		//System.out.println("hi");
            	}
            }
        });

        //addMouseMotionListener(new MouseAdapter() {
           // public void mouseDragged(MouseEvent e) {
           // 	if (queenSelected = true) {
            		//draw image to mouse
           // 	}
          //  }
       // });
		
		
		//draw grid
		
		//vertical lines
		for(int i =1;i<boardSize;i++) {
			g.drawLine(i*(width/boardSize), 0, i*(width/boardSize), height);
		}
		//horizontal lines
		for(int i =1;i<boardSize;i++) {
			g.drawLine(0, i*(height/boardSize), width, i*(height/boardSize));
		}
		
		//draw pieces 
		for (int i = 0; i < squares.length; i++) {
			if(squares[i]!=null) {
				int x = i%boardSize*(width/boardSize)+(width/(boardSize*4));
				int y = i/boardSize*(height/boardSize)+(height/(boardSize*4));
				squares[i].draw(g,x,y);
			} 
		}
	}
	public void loadQueens(int[][] queenPositions) {
		for(int i=0;i<queenPositions.length;i++) {
			int queenSpot = convertCartesianToIndex(queenPositions[i][0],queenPositions[i][1]);
			squares[queenSpot].putPieceOnSquare(new Queen(queenHeight,queenLength));
		}
	}
	private int convertCartesianToIndex(int x,int y) {
		return (x+boardSize*y);
	}
	private int convertXYToIndex(int x,int y) {
		return(convertCartesianToIndex((x/(width/boardSize))%(width/boardSize),
				(y/(height/boardSize)%(height/boardSize))));
	}
}
