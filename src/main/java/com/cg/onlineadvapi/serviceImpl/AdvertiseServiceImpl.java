package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.Advertise;
import com.cg.onlineadvapi.repository.AdvertiseRepository;
import com.cg.onlineadvapi.service.AdvertiseService;
@Service
public class AdvertiseServiceImpl implements AdvertiseService {
	@Autowired
	private AdvertiseRepository advertiseRepository;
	@Override
	public ResponseEntity<Object> viewAdvertisementByUser(int userId) {
		// Fetching Advertisement uploaded by specific user using UserId
		
		List<Advertise> fetchedData=advertiseRepository.viewAdvertisementByUser(userId);
		if(fetchedData.isEmpty())   //if an empty list is returned, error message is returned
			return ResponseEntity.accepted().body("No advertisement found for inputed user ID"); 
		//else, fetchedData is returned
		return ResponseEntity.accepted().body(fetchedData); 

	}

	@Override
	public String deleteAdvertise(int advertisementId) {
		// delete Advertisement using AdvertisementId
		try{advertiseRepository.deleteById(advertisementId);}
		catch(Exception e) {
			return("AdvertisementId not found");} 
		
		return "Advertisement deleted Successfully";
	}

}
