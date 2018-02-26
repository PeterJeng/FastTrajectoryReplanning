package gameGrid;

public class RepeatedForwardAStarV2 extends RepeatedForwardAStar{

	public RepeatedForwardAStarV2(Maze realMaze, PerceivedMaze perceivedMaze) {
		super(realMaze, perceivedMaze);
	}
	
	/**
	 * Implements a tie breaker using smallest g-Value
	 */
	public Cell nextNodeInList() {
		Cell result = this.openList.getCell(0);
		int min = result.fValue;
		Cell temp = new Cell();
		for (int i = 1; i < this.openList.heapSize(); i++) {
			temp = this.openList.getCell(i);
			if (temp.fValue == min && temp.gValue < result.gValue)
				result = temp;
		}
		return result; 
	}

}
