package com.cg.onlineadvapi.web;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * This controller class is the class which will act first for any request
 * In this case the task of the controller is to receive the request to save the user and delegate the request to service class
 * This controller class is also responsible for handling all the field related errors 
 * @author rupesh singh
 *
 */

/**
 * In this case the task of the controller is to receive the request to log in the user and delegate the request to service class
 * @author Abhishek Silelan
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	 
	@Autowired
	private UserService userService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
  Logger logger = LoggerFactory.getLogger(UserController.class);
  
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



	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult result) {
		logger.info("Inside Controller class");
		logger.info("Request for adding and returning user has been received");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) {
			logger.error("Fields consisted of error and error has been sent for same");
			return errorMap;
		}
		logger.info("Saved user has been returned");
		User savedUser = userService.saveUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user, BindingResult result , HttpSession session){
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) {
			return errorMap;
		}
		User loggedInUser= userService.authenticateUser(user.getLoginName(), user.getPassword(), session);
		if(loggedInUser.getRole().equals(1)) {
			return new ResponseEntity<String>("Admin "+loggedInUser.getName()+" has Succesfully LoggedIn" , HttpStatus.OK);
		}
		return new ResponseEntity<String>("User "+loggedInUser.getName()+" has Succesfully LoggedIn" , HttpStatus.OK);
	}
	@PostMapping("/logout")
	public ResponseEntity<?> logUserOut(HttpSession session) {	
		session.invalidate();
		return new ResponseEntity<String>("Logged Out Successfully!!",HttpStatus.OK);
	}
}

