package com.cg.onlineadvapi.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.service.UserService;
import com.cg.onlineadvapi.serviceImpl.MapValidationErrorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
public class UserController {
	 
	@Autowired
	private UserService userService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@ApiOperation(value = "Create new User")
	@PostMapping("")
	public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {
		ResponseEntity <?> errorMessage =mapValidationErrorService.mapValidationError(result);
		if(errorMessage!=null) return errorMessage;
		
		userService.saveOrUpdateUser(user);
		return new ResponseEntity<String>("User Created Successfully.", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "User Updated")
	@PatchMapping("")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result) {
		ResponseEntity <?> errorMessage =mapValidationErrorService.mapValidationError(result);
		if(errorMessage!=null) return errorMessage;
		
		userService.saveOrUpdateUser(user);
		return new ResponseEntity<String>("User Updated Successfully.", HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Delete user by Id")
	@DeleteMapping("/delete User by Id/{user_id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer user_id){
		userService.deleteById(user_id);
		return new ResponseEntity<String>("Id "+user_id+" user Deleted.",HttpStatus.OK);
	}

	@ApiOperation(value = "just to check all user managed by admin")
	@GetMapping("/viewAllUser")
	public List<User> getAllUser(){
		return userService.viewAllUser();
	}
}
