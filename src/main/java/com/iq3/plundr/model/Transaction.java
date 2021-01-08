package com.iq3.plundr.model;

import com.iq3.plundr.enums.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Transactions")
public class Transaction {

	@Id
	@GeneratedValue
	private long id;

	private BigDecimal amount;
	private LocalDate date;
	private String description;
	private Type type;

	// The account this transaction is from
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account accountOwner;

	Transaction() {}

	// Constructor
	public Transaction(BigDecimal amount, LocalDate date, String description, Type type, Account accountOwner) {
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.type = type;
		this.accountOwner = accountOwner;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getAccountId() {
		return accountOwner.getId();
	}
}
