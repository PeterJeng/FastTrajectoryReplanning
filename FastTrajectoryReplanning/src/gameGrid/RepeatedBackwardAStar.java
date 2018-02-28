package gameGrid;

/**
 * Class for Repeated Backward A*
 * 
 * Authors: Peter Jeng and Seerat Aziz 
 * Homework Assignment 1 
 * Introduction to Artificial Intelligence 
 * Spring 2018
 */

import java.util.LinkedList;

public class RepeatedBackwardAStar {
	public PerceivedMaze robotMaze;
	public BinaryHeap openList;
	public LinkedList<Cell> closedList;
	public LinkedList<Cell> traversalPath;
	public Maze realMaze;
	int numExpandedCell = 0;

	/**
	 * Constructor for algorithm. Field instantiation
	 * 
	 * @param realMaze
	 *            actual maze with blocked and unblocked cells
	 * @param perceivedMaze
	 *            maze robot sees
	 */
	public RepeatedBackwardAStar(Maze realMaze, PerceivedMaze perceivedMaze) {
		this.openList = new BinaryHeap();
		this.closedList = new LinkedList<Cell>();
		this.robotMaze = perceivedMaze;
		this.realMaze = realMaze;
		this.traversalPath = new LinkedList<Cell>();
		this.robotMaze.current = this.robotMaze.board[this.robotMaze.board.length - 1][this.robotMaze.board.length - 1];
		this.robotMaze.start = this.robotMaze.board[0][0];
		this.robotMaze.end = this.robotMaze.board[this.robotMaze.board.length - 1][this.robotMaze.board.length - 1];

	}

	/**
	 * Traverses the perceivedMazes from current Start to End.
	 * 
	 * @return boolean True if there is a path from Start to End. False if there is
	 *         no possible path.
	 */
	public boolean computePath() {
		Cell left = new Cell();
		Cell right = new Cell();
		Cell bottom = new Cell();
		Cell top = new Cell();

		robotMaze.current.parent = null;

		// calculate the gValues and fValues of the current cell.
		// robotMaze.current should be equivalent to robotMaze.start at this point
		robotMaze.current.gValue = 0;
		robotMaze.current.fValue = robotMaze.current.gValue + robotMaze.current.hValue;

		// insert the starting cell to the Binary Heap
		openList.insertCell(robotMaze.current, this.alreadyInOpenList(robotMaze.current));

		while (robotMaze.start.key != robotMaze.current.key) {
			// implement the cell locations
			// if there is no cells to the direction of the current cell, then we set the X
			// cell to null
			top = (robotMaze.current.row - 1 < 0) ? null
					: robotMaze.board[robotMaze.current.row - 1][robotMaze.current.col];
			bottom = (robotMaze.current.row + 1 > robotMaze.board.length - 1) ? null
					: robotMaze.board[robotMaze.current.row + 1][robotMaze.current.col];
			left = (robotMaze.current.col - 1 < 0) ? null
					: robotMaze.board[robotMaze.current.row][robotMaze.current.col - 1];
			right = (robotMaze.current.col + 1 > robotMaze.board.length - 1) ? null
					: robotMaze.board[robotMaze.current.row][robotMaze.current.col + 1];

			// update cell gValue, fValue, and parent pointer
			// only update and insert if cell is an unblocked cell and not already
			// visited(represented by closedList)
			if (top != null && top.state == false && !closedList.contains(top)) {
				top.gValue = robotMaze.current.gValue + 1;
				top.fValue = top.gValue + top.hValue;
				openList.insertCell(top, this.alreadyInOpenList(top));
				top.parent = robotMaze.current;
			}
			if (bottom != null && bottom.state == false && !closedList.contains(bottom)) {
				bottom.gValue = robotMaze.current.gValue + 1;
				bottom.fValue = bottom.gValue + bottom.hValue;
				openList.insertCell(bottom, this.alreadyInOpenList(bottom));
				bottom.parent = robotMaze.current;
			}
			if (left != null && left.state == false && !closedList.contains(left)) {
				left.gValue = robotMaze.current.gValue + 1;
				left.fValue = left.gValue + left.hValue;
				openList.insertCell(left, this.alreadyInOpenList(left));
				left.parent = robotMaze.current;
			}
			if (right != null && right.state == false && !closedList.contains(right)) {
				right.gValue = robotMaze.current.gValue + 1;
				right.fValue = right.gValue + right.hValue;
				openList.insertCell(right, this.alreadyInOpenList(right));
				right.parent = robotMaze.current;
			}

			closedList.add(robotMaze.current);
			numExpandedCell++;
			openList.deleteCell(robotMaze.current);

			// if heap is empty, then that means there are no possible expansions left
			// Have not reached the target node so we return false

			if (openList.isHeapEmpty()) {
				return false;
			}
			// update robotMaze.current to next one in Heap
			robotMaze.current = nextNodeInList();
		}

		// Provide a path from start cell to end cell
		traversalPath();

		return true;

	}

