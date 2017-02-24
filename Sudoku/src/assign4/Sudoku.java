package assign4;

import java.util.*;

/*
 * Encapsulates a Sudoku grid to be solved.
 * CS108 Stanford.
 */
public class Sudoku {
	// Provided grid data for main/testing
	// The instance variable strategy is up to you.

	// Provided easy 1 6 grid
	// (can paste this text into the GUI too)
	public static final int[][] easyGrid = Sudoku.stringsToGrid(
			"1 6 4 0 0 0 0 0 2",
			"2 0 0 4 0 3 9 1 0",
			"0 0 5 0 8 0 4 0 7",
			"0 9 0 0 0 6 5 0 0",
			"5 0 0 1 0 2 0 0 8",
			"0 0 8 9 0 0 0 3 0",
			"8 0 9 0 4 0 2 0 0",
			"0 7 3 5 0 9 0 0 1",
			"4 0 0 0 0 0 6 7 9");


	// Provided medium 5 3 grid
	public static final int[][] mediumGrid = Sudoku.stringsToGrid(
			"530070000",
			"600195000",
			"098000060",
			"800060003",
			"400803001",
			"700020006",
			"060000280",
			"000419005",
			"000080079");

	// Provided hard 3 7 grid
	// 1 solution this way, 6 solutions if the 7 is changed to 0
	public static final int[][] hardGrid = Sudoku.stringsToGrid(
			"3 7 0 0 0 0 0 8 0",
			"0 0 1 0 9 3 0 0 0",
			"0 4 0 7 8 0 0 0 3",
			"0 9 3 8 0 0 0 1 2",
			"0 0 0 0 4 0 0 0 0",
			"5 2 0 0 0 6 7 9 0",
			"6 0 0 0 2 1 0 4 0",
			"0 0 0 5 3 0 9 0 0",
			"0 3 0 0 0 0 0 5 1");

	public static final int SIZE = 9;  // size of the whole 9x9 puzzle
	public static final int PART = 3;  // size of each 3x3 part
	public static final int MAX_SOLUTIONS = 100;

	// Provided various static utility methods to
	// convert data formats to int[][] grid.

	/**
	 * Returns a 2-d grid parsed from strings, one string per row.
	 * The "..." is a Java 5 feature that essentially
	 * makes "rows" a String[] array.
	 * (provided utility)
	 * @param rows array of row strings
	 * @return grid
	 */
	public static int[][] stringsToGrid(String... rows) {
		int[][] result = new int[rows.length][];
		for (int row = 0; row<rows.length; row++) {
			result[row] = stringToInts(rows[row]);
		}
		return result;
	}


	/**
	 * Given a single string containing 81 numbers, returns a 9x9 grid.
	 * Skips all the non-numbers in the text.
	 * (provided utility)
	 * @param text string of 81 numbers
	 * @return grid
	 */
	public static int[][] textToGrid(String text) {
		int[] nums = stringToInts(text);
		if (nums.length != SIZE*SIZE) {
			throw new RuntimeException("Needed 81 numbers, but got:" + nums.length);
		}

		int[][] result = new int[SIZE][SIZE];
		int count = 0;
		for (int row = 0; row<SIZE; row++) {
			for (int col=0; col<SIZE; col++) {
				result[row][col] = nums[count];
				count++;
			}
		}
		return result;
	}


