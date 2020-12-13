package com.iq3.plundr.service;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findByAccountNumber(int accountNumber) {
		return null;
	}

	@Override
	public Account findByUser(User user) {
		return null;
	}

	@Override
	public Account createAccount() {
		return null;
	}

	@Override
	public void withdraw(Account account, BigDecimal amount) {

	}

	@Override
	public void deposit(Account account, BigDecimal amount) {

	}
}
