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
	PerceivedMaze robotMaze;	//what robot sees
	BinaryHeap openList; 		//A*'s open list of cells to be expanded
	LinkedList<Cell> closedList; 	//A*'s list of explored cells 
	
	public adaptiveastar () {
		robotMaze = new PerceivedMaze();
		openList = new BinaryHeap();
		closedList = new LinkedList<Cell>(); 
	}
	
	/*
	 * implements one iteration of A*
	 * returns indices of Cell that it was going to expand next
	 * TO-DO:
	 * 1. Update perceived maze
	 * 2. Make sure blocked Cells are not chosen in trajectory
	 * 3. How to determine if there is no unblocked path to goal
	 * 4. Add Tie-breaking component that is needed (if same fVal, pick node
	 * 		with larger gVal)
	 */
	public int[] aStar(Maze x, int sRow, int sCol, int gRow, int gCol) {
		int[] nextNodeInd = {0,0};
		x.calculateHeuristic(gRow, gCol); //calculates distance from goal node from each node
		//line 46 this will be moved to the adaptive part since it only has to be executed once
		x.board[sRow][sCol].state = false; //overrides if start state is blocked
		x.board[sRow][sCol].visited = true;  //since this cell will be expanded
 		Cell start = x.board[sRow][sCol]; //stores start state
 		openList.insertCell(start); //adds start state to open list if not present 
		
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
			right.parent = start;
			bottom.parent = start; 
			
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
			left.parent = start;
			bottom.parent = start; 
			
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
			right.parent = start;
			top.parent = start; 
			
			right.fValue = right.gValue + right.hValue;
			top.fValue = top.gValue + top.hValue; 
			
			openList.insertCell(right);
			openList.insertCell(top);
		}
		else if  (sRow == (x.board.length-1) && sCol == (x.board.length-1)) //bottom right corner
		{
			left = x.board[sRow][sCol+1];
			top = x.board[sRow-1][sCol];
			left.gValue = robotMaze.traveledPathCost+1; 
			top.gValue = robotMaze.traveledPathCost+1;
			left.parent = start;
			top.parent = start; 
			
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(left);
			openList.insertCell(top);
		}
		else if (sRow == 0) //top edge
		{
			right = x.board[sRow][sCol+1];
			left = x.board[sRow][sCol+1];
			bottom = x.board[sRow+1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			left.gValue = robotMaze.traveledPathCost+1;
			bottom.gValue = robotMaze.traveledPathCost+1;
			right.parent = start;
			left.parent = start;
			bottom.parent = start; 
			
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
			left = x.board[sRow][sCol+1];
			top = x.board[sRow-1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			left.gValue = robotMaze.traveledPathCost+1;
			top.gValue = robotMaze.traveledPathCost+1;
			right.parent = start;
			left.parent = start;
			top.parent = start; 
			
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
			right.parent = start;
			top.parent = start;
			bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			right.fValue = right.gValue + right.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(right);
			openList.insertCell(top);
			openList.insertCell(bottom);
		}
		else if (sCol == (x.board.length-1)) //right edge
		{
			left = x.board[sRow][sCol+1];
			top = x.board[sRow-1][sCol];
			bottom = x.board[sRow+1][sCol];
			left.gValue = robotMaze.traveledPathCost+1; 
			top.gValue = robotMaze.traveledPathCost+1;
			bottom.gValue = robotMaze.traveledPathCost+1;
			left.parent = start;
			top.parent = start;
			bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(left);
			openList.insertCell(top);
			openList.insertCell(bottom);
		}
		else { //in the middle of the grid world
			right = x.board[sRow][sCol+1];
			left = x.board[sRow][sCol+1];
			top = x.board[sRow-1][sCol];
			bottom = x.board[sRow+1][sCol];
			right.gValue = robotMaze.traveledPathCost+1; 
			left.gValue = robotMaze.traveledPathCost+1;
			top.gValue = robotMaze.traveledPathCost+1; 
			bottom.gValue = robotMaze.traveledPathCost+1; 
			right.parent = start; left.parent = start;
			top.parent = start; bottom.parent = start; 
			
			bottom.fValue = bottom.gValue + bottom.hValue; 
			right.fValue = right.gValue + right.hValue;
			left.fValue = left.gValue + left.hValue;
			top.fValue = top.gValue + top.hValue;
			
			openList.insertCell(right);
			openList.insertCell(left);
			openList.insertCell(top);
			openList.insertCell(bottom);
		}
		robotMaze.move();
		openList.deleteCell(start); //deletes startnode
		closedList.add(start); //adds start to closed linkedlist
		
		//Printing out Closed List
		for (int i = 0; i < closedList.size(); i++) {
			System.out.println("Key:" + closedList.get(i).key + " ");
		}
		
		Cell first = openList.getCell(0);
		nextNodeInd[0] = first.row;
		nextNodeInd[1] = first.col;
		openList.clearHeap(); //clears openlist for next iteration
		//or clear openlist in the adaptiveSearch method???
		
		return nextNodeInd; 
	}

	
	/*
	 * takes in a maze as an input
	 * takes in start coordinates (sRow, sCol)
	 * takes in end coordinates (gRow, gCol)
	 * and updates perceived maze along the process
	 */
	public void adaptiveSearch(Maze x, int sRow, int sCol, int gRow, int gCol) {
		
	}
	
}
