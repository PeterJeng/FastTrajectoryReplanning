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
	private ArrayList<Cell> binaryheap;

	public BinaryHeap() {
		binaryheap = new ArrayList<Cell>(5); // initial size of 5
	}

	public void insertCell(Cell x) {
		if (!this.binaryheap.contains(x)) { //to prevent duplicates
			this.binaryheap.add(x); // Cell added to end of array
			siftUp(x);
		}
	}

	public void deleteCell(Cell x) {
		int ind = binaryheap.indexOf(x);
		Cell last = binaryheap.get(binaryheap.size() - 1);
		binaryheap.set(ind, last); // sets node to be deleted as last cell
		this.binaryheap.remove(binaryheap.size() - 1); // deletes last cell
		
		//if we delete the last node in the array, do not shift
		if(ind > binaryheap.size() - 1) {
			return;
		}

		siftDown(binaryheap.get(ind));
		
		//special cases where a swapped node of last element after delete is smaller than parent. Resift
		siftUp(binaryheap.get(ind));
	}

	private void siftUp(Cell x) {
		int cInd = binaryheap.indexOf(x); // index of recently added element
		int pInd = (cInd - 1) / 2; // index of parent of recently added element
		while (cInd > 0) {
			Cell current = binaryheap.get(cInd);
			Cell parent = binaryheap.get(pInd);
			if (current.fValue < parent.fValue) {
				binaryheap.set(cInd, parent);
				binaryheap.set(pInd, current);
			}
			cInd = pInd;
			pInd = (cInd - 1) / 2;
		}
	}

	private void siftDown(Cell x) {
		int ind = binaryheap.indexOf(x);
		int l = 2 * ind + 1;
		int r = 2 * ind + 2;
		
		//leaf nodes, do not shift
		if (l > binaryheap.size() - 1 && r > binaryheap.size() - 1) {
			return;
		}
		
		while (true) {
			Cell left = binaryheap.get(l);
			Cell right = new Cell();
			Cell min = new Cell();
			int min_ind;
			if (r < binaryheap.size()) {
				right = binaryheap.get(r);
				min = (left.fValue < right.fValue) ? left : right;
			} else {
				min = left;
			}

			min_ind = binaryheap.indexOf(min);

			//swap parent and child if necessary
			if (min.fValue < x.fValue) {
				binaryheap.set(ind, min);
				binaryheap.set(min_ind, x);
			}

			//reset the indexes
			ind = min_ind;
			l = 2 * ind + 1;
			r = 2 * ind + 2;
			
			//check to see if we are at leaf nodes
			if (l > binaryheap.size() - 1 && r > binaryheap.size() - 1) {
				return;
			}
		}

	}
	
	public void clearHeap() {
		this.binaryheap.clear();
	}
	
	public Cell getCell(int x) {
		return this.binaryheap.get(x);
	}
	
	public void printHeap() {
		for (int i = 0; i < binaryheap.size(); i++) {
			System.out.println("Key: " + binaryheap.get(i).key);
			// can expand to print out more properties later
		}
		System.out.print("\n");
	}
}
