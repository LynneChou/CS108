package assign1;

import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		// hash Collection a
		HashMap<T, Integer> hma = new HashMap<T, Integer>();
		for(T each : a) {
			if(hma.containsKey(each)) {
				int freq = hma.get(each);
				hma.put(each, freq + 1);
			} else {
				hma.put(each, 0);
			}
		}
		// hash Collection b
		HashMap<T, Integer> hmb = new HashMap<T, Integer>();
		for(T each : b) {
			if(hmb.containsKey(each)) {
				int freq = hmb.get(each);
				hmb.put(each, freq + 1);
			} else {
				hmb.put(each, 0);
			}
		}
		// match a and b
		int cnt = 0;
		for(T each : hma.keySet()) {
			if(hmb.containsKey(each) && hma.get(each).equals(hmb.get(each))) {
				cnt++;
			}
		}
		return cnt; 
	}
}
