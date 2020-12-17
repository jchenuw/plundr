package com.iq3.plundr.service;

import com.iq3.plundr.model.User;
import com.iq3.plundr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findByUserId(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User createNewUser(User user) {
		return null;
	}

	@Override
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
	}

	@Override
	public void updateEmail(User user, String newEmail) {
		user.setEmail(newEmail);
	}
}
