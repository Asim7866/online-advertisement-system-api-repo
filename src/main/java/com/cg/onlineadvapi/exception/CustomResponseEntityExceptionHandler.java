package com.cg.onlineadvapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This Class is a ExceptionHandler Controller Adviser which handle
 * ResponseEntityException.
 * 
 * @author mohdansa
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleAdvertiseIdException(AdvertiseIdException ex, WebRequest request) {
		AdvertiseIdExceptionResponse advertiseIdExceptionResponse = new AdvertiseIdExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(advertiseIdExceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleNoAdvertiseException(NoAdvertiseException ex, WebRequest request) {
		NoAdvertiseExceptionResponse noAdvertiseExceptionResponse = new NoAdvertiseExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(noAdvertiseExceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * It Handle ResponseEntityResponse for SameSenderException Exception Class.
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleSameSenderException(SameSenderException ex, WebRequest request) {

		SameSenderExceptionResponse exceptionResponse = new SameSenderExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * It Handle ResponseEntityResponse for NoMessageException Exception Class.
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<Object>
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleNoMessageException(NoMessageException ex, WebRequest request) {

		NoMessageExceptionResponse exceptionResponse = new NoMessageExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> userAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
		UserAlreadyExistExceptionResponse message = new UserAlreadyExistExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If User password and comfirm password will not be same then exception
	 *         will be thrown
	 */

	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<Object> passwordMismatchException(PasswordMismatchException ex, WebRequest request) {
		PasswordMismatchExceptionResponse passwordMismatchExceptionResponse = new PasswordMismatchExceptionResponse(
				ex.getMessage());
		return new ResponseEntity<Object>(passwordMismatchExceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If any unhandled exception is thrown is going to handled by this
	 *         exception handler
	 */
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> forAllException(NumberFormatException ex, WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If any unhandled exception is thrown is going to handled by this
	 *         exception handler
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		UserNotFoundExceptionResponse exceptionResponse = new UserNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If any unhandled exception is thrown is going to handled by this
	 *         exception handler
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleFieldCannotBeBlankException(FieldCannotBeBlankException ex,
			WebRequest request) {
		FieldCannotBeBlankExceptionResponse exceptionResponse = new FieldCannotBeBlankExceptionResponse(
				ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return If any unhandled exception is thrown is going to handled by this
	 *         exception handler
	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleNullUserException(NullUserException ex, WebRequest request) {
		NullUserExceptionResponse exceptionResponse = new NullUserExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleCategoryException(CategoryException ex, WebRequest request) {
		CategoryExceptionResponse exceptionResponse = new CategoryExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public final ResponseEntity<Object> handleNoUser(NoUserException ex, WebRequest request) {
		NoUserExceptionResponse exceptionResponse = new NoUserExceptionResponse(ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
