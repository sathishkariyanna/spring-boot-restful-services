package com.sathish.springboot.restapi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sathish.springboot.restapi.dto.Employee;
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

	// post data by Path Variable
	// URL http://localhost:8080/rest/emp/createbypathvariable/2/sathish
	@RequestMapping(value = "/rest/emp/createbypathvariable/{id}/{name}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Employee createEmployeeByParam(@PathVariable("id") int empId,
			@PathVariable("name") String empName) {
		System.out.println("Start createEmployee by param.");
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(empName);
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}

	// post data by Query Parameter
	// URL http://localhost:8080/rest/emp/createbyqueryparam?id=3&name=Kariyanna
	@RequestMapping(value = "/rest/emp/createbyqueryparam", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Employee createEmployeeByQueryParam(@RequestParam("id") int empId,
			@RequestParam("name") String empName) {
		// in RequestParam we can specify required field
		// @RequestParam(value="param2", required=false) String param2)
		System.out.println("Start createEmployee by param.");
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(empName);
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
	// {"id":1,"name":"Sathish","createdDate":"2018-06-25T20:25:58.096+0000"}
	@RequestMapping(value = "/rest/emp/update", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody Employee updateEmployee(@RequestBody Employee emp) {
		System.out.println("Start updateEmployee.");
		// in real world we need to update the resource
		empData.put(emp.getId(), emp);
		return emp;
	}

	// URL http://localhost:8080//rest/emp/delete/1
	@RequestMapping(value = "/rest/emp/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int empId) {
		System.out.println("Start delete Employee.");
		Employee emp = empData.get(empId);
		if (empData.get(empId) == null) {
			System.out.println("Employee not found");
			return new ResponseEntity<>(emp, HttpStatus.NO_CONTENT);
		} else {
			System.out.println("Employee not found with Id: " + empData.get(empId));
			empData.remove(empId);
		}
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	// ResponseEntity to set the status
	// URL http://localhost:8080/rest/emp/createresponseentity/2/sathish
	@RequestMapping(value = "/rest/emp/createresponseentity/{id}/{name}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseEntity<Employee> createEmployee(@PathVariable("id") int empId,
			@PathVariable("name") String empName) {
		System.out.println("Start createEmployee by param and set the status.");
		Employee emp = new Employee();
		emp.setId(empId);
		emp.setName(empName);
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
		// you can use the below syntax, if the return type is string
		// ResponseEntity<String>
		// return ResponseEntity.status(HttpStatus.OK).body("some data");
	}

	// ResponseEntity with custom header
	// URL http://localhost:8080/rest/emp/customHeader
	@RequestMapping(value = "/rest/emp/customHeader", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Employee> customHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header1", "anything1");
		headers.add("Custom-Header2", "anything2");
		Employee emp = new Employee();
		emp = employeeService.getDefaultEmployeeService();
		empData.put(9999, emp);
		return new ResponseEntity<>(emp, headers, HttpStatus.OK);
	}

}
/*
 * @RequestHeader String dataId //is used to read header data Regarding the
 * actual question, another way to get HTTP headers is to insert the
 * HttpServletRequest into your method and then get the desired header from
 * there.
 * 
 * @RequestMapping(produces = "application/json", method = RequestMethod.GET,
 * value = "data")
 * 
 * @ResponseBody public ResponseEntity<Data> getData(HttpServletRequest
 * request, @RequestParam(value = "ID", defaultValue = "") String id) { String
 * userAgent = request.getHeader("user-agent"); }
 * 
 * ResponseEntity to Manipulate the HTTP Response ResponseEntity represents the
 * whole HTTP response: status code, headers, and body. Because of it, we can
 * use it to fully configure the HTTP response.
 */
