package com.iq3.plundr.controller;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.User;
import com.iq3.plundr.service.AccountService;
import com.iq3.plundr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

	private final AccountService accountService;
	private final UserService userService;

	@Autowired
	public AccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	// Principal requests
	@GetMapping("/account")
	Account getAuthorizedAccount(Principal principal) {
		User principalUser = userService.findByUsername(principal.getName());
		Account principalAccount = accountService.findByAccountNumber(principalUser.getAccount().getAccountNumber());

		return principalAccount;
	}

	@PostMapping("/account/withdraw")
	Account withdraw(Principal principal, @RequestParam(name="amount", required=true) String amount) {

		User principalUser = userService.findByUsername(principal.getName());
		Account principalAccount = accountService.findByAccountNumber(principalUser.getAccount().getAccountNumber());

		accountService.withdraw(principalAccount, new BigDecimal(amount));

		return principalAccount;
	}

	@PostMapping("/account/deposit")
	Account deposit(Principal principal, @RequestParam(name="amount", required=true) String amount) {
		User principalUser = userService.findByUsername(principal.getName());
		Account principalAccount = accountService.findByAccountNumber(principalUser.getAccount().getAccountNumber());

		accountService.deposit(principalAccount, new BigDecimal(amount));

		return principalAccount;
	}

	@PostMapping("/account/transfer")
	void transfer(Principal principal,
				  @RequestParam(name="recipient", required=true) String recipientUsername,
				  @RequestParam(name="amount", required=true) String amount) {
		User principalUser = userService.findByUsername(principal.getName());
		User recipientUser = userService.findByUsername(recipientUsername);

		Account principalAccount = accountService.findByAccountNumber(principalUser.getAccount().getAccountNumber());
		Account recipientAccount = accountService.findByAccountNumber(recipientUser.getAccount().getAccountNumber());

		accountService.transfer(principalAccount, recipientAccount, new BigDecimal(amount));
	}

	// Admin requests
	@GetMapping("/admin/accounts")
	List<Account> all() {
		return accountService.findAll();
	}

	@PostMapping("/admin/accounts")
	Account newAccount(@RequestBody Account newAccount) {
		return accountService.saveAccount(newAccount);
	}

	@GetMapping("/admin/accounts/{id}")
	Account getAccount(@PathVariable Long id) {
		return accountService.findByAccountId(id)
				.orElseThrow();
	}

	@PutMapping("/admin/accounts/{id}")
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

	@DeleteMapping("/admin/accounts/{id}")
	void deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
	}


}
