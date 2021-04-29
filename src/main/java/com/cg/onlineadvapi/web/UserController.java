package com.cg.onlineadvapi.web;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.User;

import com.cg.onlineadvapi.service.UserService;
import com.cg.onlineadvapi.serviceImpl.MapValidationErrorService;



@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)return errorMap;
		
		User savedUser = userService.saveOrUpdate(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
//	@PostMapping("/login")
//	public User login(@PathVariable String loginName , String password  ){
//		//String loginName = user.getLoginName();
//		//String password =user.getPassword();
//		
//		User user1 = userService.findByloginNameAndPassword(loginName , password);
//		return user1;
//		
//	}
	
	///Log in Authenticate
	
		@PostMapping("/login")
		public ResponseEntity<?> handleUserlogin(@RequestBody User user, BindingResult result , HttpSession session){
		
			ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
			if(errorMap!=null)return errorMap;
			//User loginUser = userService.findByloginNameAndPassword(user.getLoginName(), user.getPassword());
			User loggedinUser = userService.authenticateUser(user.getLoginName(),user.getPassword(), session);
			//return new ResponseEntity<String>(loginUser.getUserName() , HttpStatus.OK);
			
			return new ResponseEntity<User>(loggedinUser , HttpStatus.OK);
			
		}
	
		
		@GetMapping("/logout")
		public ResponseEntity<?> handleUserLogout(HttpSession session) {
			session.invalidate();
			return new ResponseEntity<String>("Logout Successfully | Have a nice day", HttpStatus.OK);
		}
	
	
//	{
//	    "userName": "hellowld",
//	    "loginName": "hellowld",
//	    "password": "helloworld",
//	    "confirmPassword": "helloworld",
//	    "contactNo": "898956235",
//	    "email": "hello@gmail",
//	    "role": 2,
//	    "address": null
//	}
}
