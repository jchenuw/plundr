package com.iq3.plundr.service;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.User;

import java.math.BigDecimal;

public interface AccountService {

	Account findByAccountNumber(int accountNumber);

	Account findByUser(User user);

	Account createAccount();

	void withdraw(Account account, BigDecimal amount);

	void deposit(Account account, BigDecimal amount);
}
