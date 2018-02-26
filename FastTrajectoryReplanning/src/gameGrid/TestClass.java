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
		int[] expandedCell1 = new int[50];
		int[] expandedCell2 = new int[50];
		int[] expandedCell3 = new int[50];
		int[] expandedCell4 = new int[50]; 
		
		PrintStream console = System.out; //saves original state of System.out.print
		
		int i = 0; //while loop counter
		while (i < 50) {
			File realMaze = new File("ActualMaze" + i +".txt");
			FileOutputStream rmfos = new FileOutputStream(realMaze); 
			PrintStream rm = new PrintStream(rmfos);
			System.setOut(rm);
			
			//Repeated Forward A* - tiebreaker 1
			PerceivedMaze perceived1 = new PerceivedMaze();
			perceived1.calculateHeuristic(100, 100);
			File pMaze = new File("Forward_PM_" + i + ".txt");
			FileOutputStream pmfos1 = new FileOutputStream(pMaze);
			PrintStream ps1 = new PrintStream(pmfos1);
			System.setOut(ps1);
			RepeatedForwardAStar test1 = new RepeatedForwardAStar(storedMazes[i],perceived1);
			expandedCell1[i] = test1.numExpandedCell;
			test1.start();
			
			//Repeated Forward A* - tiebreaker 2
			PerceivedMaze perceived2 = new PerceivedMaze();
			perceived2.calculateHeuristic(100, 100);
			File pMaze1 = new File("Forward_V2_PM_" + i + ".txt");
			FileOutputStream pmfos2 = new FileOutputStream(pMaze1);
			PrintStream ps2 = new PrintStream(pmfos2);
			System.setOut(ps2);
			RepeatedForwardAStarV2 test2 = new RepeatedForwardAStarV2(storedMazes[i],perceived2);
			expandedCell2[i] = test2.numExpandedCell;
			test2.start();
			
			//Repeated Backward A* - tiebreaker 1
			PerceivedMaze perceived3 = new PerceivedMaze();
			perceived3.calculateHeuristic(100, 100);
			File pMaze2 = new File("Backward_PM_" + i + ".txt");
			FileOutputStream pmfos3 = new FileOutputStream(pMaze2);
			PrintStream ps3 = new PrintStream(pmfos3);
			System.setOut(ps3);
			RepeatedBackwardAStar test3 = new RepeatedBackwardAStar(storedMazes[i],perceived3);
			expandedCell3[i] = test3.numExpandedCell;
			test3.start();
			
			//Adaptive A* - tiebreaker 1 
			PerceivedMaze perceived4 = new PerceivedMaze();
			perceived4.calculateHeuristic(100, 100);
			File pMaze3 = new File("Adaptive_PM_" + i + ".txt");
			FileOutputStream pmfos4 = new FileOutputStream(pMaze3);
			PrintStream ps4 = new PrintStream(pmfos4);
			System.setOut(ps4);
			adaptiveastar test4 = new adaptiveastar(storedMazes[i],perceived4);
			expandedCell4[i] = test4.numExpandedCell;
			test4.start();
			
			i++; 
		}
		System.setOut(console); //restores printing to console for results 
		
		//in order to print out the number of expanded cells for each algorithm
		System.out.println("#Expanded Cells per Maze for Repeated Forward A*");
		for (int m = 0; m < 50; m++) {
			System.out.println(expandedCell1[m]);
		}
		System.out.println("#Expanded Cells per Maze for Repeated Forward A* - version 2");
		for (int m = 0; m < 50; m++) {
			System.out.println(expandedCell1[m]);
		}
		System.out.println("#Expanded Cells per Maze for Repeated Backward A*");
		for (int m = 0; m < 50; m++) {
			System.out.println(expandedCell1[m]);
		}
		System.out.println("#Expanded Cells per Maze for Adaptive A*");
		for (int m = 0; m < 50; m++) {
			System.out.println(expandedCell1[m]);
		}
	
	}
}
