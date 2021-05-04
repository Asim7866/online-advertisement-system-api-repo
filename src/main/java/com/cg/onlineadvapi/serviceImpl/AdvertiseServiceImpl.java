package com.cg.onlineadvapi.serviceImpl;

import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.onlineadvapi.constant.AdvertiseConstants;
import com.cg.onlineadvapi.domain.Advertise;
import com.cg.onlineadvapi.repository.AdvertiseRepository;
import com.cg.onlineadvapi.service.AdvertiseService;
import java.util.Optional;
import com.cg.onlineadvapi.exception.AdvertiseIdException;
import com.cg.onlineadvapi.exception.NoAdvertiseException;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {
	Logger logger = LoggerFactory.getLogger(AdvertiseServiceImpl.class);
	@Autowired
	private AdvertiseRepository advertiseRepository;

	///////// shivam:
	@Override
	public ResponseEntity<Object> viewAdvertisementByUser(int userId) {
		// Fetching Advertisement uploaded by specific user using UserId
		logger.info("Fetching Advertisement of User with UserID=" + userId);
		List<Advertise> fetchedData = advertiseRepository.viewAdvertisementByUser(userId);
		if (fetchedData.isEmpty()) // if an empty list is returned, error message is returned
		{
			logger.error("No Advertisement found of User with UserID=" + userId);
			return ResponseEntity.accepted().body("No advertisement found for inputed user ID");
		}
		// else, fetchedData is returned
		logger.info("Found Advertisement of User with UserID=" + userId);
		return ResponseEntity.accepted().body(fetchedData);

	}

	@Override
	public String deleteAdvertise(int advertisementId) {
		// delete Advertisement using AdvertisementId
		logger.info("Deleting Advertisement with AdvertisementID=" + advertisementId);
		try {
			advertiseRepository.deleteById(advertisementId);
		}
		// if advertisement not found :
		catch (Exception e) {
			logger.error("Advertisement not found with AdvertisementID=" + advertisementId);
			return ("AdvertisementId not found");
		}
		// if advertisement found :
		logger.info("Advertisement Deleted with AdvertisementID=" + advertisementId);
		return "Advertisement deleted Successfully";
	}

	@Override
	public Advertise saveORUpdate(Advertise advertise) {
		logger.info("--Saving Advertise--");
		if (advertise == null) {
			logger.error("--Trying to fetch empty advertise table--");
			throw new NullPointerException("Advertise cannot be empty");
		}
		logger.info("--Default advertise status set--");
		advertise.setStatus(AdvertiseConstants.USER_STATUS_NEW);
		logger.info("--Advertise Saved Successfully--");
		logger.info("--" + advertise.toString() + "--");
		return advertiseRepository.save(advertise);
	}

	@Override
	public Advertise openOrRejectOrClosedAdvertise(Advertise advertise) {
		logger.info("--Entered into openOrRejectOrClosedAdvertise method--");
		logger.info("--Searching for advertise by Id--");
		Optional<Advertise> advertise1 = advertiseRepository.findById(advertise.getAdvertiseId());
		logger.info("--Checking if mentioned Id is present or not--");
		if (advertise1.isPresent()) {
			logger.info("--Id is present--");
			Advertise ad = advertise1.get();
			logger.info("--Changing advertise status--");
			ad.setStatus(advertise.getStatus());
			logger.info("--Status changed and saved successfully--");
			logger.info("--" + advertise.toString() + "--");
			return advertiseRepository.save(ad);
		} else {
			logger.error(advertise.getAdvertiseId() + " is not present");
			throw new AdvertiseIdException(advertise.getAdvertiseId() + " not found");
		}
	}

	@Override
	public void deleteAdvertiseById(Integer advertiseId) {
		logger.info("--Entered into deleteAdvertiseById method--");
		logger.info("--Searching for advertise by Id--");
		Optional<Advertise> ad = advertiseRepository.findById(advertiseId);
		logger.info("--Checking if mentioned Id is present or not--");
		if (ad.isPresent()) {
			logger.info("--Id id present and deleted successfully--");
			advertiseRepository.deleteById(advertiseId);
		} else {
			logger.error(advertiseId + " not available");
			throw new AdvertiseIdException(advertiseId + " not available");
		}
	}

	@Override
	public List<Advertise> findAllAdvertise() {
		logger.info("--Entered into findAllAdvertise method--");
		logger.info("--Check for list of advertise--");
		List<Advertise> advertiseList = advertiseRepository.findAll();
		logger.info("--Getting boolean value whether list is empty or not--");
		boolean result = advertiseList.isEmpty();
		if (result == true) {
			logger.error("--No Advertise available--");
			throw new NoAdvertiseException("No Advertise available");
		} else {
			logger.info("--List of All Advertise returned--");
			for (Advertise advertise : advertiseList) {
				logger.info("--" + advertise + "--");
			}
			return advertiseList;
		}
	}

	@Override
	public List<Advertise> findAllOPENAdvertise(String advertiseTitle) {
		logger.info("--Entered into findAllOPENAdvertise method--");
		logger.info("--Getting all advertise by text whose status is open--");
		List<Advertise> advertiseList = advertiseRepository.findAllOPENAdvertise(advertiseTitle);
		logger.info("--Getting boolean value whether list is empty or not--");
		boolean result = advertiseList.isEmpty();
		if (result == true) {
			logger.error("--Product you are looking for is not available--");
			throw new NoAdvertiseException("Product you are looking for is not available");
		} else {
			logger.info("--List of advertise searched by text whose status is open is returned");
			for (Advertise advertise : advertiseList) {
				logger.info("--" + advertise + "--");
			}
			return advertiseList;
		}
	}

	@Override
	public List<Advertise> getAllOpenStatusAdvertise() {
		logger.info("--Entered into getAllOpenStatusAdvertise method--");
		logger.info("--Getting all advertise whose status is open--");
		List<Advertise> advertiseList = advertiseRepository.getAllOPENAdvertise();
		logger.info("--Getting boolean value whether list is empty or not--");
		boolean result = advertiseList.isEmpty();
		if (result == true) {
			logger.error("--No open advertise available--");
			throw new NoAdvertiseException("No OPEN Advertise available");
		} else {
			logger.info("--List of advertise whose status is open is returned");
			for (Advertise advertise : advertiseList) {
				logger.info("--" + advertise + "--");
			}
			return advertiseList;
		}
	}

	@Override
	public List<Advertise> getAllNewStatusAdvertise() {
		logger.info("--Entered into getAllNewStatusAdvertise method--");
		logger.info("--Getting all advertise whose status is new--");
		List<Advertise> advertiseList = advertiseRepository.getAllNEWAdvertise();
		logger.info("--Getting boolean value whether list is empty or not--");
		boolean result = advertiseList.isEmpty();
		if (result == true) {
			logger.error("--No new advertise available--");
			throw new NoAdvertiseException("No NEW Advertise available");
		} else {
			logger.info("--List of advertise whose status is new is returned");
			for (Advertise advertise : advertiseList) {
				logger.info("--" + advertise + "--");
			}
			return advertiseList;
		}
	}

	@Override
	public List<Advertise> getAllClosedStatusAdvertise() {
		logger.info("--Entered into getAllClosedStatusAdvertise method--");
		logger.info("--Getting all advertise whose status is closed--");
		List<Advertise> advertiseList = advertiseRepository.getAllCLOSEDAdvertise();
		logger.info("--Getting boolean value whether list is empty or not--");
		boolean result = advertiseList.isEmpty();
		if (result == true) {
			logger.error("--No closed advertise available--");
			throw new NoAdvertiseException("No CLOSED Advertise available");
		} else {
			logger.info("--List of advertise whose status is closed is returned");
			for (Advertise advertise : advertiseList) {
				logger.info("--" + advertise + "--");
			}
			return advertiseList;
		}
	}

	@Override
	public List<Advertise> getAllRejectedStatusAdvertise() {
		logger.info("--Entered into getAllRejectedStatusAdvertise method--");
		logger.info("--Getting all advertise whose status is rejected--");
		List<Advertise> advertiseList = advertiseRepository.getAllREJECTEDAdvertise();
		logger.info("--Getting boolean value whether list is empty or not--");
		boolean result = advertiseList.isEmpty();
		if (result == true) {
			logger.error("--No rejected advertise available--");
			throw new NoAdvertiseException("No REJECTED Advertise available");
		} else {
			logger.info("--List of advertise whose status is rejected is returned");
			for (Advertise advertise : advertiseList) {
				logger.info("--" + advertise + "--");
			}
			return advertiseList;
		}
	}

	@Override
	public Advertise findAdvertiseById(Integer advertiseId) {
		logger.info("--Entered into findAdvertiseById method--");
		logger.info("--Checking whether advertise is present or not--");
		Optional<Advertise> ad = advertiseRepository.findById(advertiseId);
		logger.info("--Checking whether advertise is present--");
		if (ad.isPresent()) {
			logger.info("--Checking if present advertise status is open--");
			if (ad.get().getStatus() == AdvertiseConstants.USER_STATUS_OPEN) {
				logger.info("--Returning advertise whose status is open");
				logger.info("--" + ad.get() + "--");
				return ad.get();
			}
		} else {
			logger.error(advertiseId + " not found");
			throw new AdvertiseIdException(advertiseId + " not found");
		}
		return null;
	}

	@Override
	public void deleteAdById(Integer advertiseId) {
		logger.info("--Entered into deleteAdById method--");
		logger.info("--Checking whether advertise is present or not--");
		Optional<Advertise> ad = advertiseRepository.findById(advertiseId);
		logger.info("--Checking whether advertise is present--");
		if (ad.isPresent()) {
			logger.info("--Checking if present advertise status is open--");
			if (ad.get().getStatus() == AdvertiseConstants.USER_STATUS_OPEN) {
				logger.info(advertiseId + " deleted Successfully");
				advertiseRepository.deleteById(advertiseId);
			} else {
				logger.error(advertiseId + " not found");
				throw new AdvertiseIdException(advertiseId + " not found");
			}
		}
	}
}