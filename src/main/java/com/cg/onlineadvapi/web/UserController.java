package com.cg.onlineadvapi.web;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.service.UserService;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;

@RestController

@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/viewUserList")
	public ResponseEntity<?> viewUserList() {
		logger.info("For finding all USERS list");
		List<User> user = userService.viewUserList();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	@GetMapping("/viewUser/{userId}")
	public ResponseEntity<?> viewUser(@PathVariable Integer userId) {
		logger.info("For finding USERS by ID");

		User user = userService.viewUser(userId);

//		if (Objects.isNull(user.getUserId())) // check if null object returned because of invalid user id
//			return ((BodyBuilder) ResponseEntity.notFound()).body("UserId not found"); // returns "UserId not found"
//																						
//		return ResponseEntity.accepted().body(user); // returns the user details if user id is found..
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
