package com.cg.onlineadvapi.configuration;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This Class is Used to Enable Swagger2 and Configure info of the API
 * Documentation.
 * 
 * @author mohdansa
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	/**
	 * Configure Swagger and return Docket Instance.
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cg.onlineadvapi.web"))
				.paths(PathSelectors.regex("/api.*")).build().apiInfo(metoInfo());
	}

	/**
	 * It Customize the Swagger Output.
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo metoInfo() {

		ApiInfo apiInfo = new ApiInfo("Online Advertisement System API",
				"Created By JEE Full Stack Batch - 1 Group - 1", "1.0", "Terms of Service",
				new Contact("JEE Full Stack", "https://www.capgemini.com/", "@capgemini.com"),
				"Capgemini Licence v.1.0", "https://www.capgemini.com/", new ArrayList<>());
		return apiInfo;
	}
}
