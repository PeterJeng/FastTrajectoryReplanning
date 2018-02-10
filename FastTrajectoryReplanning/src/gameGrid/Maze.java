package gameGrid;

/**
 * Maze class that contains the game grid. 
 * 
 * Authors: Peter Jeng and Seerat Aziz
 * Homework Assignment 1
 * Introduction to Artificial Intelligence
 * Spring 2018
 */
public class Maze {
	public Cell[][] board;
	
	public Maze() {
		this.board = new Cell[10][10];
		int counter = 1; 
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = new Cell();
				board[i][j].key = counter;
				counter++;
			}
		}
	}
	
	/**
	 * TODO: Implement a DFS algorithm???
	 * Currently using a random number generator to determine if the cell is blocked or unblocked
	 */
	public void generateMaze() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(Math.random() < 0.3) {
					board[i][j].state = true;
				}
				else {
					board[i][j].state = false;
				}
			}
		}
	}
	
	/**
	 * prints the state of the maze
	 */
	public void printMaze() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j].toString());
			}
			System.out.println();
		}
	}
	
	/**
	 * @param endRow	index of end state
	 * @param endCol	index of end state
	 */
	public void calculateHeuristic(int endRow, int endCol) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j].hValue = Math.abs(i - endRow) + Math.abs( j - endCol);
				//calculates Manhattan distance between current state to goal state
			}
		}
	}
}
