package com.iq3.plundr.service;

import com.iq3.plundr.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	User findByUsername(String username);

	User findByEmail(String email);

	Optional<User> findByUserId(Long id);

	List<User> findAll();

	User createNewUser(User user);

	User saveUser(User user);

	void deleteUser(Long id);

	void updatePassword(User user, String newPassword);

	void updateEmail(User user, String newEmail);
}
