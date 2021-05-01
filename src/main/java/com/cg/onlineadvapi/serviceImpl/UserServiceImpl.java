package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.PasswordMismatchException;
import com.cg.onlineadvapi.exception.UserAlreadyExistException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;

/**
 * This class is the implementation of UserService interface 
 * This class is responsible for saving the user into the database using repository class and will handle the exception if any.
 * @author rupesh singh
 *
 */
@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User saveUser(User user){
	
		logger.info("Inside Service Implementation class");
		logger.info("Method to save user has been started");
		User userToBeSave = user;
		
		/**
		 * Logic to check if Password and Confirm Password both are same or not
		 */
		
		if(!userToBeSave.getpassword().equals(userToBeSave.getConfirmPassword())) {
			throw new PasswordMismatchException("(E)Password and Confirm Password should be matching");
		}
		
		
		/**
		 * Logic to check if user is specifying correct role or not
		 */
		if(userToBeSave.getRole()!= 1 && userToBeSave.getRole()!= 2) {
			throw new NumberFormatException("(E)Cannot enter value other than 1 and 2");
		}
		
		/**
		 * Logic to check user with the same login name already exist or not
		 */
		
		String newLoginName = userToBeSave.getLoginName();
		List<User> userList = userRepository.findAll();
		for(User user1:userList) {
			String existingUserName = user1.getLoginName();
			if(existingUserName.equals(newLoginName)) {
				logger.error("Exception occoured because "+ newLoginName +" already exist");
				throw new UserAlreadyExistException("(E)User Already Exist");
			}
		}
		
		/**
		 * This function will add the user into database
		 */
		
		logger.info("User has been registered");
		return userRepository.save(user);
	}

}

