package com.iq3.plundr.controller;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.Transaction;
import com.iq3.plundr.model.User;
import com.iq3.plundr.service.AccountService;
import com.iq3.plundr.service.TransactionService;
import com.iq3.plundr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

	private final TransactionService transactionService;

	private final AccountService accountService;

	private final UserService userService;

	@Autowired
	public TransactionController(TransactionService transactionService, AccountService accountService, UserService userService) {
		this.transactionService = transactionService;
		this.accountService = accountService;
		this.userService = userService;
	}
	// Principal requests
	@GetMapping("/transactions")
	List<Transaction> getAuthorizedTransactions(Principal principal) {
		User principalUser = userService.findByUsername(principal.getName());
		Account principalAccount = accountService.findByAccountNumber(principalUser.getAccount().getAccountNumber());
		List<Transaction> principalTransactions = transactionService.findAllFromAccount(principalAccount);

		return principalTransactions;
	}

	// Admin requests
	@GetMapping("/admin/transactions")
	List<Transaction> all() {
		return transactionService.findAll();
	}

	@PostMapping("/admin/transactions")
	Transaction newTransaction (@RequestBody Transaction newTransaction) {
		return transactionService.saveTransaction(newTransaction);
	}

	@GetMapping("/admin/transactions/{id}")
	Transaction getTransaction(@PathVariable Long id) {
		return transactionService.findByTransactionId(id)
				.orElseThrow();
	}

	@PutMapping("/admin/transactions/{id}")
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

	@DeleteMapping("/admin/transactions/{id}")
	void deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
	}


}
