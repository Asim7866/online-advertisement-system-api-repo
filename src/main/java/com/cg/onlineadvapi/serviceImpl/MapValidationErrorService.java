package com.cg.onlineadvapi.serviceImpl;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * This Class is responsible to get all Field Error.
 * 
 * @author mohdansa
 */
@Service
public class MapValidationErrorService {

	/**
	 * It binds all the field error inside a Map
	 * 
	 * @param bindingResult
	 * @return Map<String,String>
	 */
	public ResponseEntity<?> mapValidationError(BindingResult result) {
		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
