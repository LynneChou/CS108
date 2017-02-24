// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringCodeTest {
	//
	// blowup
	//
	@Test
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
		assertEquals("attttxzzz", StringCode.blowup("a3tx2z"));
		assertEquals("2xxx", StringCode.blowup("12x"));
		assertEquals("AAAbbbCCC", StringCode.blowup("2A2b2C"));
	}

	@Test
	public void testBlowup2() {
		// things with digits

		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));

		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));

		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));

		// try only digits
		assertEquals("233", StringCode.blowup("0123"));		

		// try only zeros
		assertEquals("", StringCode.blowup("000"));
	}

	@Test
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));

		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));

		// string with spaces
		assertEquals("aa    bb", StringCode.blowup("aa3 bb"));
		assertEquals("   ", StringCode.blowup("   "));
	}


	//
	// maxRun
	//
	@Test
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
		assertEquals(2, StringCode.maxRun("=="));
		assertEquals(1, StringCode.maxRun("aha"));
		assertEquals(1, StringCode.maxRun("~!@#$%^&*()_+"));
	}

	@Test
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
		assertEquals(2, StringCode.maxRun("Good morning, Sir!"));
		assertEquals(5, StringCode.maxRun("I Looooove you!"));
	}

	@Test
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
		assertEquals(3, StringCode.maxRun("11122333"));
		assertEquals(4, StringCode.maxRun("111223333"));
	}


	//
	// stringIntersect
	//
	@Test
	public void stringIntersect() {
		assertEquals(false, StringCode.stringIntersect("", "abc", 1));
		assertEquals(false, StringCode.stringIntersect("abc", "abd", 3));
		assertEquals(true, StringCode.stringIntersect("abc", "abcabc", 3));
		assertEquals(false, StringCode.stringIntersect("aabbcc", "abcabc", 3));
		assertEquals(true, StringCode.stringIntersect("aabbcc", "abcabc", 2));
		assertEquals(true, StringCode.stringIntersect("!@#$!@# $%", "abc@# abc", 3));
	}

}
