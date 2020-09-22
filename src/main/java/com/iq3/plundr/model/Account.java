package com.iq3.plundr.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

	private @Id @GeneratedValue Long accountId;

	private int accountNumber;
	private BigDecimal balance;

	@ManyToOne
	private User userOwner;

	// Accounts of the user
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Transaction> transactionList;

	Account() {}

	// Constructor
	public Account(Long accountId, int accountNumber, BigDecimal balance) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.userOwner = userOwner;
	}

	// Getters and Settlers
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
