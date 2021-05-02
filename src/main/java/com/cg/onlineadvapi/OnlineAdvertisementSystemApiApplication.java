package com.cg.onlineadvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Class is Startup the Spring ApplicationContext.
 * @author mohdansa
 */
@SpringBootApplication
public class OnlineAdvertisementSystemApiApplication {

	/**
	 * Main Method is where Compiler starts Program Execution.
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * It bootstraps a Spring Application as a Stand-Alone Application.
		 * It creates an appropriate ApplicationContext instance and load beans.
		 * It also runs embedded Tomcat server in Spring Web Application.
		 */
		SpringApplication.run(OnlineAdvertisementSystemApiApplication.class, args);
		
	}
}
 