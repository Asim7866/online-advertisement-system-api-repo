package com.cg.onlineadvapi.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.domain.Advertise;
import com.cg.onlineadvapi.repository.AdvertiseRepository;
import com.cg.onlineadvapi.service.AdvertiseService;
@Service
public class AdvertiseServiceImpl implements AdvertiseService {
	Logger logger = LoggerFactory.getLogger(AdvertiseServiceImpl.class);
	@Autowired
	private AdvertiseRepository advertiseRepository;
	@Override
	public ResponseEntity<Object> viewAdvertisementByUser(int userId) {
		// Fetching Advertisement uploaded by specific user using UserId
		logger.info("Fetching Advertisement of User with UserID="+userId);
		List<Advertise> fetchedData=advertiseRepository.viewAdvertisementByUser(userId);
		if(fetchedData.isEmpty())   //if an empty list is returned, error message is returned
			{logger.error("No Advertisement found of User with UserID="+userId);
			return ResponseEntity.accepted().body("No advertisement found for inputed user ID"); 
			}
		//else, fetchedData is returned
		logger.info("Found Advertisement of User with UserID="+userId);
		return ResponseEntity.accepted().body(fetchedData); 

	}

	@Override
	public String deleteAdvertise(int advertisementId) {
		// delete Advertisement using AdvertisementId
		logger.info("Deleting Advertisement with AdvertisementID="+advertisementId);
		try{advertiseRepository.deleteById(advertisementId);}
		//if advertisement not found :
		catch(Exception e) {
			logger.error("Advertisement not found with AdvertisementID="+advertisementId);
			return("AdvertisementId not found");} 
		//if advertisement found :
		logger.info("Advertisement Deleted with AdvertisementID="+advertisementId);
		return "Advertisement deleted Successfully";
	}

}
