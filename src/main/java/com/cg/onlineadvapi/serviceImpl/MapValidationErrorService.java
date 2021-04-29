package com.cg.onlineadvapi.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class MapValidationErrorService {
	
	public ResponseEntity<?> mapValidationError(BindingResult result){
		if(result.hasErrors()) {
			Map<String, String> errorDetails = new HashMap<>();
			for(FieldError fieldError:result.getFieldErrors()) {
				errorDetails.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(errorDetails, HttpStatus.CREATED); 
		}return null;
	}

}