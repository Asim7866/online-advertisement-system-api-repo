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
class AdvertiseServicesTest {
	// creating mock objects
	@Mock
	private AdvertiseRepository advertiseRepository;
	

	// injecting mocks to class instance
	@InjectMocks
	private AdvertiseServiceImpl advertiseServiceImpl;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this); // initializing mock objects before every test case
	}

	// Stubs Required
	List<Advertise> advertiseList1;
	List<Advertise> advertiseList2;

	@Test
	void test_viewAdvertisementByUser_GivenCorrectUserId_ShouldReturnAdvertisementList() {
		// initialization of required objects

		advertiseList1 = new ArrayList<>();
		advertiseList2 = new ArrayList<>();
		advertiseList1.add(new Advertise(1, "1", "1", 1d, "1", "1"));

		// setting up mock object's method to return list of advertisement, when called
		BDDMockito.given(advertiseRepository.viewAdvertisementByUser(0)).willReturn(advertiseList1);

		advertiseList2 = (List<Advertise>) advertiseServiceImpl.viewAdvertisementByUser(0).getBody();

		assertEquals(advertiseList1, advertiseList2);
	}

	@Test
	void test_viewAdvertisementByUser_GivenIncorrectUserId_ShouldReturnErrorMessage() {
		// initialization of required objects

		advertiseList1 = new ArrayList<>();
		advertiseList2 = new ArrayList<>();

		// setting up mock object's method to return empty list, when called
		BDDMockito.given(advertiseRepository.viewAdvertisementByUser(0)).willReturn(advertiseList1);

		String errorMessage = (String) advertiseServiceImpl.viewAdvertisementByUser(0).getBody();

		assertEquals("No advertisement found for inputed user ID", errorMessage);
	}

	@Test
	void test_deleteAdvertise_GivenCorrectAdvertisementId_ShouldReturDeletionErrorMessage() {
		// setting up mock object's method to throw exception, when called
		BDDMockito.doThrow(new IllegalArgumentException("")).when(advertiseRepository).deleteById(0);

		String message = advertiseServiceImpl.deleteAdvertise(0);

		assertEquals("AdvertisementId not found", message);
	}

	@Test
	void test_deleteAdvertise_GivenCorrectAdvertisementId_ShouldReturnDeletionSuccessfulMessage() {
		// setting up mock object's method to do-nothing, when called

		BDDMockito.doNothing().when(advertiseRepository).deleteById(0);

		String message = advertiseServiceImpl.deleteAdvertise(0);

		assertEquals("Advertisement deleted Successfully", message);
	}

	

	

}
