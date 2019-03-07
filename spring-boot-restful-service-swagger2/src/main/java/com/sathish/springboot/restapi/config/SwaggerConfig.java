package com.sathish.springboot.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * 
 * @author Sathish
 *
 */
@Configuration
@EnableSwagger2
/*
 * //this is the minimum configuration to use swagger2 public class
 * SwaggerConfig {
 * 
 * @Bean public Docket productApi() { return new
 * Docket(DocumentationType.SWAGGER_2).select()
 * .apis(RequestHandlerSelectors.basePackage(
 * "com.sathish.springboot.restapi.controller")) .paths(regex("/rest.*"))
 * .build(); } }
 */
public class SwaggerConfig {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sathish.springboot.restapi.controller"))
				.paths(regex("/rest.*")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for ABC company", "1.0",
				"Terms of service",
				new Contact("Sathish Kariyanna", "http://sathishkariyanna.blogspot.com/", "abc@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}
