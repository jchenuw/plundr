package com.iq3.plundr.repository;

import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByAccountOwner(Account account);
}
