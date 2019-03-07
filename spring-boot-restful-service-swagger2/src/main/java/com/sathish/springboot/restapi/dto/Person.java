package com.sathish.springboot.restapi.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Sathish
 *
 */
public class Person {

	@ApiModelProperty(notes = "The person Name") // to display on swagger2 UI
	private String name;

	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

}
