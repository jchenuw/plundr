package com.iq3.plundr.service;

import com.iq3.plundr.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

	List<Transaction> findAll();

	Optional<Transaction> findByTransactionId(Long id);

	Transaction saveTransaction(Transaction transaction);

	void deleteTransaction(Long id);
}
