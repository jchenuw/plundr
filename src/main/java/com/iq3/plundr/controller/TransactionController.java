package com.iq3.plundr.controller;

import com.iq3.plundr.model.Transaction;
import com.iq3.plundr.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping
	List<Transaction> all() {
		return transactionService.findAll();
	}

	@PostMapping
	Transaction newTransaction (@RequestBody Transaction newTransaction) {
		return transactionService.saveTransaction(newTransaction);
	}

	@GetMapping("/{id}")
	Transaction getTransaction(@PathVariable Long id) {
		return transactionService.findByTransactionId(id)
				.orElseThrow();
	}

	@PutMapping("/{id}")
	Transaction replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {
		return transactionService.findByTransactionId(id)
				.map(transaction -> {
					transaction.setAmount(transaction.getAmount());
					transaction.setDate(transaction.getDate());
					transaction.setDescription(transaction.getDescription());
					transaction.setType(transaction.getType());
					return transactionService.saveTransaction(transaction);
				})
				.orElseGet(() -> {
					newTransaction.setId(id);
					return transactionService.saveTransaction(newTransaction);
				});
	}

	@DeleteMapping("/{id}")
	void deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
	}


}
