package edu.stanford.cs108;

public class Account {
	protected int id;
	protected int balance;
	protected int transNum;
	private static Object lock = new Object();
	
	/**
	 * Constructor
	 * @param id, account id
	 * @param balance, balance in this account
	 * @param transNum, the number of transactions (deposits and withdrawals) which occurred on this account 
	 */
	public Account(int id, int balance, int transNum) {
		this.id = id;
		this.balance = balance;
		this.transNum = transNum;
	}
	
	/**
	 * transfer "amount" money from "this" to "that"
	 * @param that
	 * @param amount
	 */
	public void transfer(Account that, int amount) {
		synchronized(lock) {
			this.balance -= amount;
			that.balance += amount;
			this.transNum++;
			that.transNum++;
		}
	}
	
	/**
	 * toString() override
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("acct:");
		sb.append(id);
		sb.append(" bal:");
		sb.append(balance);
		sb.append(" trans:");
		sb.append(transNum);
		return sb.toString();
	}
}
