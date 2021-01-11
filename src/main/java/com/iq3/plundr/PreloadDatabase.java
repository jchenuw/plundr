package com.iq3.plundr;

import com.iq3.plundr.enums.Type;
import com.iq3.plundr.model.Account;
import com.iq3.plundr.model.Transaction;
import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.AccountRepository;
import com.iq3.plundr.repository.TransactionRepository;
import com.iq3.plundr.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class PreloadDatabase {

	private static final Logger log =
			LoggerFactory.getLogger(PreloadDatabase.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, AccountRepository accountRepository,
			TransactionRepository transactionRepository) {

		return args -> {

			String fryPass = "12345";
			String encodeFryPass = passwordEncoder.encode(fryPass);
			log.info(fryPass + " " + encodeFryPass + " " + passwordEncoder.matches(fryPass, encodeFryPass));

			// Test users
			User userFry = new User("Fry", "Phillip", "fryfryfry",
					encodeFryPass, "fry@gmail.com");
			User userLeela = new User("Leela", "Taranga", "Cyclops",
					passwordEncoder.encode("abcd"), "leela@gmail.com");

			// Test accounts
			Account accountFry = new Account(111, new BigDecimal(500.00), userFry);
			Account accountLeela = new Account(001, new BigDecimal(2020.20), userLeela);

			// Test transactions
			Transaction buyFries = new Transaction(new BigDecimal(12.00), LocalDate.of(3000,2,20),
					"Fry buys fries", Type.WITHDRAW, accountFry);
			Transaction buyEyePhone = new Transaction(new BigDecimal(500.00), LocalDate.of(3002,3,3),
					"Take my money", Type.WITHDRAW, accountFry);
			Transaction paycheck = new Transaction(new BigDecimal(200.00), LocalDate.of(3001,1,1),
					"Planet Express paycheck", Type.DEPOSIT, accountLeela);

			log.info("Preloading " + userRepository.save(userFry));
			log.info("Preloading " + userRepository.save(userLeela));

			// Add accounts
			log.info("Preloading " + accountRepository.save(accountFry));
			log.info("Preloading " + accountRepository.save(accountLeela));

			// Add transactions
			log.info("Preloading " + transactionRepository.save(buyFries));
			log.info("Preloading " + transactionRepository.save(buyEyePhone));
			log.info("Preloading " + transactionRepository.save(paycheck));
		};
	}

}
