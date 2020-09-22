package com.iq3.plundr.model;

import com.iq3.plundr.enums.Type;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {

	private @Id @GeneratedValue long transactionId;
	private BigDecimal amount;
	private Date date;
	private String description;
	private Type type;

	Transaction() {}

	// Constructor
	public Transaction(long transactionId, BigDecimal amount, Date date, String description, Type type) {
		this.transactionId = transactionId;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.type = type;
	}

	// Getters and Setters
	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
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
