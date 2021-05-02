package com.cg.onlineadvapi.web;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
	
	
	@GetMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable int userId)
	{	//returns "UserId not found" message for invalid userId, otherwise returns "User deleted successfully"
		return userService.deleteUser(userId);
	}
}
