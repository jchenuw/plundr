package com.iq3.plundr.model;

import org.springframework.data.annotation.TypeAlias;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	private @Id @GeneratedValue Long id;

	private String firstName;
	private String lastName;

	// Login information
	private String password;
	private String email;

	// Accounts of the user
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Account> accounts;

	User() {}

	public User(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
