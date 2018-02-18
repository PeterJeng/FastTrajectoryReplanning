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
	public RepeatedForwardAStar(Maze realMaze, PerceivedMaze perceivedMaze) {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = perceivedMaze;
		this.realMaze = realMaze;

	}

	public void computePath() {
		Cell left = new Cell();
		Cell right = new Cell();
		Cell bottom = new Cell();
		Cell top = new Cell();

		// local variable for easier access
		int currentRow = robotMaze.current.row;
		int currentCol = robotMaze.current.col;

		robotMaze.current.gValue = 0;
		robotMaze.current.fValue = robotMaze.current.gValue + robotMaze.current.hValue;
		
		openList.insertCell(robotMaze.current, this.alreadyInOpenList(robotMaze.current));

		while (robotMaze.current.key != robotMaze.end.key) {
			// implement the cell locations
			// if there is no cells to the direction of the current cell, then we set the X
			// cell to null
			top = (robotMaze.current.row - 1 < 0) ? null : robotMaze.board[currentRow - 1][currentCol];
			bottom = (robotMaze.current.row + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow + 1][currentCol];
			left = (robotMaze.current.col - 1 < 0) ? null : robotMaze.board[currentRow][currentCol - 1];
			right = (robotMaze.current.col + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow][currentCol + 1];

			//update cell gValue, fValue, and parent pointer
			//only update and insert if cell is an unblocked cell
			if (top != null && top.state == false) {
				top.gValue = robotMaze.current.gValue + 1;
				top.fValue = top.gValue + top.hValue;
				top.parent = robotMaze.current;
				openList.insertCell(top, this.alreadyInOpenList(top));
			}
			if (bottom != null && bottom.state == false) {
				bottom.gValue = robotMaze.current.gValue + 1;
				bottom.fValue = bottom.gValue + bottom.hValue;
				bottom.parent = robotMaze.current;
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
			}
			if (left != null && left.state == false) {
				left.gValue = robotMaze.current.gValue + 1;
				left.fValue = left.gValue + left.hValue;
				left.parent = robotMaze.current;
				openList.insertCell(left, this.alreadyInOpenList(left));
			}
			if (right != null && right.state == false) {
				right.gValue = robotMaze.current.gValue + 1;
				right.fValue = right.gValue + right.hValue;
				right.parent = robotMaze.current;
				openList.insertCell(right, this.alreadyInOpenList(right));
			}

			closedList.add(robotMaze.current);
			openList.deleteCell(robotMaze.current);
			robotMaze.current = nextNodeInList();
			//quick check to see if current node exist in closed list
			if(closedList.contains(robotMaze.current)) {
				openList.deleteCell(robotMaze.current);
				robotMaze.current = nextNodeInList();
			}
			

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
		for (int i = 0; i < closedList.size(); i++) {
			cellPtr = closedList.get(i);
	
			// cell is blocked
			if (realMaze.board[cellPtr.row][cellPtr.col].state == true) {
				//this is to keep track of where the robot is now in the maze
				robotMaze.current = closedList.get(i - 1);
				
				// update the perceived Maze
				robotMaze.board[cellPtr.row][cellPtr.col].state = true;
				System.out.println();
				System.out.println("HIT A BLOCK AT: " + cellPtr.key);
				
				//clear the open and closed list
				openList.clearHeap();
				closedList.clear();
				
				return;
			}
			System.out.print(cellPtr.key + " ");
		}
	}

	public void start() {
		while(robotMaze.current.key != robotMaze.end.key) {
			computePath();
			System.out.println("Planned path: ");

			Cell cellPtr = new Cell();
			for (int i = 0; i < closedList.size(); i++) {
				cellPtr = closedList.get(i);
				System.out.print(cellPtr.key + " ");
			}
			
			System.out.println();
			traverseMaze();
			System.out.println();
		}
		
	}

	/**
	 * Implements a tie breaker using largest g-Value
	 * 
	 */
	private Cell nextNodeInList() {
		Cell result = this.openList.getCell(0);
		int min = result.fValue;
		Cell temp = new Cell();
		for (int i = 1; i < this.openList.heapSize(); i++) {
			temp = this.openList.getCell(i);
			if (temp.fValue == min && temp.gValue > result.gValue)
				result = temp; 
		}
		return result; 
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
