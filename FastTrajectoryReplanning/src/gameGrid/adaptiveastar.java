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
			Cell start = x.board[sRow][sCol];
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
				
				openList.insertCell(right, this.alreadyInOpenList(right));
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
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
				
				openList.insertCell(left, this.alreadyInOpenList(left));
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
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
				
				openList.insertCell(right, this.alreadyInOpenList(right));
				openList.insertCell(top, this.alreadyInOpenList(top));
			}
			else if  (sRow == (x.board.length-1) && sCol == (x.board.length-1)) //bottom right corner
			{
				left = x.board[sRow][sCol-1];
				top = x.board[sRow-1][sCol];
				left.gValue = robotMaze.traveledPathCost+1; 
				top.gValue = robotMaze.traveledPathCost+1;
				left.parent = start;
				top.parent = start; 
				
				left.fValue = left.gValue + left.hValue;
				top.fValue = top.gValue + top.hValue;
				
				openList.insertCell(left, this.alreadyInOpenList(left));
				openList.insertCell(top, this.alreadyInOpenList(top));
			}
			else if (sRow == 0) //top edge
			{
				right = x.board[sRow][sCol+1];
				left = x.board[sRow][sCol-1];
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
				
				openList.insertCell(right, this.alreadyInOpenList(right));
				openList.insertCell(left, this.alreadyInOpenList(left));
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
			}
			else if (sRow == (x.board.length-1)) //bottom edge
			{
				right = x.board[sRow][sCol+1];
				left = x.board[sRow][sCol-1];
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
				
				openList.insertCell(right, this.alreadyInOpenList(right));
				openList.insertCell(left, this.alreadyInOpenList(left));
				openList.insertCell(top, this.alreadyInOpenList(top));
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
				
				openList.insertCell(right, this.alreadyInOpenList(right));
				openList.insertCell(top, this.alreadyInOpenList(top));
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
			}
			else if (sCol == (x.board.length-1)) //right edge
			{
				left = x.board[sRow][sCol-1];
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
				
				openList.insertCell(left, this.alreadyInOpenList(left));
				openList.insertCell(top, this.alreadyInOpenList(top));
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
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
				right.parent = start; left.parent = start;
				top.parent = start; bottom.parent = start; 
				
				bottom.fValue = bottom.gValue + bottom.hValue; 
				right.fValue = right.gValue + right.hValue;
				left.fValue = left.gValue + left.hValue;
				top.fValue = top.gValue + top.hValue;
				
				openList.insertCell(right, this.alreadyInOpenList(right));
				openList.insertCell(left, this.alreadyInOpenList(left));
				openList.insertCell(top, this.alreadyInOpenList(top));
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
			}
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
		 * runs one A* search
		 * need to consider what to do at a dead end - DONE
		 * need to update traversedPath list properly - kind of done
		 * need to stop it from jumping around -- check it see if next cell is adjacent to last cell
		 * 	-if it has to jump around, then it should restart the algorithm from new starting point
		 * 	-it should break while loop and return false for the repeatedAdaptiveSearch() function
		 */
		public void aStar(Maze x, int sRow, int sCol, int gRow, int gCol) {
			Cell start = x.board[sRow][sCol];
			start.fValue = start.gValue + start.hValue;
			this.openList.insertCell(start, this.alreadyInOpenList(start));
			this.cellNeighbors(x, sRow, sCol);
			//this.openList.printHeap();
			Cell next = new Cell();
			
			while(!this.openList.isHeapEmpty()) {
				this.openList.deleteCell(start);
				this.traversedPath.add(start);
				if (this.openList.isHeapEmpty())
					break; 
				next = this.tieBreaker();
				//System.out.println("\nnextRow: " + next.row + " nextCol: " + next.col);
				this.closedList.add(start);
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
				this.cellNeighbors(x, next.row, next.col);
				start = next;
				//this.openList.printHeap();
			}
			
			System.out.println("Solution could not be found for this maze.\nTraversed Path: ");
			for (int i = 0; i < this.traversedPath.size()-1; i++) {
				System.out.print(this.traversedPath.get(i).key + " -> ");
			}
			System.out.print(next.key + "\n");
			return; 
		}
		
		public void updateHeuristic() {
			//for adaptive a* only
		}
		
		public void adaptiveRepeatedForward(Maze x, int sRow, int sCol, int gRow, int gCol) {
			//when to stop calling A*???
			//after scanning the perceived maze after each search???
			//update GUI from this function??? to show where the robot is traveling
			//closed and open lists cleared after each search
		}
}
