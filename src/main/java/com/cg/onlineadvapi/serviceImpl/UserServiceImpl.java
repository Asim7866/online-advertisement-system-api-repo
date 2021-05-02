package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	//Dependency
	@Autowired
	private UserRepository userRepository;
	Logger logger = LoggerFactory.getLogger(AdvertiseServiceImpl.class);
	
	//////////shivam:
	@Override
	public String deleteUser(int userId) {
		// delete User using UserId
		logger.info("Deleting User with UserID="+userId);
		
		try{userRepository.deleteById(userId);}
		
		catch(Exception e) {
			logger.error("No user found with UserID="+userId);
			return("UserId not found");} //if user id not found, error message is returned 
		
		logger.info("User deleted with UserID="+userId);
		return "User Deleted Successfully";	//if user id found, successful execution message is returned
	}
	
	
	//////////////vishal:
	
	@Override
	public List<User> viewUserList() {
		logger.info("For finding all USERS");
		// Method to return list of all users, null if no users found.
		return userRepository.findAll();
	}
	
	@Override
	public User viewUser(int userId) {
		logger.info("For finding USERS by ID");
		// Returns user by UserId or null if not found
		return userRepository.findByUserId(userId);
	}

}
