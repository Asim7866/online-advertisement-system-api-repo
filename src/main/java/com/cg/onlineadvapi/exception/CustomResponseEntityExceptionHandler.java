package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends  ResponseEntityExceptionHandler{

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If User tries to register with existing login name then that exception is handled by this exception handler
	 */
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> userAlreadyExistException(UserAlreadyExistException ex, WebRequest request){
		UserAlreadyExistExceptionResponse message = new UserAlreadyExistExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<Object> passwordMismatchException(PasswordMismatchException ex, WebRequest request){
		PasswordMismatchExceptionResponse passwordMismatchExceptionResponse = new PasswordMismatchExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(passwordMismatchExceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If any unhandled exception is thrown is going to handled by this exception handler
	 */
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> forAllException(NumberFormatException ex, WebRequest request){
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
