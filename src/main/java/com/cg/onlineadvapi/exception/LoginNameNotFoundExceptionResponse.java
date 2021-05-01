package com.cg.onlineadvapi.exception;

public class LoginNameNotFoundExceptionResponse {
		private String loginName;
		

		public LoginNameNotFoundExceptionResponse(String loginName) {
			super();
			this.loginName = loginName;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		
	}


