package com.sathish.springboot.restapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathish.springboot.restapi.dto.Person;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @author Sathish
 *
 */
@RestController
@Api(value = "some company", description = "person description") // to display on swagger2 UI
public class PersonController {
	// swaggger URL http://localhost:8080/swagger-ui.html
	// URL http://localhost:8080/rest/person
	@ApiOperation(value = "View list of persons", response = PersonController.class) // to display on swagger2 UI
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved person"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/rest/person", method = RequestMethod.GET)
	public Person Person() {
		return new Person("Sathish Kariyanna");
	}

}
