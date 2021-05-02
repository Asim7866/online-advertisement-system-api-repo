package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	


	@Override
	public List<User> viewUserList() {
		// Method to return list of all users, null if no users found.
		return userRepository.findAll();
	}

	@Override
	public User viewUser(int userId) {
		// Returns user by UserId or null if not found
		return userRepository.findByUserId(userId);
	}

	
}
