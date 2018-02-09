package gameGrid;

public class PerceivedMaze extends Maze{
	public int traveledPathCost;
	
	public PerceivedMaze() {
		super();	
	}
	
	
	public void move(int row, int col) {
		this.traveledPathCost++;
	}
	
	/**
	 * update the maze with new information
	 */
	public void update() {
		
	}
}
