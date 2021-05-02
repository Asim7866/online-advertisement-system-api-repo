package com.cg.onlineadvapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public class FieldCannotBeBlankException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		/**
		 * Create UserNotFoundException object without error message
		 */
		public FieldCannotBeBlankException() {
			super();
		}
		/**
		 * Create UserNotFoundException object with error message
		 */
		public FieldCannotBeBlankException(String errMsg) {
			super(errMsg);
		}
	}


