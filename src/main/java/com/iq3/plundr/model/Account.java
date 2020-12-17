package com.iq3.plundr.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	private int accountNumber;
	private BigDecimal balance;

	// User owner of the account
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
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

	// Methods
	public void withdrawBalance(BigDecimal amount) {
		this.setBalance(this.getBalance().subtract(amount));
	}

	public void depositBalance(BigDecimal amount) {
		this.setBalance(this.getBalance().add(amount));
	}

	// Getters and Settlers
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUserId() {
		return userOwner.getId();
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}
}
