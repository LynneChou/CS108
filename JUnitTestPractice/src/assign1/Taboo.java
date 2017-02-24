/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {
	private HashMap<T, HashSet<T>> hm;
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		hm = new HashMap<T, HashSet<T>>();
		int len = rules.size();
		for(int i = 0; i < len - 1; i++) {
			if(rules.get(i) == null || rules.get(i + 1) == null) {
				continue;
			}
			T key = rules.get(i);
			T val = rules.get(i + 1);
			if(hm.containsKey(key)) {
				HashSet<T> set = hm.get(key);
				set.add(val);
				hm.put(key, set);
			} else {
				HashSet<T> set = new HashSet<T>();
				set.add(val);
				hm.put(key, set);
			}
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if(hm.containsKey(elem)) {
			return hm.get(elem);
		} else {
			return Collections.emptySet();
		}
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		int ptr = 0;
		while(ptr < list.size() - 1) {
			T curr = list.get(ptr);
			if(hm.containsKey(curr)) {
				T next = list.get(ptr + 1);
				HashSet<T> hs = hm.get(curr);
				if(hs.contains(next)) {
					list.remove(ptr + 1);
				} else {
					ptr++;
				}
			} else {
				ptr++;
			}
		}
	}
}
