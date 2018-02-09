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
		
		generateMaze();
	}
	
	/**
	 * TODO: Implement a DFS algorithm???
	 * Currently using a random number generator to determine if the cell is blocked or unblocked
	 */
	public void generateMaze() {
		/*
		 * Steps to take:
		 * 1. Create a data structure for frontier nodes  - stack right?
		 * 2. Create a data structure for visited nodes   - stack right?
		 * 3. Randomly pick a node from the 2D matrix & update approp data structure
		 * 		- can use function from Math class to pick a number in a the index range?
		 * 4. Expand the node's branches using DFS
		 * 5. Use the specified probability to mark the node as blocked/unblocked
		 * 6. Enhance the node so that it considers the edge cases
		 * 		- dead-end nodes with no unvisited nodes (need to backtrack)
		 * 7. Make sure this process repeats until every node as been visited
		 * 		- can check by sorting the keys of each cell in ascending order (1-101)
		 * ALSO: since our start node is always [0][0], that always has to be unblocked
		 *       same goes with the goal node [100][100]
		 */
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
}
