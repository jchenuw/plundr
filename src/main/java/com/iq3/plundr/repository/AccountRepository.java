package com.iq3.plundr.repository;

import com.iq3.plundr.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccountNumber(int accountNumber);

}