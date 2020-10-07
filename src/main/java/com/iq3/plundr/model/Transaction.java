package com.iq3.plundr.model;

import com.iq3.plundr.enums.Type;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	private long id;
	
	private BigDecimal amount;
	private Date date;
	private String description;
	private Type type;

	// The account this transaction is from
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account accountOwner;

	Transaction() {}

	// Constructor
	public Transaction(BigDecimal amount, Date date, String description, Type type, Account accountOwner) {
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.type = type;
		this.accountOwner = accountOwner;
	}

	// Getters and Setters
	public long getTransactionId() {
		return id;
	}

	public void setTransactionId(long transactionId) {
		this.id = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
}
