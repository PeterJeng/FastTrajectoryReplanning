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
	
	public PerceivedMaze() {
		super();	
	}
	
	
	public void move() {
		this.traveledPathCost++;
	}
	
	/**
	 * update the maze with new information
	 */
	public void update() {
		
	}
}
