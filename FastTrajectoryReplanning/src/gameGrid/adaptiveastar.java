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
	private LinkedList<Cell> traversedPath; //overall traversed path
	
	public adaptiveastar () {
		robotMaze = new PerceivedMaze();
		openList = new BinaryHeap();
		closedList = new LinkedList<Cell>(); 
		traversedPath = new LinkedList<Cell>();
	}
	
	//helper function that finds the neighboring nodes of the current cell
		//updates the openlist appropriately
		private void cellNeighbors(Maze x, int sRow, int sCol) {
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
			
			this.updatePerceivedMaze();
		}
		
		private void updatePerceivedMaze() {
			int x = 0; int y = 0;
			for (int i = 0; i < this.openList.heapSize(); i++) {
				if (this.openList.getCell(i).state==true) {
					x = this.openList.getCell(i).row;
					y = this.openList.getCell(i).col;
					this.robotMaze.update(x, y);
				}
			}
		}
		
		private Cell tieBreaker() {
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
		/*
		 * finds solution in some cases
		 * need to consider what to do for a dead end & unsolvable puzzle
		 */
		public void aStar(Maze x, int sRow, int sCol, int gRow, int gCol) {
			Cell start = x.board[sRow][sCol];
			start.fValue = start.gValue + start.hValue;
			this.openList.insertCell(start);
			this.cellNeighbors(x, sRow, sCol);
			this.openList.printHeap();
			Cell next = new Cell();
			
			while(!this.openList.isHeapEmpty()) {
				next = this.tieBreaker();
				this.openList.deleteCell(start);
				this.closedList.add(start);
				this.traversedPath.add(start);
				this.robotMaze.move();
				x.board[start.row][start.col].visited = true;
				
				if (next.row == gRow && next.col == gCol) {
					this.openList.deleteCell(next);
					this.closedList.add(next);
					this.traversedPath.add(next);
					System.out.println("Goal found!\nTraversed Path: ");
					for (int i = 0; i < this.traversedPath.size()-1; i++) {
						System.out.print(this.traversedPath.get(i).key + " -> ");
					}
					System.out.print(next.key + "\n");
					return; 
				}
				
				start = next;
				this.cellNeighbors(x, start.row, start.col);
				this.openList.printHeap();
			}
			
			System.out.println("Solution could not be found for this maze.");
			return; 
		}
		
		public void adaptiveRepeatedForward(Maze x, int sRow, int sCol, int gRow, int gCol) {
			
		}
}
