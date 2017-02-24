package assign1;

import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if(str.isEmpty()) {
			return 0;
		}
		int len = str.length();
		char curr = str.charAt(0);
		int ptr = 1;		// point to current char
		int max = 1;
		int cnt = 1;		// current counter
		while(ptr < len) {
			if(str.charAt(ptr) == curr) {
				cnt++;
			} else {
				curr = str.charAt(ptr);
				max = Math.max(max, cnt);
				cnt = 1;
			}
			ptr++;
		}
		max = Math.max(max, cnt); // for the last several chars
		return max; 
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		// corner case
		if(str.isEmpty()) {
			return "";
		}
		// check chars other than the last
		int len = str.length();
		String res = "";
		for(int i = 0; i < len - 1; i++) {
			char tmp = str.charAt(i);
			if('0' <= tmp && tmp <= '9') {
				for(int j = 0; j < tmp - '0'; j++) {
					res = res + str.charAt(i + 1);
				}
			} else {
				res = res + tmp;
			}
		}
		// check the last char
		char last = str.charAt(len - 1);
		if(last < '0' || '9' < last) {
			res = res + last;
		}
		return res;
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		int alen = a.length();
		int blen = b.length();
		if(alen < len || blen < len) {
			return false;
		}
		// hash string a
		HashSet<String> hs = new HashSet<String>();
		for(int i = 0; i <= alen - len; i++) {
			String sub = a.substring(i, i + len);
			hs.add(sub);
		}
		// traverse b to check
		for(int j = 0; j <= blen - len; j++) {
			String trunc = b.substring(j, j + len);
			if(hs.contains(trunc)) {
				return true;
			}
		}
		return false;
	}
}
