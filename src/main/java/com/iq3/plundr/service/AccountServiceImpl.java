package com.iq3.plundr.service;

import com.iq3.plundr.enums.Type;
import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.Transaction;
import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.AccountRepository;
import com.iq3.plundr.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	// Methods
	@Override
	public Account findByAccountNumber(int accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public Optional<Account> findByAccountId(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account saveAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account createAccount() {
		return null;
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void withdraw(Account account, BigDecimal amount) {
		account.withdrawBalance(amount);

		accountRepository.save(account);
	}

	@Override
	public void deposit(Account account, BigDecimal amount) {
		account.depositBalance(amount);

		accountRepository.save(account);
	}

	@Override
	public void transfer(Account senderAccount, Account recipientAccount, BigDecimal amount) {
		// Withdraw amount from sender and deposit the amount to recipient
		senderAccount.withdrawBalance(amount);
		recipientAccount.depositBalance(amount);

		// Save and create new transactions for both the accounts
		transactionRepository.save(new Transaction(amount, LocalDate.now(),
				"e-Transfer withdraw", Type.WITHDRAW, senderAccount));
		transactionRepository.save(new Transaction(amount, LocalDate.now(),
				"e-Transfer deposit", Type.DEPOSIT, recipientAccount));

		// Save accounts
		accountRepository.save(senderAccount);
		accountRepository.save(recipientAccount);
	}
}
