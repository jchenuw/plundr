package com.iq3.plundr.repository;

import com.iq3.plundr.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}
