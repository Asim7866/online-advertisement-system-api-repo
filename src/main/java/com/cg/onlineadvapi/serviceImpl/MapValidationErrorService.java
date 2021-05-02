package com.cg.onlineadvapi.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	/**
	 * 
	 * @param result
	 * @return this function will return the name of fields and associated message and status code otherwise null
	 */
	
	public ResponseEntity<?> mapValidationError(BindingResult result){
		logger.info("Inside MapValidationErrorServiseClass");
		if(result.hasErrors()) {
			logger.info("Checking for errors");
			Map<String, String> errorDetails = new HashMap<>();
			for(FieldError fieldError:result.getFieldErrors()) {
				logger.info("Storing the fieldName and the message into the map");
				errorDetails.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			logger.info("Returning the errorDetails map with status code");
			return new ResponseEntity<Map<String,String>>(errorDetails, HttpStatus.BAD_REQUEST); 
		}return null;
	}

}

