package com.cg.onlineadvapi.exception;

public class UserPwdNotFoundExceptionResponse {
		private String password;
		

		public UserPwdNotFoundExceptionResponse(String password) {
			super();
			this.password = password;
		}

		public String getLoginName() {
			return password;
		}

		public void setLoginName(String password) {
			this.password = password;
		}
		
	}


