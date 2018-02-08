package gameGrid;

/**
 * Individual representation of the cells. A cell will have the properties 
 * @author Peter
 *
 */
public class Cell {
	boolean state;
	boolean visited;
	
	public Cell() {
		this.state = false;
		this.visited = false;
	}	
	
	/**
	 * A "false" state represents unoccupied space, using "_" as representation
	 * A "true" state represents occupied space, using "X" as representation
	 */
	public String toString() {
		
		//ternary operator, basically if(state == true) return X else return _
		return this.state ? "X " : "_ ";
	}
}
