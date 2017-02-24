package edu.stanford.cs108;

import java.io.*;
import java.util.concurrent.*;

public class Bank {
	private static Account[] accounts;
	private final static int accountNum = 20;
	private static ArrayBlockingQueue<Transaction> queue;
	private final static int queueSize = 10;
	private static Worker[] workers;
	private static int workerNum;
	private static CountDownLatch latch;
	private final static int initBalance = 1000;
	private final static int initTrans = 0;

	public static void main(String[] args) {
		// args[0] is the file name
		// args[1] is the worker number
//		System.out.println("file name: " + args[0]);
//		System.out.println("thread num: " + args[1]);

		// create accounts
		accounts = new Account[accountNum];
		for(int i = 0; i < accountNum; i++) {
			accounts[i] = new Account(i, initBalance, initTrans);
		}

		// create blockingQueue
		queue = new ArrayBlockingQueue<Transaction>(queueSize);

		// create worker threads
		workerNum = Integer.parseInt(args[1]);
		workers = createWorkers(workerNum);

		// set countDownLatch
		latch = new CountDownLatch(workerNum);

		// start worker threads
		for(int i = 0; i < workerNum; i++)  {
			workers[i].start();
		}

		// read file and encapsulate into transaction
		readFile(args[0]);

		// trigger countDownLatch and print
		try {
			latch.await();
			printAccounts();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}     
	}
	
	/**
	 * print the final status of all accounts after transactions
	 */
	private static void printAccounts() {
		for(int i = 0; i < accountNum; i++) {
			System.out.println(accounts[i]);
		}
	}
	
	/**
	 * create worker threads
	 * @param workerNum, the threads to be created
	 * @return worker threads
	 */
	private static Worker[] createWorkers(int workerNum) {
		workers = new Worker[workerNum];
		for(int i = 0; i < workerNum; i++) {
			workers[i] = new Worker();
		}
		return workers;
	}

	/**
	 * read transactions per line from the file
	 * @param path, file path
	 */
	private static void readFile(String path) {
		try {
			// read file content from file
			StringBuffer sb= new StringBuffer("");

			FileReader reader = new FileReader(path);
			BufferedReader br = new BufferedReader(reader);

			String str = null;
			while((str = br.readLine()) != null) {
				sb.append(str+"/n");
				String[] words = str.split(" ");
				int from = Integer.parseInt(words[0]);
				int to = Integer.parseInt(words[1]);
				int amount = Integer.parseInt(words[2]);
				Transaction trans = new Transaction(from, to, amount);
				try {
					queue.put(trans);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			br.close();
			reader.close();
			
			// add nullTrans
			Transaction nullTrans = new Transaction(-1, 0, 0);
			for(int i = 0; i < workerNum; i++) {
				try {
					queue.put(nullTrans);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inner class of Bank
	 * used for create worker threads
	 */
	public static class Worker extends Thread {
		@Override
		public void run() {
			while(true) {
				Transaction trans;
				try {
					trans = queue.take();
					if(trans.getFrom() != -1) {
						Account from = accounts[trans.getFrom()];
						Account to = accounts[trans.getTo()];
						int money = trans.getAmount();
						from.transfer(to, money);
					} else {
						latch.countDown();
						break;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
