package gameGrid;

/**
 * Maze class that contains the game grid. 
 * 
 * @author Peter
 *
 */
public class Maze {
	public Cell[][] board;
	
	public Maze() {
		this.board = new Cell[5][5];
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = new Cell();
			}
		}
		
		generateMaze();
	}
	
	/**
	 * TODO: Implement a DFS algorithm???
	 * Currently using a random number generator to determine if the cell is blocked or unblocked
	 */
	public void generateMaze() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(Math.random() < 0.3) {
					board[i][j].state = true;
				}
				else {
					board[i][j].state = false;
				}
			}
		}
	}
	
	/**
	 * prints the state of the maze
	 */
	public void printMaze() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j].toString());
			}
			System.out.println();
		}
	}
}
