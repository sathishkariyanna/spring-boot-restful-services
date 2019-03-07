package com.sathish.springboot.restapi.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

/**
 * 
 * @author Sathish
 *
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = -7788619177798333712L;

	private int id;
	private String name;
	private Date createdDate;

	public Employee() {

	}

	public Employee(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", createdDate=" + createdDate + "]";
	}
}
