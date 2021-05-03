package com.cg.onlineadvapi.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.cg.onlineadvapi.domain.Advertise;
import com.cg.onlineadvapi.domain.Category;
import com.cg.onlineadvapi.domain.User;
import com.cg.onlineadvapi.repository.AdvertiseRepository;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.AdvertiseServiceImpl;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;
import com.cg.onlineadvapi.web.CategoryController;

@SpringBootTest
class UserServiceTest {
	
	// creating mock objects
	@Mock
	private UserRepository userRepository;

	// injecting mocks to class instance
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this); // initializing mock objects before every test case
	}
	//////////////////vishal stubs:
	private User user;
	private User user1;
	private User user2;
	private List<User> userList;
	
	//////////////vishal stubs:
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		userList = new ArrayList<>(); 
		
	}
	
	
	////////////shivam's test cases:
	@Test
	void test_deleteUser_GivenCorrectUserId_ShouldReturnDeletionErrorMessage() {
		// setting up mock object method's to throw exception, when called
		BDDMockito.doThrow(new IllegalArgumentException("")).when(userRepository).deleteById(0);

		String message = userServiceImpl.deleteUser(0);

		assertEquals("UserId not found", message);
	}
	////////////////shivam's test cases:
	@Test
	void test_deleteUser_GivenCorrectUserId_ShouldReturnDeletionSuccessfulMessage() {
		// setting up mock object's method to do-nothing, when called
		BDDMockito.doNothing().when(userRepository).deleteById(0);

		String message = userServiceImpl.deleteUser(0);

		assertEquals("User Deleted Successfully", message);
	}
	
	////////////vishal's test cases:
	@Test
	public void test_viewUserList_ShouldReturnListOfUser() {
		user2 = new User("shivam","adm");
		user1 = new User("vishal","admin");
		
		userList.add(user2);
		userList.add(user1);
		BDDMockito.given(userRepository.findAll()).willReturn(userList);
		
		List<User> fetchData = userServiceImpl.viewUserList();
		assertNotNull(userList);
		assertNotNull(fetchData);
		assertEquals(2,fetchData.size());
		assertEquals(user2, fetchData.get(0));
		assertEquals(user1, fetchData.get(1));
	}
	
	@Test
	public void test_viewUser_GivenUserId_ShouldReturnUser() {
	User user = new User("vishal","admin");

		BDDMockito.given(userRepository.findByUserId(0)).willReturn(user);
		User fetchedUser =userServiceImpl.viewUser(0);
		assertEquals(user,fetchedUser);
//		assertEquals(user.getPassword(), fetchedUser.getPassword());
	}

}
