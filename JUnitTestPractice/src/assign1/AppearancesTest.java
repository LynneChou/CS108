package assign1;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class AppearancesTest {
	// utility -- converts a string to a list with one
	// elem for each char.
	private List<String> stringToList(String s) {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<s.length(); i++) {
			list.add(String.valueOf(s.charAt(i)));
			// note: String.valueOf() converts lots of things to string form
		}
		return list;
	}

	@Test
	public void testSameCount1() {
		List<String> a = stringToList("abbccc");
		List<String> b = stringToList("cccbba");
		assertEquals(3, Appearances.sameCount(a, b));
	}

	@Test
	public void testSameCount2() {
		// basic List<Integer> cases
		List<Integer> a = Arrays.asList(1, 2, 3, 1, 2, 3, 5);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 9, 9, 1)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(1, 3, 3, 1, 1)));
	}

	@Test
	public void testSameCount3() {
		// basic List<Boolean> cases
		List<Boolean> a = Arrays.asList(true, true, false, false, true, true);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(false, false, true)));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(true, true, true, true, false)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(false, false, true, true, true, true)));
	}

	@Test
	public void testSameCount4() {
		// basic List<Character> cases
		List<Character> a = Arrays.asList('a', 'b', 'c', 'b', 'c', 'c', 'd');
		assertEquals(1, Appearances.sameCount(a, Arrays.asList('a')));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList('a', 'd')));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList('a', 'a', 'b', 'c', 'c', 'd', 'd')));
	}

	@Test
	public void testSameCount5() {
		// basic List<Double> cases
		List<Double> a = Arrays.asList(3.3, 2.2, 1.1, 5.5, 3.3, 2.2, 1.1);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(3.3, 4.4, 5.5, 6.6)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(2.2, 2.2, 3.3, 3.3)));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(3.3, 2.2, 1.1, 5.5, 5.5)));
	}

	@Test
	public void testSameCount6() {
		// basic List<Float> cases
		List<Float> a = Arrays.asList(3.3f, 2.2f, 1.1f, 5.5f, 3.3f, 2.2f, 1.1f);
		assertEquals(1, Appearances.sameCount(a, Arrays.asList(3.3f, 4.4f, 5.5f, 6.6f)));
		assertEquals(2, Appearances.sameCount(a, Arrays.asList(2.2f, 2.2f, 3.3f, 3.3f)));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList(3.3f, 2.2f, 1.1f, 5.5f, 5.5f)));
	}
	
	@Test
	public void testSameCount7() {
		// basic List<String> cases
		List<String> a = Arrays.asList("the", "day", "before", "the", "day", "before", "yesterday");
		assertEquals(2, Appearances.sameCount(a, Arrays.asList("the", "the", "day", "day")));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList("before", "the", "day", "yesterday")));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList("the", "yesterday", "yesterday", "day")));
	}
	
	@Test
	public void testSameCount8() {
		// basic List<String> cases
		List<String> a = Arrays.asList("", "", "", "the", "day", "before", "yesterday");
		assertEquals(0, Appearances.sameCount(a, Arrays.asList("", "", "", "")));
		assertEquals(1, Appearances.sameCount(a, Arrays.asList("", "", "")));
		assertEquals(0, Appearances.sameCount(a, Arrays.asList("", "")));
	}
}
