package edu.stanford.cs108;

public class Transaction {
	private int fromId;
	private int toId;
	private int amount;
	
	/**
	 * Constructor
	 * @param from, the id of the account from which the money is being transferred
	 * @param to,  the id of the account to which the money is going
	 * @param money, the amount of money
	 */
	public Transaction(int from, int to, int money) {
		this.fromId = from;
		this.toId = to;
		this.amount = money;
	}
	
	public int getFrom() {
		return fromId;
	}
	
	public int getTo() {
		return toId;
	}
	
	public int getAmount() {
		return amount;
	}
}
