// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}

	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		if(grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int rowNum = grid.length;
		int colNum = grid[0].length;
		int minRow = Integer.MAX_VALUE;
		int maxRow = Integer.MIN_VALUE;
		int minCol = Integer.MAX_VALUE;
		int maxCol = Integer.MIN_VALUE;
		boolean noChar = true;
		for(int r = 0; r < rowNum; r++) {
			for(int c = 0; c < colNum; c++) {
				if(grid[r][c] == ch) {
					minRow = Math.min(minRow, r);
					maxRow = Math.max(maxRow, r);
					minCol = Math.min(minCol, c);
					maxCol = Math.max(maxCol, c);
					noChar = false;
				}
			}
		}
		if(noChar) {
			return 0;
		} else {
			return (maxRow - minRow + 1) * (maxCol - minCol + 1);
		}
	}

	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		if(grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int rowNum = grid.length;
		int colNum = grid[0].length;
		int count = 0;
		for(int r = 0; r < rowNum; r++) {
			for(int c = 0; c < colNum; c++) {
				if(isPlus(r, c, rowNum, colNum)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 
	 * @param row, current row number
	 * @param col, current column number
	 * @param rowNum, total row number
	 * @param colNum, total column number
	 * @return
	 */
	private boolean isPlus(int row, int col, int rowNum, int colNum) {
		char ch = grid[row][col];
		// count up arm
		int up = 0;
		int r = row;
		int c = col;
		while(0 <= r && r < rowNum && grid[r][c] == ch) {
			up++;
			r--;
		}
		// count down arm
		int down = 0;
		r = row;
		c = col;
		while(0 <= r && r < rowNum && grid[r][c] == ch) {
			down++;
			r++;
		}
		// count left arm
		int left = 0;
		r = row;
		c = col;
		while(0 <= c && c < colNum && grid[r][c] == ch) {
			left++;
			c--;
		}
		// count right arm
		int right = 0;
		r = row;
		c = col;
		while(0 <= c && c < colNum && grid[r][c] == ch) {
			right++;
			c--;
		}
		// check whether they are equal
		if(up == down && down == left && left == right && right >=2) {
			return true;
		}
		return false;
	}

}
