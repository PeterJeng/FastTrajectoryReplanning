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
	/*	Maze maze = new Maze();
		maze.generateMaze();
		
		maze.calculateHeuristic(9, 9);
		
		maze.printMaze();
		
		for(int i = 0; i < maze.board.length; i++) {
			for(int j = 0; j < maze.board[0].length; j++) {
				System.out.print(maze.board[i][j].hValue + " ");
			}
			System.out.println();
		}
	*/
	/*
		Cell firstCell = new Cell();
		Cell secondCell = new Cell();
		Cell thirdCell = new Cell();
		Cell fourthCell = new Cell();
		Cell fifthCell = new Cell();
		Cell sixthCell = new Cell();
		Cell seventhCell = new Cell();
		
		firstCell.key = 1;
		secondCell.key = 2;
		thirdCell.key = 3;
		fourthCell.key = -1;
		fifthCell.key = -3; 
		sixthCell.key = 6;
		seventhCell.key = 7;
		
		
		BinaryHeap openlist = new BinaryHeap();
		openlist.insertCell(sixthCell);
		openlist.insertCell(seventhCell);
		openlist.insertCell(firstCell);
		openlist.insertCell(secondCell);
		openlist.insertCell(thirdCell);
		openlist.insertCell(fourthCell);
		openlist.insertCell(fifthCell);
		openlist.printHeap();
		
		openlist.deleteCell(fifthCell);
		openlist.printHeap();
	*/
		//for testing my implementation of aStar() - Seerat (adaptiveastar class)
		Maze maze1 = new Maze();
		maze1.generateMaze();
		adaptiveastar testStar = new adaptiveastar();
		maze1.printMaze();
		testStar.adaptiveSearch(maze1, 0, 0, 4, 4);
	}	
}
