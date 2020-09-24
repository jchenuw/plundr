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

	// User owner of the account
	@ManyToOne
	private User userOwner;

	// Transactions of the account
	@OneToMany(mappedBy = "accountOwner", cascade = CascadeType.ALL)
	private List<Transaction> transactionList;

	Account() {}

	// Constructor
	public Account(int accountNumber, BigDecimal balance, User userOwner) {
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
