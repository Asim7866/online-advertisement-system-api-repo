package com.cg.onlineadvapi.service;

import org.springframework.http.ResponseEntity;

public interface AdvertiseService {
	/**
	 * This viewAdvertisementByUser method will return list of all Advertisement uploaded by specific user
	 * @param userId, whose advertisement list to be fetched
	 * @return List<Advertise> 
	 * @author shivam
	 */
	public ResponseEntity<Object> viewAdvertisementByUser(int userId);
	
	/**
	 * This deleteAdvertise method will delete the required advertisement from the database.
	 * @param AdvertisementID, whose data to be deleted
	 * @return Advertise, which is deleted
	 * @author shivam
	 */
	public String deleteAdvertise(int advertisementId);
}
