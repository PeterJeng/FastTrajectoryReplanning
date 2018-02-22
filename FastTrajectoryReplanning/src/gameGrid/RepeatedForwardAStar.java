package gameGrid;

import java.util.LinkedList;

public class RepeatedForwardAStar {
	public PerceivedMaze robotMaze;
	public BinaryHeap openList;
	public LinkedList<Cell> closedList;
	public LinkedList<Cell> traversalPath;
	public Maze realMaze;
	int counter = 0;

	public RepeatedForwardAStar() {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = new PerceivedMaze();
		this.traversalPath = new LinkedList<Cell>();
	}

	/*
	 * Initialize all the values of the board
	 */
	public RepeatedForwardAStar(Maze realMaze, PerceivedMaze perceivedMaze) {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = perceivedMaze;
		this.realMaze = realMaze;
		this.traversalPath = new LinkedList<Cell>();

	}

	public boolean computePath() {
		Cell left = new Cell();
		Cell right = new Cell();
		Cell bottom = new Cell();
		Cell top = new Cell();
		
		robotMaze.start.parent = null;

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
			if (top != null && top.state == false && !closedList.contains(top)) {
				top.gValue = robotMaze.current.gValue + 1;
				top.fValue = top.gValue + top.hValue;
				openList.insertCell(top, this.alreadyInOpenList(top));
				top.parent = robotMaze.current;

			}
			if (bottom != null && bottom.state == false && !closedList.contains(bottom)) {
				bottom.gValue = robotMaze.current.gValue + 1;
				bottom.fValue = bottom.gValue + bottom.hValue;
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));

				bottom.parent = robotMaze.current;

			}
			if (left != null && left.state == false && !closedList.contains(left)) {
				left.gValue = robotMaze.current.gValue + 1;
				left.fValue = left.gValue + left.hValue;
				openList.insertCell(left, this.alreadyInOpenList(left));
				left.parent = robotMaze.current;
			}
			if (right != null && right.state == false && !closedList.contains(right)) {
				right.gValue = robotMaze.current.gValue + 1;
				right.fValue = right.gValue + right.hValue;
				openList.insertCell(right, this.alreadyInOpenList(right));
				right.parent = robotMaze.current;
			}

			closedList.add(robotMaze.current);
			openList.deleteCell(robotMaze.current);
			
			if(openList.isHeapEmpty()) {
				System.out.println("NO SOLUTION");
				return false;
			}
			
			robotMaze.current = nextNodeInList( );
			//quick check to see if current node exist in closed list
			//sometimes the planned path will go 4 -> 3 -> 4 due to the nature of the heap removal
			if(closedList.contains(robotMaze.current)) {
				openList.deleteCell(robotMaze.current);
				robotMaze.current = nextNodeInList();
			}
			

			currentRow = robotMaze.current.row;
			currentCol = robotMaze.current.col;
			
			
		}

		//quick add of the last node
		closedList.add(robotMaze.current);
		
		//Provide a path from start cell to end cell
		traversalPath();
		
		return true;
		
	}

	/**
	 * Movement of the robot in the actual maze
	 */
	public void traverseMaze() {
		System.out.println("Starting travesal...");
		System.out.println("Reached: ");
		Cell cellPtr = new Cell();
		for (int i = 0; i < traversalPath.size(); i++) {
			cellPtr = traversalPath.get(i);
	
			// cell is blocked
			if (realMaze.board[cellPtr.row][cellPtr.col].state == true) {
				//this is to keep track of where the robot is now in the maze
				robotMaze.current = traversalPath.get(i - 1);
				
				// update the perceived Maze
				robotMaze.board[cellPtr.row][cellPtr.col].state = true;
				System.out.println();
				System.out.println("HIT A BLOCK AT: " + cellPtr.key);
				
				//clear the open and closed list
				openList.clearHeap();
				closedList.clear();
				
				
				//update the start location to new location
				robotMaze.start = robotMaze.current;
				
				return;
			}
			
			updateSurrounding(cellPtr);
			
			System.out.print(cellPtr.key + " ");
		}
	}

	public void start() {
		//takes a look around its surrounding for the first iteration of A*
		updateSurrounding(robotMaze.current);
		while(robotMaze.current.key != robotMaze.end.key) {
			counter++;
			if(computePath()) {
				System.out.println("Planned path: ");

				Cell cellPtr = new Cell();
				for (int i = 0; i < traversalPath.size(); i++) {
					cellPtr = traversalPath.get(i);
					System.out.print(cellPtr.key + " ");
				}
				
				System.out.println();
				traverseMaze();
				traversalPath.clear();
				System.out.println();
			}
			else {
				break;
			}
			
		}
		
	}
	
	public void traversalPath() {
		//update the traversalPath
		//start from the target and work backward until you reach start node
		//add the final node first, and then work backwards until we reach the start node
		Cell temp = robotMaze.end;
		
		while(temp != null) {
			traversalPath.addFirst(temp);
			temp = temp.parent;
		}
		
	}
	
	
	public void updateSurrounding(Cell cell) {
//		Cell top = new Cell();
//		Cell bottom = new Cell();
//		Cell left = new Cell();
//		Cell right = new Cell();
		
		Cell top = (cell.row - 1 < 0) ? null : robotMaze.board[cell.row - 1][cell.col];
		Cell bottom = (cell.row + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[cell.row + 1][cell.col];
		Cell left = (cell.col - 1 < 0) ? null : robotMaze.board[cell.row][cell.col - 1];
		Cell right = (cell.col + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[cell.row][cell.col + 1];
		
		if(top != null) {
			robotMaze.board[top.row][top.col].state = realMaze.board[top.row][top.col].state;
		}
		
		if(bottom != null) {
			robotMaze.board[bottom.row][bottom.col].state = realMaze.board[bottom.row][bottom.col].state;
		}
		
		if(left != null) {
			robotMaze.board[left.row][left.col].state = realMaze.board[left.row][left.col].state;
		}
		
		if(right != null) {
			robotMaze.board[right.row][right.col].state = realMaze.board[right.row][right.col].state;
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
				if(temp.visited == true && result.visited == false)
					continue;
				else {
					result = temp;
				}
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
