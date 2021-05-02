package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.CategoryException;
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
		return userRepository.findAll();
	}		
//		User user = new User;
//		List<User> viewUserList = userRepository.findAll();
//		for (User user : viewUserList) {
//			String existingUser = category2.getCategoryName();
//			if(existingUser.equals(newCatergory)) {
//				throw new userException("User does  exist");
//			}
//		}			
//		return categoryRepository.save(category);
//	}

	@Override
	public User viewUser(int userId) {
		logger.info("For finding USERS by ID");
		// Returns user by UserId or null if not found
		return userRepository.findByUserId(userId);
	}

}
