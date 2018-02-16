package gameGrid;

import java.util.LinkedList;

public class RepeatedForwardAStar {
	PerceivedMaze robotMaze;
	BinaryHeap openList;
	LinkedList<Cell> closedList;

	/*
	 * Initialize all the values of the board
	 */
	public RepeatedForwardAStar() {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = new PerceivedMaze();
	}

	public void computePath() {
		Cell left = new Cell();
		Cell right = new Cell();
		Cell bottom = new Cell();
		Cell top = new Cell();
		
		//local variable for easier access
		int currentRow = robotMaze.current.row;
		int currentCol = robotMaze.current.col;
		
		openList.insertCell(robotMaze.current);

		while(robotMaze.current.key != robotMaze.end.key) {
			// implement the cell locations
			// if there is no cells to the direction of the current cell, then we set the X cell to null
			top = (robotMaze.current.row - 1 < 0) ? null : robotMaze.board[currentRow - 1][currentCol];
			bottom = (robotMaze.current.row + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow + 1][currentCol];
			left = (robotMaze.current.col - 1 < 0) ? null : robotMaze.board[currentRow][currentCol - 1];
			right = (robotMaze.current.col + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow][currentCol + 1];
			
			if(top != null && openList.insertCell(top)) {
				top.parent = robotMaze.current;
			}
			if(bottom != null && openList.insertCell(bottom)) {
				bottom.parent = robotMaze.current;
			}
			if(right != null && openList.insertCell(right)) {
				right.parent = robotMaze.current;
			}
			if(left != null && openList.insertCell(left)) {
				left.parent = robotMaze.current;
			}

			closedList.add(robotMaze.current);
			openList.deleteCell(robotMaze.current);
			robotMaze.current = openList.getCell(0);
			
			
			
			currentRow = robotMaze.current.row;
			currentCol = robotMaze.current.col;
			
			System.out.println(robotMaze.current.key);
			//update perceived maze
		}
	

	}
	
	/* Do I need this? 
	public Cell lowestHeuristic(Cell top, Cell bottom, Cell left, Cell right) {
		Cell lowest = new Cell();
		
		if(top != null && bottom != null && top.hValue <= bottom.hValue) {
			lowest = top;
		}
		else {
			lowest = bottom;
		}
		
		if(left != null && lowest.hValue > left.hValue) {
			lowest = left;
		}
		
		if(right != null && lowest.hValue > right.hValue) {
			lowest = right;
		}

		return lowest;
	}
	*/

	public void traverseMaze() {
		// Add open node to openList
		// Add closed node to closedList
	}

}
