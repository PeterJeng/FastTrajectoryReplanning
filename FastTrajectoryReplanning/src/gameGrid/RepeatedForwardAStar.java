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
	
	public void planRoute() {
		//plan the route via the heuristics
	}
	
	public void traverseMaze() {
		//Add open node to openList
		//Add closed node to closedList
	}
	
	/**
	 * Scan the environment of current cell
	 */
	public void scan() {
		int rowIndex = robotMaze.current.row;
		int colIndex = robotMaze.current.col;
		
		if((rowIndex - 1) < 0) {
			//scan right, at the leftmost edge
		}
		if((colIndex - 1) < 0) {
			//scan bottom, at the topmost edge
		}
	}
	
} 
