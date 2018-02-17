package gameGrid;

/**
 * Class for updating the maze that the robot sees
 * 
 * Authors: Peter Jeng and Seerat Aziz
 * Homework Assignment 1
 * Introduction to Artificial Intelligence
 * Spring 2018
 */
public class PerceivedMaze extends Maze{
	public int traveledPathCost;
	Cell start, end, current;
	
	public PerceivedMaze() {
		super();	
		this.current = board[0][0];
		this.start = board[0][0];
		this.end = board[board.length - 1][board.length - 1];
	}
	
	
	public void move() {
		this.traveledPathCost++;
	}
	
	/**
	 * update the maze with new information
	 */
	public void update(int x, int y) {
		this.board[x][y].state = true; //updates if Cell is blocked in actual maze
	}
}
