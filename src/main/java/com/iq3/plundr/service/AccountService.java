package com.iq3.plundr.service;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

	Account findByAccountNumber(int accountNumber);

	Optional<Account> findByAccountId(Long id);

	List<Account> findAll();

	Account saveAccount(Account account);

	Account createAccount();

	void deleteAccount(Long id);

	void withdraw(Account account, BigDecimal amount);

	void deposit(Account account, BigDecimal amount);
}
