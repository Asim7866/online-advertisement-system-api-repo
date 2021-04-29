package com.cg.onlineadvapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public class UserPwdNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		/**
		 * Create UserNotFoundException object without error message
		 */
		public UserPwdNotFoundException() {
			super();
		}
		/**
		 * Create UserNotFoundException object with error message
		 */
		public UserPwdNotFoundException(String errMsg) {
			super(errMsg);
		}
	}


