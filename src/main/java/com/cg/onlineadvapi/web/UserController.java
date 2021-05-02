package com.cg.onlineadvapi.web;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.service.UserService;

@RestController

@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/viewUserList")
	public ResponseEntity<Object> viewUserList()
	{	List<User> userList=userService.viewUserList();
		if(userList.isEmpty())		//check if no user in found in list
			return ((BodyBuilder) ResponseEntity.notFound()).body("No user found"); //returns "No user found" message if empty list is returned
		return ResponseEntity.accepted().body(userList); //returns the user details if found..
			
	}
	@GetMapping("/viewUser/{userId}")
	public ResponseEntity<Object> viewUser(@PathVariable int userId)
	{	User user=userService.viewUser(userId);
	
		if(Objects.isNull(user.getUserId()))		//check if null object returned because of invalid user id
			return ((BodyBuilder) ResponseEntity.notFound()).body("UserId not found"); //returns "UserId not found" message if null object is returned
		return ResponseEntity.accepted().body(user); //returns the user details if user id is found..
	}

}
