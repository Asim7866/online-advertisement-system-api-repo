package com.cg.onlineadvapi.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.service.UserService;
import com.cg.onlineadvapi.serviceImpl.MapValidationErrorService;

@RestController
@RequestMapping("api/")
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody User user, BindingResult result , HttpSession session){
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) {
			return errorMap;
		}
		User loggedInUser= userService.authenticateUser(user.getLoginName(), user.getPassword(), session);
//		if(loggedInUser==null) {
//			return new ResponseEntity<String>("Login Failed! Enter correct Credential.",HttpStatus.BAD_REQUEST);
//		}
		return new ResponseEntity<String>(loggedInUser.getname()+" has Succesfully LoggedIn" , HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logUserOut(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<String>("Logged Out Successfully!!",HttpStatus.OK);
	}
	
}


