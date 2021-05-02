package com.cg.onlineadvapi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public String deleteUser(int userId) {
		// delete User using UserId
		try{userRepository.deleteById(userId);}
		catch(Exception e) {
			return("UserId not found");} //if user id not found, error message is returned 
		return "User Deleted Successfully";	//if user id found, successful execution message is returned
	}

}
