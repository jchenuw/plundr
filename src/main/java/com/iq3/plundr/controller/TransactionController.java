package com.iq3.plundr.controller;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.Transaction;
import com.iq3.plundr.repository.AccountRepository;
import com.iq3.plundr.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionRepository repository;

	public TransactionController(TransactionRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	List<Transaction> all() {
		return repository.findAll();
	}

	@PostMapping
	Transaction newTransaction (@RequestBody Transaction newTransaction) {
		return repository.save(newTransaction);
	}

	@GetMapping("/{id}")
	Transaction getTransaction(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow();
	}

	@PutMapping("/{id}")
	Transaction replaceTransaction(@RequestBody Transaction newTransaction, @PathVariable Long id) {
		return repository.findById(id)
				.map(transaction -> {
					transaction.setAmount(transaction.getAmount());
					transaction.setDate(transaction.getDate());
					transaction.setDescription(transaction.getDescription());
					transaction.setType(transaction.getType());
					return repository.save(transaction);
				})
				.orElseGet(() -> {
					newTransaction.setId(id);
					return repository.save(newTransaction);
				});
	}

	@DeleteMapping("/{id}")
	void deleteTransaction(@PathVariable Long id) {
		repository.deleteById(id);
	}


}
