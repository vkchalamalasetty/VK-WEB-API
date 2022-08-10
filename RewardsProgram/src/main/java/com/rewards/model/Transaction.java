package com.rewards.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Transaction {

	private String transactionRef;
	private String customerID;
	private int amount;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date transactionDate;

	public Transaction() {
		super();
	}

	public Transaction(String transactionRef, String customerID, int amount, Date transactionDate) {
		super();
		this.transactionRef = transactionRef;
		this.customerID = customerID;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", customerID=" + customerID + ", transactionDate=" + transactionDate
				+ ", transactionRef=" + transactionRef + "]";
	}

}
