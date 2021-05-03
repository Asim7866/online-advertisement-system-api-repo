package com.cg.onlineadvapi.web;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.onlineadvapi.domain.Message;
import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.service.UserService;
import com.cg.onlineadvapi.serviceImpl.AdvertiseServiceImpl;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineadvapi.command.LoginCommand;
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
 * In this case the task of the controller is to receive the request to log in
 * the user and delegate the request to service class
 * 
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

  
	Logger logger = LoggerFactory.getLogger(AdvertiseServiceImpl.class);
	
	
	//////////shivam:
	@GetMapping("/deleteUser/{userId}")
	public String deleteUser(@PathVariable int userId)
	{	//returns "UserId not found" message for invalid userId, otherwise returns "User deleted successfully"
		return userService.deleteUser(userId);
	}
	
	/////////////////vishal :
	
	@GetMapping("/viewUserList")
	public ResponseEntity<?> viewUserList() {
		logger.info("For finding all USERS list");
		List<User> user = userService.viewUserList();
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	@GetMapping("/viewUser/{userId}")
	public ResponseEntity<Object> viewUser(@PathVariable int userId) {
		logger.info("For finding USERS by ID");

		User user = userService.viewUser(userId);

		if (Objects.isNull(user.getUserId())) // check if null object returned because of invalid user id
			return ((BodyBuilder) ResponseEntity.notFound()).body("UserId not found"); // returns "UserId not found"
																						
		return ResponseEntity.accepted().body(user); // returns the user details if user id is found..
	}

	
//	@ApiOperation(value = "Create new User")
//	@PostMapping("")
//	public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {
//		ResponseEntity<?> errorMessage = mapValidationErrorService.mapValidationError(result);
//		if (errorMessage != null)
//			return errorMessage;
//
//		userService.saveOrUpdateUser(user);
//		return new ResponseEntity<String>("User Created Successfully.", HttpStatus.CREATED);
//	}


	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult result) {
		logger.info("Inside Controller class");
		logger.info("Request for adding and returning user has been received");
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null) {
			logger.error("Fields consisted of error and error has been sent for same");
			return errorMap;
		}
		logger.info("Saved user has been returned");
		User savedUser = userService.saveUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	
	
		@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginCommand user, BindingResult result, HttpSession session) {
		if (session.getAttribute("user")==null) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		User loggedInUser = userService.authenticateUser(user.getLoginName(), user.getPassword(), session);
		if (loggedInUser.getRole().equals(UserRole.USER_ROLE_ADMIN)) {
			return new ResponseEntity<String>("Admin " + loggedInUser.getName() + " has Succesfully LoggedIn",
					HttpStatus.OK);
		}
		return new ResponseEntity<String>("User " + loggedInUser.getName() + " has Succesfully LoggedIn",
				HttpStatus.OK);
	}

		return new ResponseEntity<String>("You have already logged In ", HttpStatus.BAD_REQUEST);
	}
	
	
	@PostMapping("/logout")
	public ResponseEntity<?> logUserOut(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<String>("Logged Out Successfully!!", HttpStatus.OK);
	}
	
	@ApiOperation(value = "User Update")
	@PatchMapping("")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result , HttpSession session) {
		if (session.getAttribute("user")!=null) {
		ResponseEntity<?> errorMessage = mapValidationErrorService.mapValidationError(result);
		if (errorMessage != null)
			return errorMessage;

		userService.saveOrUpdateUser(user);
		return new ResponseEntity<String>("User Updated Successfully.", HttpStatus.CREATED);
	}
		return new ResponseEntity<String>("You have not Logged In || Please  Login First ", HttpStatus.BAD_REQUEST);
	}	
	

	
	@ApiOperation(value = "Delete user by Id")
	@DeleteMapping("/delete User by Id/{user_id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Integer user_id , HttpSession session) {
		if (session.getAttribute("user")!=null) {
		userService.deleteById(user_id);
		return new ResponseEntity<String>("Id " + user_id + " user Deleted.", HttpStatus.OK);
	}
		return new ResponseEntity<String>("You have not Logged In || Please  Login First ", HttpStatus.BAD_REQUEST);
	}	
	
	
	
	
}
