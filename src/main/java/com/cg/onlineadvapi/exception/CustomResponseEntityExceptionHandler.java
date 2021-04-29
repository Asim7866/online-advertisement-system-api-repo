package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler{
	@ExceptionHandler
	public final ResponseEntity<Object> handleLoginNameNotFoundException(LoginNameNotFoundException ex, WebRequest request){		
		LoginNameNotFoundExceptionResponse exceptionResponse=new LoginNameNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserPwdNotFoundException(UserPwdNotFoundException ex, WebRequest request){		
		UserPwdNotFoundExceptionResponse exceptionResponse=new UserPwdNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleFieldCannotBeBlankException(FieldCannotBeBlankException ex, WebRequest request){		
		FieldCannotBeBlankExceptionResponse exceptionResponse=new FieldCannotBeBlankExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

}
