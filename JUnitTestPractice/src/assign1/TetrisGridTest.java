package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class TetrisGridTest {

	// Provided simple clearRows() test
	// width 2, height 3 grid
	@Test
	public void testClear1() {
		boolean[][] before =
			{	
					{true, true, false, },
					{false, true, true, }
			};

		boolean[][] after =
			{	
					{true, false, false},
					{false, true, false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}

	@Test
	public void testClear2() {
		boolean[][] before = 
			{
					{true}
			};

		boolean[][] after =
			{
					{false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));
	};

	@Test
	public void testClear3() {
		boolean[][] before = 
			{
					{true, true, true, true},
					{true, true, true, true},
					{true, true, true, true},
					{true, true, true, true}
			};

		boolean[][] after =
			{
					{false, false, false, false},
					{false, false, false, false},
					{false, false, false, false},
					{false, false, false, false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));		
	}

	@Test
	public void testClear4() {
		boolean[][] before = 
			{
					{false, false, false, false},
					{false, false, false, false},
					{false, false, false, false},
					{false, false, false, false}
			};

		boolean[][] after =
			{
					{false, false, false, false},
					{false, false, false, false},
					{false, false, false, false},
					{false, false, false, false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));	
	}

	@Test
	public void testClear5() {
		boolean[][] before = 
			{
					{true, true, true, true}
			};

		boolean[][] after =
			{
					{false, false, false, false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));	
	}

	@Test
	public void testClear6() {
		boolean[][] before = 
			{
					{true},
					{true}, 
					{true},
					{true}
			};

		boolean[][] after =
			{
					{false},
					{false}, 
					{false}, 
					{false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));	
	}

	@Test
	public void testClear7() {
		boolean[][] before = 
			{
					{true, false, true, false},
					{false, true, false, true}, 
			};

		boolean[][] after =
			{
					{true, false, true, false},
					{false, true, false, true}, 
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));	
	}

	@Test
	public void testClear8() {
		boolean[][] before = 
			{
					{true, false, true, false},
					{true, true, false, false}
			};

		boolean[][] after =
			{
					{false, true, false, false},
					{true, false, false, false}
			};

		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue(Arrays.deepEquals(after, tetris.getGrid()));	
	}
}
