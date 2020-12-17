package com.iq3.plundr.service;

import com.iq3.plundr.model.Transaction;
import com.iq3.plundr.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Optional<Transaction> findByTransactionId(Long id) {
		return transactionRepository.findById(id);
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}
}
