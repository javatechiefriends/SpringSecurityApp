package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Long saveUserData(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user).getUserId();
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

}
