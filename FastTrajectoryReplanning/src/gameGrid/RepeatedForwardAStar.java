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

		// implement the cell locations
		// if there is no cells to the direction of the current cell, then we set the X cell to null
		top = (robotMaze.current.row - 1 < 0) ? null : robotMaze.board[currentRow - 1][currentCol];
		bottom = (robotMaze.current.row + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow + 1][currentCol];
		left = (robotMaze.current.col - 1 < 0) ? null : robotMaze.board[currentRow][currentCol - 1];
		right = (robotMaze.current.col + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[currentRow][currentCol + 1];

		//from here, we just add to open list
		//this will also know if the cells are blocked or unblocked

	}

	public void traverseMaze() {
		// Add open node to openList
		// Add closed node to closedList
	}

}
