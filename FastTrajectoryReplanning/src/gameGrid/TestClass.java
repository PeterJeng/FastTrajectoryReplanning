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
		/*
		PerceivedMaze test = new PerceivedMaze();
		
//		test.board = maze.board;
		test.calculateHeuristic(4, 4);
		test.printMaze();
		
		RepeatedForwardAStar testAlgo = new RepeatedForwardAStar();
		testAlgo.robotMaze = test;
		
		testAlgo.computePath();
		
		*/
		
		//for testing adaptiveastar class
		//now using running openlist with tiebreaker, solves some mazes & not all
		//still trying to figure out dead-end part and other random cases that dont work
		//need to add case in insertCell() about when a cell is already in the openList
		Maze maze1 = new Maze();
		//calculates heuristic for each cell
		maze1.calculateHeuristic(4, 4);
		//manually coding the state of the maze
		maze1.board[0][2].state = true;
		maze1.board[1][2].state = true;
		maze1.board[2][2].state = true;
		maze1.board[3][2].state = true;
		maze1.board[4][2].state = true;
		//prints out maze
		maze1.printMaze();
		System.out.print("\n");
		//testing out simpler aStar, does one iteration only
		adaptiveastar test = new adaptiveastar();
		test.aStar(maze1, 0, 0, 4, 4); //runs one A* search (no restarts)
		
	}	
}
