package com.iq3.plundr.controller;

import com.iq3.plundr.model.User;
import com.iq3.plundr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	List<User> all() {
		return userService.findAll();
	}

	@PostMapping
	User newUser(@RequestBody User newUser) {
		return userService.saveUser(newUser);
	}

	@GetMapping("/{id}")
	User getUser(@PathVariable Long id) {
		return userService.findByUserId(id)
				.orElseThrow();
	}

	@PutMapping("/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

		return userService.findByUserId(id)
				.map(user -> {
					user.setFirstName(newUser.getFirstName());
					user.setLastName(newUser.getLastName());
					user.setPassword(newUser.getPassword());
					return userService.saveUser(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return userService.saveUser(newUser);
				});
	}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
