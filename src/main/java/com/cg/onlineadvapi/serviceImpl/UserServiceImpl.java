package com.cg.onlineadvapi.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.NoUserException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> viewUserList() {
		logger.info("For finding all USERS");
		// Method to return list of all users, null if no users found.
		List<User> userList = userRepository.findAll();
		if(userList.isEmpty()) {
			throw new NoUserException("No User Found");
		}
		return userList;
	}
	
	@Override
	public User viewUser(int userId) {
		logger.info("For finding USERS by ID");
		// Returns user by UserId or null if not found
		User user = userRepository.findByUserId(userId);
		if((user= userRepository.findByUserId(userId))==null) {
			throw new NoUserException("User with userid "+userId+" doesn't Exist");
		}
		return user;
	}
}
