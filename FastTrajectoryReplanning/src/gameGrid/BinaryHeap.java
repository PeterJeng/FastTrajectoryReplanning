package gameGrid;

/**
 * Implementation of a Binary Heap that utilizes min-heap property for A* search's
 * open list
 * 
 * Authors: Peter Jeng and Seerat Aziz
 * Homework Assignment 1
 * Introduction to Artificial Intelligence
 * Spring 2018
 */

/*
 * array implementation of BinaryHeap that uses min-heap property
 * why min-heap? A*'s open list is kept in ascending order
 * uses the ArrayList class so that the array can be resized easily
 */

//for now my implementation only sorts heap based on key value
//can change it later to use fValues instead 
//***deleteCell function giving errors, will have to fix***

import java.util.ArrayList;

public class BinaryHeap {
	public ArrayList<Cell> binaryheap; 
	
	public BinaryHeap () {
		binaryheap = new ArrayList<Cell>(5); //initial size of 5
	}
	
	public void insertCell(Cell x) {
		this.binaryheap.add(x); //Cell added to end of array
		
		siftUp();
	}
	
	/*
	public void deleteCell(Cell x) { 
		int ind = binaryheap.indexOf(x); 
		Cell last = binaryheap.get(binaryheap.size()-1);
		binaryheap.set(ind, last); //sets node to be deleted as last cell
		this.binaryheap.remove(last); //deletes last cell 
		
		siftDown(binaryheap.get(ind));
	}
	*/
	
	private void siftUp() {
		//need to change so that it orders list based on fValue
		int cInd = binaryheap.size() - 1; //index of recently added element
		int pInd = (cInd - 1)/2; //index of parent of recently added element
		while (cInd > 0) {
			Cell current = binaryheap.get(cInd);
			Cell parent = binaryheap.get(pInd);
			if (current.key < parent.key) {
				binaryheap.set(cInd, parent);
				binaryheap.set(pInd, current);
			}
			cInd = pInd;
			pInd = (cInd - 1)/2; 
		}
	}
	
	/*
	private void siftDown(Cell x) {
		//need to change so that it orders list based on fValue
		int ind = binaryheap.indexOf(x);
		int l = 2*ind + 1;
		int r = 2*ind + 2;
		while (true) {
			Cell left = binaryheap.get(l);
			Cell right = new Cell();
			Cell min = new Cell();
			int min_ind; 
			if (r<binaryheap.size()) {
				right = binaryheap.get(r);
				min = (left.key < right.key) ? left : right;
			}
			else {
				min = left;
			}
			
			min_ind = binaryheap.indexOf(min);
			
			if (min.key < x.key) {
				binaryheap.set(ind, min);
				binaryheap.set(min_ind, x);
			}
			
			ind = min_ind;
			l = 2*ind + 1;
			r = 2*ind + 2; 
		}
		
	}
	*/
	
	public void printHeap() {
		for (int i = 0; i < binaryheap.size(); i++) {
			Cell temp = binaryheap.get(i);
			System.out.println("Key: " + temp.key);
			//can expand to print out more properties later
		}
	}
}
