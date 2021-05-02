package com.cg.onlineadvapi.serviceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
	/////////shivam:
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




	@Override
	public Advertise saveORUpdate(Advertise advertise) {
		if(advertise == null) {
			throw new NullPointerException("Advertise Object cannot be null");
		}
		advertise.setStatus("NEW");
		return advertiseRepository.save(advertise);
	}
	
	@Override
	public Advertise openOrRejectOrClosedAdvertise(Advertise advertise) {
		Optional<Advertise> advertise1 = advertiseRepository.findById(advertise.getAdvertiseId());
		Advertise ad = advertise1.get();
		ad.setStatus(advertise.getStatus());
		return advertiseRepository.save(ad);
	}
	
	@Override
	public void deleteAdvertiseById(Integer advertiseId) {
		Optional<Advertise> ad = advertiseRepository.findById(advertiseId);
		if(ad.isPresent())
			advertiseRepository.deleteById(advertiseId);
		else
			throw new AdvertiseIdException(advertiseId+" not available");
	}
	
	@Override
	public List<Advertise> findAllAdvertise() {
		List<Advertise> advertiseList = advertiseRepository.findAll();
		boolean result = advertiseList.isEmpty();
		if(result == true) {
			throw new NoAdvertiseException("No Advertise available");
		}else {
			return advertiseList;
		}
	}
	
	@Override
	public List<Advertise> findAllOPENAdvertise(String advertiseTitle) {
		List<Advertise> advertiseList = advertiseRepository.findAllOPENAdvertise(advertiseTitle);
		boolean result = advertiseList.isEmpty();
		if(result == true) {
			throw new NoAdvertiseException("Product you are looking for is not available");
		}else {
			return advertiseList;
		}
	}
	
	@Override
	public List<Advertise> getAllOpenStatusAdvertise() {
		List<Advertise> advertiseList = advertiseRepository.getAllOPENAdvertise();
		boolean result = advertiseList.isEmpty();
		if(result == true) {
			throw new NoAdvertiseException("No OPEN Advertise available");
		}else {
			return advertiseList;
		}
	}
	
	@Override
	public List<Advertise> getAllNewStatusAdvertise() {
		List<Advertise> advertiseList = advertiseRepository.getAllNEWAdvertise();
		boolean result = advertiseList.isEmpty();
		if(result == true) {
			throw new NoAdvertiseException("No NEW Advertise available");
		}else {
			return advertiseList;
		}
	}
	
	@Override
	public List<Advertise> getAllClosedStatusAdvertise() {
		List<Advertise> advertiseList = advertiseRepository.getAllCLOSEDAdvertise();
		boolean result = advertiseList.isEmpty();
		if(result == true) {
			throw new NoAdvertiseException("No CLOSED Advertise available");
		}else {
			return advertiseList;
		}
	}
	
	@Override
	public List<Advertise> getAllRejectedStatusAdvertise() {
		List<Advertise> advertiseList = advertiseRepository.getAllREJECTEDAdvertise();
		boolean result = advertiseList.isEmpty();
		if(result == true) {
			throw new NoAdvertiseException("No REJECTED Advertise available");
		}else {
			return advertiseList;
		}
	}

	@Override
	public Advertise findAdvertiseById(Integer advertiseId) {
		Optional<Advertise> ad = advertiseRepository.findById(advertiseId);
		if(ad.isPresent()){
			if(ad.get().getStatus().equals("OPEN"))
				return ad.get();
			}
		else
			throw new AdvertiseIdException(advertiseId+" not found");
		return null;
	}

	@Override
	public void deleteAdById(Integer advertiseId) {
		Optional<Advertise> ad = advertiseRepository.findById(advertiseId);
		if(ad.isPresent()){
			if(ad.get().getStatus().equals("OPEN"))
				advertiseRepository.deleteById(advertiseId);
			else
				throw new AdvertiseIdException(advertiseId+" not found");
			}
	}
	
}
