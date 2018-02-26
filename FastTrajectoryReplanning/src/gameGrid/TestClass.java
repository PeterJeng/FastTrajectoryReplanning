package gameGrid;

import java.io.*;

/**
 * Class for testing purpose
 * 
 * Authors: Peter Jeng and Seerat Aziz 
 * Homework Assignment 1 
 * Introduction to Artificial Intelligence 
 * Spring 2018
 */

public class TestClass {
	public static void main(String[] args) throws FileNotFoundException {
		//Generates and stores 50 mazes
		Maze[] storedMazes = new Maze[50]; 
		for (int i = 0; i < 50; i++) {
			storedMazes[i] = new Maze();
			storedMazes[i].generateMaze();
		}
		int i = 0; //while loop counter
		
		File realMaze = new File("ActualMaze" + i +".txt");
		FileOutputStream rmfos = new FileOutputStream(realMaze); 
		PrintStream rm = new PrintStream(rmfos);
		System.setOut(rm);
		
		//Repeated Forward A* - tiebreaker 1
		PerceivedMaze perceived1 = new PerceivedMaze();
		File pMaze = new File("Forward_PM_" + i + ".txt");
		FileOutputStream pmfos1 = new FileOutputStream(pMaze);
		PrintStream ps1 = new PrintStream(pmfos1);
		System.setOut(ps1);
		RepeatedForwardAStar test1 = new RepeatedForwardAStar(storedMazes[i],perceived1);
		test1.start();
		
		//Repeated Forward A* - tiebreaker 2
		PerceivedMaze perceived2 = new PerceivedMaze();
		
		//Repeated Backward A* - tiebreaker 1
		PerceivedMaze perceived3 = new PerceivedMaze();
		
		//Adaptive A* - tiebreaker 1 
		PerceivedMaze perceived4 = new PerceivedMaze();
		File pMaze3 = new File("Adaptive_PM_" + i + ".txt");
		FileOutputStream pmfos4 = new FileOutputStream(pMaze3);
		PrintStream ps4 = new PrintStream(pmfos4);
		System.setOut(ps4);
		adaptiveastar test4 = new adaptiveastar(storedMazes[i],perceived4);
		test4.start();
	
	}
}
