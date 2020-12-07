package com.iq3.plundr.service;

import com.iq3.plundr.model.User;

public interface UserService {

	User findByUsername(String username);

	User findByEmail(String email);

	User createNewUser(User user);

	void deleteExistingUser(User user);

	void updatePassword(String newPassword);

	void updateEmail(String newEmail);
}
