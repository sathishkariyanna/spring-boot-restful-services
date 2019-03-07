package com.sathish.springboot.restapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sathish.springboot.restapi.dto.Person;

/**
 * 
 * @author Sathish
 *
 */
@RestController
public class PersonController {
	//swaggger URL http://localhost:8080/swagger-ui.html
	// URL http://localhost:8080/rest/person
	@RequestMapping(value = "/rest/person", method = RequestMethod.GET)
	public Person Person() {
		return new Person("Sathish Kariyanna");
	}

}
