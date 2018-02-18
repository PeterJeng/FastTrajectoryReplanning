package gameGrid;

import java.util.LinkedList;

public class RepeatedForwardAStar {
	public PerceivedMaze robotMaze;
	public BinaryHeap openList;
	public LinkedList<Cell> closedList;
	public Maze realMaze;
	
	
	public RepeatedForwardAStar() {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = new PerceivedMaze();
	}
	/*
	 * Initialize all the values of the board
	 */
	public RepeatedForwardAStar(Maze realMaze) {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = new PerceivedMaze();
		this.realMaze = new Maze();
		
	}

	public void computePath() {
		Cell left = new Cell();
		Cell right = new Cell();
		Cell bottom = new Cell();
		Cell top = new Cell();
		
		//local variable for easier access
		int currentRow = robotMaze.current.row;
		int currentCol = robotMaze.current.col;
		
		openList.insertCell(robotMaze.current, this.alreadyInOpenList(robotMaze.current));

		while(robotMaze.current.key != robotMaze.end.key) {
			// implement the cell locations
			// if there is no cells to the direction of the current cell, then we set the X cell to null
			top = (robotMaze.current.row - 1 < 0) ? null : robotMaze.board[currentRow - 1][currentCol];
			bottom = (robotMaze.current.row + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow + 1][currentCol];
			left = (robotMaze.current.col - 1 < 0) ? null : robotMaze.board[currentRow][currentCol - 1];
			right = (robotMaze.current.col + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow][currentCol + 1];
			
			if(top != null && openList.insertCell(top, this.alreadyInOpenList(top))) {
				top.parent = robotMaze.current;
			}
			if(bottom != null && openList.insertCell(bottom, this.alreadyInOpenList(bottom))) {
				bottom.parent = robotMaze.current;
			}
			if(right != null && openList.insertCell(right, this.alreadyInOpenList(right))) {
				right.parent = robotMaze.current;
			}
			if(left != null && openList.insertCell(left, this.alreadyInOpenList(left))) {
				left.parent = robotMaze.current;
			}

			closedList.add(robotMaze.current);
			openList.deleteCell(robotMaze.current);
			robotMaze.current = openList.getCell(0);
									
			currentRow = robotMaze.current.row;
			currentCol = robotMaze.current.col;
		}
	}

	/**
	 * Movement of the robot in the actual maze
	 */
	public void traverseMaze() {		
		System.out.println("Starting travesal...");
		System.out.println("Reached: ");
		Cell cellPtr = new Cell();
		for(int i = 0; i < closedList.size(); i++) {
			cellPtr = closedList.get(i);
			
			//cell is blocked
			if(realMaze.board[cellPtr.row][cellPtr.col].state == true) {
				//update the perceived Maze
				robotMaze.board[cellPtr.row][cellPtr.col].state = true;
				System.out.println("HIT A BLOCK!");
				return;
			}
			System.out.print(cellPtr.key + " ");
		}
	}

	public void start() {
		computePath();
		System.out.println("Planned path: ");
		
		Cell cellPtr = new Cell();
		for(int i = 0; i < closedList.size(); i++) {
			cellPtr = closedList.get(i);
			System.out.print(cellPtr.key + " ");
		}
		
		traverseMaze();
	}
	
	private boolean alreadyInOpenList(Cell x) {
		int heapSize = this.openList.heapSize();
		int cellKey = x.key;
		Cell temp = new Cell();
		for (int i = 0; i < heapSize; i++) {
			temp = this.openList.getCell(i);
			if (temp.key == cellKey) {
				return true;
			}
		}
		return false; 
	}
}
