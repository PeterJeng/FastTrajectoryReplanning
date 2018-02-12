package gameGrid;

/**
 * Individual representation of the cells. 
 * 
 * Authors: Peter Jeng and Seerat Aziz
 * Homework Assignment 1
 * Introduction to Artificial Intelligence
 * Spring 2018
 */
public class Cell {
	int key; 	//"name" of the node, this attribute can be removed later, just for testing??
	boolean state; //blocked or unblocked
	boolean visited; //visited or unvisited ON PAUSE
	int hValue, gValue, fValue;
	Cell parent; //to keep track of parent of the cell and make it easier to backtrack??
	int row, col; //to keep track of Cell's location within the grid world
	
	public Cell() {
		this.state = false;		
	}	
	
	/**
	 * A "false" state represents unoccupied space, using "_" as representation
	 * A "true" state represents occupied space, using "X" as representation
	 */
	public String toString() {
		
		//ternary operator, basically if(state == true) return X else return _
		return this.state ? "X " : "_ ";
	}
}
