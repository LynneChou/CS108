//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.
package assign1;

public class TetrisGrid {
	
	/**
	 * Store grid by reference
	 */
	private boolean[][] tetris;
	private int xlen;
	private int ylen;
	
	
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		tetris = grid;
		xlen = grid.length;
		ylen = grid[0].length;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		int currY = 0;		// point to current y, in which are all false
		int nextY = 0;		// point to next y, to find which are not all false
		while(nextY < ylen) {
			if(checkRow(nextY)) {
				nextY++;
			} else {
				for(int x = 0; x < xlen; x++) {		// swap
					boolean tmp = tetris[x][currY];
					tetris[x][currY] = tetris[x][nextY];
					tetris[x][nextY] = tmp;
				}
				currY++;
				nextY++;
			}
		}
	}
	
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return tetris;
	}
	
	
	/**
	 * check at y row, whether the elements are the same
	 * @param y, the y(th) row
	 * @return true if all the elements are the same in this row
	 * 		   false, otherwise
	 */
	private boolean checkRow(int y) {
		// case 1: if all x are true, make them false, return true;
		boolean allTrueFlag = true;
		for(int x = 0; x < xlen; x++) {
			if(tetris[x][y] == false) {
				allTrueFlag = false;
				break;
			}
		}
		if(allTrueFlag) {
			for(int x = 0; x < xlen; x++) {
				tetris[x][y] = false;
			}
			return true;
		}
		// case 2: if all x are false, return true;
		boolean allFalseFlag = true;
		for(int x = 0; x < xlen; x++) {
			if(tetris[x][y] == true) {
				allFalseFlag = false;
				break;
			}
		}
		if(allFalseFlag) {
			return true;
		}
		// case 3: both true and false exists
		return false;
	}
}
