package gameGrid;

/**
 * Class for testing purpose
 * 
 * Authors: Peter Jeng and Seerat Aziz
 * Homework Assignment 1
 * Introduction to Artificial Intelligence
 * Spring 2018
 */
public class TestClass {

	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.generateMaze();
		
		maze.calculateHeuristic(9, 9);
		
		maze.printMaze();
		
		for(int i = 0; i < maze.board.length; i++) {
			for(int j = 0; j < maze.board[0].length; j++) {
				System.out.print(maze.board[i][j].hValue + " ");
			}
			System.out.println();
		}

	}

}
