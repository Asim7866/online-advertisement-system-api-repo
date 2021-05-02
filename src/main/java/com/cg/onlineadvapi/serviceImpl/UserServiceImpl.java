package com.cg.onlineadvapi.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.exception.AdvertiseIdException;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveOrUpdateUser(User user) {
		if(user == null) {
			throw new NullPointerException("User Object cannot be null");
		}
		user.setRole(2);
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Integer user_id) {
		 Optional<User> user = userRepository.findById(user_id);
		 if(user.isPresent()) {
			 userRepository.deleteById(user_id);
		 }else {
			 throw new AdvertiseIdException(user_id+" not found");
		 }

	}

	@Override
	public List<User> viewAllUser() {
		return userRepository.findAll();
	}
}
