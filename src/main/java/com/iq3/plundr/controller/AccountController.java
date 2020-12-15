package com.iq3.plundr.controller;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.User;
import com.iq3.plundr.service.AccountService;
import com.iq3.plundr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;
	private final UserService userService;

	@Autowired
	public AccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	@GetMapping
	List<Account> all() {
		return accountService.findAll();
	}

	@GetMapping("/authorizedAccount")
	Account getAuthorizedAccount(Principal principal) {
		User principalUser = userService.findByUsername(principal.getName());
		return accountService.findByAccountNumber(principalUser.getAccount().getAccountNumber());
	}

	@PostMapping
	Account newAccount(@RequestBody Account newAccount) {
		return accountService.saveAccount(newAccount);
	}

	@GetMapping("/{id}")
	Account getAccount(@PathVariable Long id) {
		return accountService.findByAccountId(id)
				.orElseThrow();
	}

	@PutMapping("/{id}")
	Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {
		return accountService.findByAccountId(id)
				.map(account -> {
					account.setAccountNumber(account.getAccountNumber());
					account.setBalance(account.getBalance());
					return accountService.saveAccount(account);
				})
				.orElseGet(() -> {
					newAccount.setId(id);
					return accountService.saveAccount(newAccount);
				});
	}

	@DeleteMapping("/{id}")
	void deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}


}
