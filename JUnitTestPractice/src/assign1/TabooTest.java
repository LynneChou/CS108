// TabooTest.java
// Taboo class tests -- nothing provided.
package assign1;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TabooTest {

	/*
	 * test noFollow
	 */
	
	@Test
	public void testNoFollow1() {
		List<String> l = Arrays.asList("a", "c", "a", "b");
		Taboo<String> tb = new Taboo<String>(l);
		
		HashSet<String> ss = new HashSet<String>();
		Collections.addAll(ss, "b", "c");
		assertEquals(ss, tb.noFollow("a"));
		
		HashSet<String> tt = new HashSet<String>();
		Collections.addAll(tt, "a");
		assertEquals(tt, tb.noFollow("c"));

		assertEquals(Collections.emptySet(), tb.noFollow("b"));
	}
	
	@Test
	public void testNoFollow2() {
		List<String> l = Arrays.asList("a", "b", null, "c", "d");
		Taboo<String> tb = new Taboo<String>(l);
		
		HashSet<String> ss = new HashSet<String>();
		Collections.addAll(ss, "b");
		assertEquals(ss, tb.noFollow("a"));
		
		HashSet<String> tt = new HashSet<String>();
		Collections.addAll(tt, "d");
		assertEquals(tt, tb.noFollow("c"));

		assertEquals(Collections.emptySet(), tb.noFollow("b"));
		assertEquals(Collections.emptySet(), tb.noFollow("d"));
	}
	
	/*
	 * test reduce
	 */
	
	@Test
	public void testReduce1() {
		List<String> l = Arrays.asList("a", "c", "a", "b");
		Taboo<String> tb = new Taboo<String>(l);
		
		List<String> preBefore = Arrays.asList("a", "c", "b", "x", "c", "a");
		List<String> before = new ArrayList<String>();
		for(int i = 0; i < preBefore.size(); i++) {
			before.add(preBefore.get(i));
		}
		tb.reduce(before);
		List<String> after = Arrays.asList("a", "x", "c");
		assertEquals(after, before);
	}
	
	@Test
	public void testReduce2() {
		List<String> l = Arrays.asList("a", "b", null, "c", "d");
		Taboo<String> tb = new Taboo<String>(l);
		
		List<String> preBefore = Arrays.asList("a", "b", "c", "d");
		List<String> before = new ArrayList<String>();
		for(int i = 0; i < preBefore.size(); i++) {
			before.add(preBefore.get(i));
		}
		tb.reduce(before);
		List<String> after = Arrays.asList("a", "c");
		assertEquals(after, before);
	}
	
	@Test
	public void testReduce3() {
		List<Integer> l = Arrays.asList(null, 1, 2, 3, 4, null);
		Taboo<Integer> tb = new Taboo<Integer>(l);
		
		List<Integer> preBefore = Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4);
		List<Integer> before = new ArrayList<Integer>();
		for(int i = 0; i < preBefore.size(); i++) {
			before.add(preBefore.get(i));
		}
		tb.reduce(before);
		List<Integer> after = Arrays.asList(1, 1, 3, 3);
		assertEquals(after, before);
	}
	
	@Test
	public void testReduce4() {
		List<Boolean> l = Arrays.asList(true, true, false, false, true);
		Taboo<Boolean> tb = new Taboo<Boolean>(l);
		
		List<Boolean> preBefore = Arrays.asList(true, true, false, false, true);
		List<Boolean> before = new ArrayList<Boolean>();
		for(int i = 0; i < preBefore.size(); i++) {
			before.add(preBefore.get(i));
		}
		tb.reduce(before);
		List<Boolean> after = Arrays.asList(true);
		assertEquals(after, before);
	}
	
	@Test
	public void testReduce5() {
		List<Double> l = Arrays.asList(1.1, 2.2, null, 1.1, 3.3, null, 2.2);
		Taboo<Double> tb = new Taboo<Double>(l);
		
		List<Double> preBefore = Arrays.asList(1.1, 2.2, 3.3, 3.3, 1.1, 1.2, 2.3, 3.4);
		List<Double> before = new ArrayList<Double>();
		for(int i = 0; i < preBefore.size(); i++) {
			before.add(preBefore.get(i));
		}
		tb.reduce(before);
		List<Double> after = Arrays.asList(1.1, 1.1, 1.2, 2.3, 3.4);
		assertEquals(after, before);
	}
}
