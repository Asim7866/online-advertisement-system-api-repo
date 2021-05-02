package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This Class is a ExceptionHandler Controller Adviser which handle ResponseEntityException.
 * @author mohdansa
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler{
	
	/**
	 * It Handle ResponseEntityResponse for SameSenderException Exception Class.
	 * @param ex
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleSameSenderException(SameSenderException ex, WebRequest request) {
		
		SameSenderExceptionResponse exceptionResponse =  new SameSenderExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * It Handle ResponseEntityResponse for NoMessageException Exception Class.
	 * @param ex
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleNoMessageException(NoMessageException ex, WebRequest request) {
		
		NoMessageExceptionResponse exceptionResponse =  new NoMessageExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	

}
