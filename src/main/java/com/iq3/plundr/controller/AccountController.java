package com.iq3.plundr.controller;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountRepository repository;

	public AccountController(AccountRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	List<Account> all() {
		return repository.findAll();
	}

	@PostMapping
	Account newAccount(@RequestBody Account newAccount) {
		return repository.save(newAccount);
	}

	@GetMapping("/{id}")
	Account getAccount(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow();
	}

	@PutMapping("/{id}")
	Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {
		return repository.findById(id)
				.map(account -> {
					account.setAccountNumber(account.getAccountNumber());
					account.setBalance(account.getBalance());
					return repository.save(account);
				})
				.orElseGet(() -> {
					newAccount.setAccountId(id);
					return repository.save(newAccount);
				});
	}

	@DeleteMapping("/{id}")
	void deleteAccount(@PathVariable Long id) {
		repository.deleteById(id);
	}


}
