package gameGrid;

/**
 * Implementation of Adaptive A* 
 * 
 * Authors: Peter Jeng and Seerat Aziz
 * Homework Assignment 1
 * Introduction to Artificial Intelligence
 * Spring 2018
 */

import java.util.LinkedList; 

//all properties are public for testing purposes
public class adaptiveastar {
	private PerceivedMaze robotMaze;	//what robot sees
	private BinaryHeap openList; 		//A*'s open list of cells to be expanded
	private LinkedList<Cell> closedList; 	//A*'s list of explored cells 
	
	public adaptiveastar () {
		robotMaze = new PerceivedMaze();
		openList = new BinaryHeap();
		closedList = new LinkedList<Cell>(); 
	}
	
	//helper function that finds the neighboring nodes of the current cell
	//updates the openlist appropriately
	public void cellNeighbors(Maze x, int sRow, int sCol) {
		Cell start = x.board[sRow][sCol]; //stores start state
		
		Cell right = new Cell(); Cell left = new Cell(); 
		Cell bottom = new Cell(); Cell top = new Cell();
		
		//calculates g and f values of start state's children
		//then adds them to the open list 
		if (sRow == 0 && sCol == 0) //top left corner
		{ 
			right = x.board[sRow][sCol+1];
			bottom = x.board[sRow+1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			bottom.gValue = robotMaze.traveledPathCost+1; 
			//right.parent = start;
			//bottom.parent = start; 
			
			right.fValue = right.gValue + right.hValue;
			bottom.fValue = bottom.gValue + bottom.hValue; 
			
			openList.insertCell(right);
			openList.insertCell(bottom);
		}
		else if (sRow == 0 && sCol == (x.board.length-1)) //top right corner
		{
			left = x.board[sRow][sCol-1];
			bottom = x.board[sRow+1][sCol];
			left.gValue = robotMaze.traveledPathCost+1;
			bottom.gValue = robotMaze.traveledPathCost+1;
			//left.parent = start;
			//bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue;
			left.fValue = left.gValue + left.hValue; 
			
			openList.insertCell(left);
			openList.insertCell(bottom);
		}
		else if (sRow == (x.board.length-1) && sCol == 0)  //bottom left corner
		{
			right = x.board[sRow][sCol+1];
			top = x.board[sRow-1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			top.gValue = robotMaze.traveledPathCost+1; 
			//right.parent = start;
			//top.parent = start; 
			
			right.fValue = right.gValue + right.hValue;
			top.fValue = top.gValue + top.hValue; 
			
			openList.insertCell(right);
			openList.insertCell(top);
		}
		else if  (sRow == (x.board.length-1) && sCol == (x.board.length-1)) //bottom right corner
		{
			left = x.board[sRow][sCol-1];
			top = x.board[sRow-1][sCol];
			left.gValue = robotMaze.traveledPathCost+1; 
			top.gValue = robotMaze.traveledPathCost+1;
			//left.parent = start;
			//top.parent = start; 
			
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(left);
			openList.insertCell(top);
		}
		else if (sRow == 0) //top edge
		{
			right = x.board[sRow][sCol+1];
			left = x.board[sRow][sCol-1];
			bottom = x.board[sRow+1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			left.gValue = robotMaze.traveledPathCost+1;
			bottom.gValue = robotMaze.traveledPathCost+1;
			//right.parent = start;
			//left.parent = start;
			//bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			right.fValue = right.gValue + right.hValue;
			left.fValue = left.gValue + left.hValue;
			
			openList.insertCell(right);
			openList.insertCell(left);
			openList.insertCell(bottom);
		}
		else if (sRow == (x.board.length-1)) //bottom edge
		{
			right = x.board[sRow][sCol+1];
			left = x.board[sRow][sCol-1];
			top = x.board[sRow-1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			left.gValue = robotMaze.traveledPathCost+1;
			top.gValue = robotMaze.traveledPathCost+1;
			//right.parent = start;
			//left.parent = start;
			//top.parent = start; 
			
			right.fValue = right.gValue + right.hValue;
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(right);
			openList.insertCell(left);
			openList.insertCell(top);
		} 
		else if (sCol == 0) //left edge
		{
			right = x.board[sRow][sCol+1];
			top = x.board[sRow-1][sCol];
			bottom = x.board[sRow+1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			top.gValue = robotMaze.traveledPathCost+1;
			bottom.gValue = robotMaze.traveledPathCost+1;
			//right.parent = start;
			//top.parent = start;
			//bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			right.fValue = right.gValue + right.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(right);
			openList.insertCell(top);
			openList.insertCell(bottom);
		}
		else if (sCol == (x.board.length-1)) //right edge
		{
			left = x.board[sRow][sCol-1];
			top = x.board[sRow-1][sCol];
			bottom = x.board[sRow+1][sCol];
			left.gValue = robotMaze.traveledPathCost+1; 
			top.gValue = robotMaze.traveledPathCost+1;
			bottom.gValue = robotMaze.traveledPathCost+1;
			//left.parent = start;
			//top.parent = start;
			//bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(left);
			openList.insertCell(top);
			openList.insertCell(bottom);
		}
		else { //in the middle of the grid world
			right = x.board[sRow][sCol+1];
			left = x.board[sRow][sCol-1];
			top = x.board[sRow-1][sCol];
			bottom = x.board[sRow+1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			left.gValue = robotMaze.traveledPathCost+1;
			top.gValue = robotMaze.traveledPathCost+1; 
			bottom.gValue = robotMaze.traveledPathCost+1; 
			//right.parent = start; left.parent = start;
			//top.parent = start; bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			right.fValue = right.gValue + right.hValue;
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(right);
			openList.insertCell(left);
			openList.insertCell(top);
			openList.insertCell(bottom);
		}
	}
	
	//to back track until an unvisited node is found 
	//if no unvisited nodes then there is a dead end - IMPLEMENT
	//make it return indices of unvisited node 
	public int[] backTrack(Maze x, Cell c) {
		Cell unvisited = new Cell();
		int[] result = null; //returns null array if no unvisited neighbors
		while (c.parent != null) {
			this.cellNeighbors(x, c.row, c.col); //openlist cell's neighbors
			for (int i = 0; i < this.openList.heapSize(); i++) {
				if (!this.openList.getCell(i).visited) {
					unvisited = openList.getCell(i); //picks closest unvisited cell
					result = new int[2];
					result[0] = unvisited.row;
					result[1] = unvisited.col; 
	 				break;
				}
			}
			this.openList.clearHeap(); //clears openlist
			c = c.parent;
		}
		return result; 
	}
	
	/*
	 * implements one iteration of A*
	 * returns indices of Cell that it was going to expand next
	 * TO-DO:
	 * 1. Update perceived maze
	 * 2. Make sure blocked Cells are not chosen in trajectory - DONE 
	 * 3. How to determine if there is no unblocked path to goal
	 * 4. Add Tie-breaking component that is needed (if same fVal, pick node
	 * 		with larger gVal)
	 * 5. Need to implement back-tracking to visit unvisited nodes to prevent cycling between 
	 *    two nodes - DONE?
	 */
	public int[] aStar(Maze x, int sRow, int sCol, int gRow, int gCol) {
		int[] nextNodeInd = null;
		x.board[sRow][sCol].visited = true;  //since this cell will be expanded
 		Cell start = x.board[sRow][sCol];
 		openList.insertCell(start); //adds start state to open list
 		
 		//if start cell is already in closed list, choose unvisited node 
 		if (closedList.contains(start)) {
 			nextNodeInd = this.backTrack(x, start);
 			if (nextNodeInd != null) {
 				closedList.add(start); 
 	 			return nextNodeInd;
 			}
 			else {
 				//nextNodeInd = new int[2];
 				//nextNodeInd[0] = start.parent.row;
 				//nextNodeInd[1] = start.parent.col;
 				return nextNodeInd;
 			}
 		}
 		
 		this.cellNeighbors(x, sRow, sCol); //updates openlist with neighbors
		if (!openList.isHeapEmpty() && openList.heapSize()>1) { //openlist must have at least 2 Cells
			nextNodeInd = new int[2];
			robotMaze.move(); //move only if robot isn't blocked from all sides
			openList.deleteCell(start); //deletes startnode
			closedList.add(start); //adds start to closed linkedlist
			Cell first = openList.getCell(0);
			nextNodeInd[0] = first.row;
			nextNodeInd[1] = first.col;
			openList.clearHeap(); //clears openlist for next iteration
			return nextNodeInd;
		}
		else {
			return nextNodeInd; //returns null array if heap is empty (blocked all sides)
		}
	}

	
	/*
	 * takes in a maze as an input
	 * takes in start coordinates (sRow, sCol)
	 * takes in end coordinates (gRow, gCol)
	 * and updates perceived maze along the process
	 */
	public void adaptiveSearch(Maze x, int sRow, int sCol, int gRow, int gCol) {
		x.calculateHeuristic(gRow, gCol); //calculates distance from goal node from each node
		x.board[sRow][sCol].state = false; //overrides if start state is blocked
		x.board[gRow][gCol].state = false; //overrides if goal state is blocked
		int[] result = this.aStar(x, sRow, sCol, gRow, gCol);
		while (result != null) {
			x.board[result[0]][result[1]].parent = x.board[sRow][sCol];
			sRow = result[0]; sCol = result[1]; 
			result = this.aStar(x, result[0], result[1], gRow, gCol);
			if (result == null) {
				System.out.println("No solution found for this maze");
				for (int i = 0; i < closedList.size(); i++) {
 					System.out.println("Key:" + closedList.get(i).key + " ");
 				}
 				System.out.print("\n");
				return; 
			} else if (result[0] == gRow && result[1] == gCol) {
				this.closedList.add(x.board[gRow][gCol]);
				System.out.println("Solution found for this maze");
				for (int i = 0; i < closedList.size(); i++) {
 					System.out.println("Key:" + closedList.get(i).key + " ");
 				}
 				System.out.print("\n");
				return;
			}
		}
		return;
	}
	
}
