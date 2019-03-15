package com.sathish.springboot.restapi.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.sathish.springboot.restapi.dto.Employee;

/**
 * 
 * @author Sathish
 *
 */
@Component
public class EmployeeService {
	public Employee getDefaultEmployeeService() {
		System.out.println("Start getDefaultEmployee Service");
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Default Employee Name");
		emp.setCreatedDate(new Date());
		return emp;
	}

}
