package com.cg.onlineadvapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineadvapi.domain.Advertise;
import com.cg.onlineadvapi.service.AdvertiseService;

@RestController

@RequestMapping("/api")
public class AdvertiseController {
	@Autowired
	private AdvertiseService advertiseService;
	
	////////////shivam:
	@PostMapping("/viewAdvertisementByUser")
	public ResponseEntity<Object> viewAdvertisementByUser(int userId) throws Exception
	{	//ResponseEntity<Object> fetchedData=adminService.viewAdvertisementByUser(userId);
		
		return advertiseService.viewAdvertisementByUser(userId);
	}
	@PostMapping("/deleteAdvertise")
	public String deleteAdvertise(int advertisementId)
	{	//returns "AdvertisementId not found" message for invalid AdvertisementId, otherwise returns "Advertisement deleted successfully"
		return advertiseService.deleteAdvertise(advertisementId);
	}
}
