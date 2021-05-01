package com.cg.onlineadvapi.service;
/**
 * This service is responsible for encrypting password.
 * @author Sarvesh Barve
 *
 */
public interface EncryptPwdService {
	/**
	 * This getMd5 method converts input to the md5 hash input
	 * @param input
	 * @return hashed input
	 */
	public  String getMd5(String input);
}
