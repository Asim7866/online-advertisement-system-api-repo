package com.cg.onlineadvapi.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.cg.onlineadvapi.repository.AdvertiseRepository;
import com.cg.onlineadvapi.repository.UserRepository;
import com.cg.onlineadvapi.serviceImpl.AdvertiseServiceImpl;
import com.cg.onlineadvapi.serviceImpl.UserServiceImpl;
import com.cg.onlineadvapi.web.CategoryController;

@SpringBootTest
class UserServicesTest {
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

	
	@Test
	void test_deleteUser_GivenCorrectUserId_ShouldReturnDeletionErrorMessage() {
		// setting up mock object method's to throw exception, when called
		BDDMockito.doThrow(new IllegalArgumentException("")).when(userRepository).deleteById(0);

		String message = userServiceImpl.deleteUser(0);

		assertEquals("UserId not found", message);
	}

	@Test
	void test_deleteUser_GivenCorrectUserId_ShouldReturnDeletionSuccessfulMessage() {
		// setting up mock object's method to do-nothing, when called
		BDDMockito.doNothing().when(userRepository).deleteById(0);

		String message = userServiceImpl.deleteUser(0);

		assertEquals("User Deleted Successfully", message);
	}

}
