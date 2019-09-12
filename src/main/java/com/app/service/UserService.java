package com.app.service;

import java.util.List;

import com.app.model.User;

public interface UserService {

	public Long saveUserData(User user);
	public List<User> getAllUsers();
	public User getUserByEmail(String email);
}
