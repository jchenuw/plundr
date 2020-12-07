package com.iq3.plundr.service;

import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return null;
	}

	@Override
	public User createNewUser(User user) {
		return null;
	}

	@Override
	public void deleteExistingUser(User user) {

	}

	@Override
	public void updatePassword(String newPassword) {

	}

	@Override
	public void updateEmail(String newEmail) {

	}
}
