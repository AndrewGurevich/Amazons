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
	private int oldIndex = 5;
	private boolean checkerPlaced = true;
	private int turn = 0;
	
	public Board(int boardSize,int width,int height,int queenHeight,int queenLength) {
		squares = new Square[boardSize*boardSize];
		this.boardSize = boardSize;
		this.queenHeight = queenHeight;
		this.queenLength = queenLength;
		this.width = width;
		this.height = height;
		fillBoard();
		
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	int index = convertXYToIndex(e.getX(),e.getY());
            	//value = squares[index]
            	//Game logic
        		//if(turn==0 && checkLose("blackQueen")) {
        			//System.out.println("White Wins!");
        			//turn = -1;
        		//}
        		//if(turn==1 && checkLose("whiteQueen")){
        			//System.out.println("Black Wins!");
        			//turn = -1;
        		//}
        		if(turnComplete()) {
        			changeTurn();
        		}
            	chosenSquare = squares[index];
            	
            	//if we have a stored queen
            	if(selected!=null && (selected.getPiece()!=null)){
            		//if queen has moved and not placed checker
            		System.out.println("Using stored queen");
            		if(selected.getPiece().getCanMove()==false &&
            			selected.getPiece().getCheckerPlaced()==false) {
            			System.out.println("placing checker");
            			//see if move valid using stored value and new
            			if(moveIsValid(oldIndex,index)&&oldIndex!=index&&
            					squares[index].getPiece()==null) {
            				//place checker
            				placeChecker(index);
            				//set checker placed to true
            				selected.getPiece().setCheckerPlaced(true);
            				//free up stored variable
            				selected = null;
            				
            				
            				
            			}
            		}
            		//if queen hasnt moved
            		else if(selected.getPiece().getCanMove()==true) {
            			System.out.println("moving queen");
            			//see if move valid using stored value and new
            			if(moveIsValid(oldIndex,index)&&oldIndex!=index&&
            					squares[index].getPiece()==null) {
            				//set can move to false
            				selected.getPiece().setCanMove(false);
            				//move
            				movePiece(selected,chosenSquare);
            				System.out.println("piece moved");
            				//set stored value to current value
            				oldIndex = convertXYToIndex(e.getX(),e.getY());
            				selected = squares[oldIndex];
            			}
            		}
            	}
            	else if(chosenSquare.getPiece()!=null){
            		
            		//if object can move, object is queen
            		if(chosenSquare.getPiece().getCanMove()==true) {
            			System.out.println("piece chosen");
            			//stored value = object
            			selected = squares[index];
            			//set old index
            			oldIndex = convertXYToIndex(e.getX(),e.getY());
            		}
            	}
            }
            	
        });
	
	}
	private boolean moveIsValid(int old,int index) {
		int y = (old/boardSize)-1;
		int x = (old-(old/boardSize)*boardSize)-1;
		
		//diagonals
		//up and left
		while(x>=0 && y>=0) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				
				return true;
			}
			x--;
			y--;
		}
		
		//up and right
		y = (old/boardSize)-1;
		x = (old-(old/boardSize)*boardSize)+1;
		while(x<boardSize && y>=0) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x++;
			y--;
		}
		
		//down and left
		y = (old/boardSize)+1;
		x = (old-(old/boardSize)*boardSize)-1;
		while(x>=0 && y<boardSize) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x--;
			y++;
		}
		
		//down and right
		y = (old/boardSize)+1;
		x = (old-(old/boardSize)*boardSize)+1;
		while(x<boardSize && y<boardSize) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x++;
			y++;
		}
		
		//up
		
		y = (old/boardSize)+1;
		x = (old-(old/boardSize)*boardSize);
		while(y<boardSize) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			y++;
		}
		//down
		y = (old/boardSize)-1;
		x = (old-(old/boardSize)*boardSize);
		while(y>=0) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			y--;
		}
		
		//right
		
		y = (old/boardSize);
		x = (old-(old/boardSize)*boardSize)+1;
		while(x<boardSize) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null&&
					x!=old-(old/boardSize)*boardSize) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x++;
		}
		//left
		y = (old/boardSize);
		x = (old-(old/boardSize)*boardSize)-1;
		while(x>=0) {
			if(squares[this.convertCartesianToIndex(x, y)].getPiece()!=null) {
				break;
			}
			//System.out.println(this.convertCartesianToIndex(x, y));
			if(index == this.convertCartesianToIndex(x, y)) {
				return true;
			}
			x--;
		}		
		
		
		
		return false;
	}
	public void movePieceTest() {
		movePiece(squares[12],squares[20]);
	}	
	private void placeChecker(int index) {
		squares[index].putPieceOnSquare(new Checker(50,50));
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
		int[][] list = {{0,boardSize/2},{boardSize-1,boardSize/2}};
		loadQueens(list);
	}
	private boolean turnComplete() {
		if (turn==1) {
			for (int i = 0; i < squares.length; i++) {
				if(squares[i].getPiece()!=null) {
					if(squares[i].getPiece().getName().matches("whiteQueen")) {
						if(squares[i].getPiece().getCanMove()) {
							
							return false;
						}
					}
				}
			}
			
			return true;
		}
		else {
			for (int i = 0; i < squares.length; i++) {
				if(squares[i].getPiece()!=null) {
					if(squares[i].getPiece().getName().matches("blackQueen")) {
						if(squares[i].getPiece().getCanMove()) {
							
							return false;
						}
					}
				}
			}
			
			return true;
		}
	}
	private void changeTurn() {
		if(turn==0) {
			
			for (int i = 0; i < squares.length; i++) {
				if(squares[i].getPiece()!=null) {
					System.out.println(squares[i].getPiece().getName());
					if(squares[i].getPiece().getName().matches("whiteQueen")) {
						squares[i].getPiece().setCanMove(true);
						squares[i].getPiece().setCheckerPlaced(false);
						System.out.println("turn changed");
						turn=1;
					}
				}
			}
		}
		else{
			for (int i = 0; i < squares.length; i++) {
				if(squares[i].getPiece()!=null) {
					if(squares[i].getPiece().getName().matches("blackQueen")) {
						squares[i].getPiece().setCanMove(true);
						squares[i].getPiece().setCheckerPlaced(false);
						turn = 0;
					}
				}
			}
		}
	}
	private boolean checkLose(String piece) {
		System.out.println(oldIndex);
		for (int k = 0; k < squares.length; k++) {
			if(squares[k].getPiece()!=null) {
				if(squares[k].getPiece().getName().matches(piece)) {
					for (int i = 0; i < boardSize; i++) {
						for (int j = 0; j < boardSize; j++) {
							if(moveIsValid(oldIndex,i+j)==false) {
								return false;
							}
						}
							
					}
				}
			}
		}
		return true;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
	

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
		for(int i=0;i<queenPositions.length/2;i++) {
			int queenSpot = convertCartesianToIndex(queenPositions[i][0],queenPositions[i][1]);
			squares[queenSpot].putPieceOnSquare(new whiteQueen(queenHeight,queenLength));
		}
		for(int i=queenPositions.length/2;i<queenPositions.length;i++) {
			int queenSpot = convertCartesianToIndex(queenPositions[i][0],queenPositions[i][1]);
			squares[queenSpot].putPieceOnSquare(new blackQueen(queenHeight,queenLength));
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
