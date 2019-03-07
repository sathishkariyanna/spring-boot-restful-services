package com.sathish.springboot.restapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sathish.springboot.restapi.dto.Name;
import com.sathish.springboot.restapi.dto.PersonV1;
import com.sathish.springboot.restapi.dto.PersonV2;

/**
 * 
 * @author Sathish
 *
 */
@RestController
public class PersonVersioningController {

	// URI Versioning
	// URL http://localhost:8080/rest/v1/person
	@RequestMapping(value = "/rest/v1/person", method = RequestMethod.GET)
	public PersonV1 PersonV1() {
		return new PersonV1("Sathish Kariyanna");
	}

	// URI Versioning
	// URL http://localhost:8080/rest/v2/person
	@RequestMapping(value = "/rest/v2/person", method = RequestMethod.GET)
	public PersonV2 PersonV2() {
		return new PersonV2(new Name("Sathish", "Kariyanna"));
	}

	// Request Parameter versioning
	// URL http://localhost:8080/rest/person/param?version=1
	@RequestMapping(value = "/rest/person/param", method = RequestMethod.GET, params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Sathish Kariyanna");
	}

	// Request Parameter versioning
	// URL http://localhost:8080/rest/person/param?version=2
	@RequestMapping(value = "/rest/person/param", method = RequestMethod.GET, params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Sathish", "Kariyanna"));
	}

	// Headers(Custom) versioning
	// URL http://localhost:8080/rest/person/header and add header [X-API-VERSION=1]
	@RequestMapping(value = "/rest/person/header", method = RequestMethod.GET, headers = "X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Sathish Kariyanna");
	}

	// Headers(Custom) versioning
	// URL http://localhost:8080/rest/person/header and add header [X-API-VERSION=2]
	@RequestMapping(value = "/rest/person/header", method = RequestMethod.GET, headers = "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Sathish", "Kariyanna"));
	}

	// Media type versioning (“content negotiation” or “accept header”)
	// URL http://localhost:8080/rest/person/produces and add
	// headers[Accept=application/vnd.company.app-v1+json]
	@RequestMapping(value = "/rest/person/produces", method = RequestMethod.GET, produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Sathish Kariyanna");
	}

	// Media type versioning (“content negotiation” or “accept header”)
	// URL http://localhost:8080/rest/person/produces and add
	// headers[Accept=application/vnd.company.app-v2+json]
	@RequestMapping(value = "/rest/person/produces", method = RequestMethod.GET, produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Sathish", "Kariyanna"));
	}

}
