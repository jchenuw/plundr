package com.iq3.plundr.controller;

import com.iq3.plundr.exception.UserNotFoundException;
import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	List<User> all() {
		return repository.findAll();
	}

	@PostMapping
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	@GetMapping("/{id}")
	User getUser(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow();
	}

	@PutMapping("/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

		return repository.findById(id)
				.map(user -> {
					user.setFirstName(newUser.getFirstName());
					user.setLastName(newUser.getLastName());
					user.setPassword(newUser.getPassword());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return repository.save(newUser);
				});
	}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