	/**
	 * Movement of the robot in the actual maze
	 */
	public void traverseMaze() {
		System.out.println("Starting travesal...");
		System.out.println("Reached: ");
		Cell cellPtr = new Cell();
		for (int i = 0; i < traversalPath.size(); i++) {
			cellPtr = traversalPath.get(i);

			// cell is blocked
			if (realMaze.board[cellPtr.row][cellPtr.col].state == true) {
				// keep track of where the robot is now in the maze, backtrack one step
				robotMaze.start = traversalPath.get(i - 1);

				// reset the position of the current to end
				robotMaze.current = robotMaze.board[robotMaze.end.row][robotMaze.end.col];

				// update the perceived Maze to a blocked state
				robotMaze.board[cellPtr.row][cellPtr.col].state = true;
				System.out.println();
				System.out.println("HIT A BLOCK AT: " + cellPtr.key);

				// clear the open and closed list
				openList.clearHeap();
				closedList.clear();

				return;
			}
			// marks traversed cell as visited
			robotMaze.board[cellPtr.row][cellPtr.col].visited = true;

			// look at the surroundings of the current cell and update if there is anything
			// blocked
			updateSurrounding(cellPtr);

			System.out.print(cellPtr.key + " ");
		}
	}

	/**
	 * Start method to begin the algorithm Will call computePath() and
	 * traverseMaze() respectively.
	 */
	public void start() {
		// takes a look around its surrounding for the first iteration of A*
		updateSurrounding(robotMaze.start);
		while (robotMaze.current.key != robotMaze.start.key) {

			// re-calculate heuristic value to that of the agent
			robotMaze.calculateHeuristic(robotMaze.start.row, robotMaze.start.col);

			if (computePath()) {
				System.out.println("Planned path: ");

				Cell cellPtr = new Cell();
				for (int i = 0; i < traversalPath.size(); i++) {
					cellPtr = traversalPath.get(i);
					System.out.print(cellPtr.key + " ");
				}

				System.out.println();

				traverseMaze();
				System.out.println();
				robotMaze.printMaze();
				traversalPath.clear();

				System.out.println();
			} else {
				System.out.println();
				robotMaze.printMaze();
				System.out.println("NO SOLUTION");
				break;
			}
		}
	}

	/**
	 * Plan the traversal path by using parent pointers starting from the end
	 */
	public void traversalPath() {
		// update the traversalPath
		// start from the target and work backward until you reach start node
		// add the final node first, and then work backwards until we reach the start
		// node
		Cell temp = robotMaze.start;

		while (temp != null) {
			traversalPath.addLast(temp);
			temp = temp.parent;
		}
	}

	/**
	 * Look at the surrounding cells of a given cell and update perceivedMaze if any
	 * cells are blocked
	 * 
	 * @param cell
	 *            location to observe surrounding cells
	 */
	public void updateSurrounding(Cell cell) {
		Cell top = (cell.row - 1 < 0) ? null : robotMaze.board[cell.row - 1][cell.col];
		Cell bottom = (cell.row + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[cell.row + 1][cell.col];
		Cell left = (cell.col - 1 < 0) ? null : robotMaze.board[cell.row][cell.col - 1];
		Cell right = (cell.col + 1 > robotMaze.board.length - 1) ? null : robotMaze.board[cell.row][cell.col + 1];

		if (top != null) {
			robotMaze.board[top.row][top.col].state = realMaze.board[top.row][top.col].state;
		}

		if (bottom != null) {
			robotMaze.board[bottom.row][bottom.col].state = realMaze.board[bottom.row][bottom.col].state;
		}

		if (left != null) {
			robotMaze.board[left.row][left.col].state = realMaze.board[left.row][left.col].state;
		}

		if (right != null) {
			robotMaze.board[right.row][right.col].state = realMaze.board[right.row][right.col].state;
		}
	}

	/**
	 * Implements a tie breaker using largest g-Value
	 */
	private Cell nextNodeInList() {
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
}