	/**
	 * Given a string containing digits, like "1 23 4",
	 * returns an int[] of those digits {1 2 3 4}.
	 * (provided utility)
	 * @param string string containing ints
	 * @return array of ints
	 */
	public static int[] stringToInts(String string) {
		int[] a = new int[string.length()];
		int found = 0;
		for (int i=0; i<string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				a[found] = Integer.parseInt(string.substring(i, i+1));
				found++;
			}
		}
		int[] result = new int[found];
		System.arraycopy(a, 0, result, 0, found);
		return result;
	}


	// Provided -- the deliverable main().
	// You can edit to do easier cases, but turn in
	// solving hardGrid.
	public static void main(String[] args) {
		Sudoku sudoku;
		sudoku = new Sudoku(hardGrid);
		System.out.println(sudoku); // print the raw problem

		int count = sudoku.solve();
		System.out.println("solutions:" + count);
		System.out.println("elapsed:" + sudoku.getElapsed() + "ms");
		System.out.println(sudoku.getSolutionText());
	}


	private static int[][] board;		// deep copy of the spots from int[][]
	private static int blankNum;		// count the # of blanks in the board
	private static long startTime;		// record the startTime
	private static long endTime;		// record the endTime
	private static int solutionsNum;	// record the # of solutions (<100)
	private Spot[] spots;				// spots objects
	private static boolean flag;		// flag to check the first solution
	private static String solutionText;	// record the first solution text
	private static final boolean DEBUG = false;		// debug mode

	
	public class Spot implements Comparable<Spot> {
		public int r;					// row #
		public int c;					// col #
		public int centerR;				// the center row # where r, c locate
		public int centerC;				// the center col # where r, c locate
		public HashSet<Integer> used;	// the used nums in this row, col and part	

		
		/**
		 * Constructor of Spot
		 * @param r, row #
		 * @param c, col #
		 * @param used, the used nums in this row, col and part	
		 */
		public Spot(int r, int c, HashSet<Integer> used) {
			this.r = r;
			this.c = c;
			centerR = r / 3 * 3 + 1;
			centerC = c / 3 * 3 + 1;
			this.used = new HashSet<Integer>();
			this.used.addAll(used);
		}

		
		/**
		 * set the spot to the value 
		 * @param val, value to be set
		 */
		public void set(int val) {
			board[r][c] = val;
		}
		
		
		/**
		 * reset the spot to 0
		 */
		public void reset() {
			board[r][c] = 0;
		}

		
		/**
		 * check current spot, which of the numbers are used
		 * @return the set of used nums, including row, col and part
		 */
		public HashSet<Integer> check() {
			HashSet<Integer> res = new HashSet<Integer>();
			for(int rr = 0; rr < SIZE; rr++) {
				res.add(board[rr][c]);
			}
			for(int cc = 0; cc < SIZE; cc++) {
				res.add(board[r][cc]);
			}
			for(int dr = -1; dr <= 1; dr++) {
				for(int dc = -1; dc <= 1; dc++) {
					res.add(board[(r / 3 * 3 + 1) + dr][(c / 3 * 3 + 1) + dc]);
				}
			}
			return res;
		}

		/**
		 * implement the method in Comparable interface, to Array.sort spots
		 */
		public int compareTo(Spot s) {
	    	Integer self = used.size();
	    	Integer other = s.used.size();
	        return other.compareTo(self);		
		}

	}

	/**
	 * Constructor with String text input
	 * @param text: 81 input numbers in String format
	 */
	public Sudoku(String text) {
		this(textToGrid(text));
	}
	
	
	/**
	 * Sets up based on the given ints.
	 */
	public Sudoku(int[][] ints) {
		// YOUR CODE HERE
		flag = true;
		solutionText = "";
		solutionsNum = 0;

		// count # of blank spots
		blankNum = 0;
		board = new int[SIZE][SIZE];
		for(int r = 0; r < SIZE; r++) {
			for(int c = 0; c < SIZE; c++) {
				board[r][c] = ints[r][c];
				if(ints[r][c] == 0) {
					blankNum++;
				}
			}
		}
		
		// initialize spots
		spots = new Spot[blankNum];
		int cnt = 0;
		for(int r = 0; r < SIZE; r++) {
			for(int c = 0; c < SIZE; c++) {
				if(ints[r][c] == 0) {			// when it is blank
					HashSet<Integer> hs = new HashSet<Integer>();
					for(int rr = 0; rr < SIZE; rr++) {
						hs.add(ints[rr][c]);
					}
					for(int cc = 0; cc < SIZE; cc++) {
						hs.add(ints[r][cc]);
					}
					for(int dr = -1; dr <= 1; dr++) {
						for(int dc = -1; dc <= 1; dc++) {
							hs.add(ints[(r / 3 * 3 + 1) + dr][(c / 3 * 3 + 1) + dc]);
						}
					}
					spots[cnt] = new Spot(r, c, hs);
					cnt++;
				}
			}
		}
		// YOUR CODE ENDS
	}
	
	/**
	 * override toString() method
	 * return: String of the board in SIZE lines
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for(int r = 0; r < SIZE; r++) {
			for(int c = 0; c < SIZE; c++) {
				sb.append(board[r][c]);
				if(c != SIZE - 1) {
					sb.append(" ");
				}
			}
			if(r != SIZE - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}


	/**
	 * Solves the puzzle, invoking the underlying recursive search.
	 * return: # of solutions we found 
	 */
	public int solve() {
		startTime = System.currentTimeMillis();
		// first sort
		Arrays.sort(spots);
		// second find
		int[] res = new int[1];
		res[0] = 0;
		solveHelper(spots, 0, res);
		solutionsNum = res[0];
		endTime = System.currentTimeMillis();
		return solutionsNum; 
	}

	
	/**
	 * Store the solutionText
	 */
	private void storeSolution() {
		for(int r = 0; r < SIZE; r++) {
			for(int c = 0; c < SIZE; c++) {
				solutionText += board[r][c];
				if(c != SIZE - 1) {
					solutionText += " ";
				}
 			}
			if(r != SIZE - 1) {
				solutionText += "\n";
			}
		}
	}
	
	
	/**
	 * Recursively find feasible solution
	 * @param spots, sorted spots
	 * @param idx, # of currently processing
	 * @param res[0], # of solutions we found
	 */
	private void solveHelper(Spot[] spots, int idx, int[] res) {
		// base case: exceeds max solutions
		if(res[0] >= MAX_SOLUTIONS) {
			return;
		}

		// base case: success! we've filled out all spots
		if(idx >= spots.length) {
			res[0]++;
			if(flag) {
				storeSolution();
				flag = false;
			}
			return;
		}
		
		HashSet<Integer> curr = new HashSet<Integer>();
		curr.addAll(spots[idx].used);		// deep copy of the used numbers
		curr.addAll(spots[idx].check());	// newly added used numbers

		if(DEBUG){
			// debug ===================
			System.out.println("=====================");
			System.out.println("(" + spots[idx].r + "," + spots[idx].c + ")");
			System.out.println(curr);
			System.out.println(curr);
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.print("\n");
			}
			// debug ===================
		}
		
		// recursive case:
		for(int i = 1; i <= SIZE; i++) {
			if(!curr.contains(i)) {
				spots[idx].set(i);
				solveHelper(spots, idx + 1, res);
				spots[idx].reset();
			}
		}
	}

	
	/**
	 * format solution text
	 * @return solution text in 9 lines
	 */
	public String getSolutionText() {
		String res = "";
		if(!flag) {
			res = res + solutionText;
		}
		return res;
	}

	public long getElapsed() {
		return endTime - startTime; 
	}

}
