package com.sathish.springboot.restapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sathish.springboot.restapi.dto.Employee;
import com.sathish.springboot.restapi.exception.EmployeeNotFoundException;
import com.sathish.springboot.restapi.service.EmployeeService;

/**
 * Handles requests for the Employee service.
 */
/**
 * 
 * @author Sathish
 *
 */
@RestController
public class EmployeeController {

	// Map to store employees, ideally it should come from database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

	@Autowired
	private EmployeeService employeeService;

	// URL http://localhost:8080/rest/emp/default
	@RequestMapping(value = "/rest/emp/default", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Employee getDefaultEmployee() {
		Employee emp = new Employee();
		emp = employeeService.getDefaultEmployeeService();
		empData.put(9999, emp);
		return emp;
	}

	// URL http://localhost:8080/rest/emp/9999
	@RequestMapping(value = "/rest/emp/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		System.out.println("Start getEmployee. ID=" + empId);
		if (empData.get(empId) == null) {
			System.out.println("Employee not found");
			throw new EmployeeNotFoundException("employee id-" + empId);
		} else {
			System.out.println("Employee not found with Id: " + empData.get(empId));
		}
		return empData.get(empId);
	}

	// post data by request body
	// URL http://localhost:8080//rest/emp/create
	// request {"id":1,"name":"Sathish
	// Kariyanna","createdDate":"2018-06-24T20:25:58.096+0000"}
	@RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		System.out.println("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	// URL http://localhost:8080/rest/emps
	@RequestMapping(value = "/rest/emps", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Employee> getAllEmployees() {
		System.out.println("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for (Integer i : empIdKeys) {
			emps.add(empData.get(i));
		}
		return emps;
	}

	// URL http://localhost:8080//rest/emp/update
	// request
	// {"id":1,"name":"Sathish","createdDate":"2019-03-15T20:25:58.096+0000"}
	@RequestMapping(value = "/rest/emp/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Employee updateEmployee(@RequestBody Employee emp) {
		System.out.println("Start updateEmployee.");
		// in real world we need to update the resource
		empData.put(emp.getId(), emp);
		return emp;
	}

	// URL http://localhost:8080//rest/emp/delete/1
	@RequestMapping(value = "/rest/emp/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		System.out.println("Start delete Employee.");
		Employee emp = empData.get(empId);
		if (empData.get(empId) == null) {
			System.out.println("Employee not found");
			throw new EmployeeNotFoundException("employee id-" + empId);
		} else {
			System.out.println("Employee not found with Id: " + empData.get(empId));
			empData.remove(empId);
		}
		return emp;
	}

}
