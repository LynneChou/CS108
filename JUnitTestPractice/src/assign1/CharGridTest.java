// Test cases for CharGrid -- a few basic tests are provided.
package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharGridTest {

	/*
	 * test charArea
	 */
	
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][] {
			{'a', 'y', ' '},
			{'x', 'a', 'z'},
		};


		CharGrid cg = new CharGrid(grid);

		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
		assertEquals(1, cg.charArea('x'));
		assertEquals(1, cg.charArea('y'));
		assertEquals(0, cg.charArea('w'));
		assertEquals(1, cg.charArea(' '));
	}


	@Test
	public void testCharArea2() {
		char[][] grid = new char[][] {
			{'c', 'a', ' '},
			{'b', ' ', 'b'},
			{' ', ' ', 'a'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
		assertEquals(9, cg.charArea(' '));
		assertEquals(0, cg.charArea('d'));
	}

	
	@Test
	public void testCharArea3() {
		char[][] grid = new char[][] {
			{'.', '$', '*'},
			{'$', '*', 'b'},
			{'%', '*', '.'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea(' '));
		assertEquals(9, cg.charArea('.'));
		assertEquals(4, cg.charArea('$'));
		assertEquals(6, cg.charArea('*'));
		assertEquals(1, cg.charArea('%'));
	}

	
	@Test
	public void testCharArea4() {
		char[][] grid = new char[][] {
			{'.'},
			{'\\'},
			{'.'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea(' '));
		assertEquals(3, cg.charArea('.'));
		assertEquals(1, cg.charArea('\\'));
	}

	
	@Test
	public void testCharArea5() {
		char[][] grid = new char[][] {
			{'^'}
		};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea(' '));
		assertEquals(1, cg.charArea('^'));
	}

	
	@Test
	public void testCharArea6() {
		char[][] grid = new char[][] {};

		CharGrid cg = new CharGrid(grid);

		assertEquals(0, cg.charArea(' '));
	}
	
	
	/*
	 * test countPlus
	 */
	
	@Test
	public void countPlus1() {
		char[][] grid = new char[][] {
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
			{'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
			{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
			{' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
			{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
			{' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '}
		};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(2, cg.countPlus());
	}
	
	@Test
	public void countPlus2() {
		char[][] grid = new char[][] {
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
			{'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
			{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
			{' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
			{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
			{' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '}
		};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(2, cg.countPlus());
	}
	
	@Test
	public void countPlus3() {
		char[][] grid = new char[][] {};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void countPlus4() {
		char[][] grid = new char[][] {
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'p', 'a', ' ', ' ', ' ', 'x', ' '},
			{'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
			{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
			{' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
			{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
			{' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '}
		};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(3, cg.countPlus());
	}
	
	@Test
	public void countPlus5() {
		char[][] grid = new char[][] {
			{'\\', '\\', '\\'},
			{'\\', '\\', '\\'},
			{'\\', '\\', '\\'}
		};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(1, cg.countPlus());
	}
	
	@Test
	public void countPlus6() {
		char[][] grid = new char[][] {
			{'\\', '\\'},
			{'\\', '\\'}
		};
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
}
