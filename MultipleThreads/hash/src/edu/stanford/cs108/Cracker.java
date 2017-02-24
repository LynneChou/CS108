package edu.stanford.cs108;

import java.security.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Cracker {
	private static List<String> synchList = Collections.synchronizedList(new ArrayList<String>());
	private static String password;
	private static byte[] sha;
	private static int maxLen;
	private static int threadNum;
	private static CountDownLatch latch;

	public static void main(String[] args) {
		if(args.length == 1) {				// generation mode
			password = args[0];
			System.out.println(hexToString(generate(password)));
		} else if(args.length == 3) {		// cracking mode
			sha = hexToArray(args[0]);
			maxLen = Integer.parseInt(args[1]);
			threadNum = Integer.parseInt(args[2]);
			latch = new CountDownLatch(threadNum);		// initialize count down latch
			
			// create and start works
			int segment = CHARS.length / threadNum;
			Worker[] workers = new Worker[threadNum];
			for(int i = 0; i < threadNum - 1; i++) {
				workers[i] = new Worker(i * segment, (i + 1) * segment);
			}
			workers[threadNum - 1] = new Worker((threadNum - 1) * segment, CHARS.length);
			for(int i = 0; i < threadNum; i++) {
				workers[i].start();
			}
			
			// print all possible keys
			try {
				latch.await();
				for(String each : synchList) {
					System.out.println(each);
				}
				System.out.println("all done");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		} else {			// deal with error args number
			System.out.println("Input # of Args Error");
		}
		
	}
	
	/**
	 * Worker class for multiple-threading
	 */
	public static class Worker extends Thread {
		private int start;
		private int end;
		
		public Worker(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		/**
		 * run() with Override
		 */
		@Override
		public void run() {
			String str = "";
			int itr = 1;
			cracking(str, itr);
			latch.countDown();
		}
		
		/**
		 * brute force cracking the password
		 * @param str, current string
		 * @param itr, current iteration #
		 */
		private void cracking(String str, int itr) {
			// base case:
			if(itr > maxLen) {
				return;
			}
			// recursive case:
			if(itr == 1) {
				for(int i = start; i < end; i++) {
					String newStr = str + CHARS[i];
					byte[] gen = generate(newStr);
					if(Arrays.equals(sha, gen)) {
						synchList.add(newStr);
					}
					cracking(newStr, itr + 1);
				}
			} else {
				for(int i = 0; i < CHARS.length; i++) {
					String newStr = str + CHARS[i];
					byte[] gen = generate(newStr);
					if(Arrays.equals(sha, gen)) {
						synchList.add(newStr);
					}
					cracking(newStr, itr + 1);
				}
			}
		}
	}
	
	
	/**
	 * generate byte[] from password string according to "SHA"
	 * @param password, the password input
	 * @return the byte[] generated
	 */
	public static byte[] generate(String password) {
		byte[] res = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes());
			res = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();	
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/*
	 Given a string of hex byte values such as "24a26f", creates
	 a byte[] array of those values, one byte value -128..127
	 for each 2 chars.
	 (provided code)
	*/
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}
	
	// possible test values:
	// a 86f7e437faa5a7fce15d1ddcb9eaeaea377667b8
	// fm adeb6f2a18fe33af368d91b09587b68e3abcb9a7
	// a! 34800e15707fae815d7c90d49de44aca97e2d759
	// xyz 66b27417d37e024c46526c2f6d358a754fc552f3

}
